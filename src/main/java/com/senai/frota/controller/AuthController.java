/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.controller;

import com.senai.frota.model.LoginResponseDTO;
import com.senai.frota.model.UserDTO;
import com.senai.frota.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Micro
 */
@RestController
@RequestMapping("/api/autenticar")
public class AuthController {
    
    @Autowired
    private UserService uservice;
    
    @PostMapping("/register")
    public String registrar(@RequestBody UserDTO u) {
        uservice.register(u);
        return "Cadastro realizado com sucesso!";
    }
    
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody UserDTO u){
        return uservice.login(u); 
    }
    
}
