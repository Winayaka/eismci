package com.propensi.eis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.propensi.eis.Model.UserModel;
import com.propensi.eis.Repository.UserDb;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDb userDb;
	
	@Override
	public UserModel addUser(UserModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserModel findUserByUsername(String name) {
		return userDb.findByUsername(name);
	}

	@Override
	public void changePassword(UserModel user, String newPassword) {
		String pass = encrypt(newPassword);
		user.setPassword(pass);
		userDb.save(user);
	}
	

}