
package com.Seller.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import com.Seller.Enum.Status;
import com.Seller.Model.Product;
import com.Seller.ResponseRequest.CommonApiResponse;
import com.Seller.ResponseRequest.ProductResponse;

public interface productService {

	ResponseEntity<CommonApiResponse> addproduct(int id, String pname, int price, Status sts, MultipartFile image1);

	ResponseEntity<ProductResponse> getProduct(int id);

	ResponseEntity<ProductResponse> getAllProduct();

	ResponseEntity<ProductResponse> updateProductById(Product p, MultipartFile image1);
}
