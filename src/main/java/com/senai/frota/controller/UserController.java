/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.controller;

import com.senai.frota.model.UserDTO;
import com.senai.frota.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {
    
    @Autowired
    private UserService uservice;
    
    @PostMapping("/registrar")
    public String registrar(@RequestBody UserDTO u) {
        uservice.register(u);
        return "Cadastro realizado com sucesso!";
    }
    
    @GetMapping("/login")
    public String login(@RequestBody UserDTO u){
        return uservice.login(u); 
    }
    
}
