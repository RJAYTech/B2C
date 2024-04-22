package com.Seller.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Seller.Enum.Status;
import com.Seller.Model.Product;
import com.Seller.Repository.productDao;
import com.Seller.ResponseRequest.CommonApiResponse;
import com.Seller.ResponseRequest.ProductResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class productServiceImpl implements productService {

	@Autowired
	productDao pd;

	@Override
	public ResponseEntity<CommonApiResponse> addproduct(int id, String pname, int price, Status sts,
			MultipartFile image1) {
		log.info("Request Received for Add-Product");
		CommonApiResponse ae = new CommonApiResponse();

		List<Product> pi = pd.findByProductNameAndImage1(pname, image1);
		System.out.println(pi + "vinay.....");
		if (pi.size() == 0) {
			ae.setResponse("failed");
			ae.setResult("failed");
			return new ResponseEntity<CommonApiResponse>(ae, HttpStatus.BAD_REQUEST);

		} else {
			Product p = new Product();
			p.setId(id);
			p.setProductName(pname);
			p.setPrice(price);
			p.setStatus(sts);
			System.out.println("from postman:" + p);
			try {
				p.setImage1(image1.getBytes());

			} catch (IOException e) {
				e.printStackTrace();
				log.info("Something Went Wrong");
				ae.setResponse("failed");
				ae.setResult("failed");

				return new ResponseEntity<CommonApiResponse>(ae, HttpStatus.BAD_REQUEST);
			}
			pd.save(p);
			ae.setResponse("Success");
			ae.setResult("Success");
			return new ResponseEntity<CommonApiResponse>(ae, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<ProductResponse> getProduct(int id) {
		ProductResponse ae = new ProductResponse();
		Product product = pd.findById(id).get();
		if (product != null) {
			ae.setProducts(Arrays.asList(product));
			ae.setResponse("success");
			ae.setResult("success");
			return new ResponseEntity<ProductResponse>(ae, HttpStatus.OK);
		} else { // ae.setProduct(Arrays.asList(byId));
			ae.setResponse("Failed");
			ae.setResult("Failed");
			return new ResponseEntity<ProductResponse>(ae, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<ProductResponse> getAllProduct() {
		ProductResponse ae = new ProductResponse();
		List<Product> all = pd.findAll();
		if (all.isEmpty()) {
			ae.setResponse("No Products Available to Fetch...");
			ae.setResult("Failed");
			return new ResponseEntity<ProductResponse>(ae, HttpStatus.BAD_REQUEST);
		} else {
			ae.setProducts(all);
			ae.setResponse("success");
			ae.setResult("success");
			return new ResponseEntity<ProductResponse>(ae, HttpStatus.OK);
		}
	}

	public ResponseEntity<ProductResponse> updateProductById(Product p,MultipartFile image1) {
		ProductResponse pr = new ProductResponse();

		Product product = pd.findById(p.getId()).get();
		Product pp = new Product();

		if (product == null) {
			log.info("Something Not Available");
			pr.setResponse("No products available");
			pr.setResult("Failed");
			return new ResponseEntity<ProductResponse>(pr, HttpStatus.BAD_REQUEST);
		} else {
			log.info("Request Received to update product");
			pp.setId(p.getId());
			pp.setProductName(p.getProductName());
			pp.setPrice(p.getPrice());
			pp.setStatus(p.getStatus());
			System.out.println("from postman:" + p);
			try {
				pp.setImage1(image1.getBytes());
			

			} catch (IOException e) {
				e.printStackTrace();
				log.info("Something Went Wrong");
				pr.setResponse("failed");
				pr.setResult("failed");

				return new ResponseEntity<ProductResponse>(pr, HttpStatus.BAD_REQUEST);
			}
			pd.save(p);
			pr.setResponse("Success");
			pr.setResult("Success");
			return new ResponseEntity<ProductResponse>(pr, HttpStatus.OK);

		}

	}

	/*
	 * @Override public ResponseEntity<ProductResponse> fetchProductsById(int id) {
	 * 
	 * ProductResponse pr = new ProductResponse();
	 * 
	 * Product product = pd.findById(id).get();
	 * 
	 * if (product.getImage1() == null || product.getProductName() == null ||
	 * product.getStatus() != Status.ACTIVE) { log.info("Something Not Available");
	 * pr.setResponse("No products avaible with this Id"); pr.setResult("Failed");
	 * return new ResponseEntity<ProductResponse>(pr, HttpStatus.BAD_REQUEST); }
	 * else { log.info("Request Received to Fetch Product By Id"); List<Product> all
	 * = pd.findAll(); pr.setProducts(all);
	 * pr.setResponse("Product according to Id"); pr.setResult("Success"); return
	 * new ResponseEntity<ProductResponse>(pr, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * }
	 */

}
