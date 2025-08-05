package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.model.OrderReceivedRequest;
import com.model.UserDataModel;
import com.services.IUserDataService;

@RestController
@RequestMapping(value = "/user")
public class Controller {
	private final IUserDataService iUserDataService;
	

	public Controller(IUserDataService iUserDataService) {
		super();
		this.iUserDataService = iUserDataService;
	}

	@GetMapping(value = "/service")
	public String getService() {
		return "User Service";
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<UserDataModel>> getUserList() {
		List<UserDataModel> userList = iUserDataService.getUserList();
		if (userList.isEmpty()) {
			// Return 204 No Content if no users found
			return ResponseEntity.noContent().build();
		}

		// Return 200 OK with user list
		return ResponseEntity.ok(userList);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<UserDataModel> createUser(@RequestBody UserDataModel users) {	
		// Return 200 OK with user list
		return ResponseEntity.status(201).body(iUserDataService.addUserData(users));// 201 Created

	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<UserDataModel> updateUser(@PathVariable("id")Long id,@RequestBody UserDataModel users) {	
		// Return 200 OK with user list
		UserDataModel updateUserData = iUserDataService.updateUserData(id,users);
		if(updateUserData!=null)
			return ResponseEntity.ok(updateUserData);
		else
			return ResponseEntity.notFound().build();

	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id")Long id) {	
		// Return 200 OK with user list
		int deleteUser = iUserDataService.deleteUser(id);
		if(deleteUser==204)
			return ResponseEntity.status(204).body("Deleted");
		else
			return ResponseEntity.notFound().build();

	}
	@PostMapping(value = "/payment")
	public ResponseEntity<OrderReceivedRequest> processPayment(@RequestBody OrderReceivedRequest request) {	
		// Return 200 OK with user list
		return ResponseEntity.status(201).body(iUserDataService.checkUserAndProcessPayment(request));

	}
}
