package model.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.jdbc.SQLError;

import db.DB;
import db.DbException;
import model.DAO.DepartmentDAO;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDAO {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO department (Name) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			
			int linhaIncluida = st.executeUpdate();
			
			//código para obter o Id criado no banco de dados e associalo ao objeto 
			//fornecido como parâmetro
			if(linhaIncluida> 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultset(rs);
			}
					
		}catch(SQLException e) {
			throw new DbException("Erro ao criar departamento!\n\\n"+e.getMessage());
		}finally {
			DB.closeStatement(st);
		
		}	
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		
		try {
			conn.setAutoCommit(false);
			
			st = conn.prepareStatement(
					"UPDATE department SET Name=? WHERE Id=?",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			
			int resultado = st.executeUpdate();
			if(resultado == 0) {
				throw new SQLException("Erro ao atualizar Departamento");
			}
			conn.commit();
		}catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Erro : " + e.getMessage());
			}catch(SQLException e2 ) {
				throw new DbException("Erro ao voltar a transação no B. Dados!");
			}
		}finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT department.* FROM department WHERE Id= ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			if(rs.next()) {
				Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
				return dep;
			}
			else {
				throw new SQLException("Departamento não encontrado!");
			}
		}catch(SQLException e) {
			throw new DbException("Erro: "+e.getMessage());
		}
		finally {
			DB.closeResultset(rs);
			DB.closeStatement(st);
		}
		
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
