/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.model;

/**
 *
 * @author Micro
 */
public class UserDTO {
    
    private Long idUser;
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private String telefone;
    private String enderco;
    private String dataNascimento;
    private String cargo; // ADMIN ou OPERADOR

    public UserDTO() {
    }

    public UserDTO(Long idUser, String email, String senha, String nome, String cpf, String telefone, String endereço, String dataNascimento, String cargo) {
        this.idUser = idUser;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.enderco = endereço;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return enderco;
    }

    public void setEndereco(String endereço) {
        this.enderco = endereço;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
}
