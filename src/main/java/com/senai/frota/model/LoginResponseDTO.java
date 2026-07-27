/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.model;

/**
 *
 * @author Micro
 */
public class LoginResponseDTO {
    
    // Classe para popular atributos de login da sessao
    // Junta a resposta do login em uma estrutura JSON
    //Combina o token gerado pelo TokenService, cargo, nome e ID
    
    private Long id;
    private String token;
    private String cargo;
    private String nome;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Long id, String token, String cargo, String nome) {
        this.id = id;
        this.token = token;
        this.cargo = cargo;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    
}
