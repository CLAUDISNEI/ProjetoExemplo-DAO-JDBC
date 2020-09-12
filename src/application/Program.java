package application;

import java.util.Date;
import java.util.List;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDAO sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===Teste 1: seller findById===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== Test 2: selle findByDepartment====");
		Department department = new Department(2, null);
		List<Seller> lista = sellerDao.findByDepartment(department);
		for(Seller s: lista) {
			System.out.println(s);
		}
		
		System.out.println("\n=== Test 3: seller findAll====");
		
		lista = sellerDao.findAll();
		for(Seller s: lista) {
			System.out.println(s);
		}
		
		System.out.println("\n=== Test 4: seller insert====");
		Seller novoVendedor = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, department);
		sellerDao.insert(novoVendedor);
		System.out.println("Inserido novo Id = "+ novoVendedor.getId());
	}

}
