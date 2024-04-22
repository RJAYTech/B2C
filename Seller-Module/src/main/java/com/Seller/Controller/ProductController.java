package com.Seller.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Seller.Enum.Status;
import com.Seller.ResponseRequest.CommonApiResponse;
import com.Seller.ResponseRequest.ProductResponse;
import com.Seller.Service.productService;

@RestController
//@RequestMapping("/product")
public class ProductController {
	@Autowired
	productService ps;

	@PostMapping("/addProductImage")
	public ResponseEntity<CommonApiResponse> addProduct(@RequestParam("id") int id,
			@RequestParam("productName") String pname, @RequestParam("price") int price,
			@RequestParam("status") Status sts, @RequestParam("image1") MultipartFile image1) {
		return ps.addproduct(id, pname, price, sts, image1);
	}

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") int id) {

		return ps.getProduct(id);
	}

	@GetMapping("/getAllProduct")
	public ResponseEntity<ProductResponse> getProduct() {

		return ps.getAllProduct();

	}

	/*
	 * @PutMapping("/updateProduct") public ResponseEntity<ProductResponse>
	 * fetchProductById(@RequestBody Product p) { MultipartFile i = p.getImage1();
	 * return ps.updateProductById(p, i); }
	 */

}
