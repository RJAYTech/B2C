package com.seller.untity;

public class Constants {
	
	public enum UserRole {
		ROLE_CUSTOMER("Customer"),
		ROLE_ADMIN("Admin"),
		ROLE_SELLER("Seller"),
		ROLE_DELIVERY("Delivery");
		
		private String role;

	    private UserRole(String role) {
	      this.role = role;
	    }

	    public String value() {
	      return this.role;
	    }    
	}
	
	public enum UserStatus {
		ACTIVE("Active"),
		DEACTIVATED("Deactivated");
		
		
		private String status;

	    private UserStatus(String status) {
	      this.status = status;
	    }

	    public String value() {
	      return this.status;
	    }    
	}

	
	
	public enum ProductStatus {
		ACTIVE("Active"),
		DEACTIVATED("Deactivated");
		
		
		private String status;

	    private ProductStatus(String status) {
	      this.status = status;
	    }

	    public String value() {
	      return this.status;
	    }    
	}
	
	public enum CategoryStatus {
		ACTIVE("Active"),
		DEACTIVATED("Deactivated");
		
		
		private String status;

	    private CategoryStatus(String status) {
	      this.status = status;
	    }

	    public String value() {
	      return this.status;
	    }    
	}
	
}
