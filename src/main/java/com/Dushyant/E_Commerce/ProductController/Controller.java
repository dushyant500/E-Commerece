package com.Dushyant.E_Commerce.ProductController;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Dushyant.E_Commerce.Model.Product;
import com.Dushyant.E_Commerce.Service.ProductService;

//import jakarta.servlet.annotation.MultipartConfig;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Controller {

	ProductService pService;
	public Controller(ProductService ps) {
		this.pService = ps;
	}
		
	@GetMapping("/products")
	private List<Product> getProducts() {
		return pService.getProducts();
	}
	
	@GetMapping("/products/search")
	private ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
		return pService.getSearchProducts(keyword);
	}
	
	//api/product/${id}/image
	//api/product/${id}
	@GetMapping("/product/{id}")
	private ResponseEntity<?> getPrdtById(@PathVariable int id){
		Product p = pService.getPrdtById(id);
		if (p.getId()!=9999999) {
			byte[] img = p.getImagedata(); 
			return new ResponseEntity<>(p,HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
	}
	
	//api/product/${id}/image
	@GetMapping("/product/{productId}/image")
	private ResponseEntity<byte []> getPrdtImgById(@PathVariable int productId){
		
		Product p = pService.getPrdtById(productId);
		if (p.getId()!=9999999) {
		byte[] image = p.getImagedata(); 
		//return new ResponseEntity<>(image,HttpStatus.OK);
		return ResponseEntity.ok().contentType(MediaType.valueOf(p.getImgType())).body(p.getImagedata());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	//api/product
	@PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	private ResponseEntity<?> addPrdt(@RequestPart("product") Product pr,@RequestPart("imageFile") MultipartFile pImg){
		System.out.println(pImg);
		ResponseEntity<?> res = pService.addPrdt(pr,pImg);
		return res;
		
	}
	
	//api/product
		@PutMapping(value = "/product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		private ResponseEntity<?> updtPrdt(@PathVariable int id,@RequestPart("product") Product pr,@RequestPart("imageFile") MultipartFile pImg) throws IOException{
			
			return pService.updPrdt(id,pr,pImg);
			
			
		}
		
		@DeleteMapping("/product/{id}")
		private ResponseEntity<?> delPrdt(@PathVariable int id){
			return pService.deletePrdt(id);
		}
		
		
	
}
