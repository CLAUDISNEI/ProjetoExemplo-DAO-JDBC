package application;

import java.util.Scanner;

import model.DAO.DaoFactory;
import model.DAO.DepartmentDAO;
import model.entities.Department;

public class Program2 {
	public static void main(String[] args) {
		
		DepartmentDAO departmentDAO = DaoFactory.createDepartmentDao();
		Scanner sc = new Scanner(System.in);
		Department dep;
		
	    System.out.println("=====Teste 1: Incluindo departamento no Banco de Dados=====");
	    dep = new Department(null, "Teste");
		departmentDAO.insert(dep);
		System.out.println("Departamento criado Id: "+dep.getId());
		
		System.out.println("=====Teste 2: Localizando departamento no Banco de Dados pelo Id=====");
		dep = departmentDAO.findById(3);
		System.out.println("Departamento localizado : "+ dep.toString());
		
		System.out.println("=====Teste 3: Alterando departamento no Banco de Dados pelo Id=====");
		dep = departmentDAO.findById(4);
		dep.setNome("Livros");
		departmentDAO.update(dep);
		System.out.println("Departamento alerado!\n"+dep.toString()); 
		
		System.out.println("=====Teste 4: Excluindo departamento no Banco de Dados pelo Id=====");
		System.out.print("Informe código do departamento : ");
		int codDep = sc.nextInt();
		departmentDAO.deleteById(codDep);
		System.out.println("Departamento Excluido!");
				 
		sc.close();	
	}
}
