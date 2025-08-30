package com.Dushyant.E_Commerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Dushyant.E_Commerce.Model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query("SELECT p from Product p where LOWER (p.name) LIKE LOWER(CONCAT ('%',:keyword,'%')) OR"
			+ " LOWER (p.brand) LIKE LOWER(CONCAT ('%',:keyword,'%')) OR "
			+ "LOWER (p.description) LIKE LOWER(CONCAT ('%',:keyword,'%')) ")
	List<Product> searchProduct(String keyword);
	
}
