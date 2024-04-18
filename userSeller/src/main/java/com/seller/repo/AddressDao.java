package com.seller.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seller.enity.Address;



@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

}
