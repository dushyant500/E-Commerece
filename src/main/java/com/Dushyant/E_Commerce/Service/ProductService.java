package com.Dushyant.E_Commerce.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Dushyant.E_Commerce.Model.Product;
import com.Dushyant.E_Commerce.Repo.ProductRepo;



@Service
public class ProductService {

	ProductRepo repo;
	
	public ProductService(ProductRepo repo) {
		this.repo = repo; 
	}
	
	public Product getPrdtById(int id) {
		/*
    private int id;	
	private String name;
	private String description;
	private String brand;
	private BigDecimal price;
	private String category;
	private Date releasedDate;
	private boolean available;
	private int quantity;
		 */
		String blank = new String();
		Date date = null;
		byte[] b = null;
		Product pr = repo.findById(id).
		orElse(new Product(9999999,"No Item","Item does not exist",blank,new BigDecimal(0.0),blank,date,false,0,blank,blank,b));
		return pr;
	}

	public ResponseEntity<?> addPrdt(Product p, MultipartFile pImg) {
		p.setImgName(pImg.getOriginalFilename());
		p.setImgType(pImg.getContentType());
		try {
			p.setImagedata(pImg.getBytes());
			repo.save(p);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (IOException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<Product> getProducts() {
		return repo.findAll();
	}

	public ResponseEntity<?> updPrdt(int id,Product pr, MultipartFile pImg) throws IOException {
		
		if(repo.findById(id).orElse(null)!=null) {
			pr.setImagedata(pImg.getBytes());
			pr.setImgName(pImg.getOriginalFilename());
			pr.setImgType(pImg.getContentType());
			return new ResponseEntity(repo.save(pr),HttpStatus.OK) ;
		}
		return new ResponseEntity("No Data Found",HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<?> deletePrdt(int id) {
		if(getPrdtById(id).getId()!=9999999) {
			repo.deleteById(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Product>> getSearchProducts(String s) {
		return new ResponseEntity(repo.searchProduct(s),HttpStatus.OK);
		//return null;
	}

}
