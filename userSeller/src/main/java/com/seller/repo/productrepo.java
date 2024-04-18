
  package com.seller.repo;
  
  import java.util.List;
   
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seller.enity.UserSeller;
import com.seller.enity.product;
  
  @Repository
  public interface productrepo extends JpaRepository<product,Integer> 
  {
  
  List<product> findByStatusIn(List<String> status);
  
  List<product> findBySellerAndStatusIn(UserSeller seller, List<String>status);
  Long countByStatusIn(List<String> status);
  
  Long countByStatusInAndSeller(List<String> status, UserSeller seller);
  
  List<product> findByNameContainingIgnoreCaseAndStatusIn(String productName,List<String> status);
  
  List<product> findByNameContainingIgnoreCaseAndSellerAndStatusIn(String productName, UserSeller seller, List<String> status);


  
  }
 