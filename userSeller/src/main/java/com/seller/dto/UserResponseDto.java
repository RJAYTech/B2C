package com.seller.dto;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDto extends CommonApiResponse {

	private List<UserSellerDto> users = new ArrayList<>();

	public List<UserSellerDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserSellerDto> users) {
		this.users = users;
	}

}
