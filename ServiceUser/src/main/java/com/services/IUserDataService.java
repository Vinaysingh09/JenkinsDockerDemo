package com.services;

import java.util.List;

import com.model.UserDataModel;

public interface IUserDataService {

	public List<UserDataModel> getUserList();

	public UserDataModel addUserData(UserDataModel users);

	public UserDataModel updateUserData(Long id,UserDataModel users);

	public int deleteUser(Long id);

}
