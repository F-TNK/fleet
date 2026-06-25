/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.service;

import com.senai.frota.model.UserDTO;
import com.senai.frota.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Micro
 */
public class UserService {
    
    @Autowired
    private UserDAO udao;
    
    @Autowired
    private TokenService tServ;
    
    public void register(UserDTO u) {
        String message = "";
        if (u.getNome().isEmpty()) {
            message = "Nome não preenchido";
        } else if (u.getEmail().isEmpty()) {
            message = "E-mail não preenchido";
        } else if (u.getSenha().isEmpty()) {
            message = "Senha não preenchida";
        } else if (u.getCargo().isEmpty()) {
            u.setCargo("operador");
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }

        udao.register(u);
    }
    
    
    public String login(UserDTO u){
        String message = "";
        if (u.getEmail().isEmpty()) {
            message = "E-mail não preenchido";
        } else if (u.getSenha().isEmpty()) {
            message = "Senha não preenchida";
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }

        UserDTO dados = udao.login(u.getEmail(), u.getSenha());
        return tServ.gerarToken(dados);
    }
    
}
