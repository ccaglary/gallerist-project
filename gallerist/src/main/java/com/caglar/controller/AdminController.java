package com.caglar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caglar.exception.BaseException;
import com.caglar.exception.ErrorMessage;
import com.caglar.exception.MessageType;
import com.caglar.model.Role;
import com.caglar.model.User;
import com.caglar.repository.RoleRepository;
import com.caglar.repository.UserRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/makeAdmin/{userId}")
    public ResponseEntity<String> makeAdmin(@PathVariable Long userId) {
    	
    	
    	
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_EXIST, "User ID: " + userId)
            ));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
            .orElseThrow(() -> new BaseException(
                new ErrorMessage(MessageType.NO_RECORD_EXIST, "ROLE_ADMIN")
            ));

        user.setRole(adminRole);
        userRepository.save(user);

        return ResponseEntity.ok("Kullanıcı admin yapıldı.");
    }
}
