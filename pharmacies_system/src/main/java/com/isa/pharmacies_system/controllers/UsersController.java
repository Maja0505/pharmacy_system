package com.isa.pharmacies_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.isa.pharmacies_system.iservices.IUsersService;

@Controller
public class UsersController {
	
	private IUsersService iUsersService;
	
	@Autowired
	public UsersController(IUsersService iUsersService) {
		this.iUsersService = iUsersService;
	}
}
