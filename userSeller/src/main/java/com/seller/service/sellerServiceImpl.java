
package com.seller.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.seller.enity.UserSeller;
import com.seller.repo.UserSellerDao;

@Service

@Component
public class sellerServiceImpl implements sellerService {

	@Autowired
	private UserSellerDao sellerDao;

	
	/* @Autowired private PasswordEncoder passwordEncoder; */
	  
	  @Override public UserSeller addUser(UserSeller seller)
	  {
	  return sellerDao.save(seller); }
	 

	@Override
	public UserSeller updateUser(UserSeller seller) {
		return sellerDao.save(seller);
	}

	@Override
	public UserSeller getUserByEmailAndStatus(String emailId, String status) {
		return sellerDao.findByEmailIdAndStatus(emailId, status);
	}

	@Override
	public UserSeller getUserByEmailid(String emailId) {
		return sellerDao.findByEmailId(emailId).orElse(null);
	}

	@Override
	public List<UserSeller> getUserByRole(String role) {
		return sellerDao.findByRole(role);
	}

	@Override
	public UserSeller getUserById(int userId) {

		Optional<UserSeller> optionalUser = this.sellerDao.findById(userId);

		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} else {
			return null;
		}

	}

	@Override
	public UserSeller getUserByEmailIdAndRoleAndStatus(String emailId, String role, String status) {
		return this.sellerDao.findByEmailIdAndRoleAndStatus(emailId, role, status);
	}

	@Override
	public List<UserSeller> getUserBySellerAndRoleAndStatusIn(UserSeller seller, String role, List<String> status) {
		return this.sellerDao.findBySellerAndRoleAndStatusIn(seller, role, status);
	}

	@Override
	public List<UserSeller> updateAllUser(List<UserSeller> seller) {
		return this.sellerDao.saveAll(seller);
	}

	@Override
	public List<UserSeller> getUserByRoleAndStatus(String role, String status) {
		return this.sellerDao.findByRoleAndStatus(role, status);
	}

}
