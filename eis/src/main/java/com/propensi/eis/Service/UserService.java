package com.propensi.eis.Service;

import com.propensi.eis.Model.UserModel;

public interface UserService {
	UserModel addUser(UserModel user);
	public String encrypt(String password);
	UserModel findUserByUsername(String name);
	void changePassword(UserModel user, String passBaru);
}