package com.seller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seller.dto.CommonApiResponse;
import com.seller.dto.ProductAddRequest;
import com.seller.dto.ProductDetailUpdateRequest;
import com.seller.dto.ProductResponseDto;
import com.seller.resource.productresource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/product")
public class productController {

	@Autowired
	private productresource productresource;

	@PostMapping("/save")

	public ResponseEntity<CommonApiResponse> addProduct(ProductAddRequest productDto) {
		return this.productresource.addProduct(productDto);
	}

	@PutMapping("/update/Details")
	@Operation(summary = "Api to update product details")
	public ResponseEntity<CommonApiResponse> updateProductDetails(@RequestBody ProductDetailUpdateRequest request) {
		return this.productresource.updateProductDetail(request);
	}

	@DeleteMapping("/delete")
	@Operation(summary = "Api to delete product")
	public ResponseEntity<CommonApiResponse> deleteProduct(@RequestParam("productId") int productId,
			@RequestParam("sellerId") int sellerId) {
		return this.productresource.deleteProduct(productId, sellerId);
	}
	@PutMapping("update/image")
	@Operation(summary = "Api to update product images")
	public ResponseEntity<CommonApiResponse> updateProductDetails(ProductAddRequest request) {
		return this.productresource.updateProductImage(request);
	}
	@GetMapping("/fetch")
	@Operation(summary = "Api to fetch product by Id")
	public ResponseEntity<ProductResponseDto> fetchProductById(@RequestParam(name = "productId") int productId) {
		return this.productresource.fetchProductById(productId);
	}
	@GetMapping("/fetch/all")
	@Operation(summary = "Api to fetch all active product")
	public ResponseEntity<ProductResponseDto> fetchAllProduct() {
		return this.productresource.fetchAllProducts();
	}
	@GetMapping("/search")
	@Operation(summary = "Api to search the products by name")
	public ResponseEntity<ProductResponseDto> searchProductsByName(
			@RequestParam(name = "productName") String productName)
	{
		return this.productresource.searchProductByName(productName);
	}
}
