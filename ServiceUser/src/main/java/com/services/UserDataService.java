package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.common.model.OrderReceivedRequest;
import com.model.UserDataModel;
import com.repository.UserRepository;
@Service
public class UserDataService implements IUserDataService {
	private final UserRepository userRepository;
	private final IUserPayment userPayment;

	public UserDataService(UserRepository userRepository, IUserPayment userPayment) {
		super();
		this.userRepository = userRepository;
		this.userPayment = userPayment;
	}

	@Override
	public List<UserDataModel> getUserList() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public UserDataModel addUserData(UserDataModel users) {
		// TODO Auto-generated method stub
		return userRepository.save(users);
	}

	@Override
	public UserDataModel updateUserData(Long id, UserDataModel updatedUser) {
	    if (updatedUser == null) {
	        // You can throw an exception or return null based on your design
	        throw new IllegalArgumentException("Updated user data must not be null");
	    }

	    return userRepository.findById(id).map(user -> {
	        if (updatedUser.getUsername() != null) {
	            user.setUsername(updatedUser.getUsername());
	        }
	        if (updatedUser.getPassword() != null) {
	            user.setPassword(updatedUser.getPassword());
	        }
	        if (updatedUser.getEmail() != null) {
	            user.setEmail(updatedUser.getEmail());
	        }
	        if (updatedUser.getFullName() != null) {
	            user.setFullName(updatedUser.getFullName());
	        }
	        if (updatedUser.getDateOfBirth() != null) {
	            user.setDateOfBirth(updatedUser.getDateOfBirth());
	        }

	        // Boolean primitives can't be null, so you might want to use Boolean object instead
	        user.setActive(updatedUser.isActive());

	        return userRepository.save(user);
	    }).orElse(null); // Or throw new UserNotFoundException(id);
	}

	@Override
	public int deleteUser(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).map(user->{
			userRepository.deleteById(id);
			return 204;
		}).orElse(404);
	}

	@Override
	public OrderReceivedRequest checkUserAndProcessPayment(OrderReceivedRequest req) {
		// TODO Auto-generated method stub
		 return userRepository.findById(req.getUserId()).map(e->{
			 OrderReceivedRequest processPayment = userPayment.processPayment(req);
			 return processPayment;
		 }).orElseThrow();
	}

}
