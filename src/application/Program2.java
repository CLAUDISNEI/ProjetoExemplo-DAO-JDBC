package application;

import java.util.Scanner;

import model.DAO.DaoFactory;
import model.DAO.DepartmentDAO;
import model.entities.Department;

public class Program2 {
	public static void main(String[] args) {
		
		DepartmentDAO departmentDAO = DaoFactory.createDepartmentDao();
		Scanner sc = new Scanner(System.in);
		
		/*System.out.println("=====Teste 1: Incluindo departamento no Banco de Dados=====");
		Department novoDepartamento = new Department(null, "Automóveis");
		departmentDAO.insert(novoDepartamento);
		System.out.println("Departamento criado Id: "+novoDepartamento.getId());*/
		
		System.out.println("=====Teste 2: Localizando departamento no Banco de Dados pelo Id=====");
		//Department novoDepartamento = new Department(null, "Automóveis");
		Department depAlterado = departmentDAO.findById(3);
		System.out.println("Departamento localizado : "+ depAlterado.toString());
		
		System.out.println("=====Teste 3: Alterando departamento no Banco de Dados pelo Id=====");
		depAlterado = departmentDAO.findById(3);
		depAlterado.setNome("Moda Verão");
		System.out.println("Novo departamento: "+depAlterado.toString());
		departmentDAO.update(depAlterado);
		System.out.println("Departamento alerado!");
		
				 
				
	}
}
