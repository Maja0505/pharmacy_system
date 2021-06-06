package com.isa.pharmacies_system.controller;

import com.isa.pharmacies_system.DTO.ChangePasswordDTO;
import com.isa.pharmacies_system.service.iService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins="*")
@RequestMapping("api/user")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_DERMATOLOGIST') or hasRole('ROLE_PHARMACIST')")
    @PutMapping("/changePassword/firstSingUp")
    ResponseEntity<Boolean> changePasswordWhenFirstTimeSingUp(@RequestBody ChangePasswordDTO changePasswordDTO){
        try {
            if(userService.changePasswordWhenFirstTimeSingUp(changePasswordDTO.getNewPassword(),changePasswordDTO.getConfirmationOfNewPassword(), changePasswordDTO.getId()) == null){
                return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
