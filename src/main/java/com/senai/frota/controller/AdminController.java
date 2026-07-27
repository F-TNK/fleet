/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.controller;

import com.senai.frota.model.UserDTO;
import com.senai.frota.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Micro
 */
@RestController
@RequestMapping("/api/admin/user")
public class AdminController {
    
    @Autowired
    private UserService uservice;

    @GetMapping
    public List<UserDTO> listUsers() {
        return uservice.listUsers();
    }

    @PutMapping
    public String editUser(@RequestBody UserDTO u) {
        uservice.editUser(u);
        return "Usuario editado com sucesso";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
        uservice.deleteUser(id);
        return "Usuario excluído com sucesso";
    }
}
