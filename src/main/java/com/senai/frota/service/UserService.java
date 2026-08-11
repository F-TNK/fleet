/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.service;

import com.senai.frota.model.LoginResponseDTO;
import com.senai.frota.model.UserDTO;
import com.senai.frota.repository.UserDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Micro
 */
@Service
public class UserService {
    
    @Autowired
    private UserDAO udao;
    
    @Autowired
    private TokenService tServ;
    
    public void register(UserDTO u) {
        
        if (u.getCargo() == null || u.getCargo().trim().isEmpty()) {
            u.setCargo("operador");
        }

        // .trim() elimina "espacos" no inicio e no fim da STRING. Evita erros
        String message = "";
        if (u.getNome() == null || u.getNome().trim().isEmpty()) {
            message = "Nome não preenchido";
        } else if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
            message = "E-mail não preenchido";
        } else if (u.getSenha() == null || u.getSenha().trim().isEmpty()) {
            message = "Senha não preenchida";
        } else if (u.getCpf() == null || u.getCpf().trim().isEmpty()) {
            message = "CPF não preenchido";
        } else if (u.getTelefone() == null || u.getTelefone().trim().isEmpty()) {
            message = "Telefone não preenchido";
        } else if (u.getEndereco() == null || u.getEndereco().trim().isEmpty()) {
            message = "Endereco não preenchido";
        } else if (u.getDataNascimento() == null || u.getDataNascimento().trim().isEmpty()) {
            message = "Data De Nascimento não preenchida";
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        // .BAD_REQUEST eh um ENUM nativo do Java para erro .valueOf(400) - BOAS PRATICAS
        
        udao.register(u);
    }
    
    
    public LoginResponseDTO login(UserDTO u){
        String message = "";
        
        // .trim() elimina "espacos" no inicio e no fim da STRING. Evita erros
        if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
            message = "E-mail não preenchido";
        } else if (u.getSenha() == null || u.getSenha().trim().isEmpty()) {
            message = "Senha não preenchida";
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        UserDTO dados = udao.login(u.getEmail(), u.getSenha());
        
        // Verifica se o usuário realmente existe
        if (dados.getIdUser() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos");
        }
        // .UNAUTHORIZED eh um ENUM nativo do Java para erro .valueOf(401) Não autorizado/Credenciais Inválidas
        // BOAS PRATICAS !!
        
        String token = tServ.gerarToken(dados);
        
        // Devolve o ID, token, cargo e nome juntos
        return new LoginResponseDTO(dados.getIdUser(), token, dados.getCargo(), dados.getNome());
        
    }
    
    
    public List<UserDTO> listUsers() {
        return udao.listUsers(); 
    }
    
    public List<UserDTO> listOp(String cargo) {
        return udao.listOp(cargo); 
    }
    
    public void editUser(UserDTO u) {
        // .trim() elimina "espacos" no inicio e no fim da STRING. Evita erros
        String message = "";
        if (u.getNome() == null || u.getNome().trim().isEmpty()) {
            message = "Nome não preenchido";
        } else if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
            message = "E-mail não preenchido";
        } else if (u.getSenha() == null || u.getSenha().trim().isEmpty()) {
            message = "Senha não preenchida";
        } else if (u.getCpf() == null || u.getCpf().trim().isEmpty()) {
            message = "CPF não preenchido";
        } else if (u.getTelefone() == null || u.getTelefone().trim().isEmpty()) {
            message = "Telefone não preenchido";
        } else if (u.getEndereco() == null || u.getEndereco().trim().isEmpty()) {
            message = "Endereco não preenchido";
        } else if (u.getDataNascimento() == null || u.getDataNascimento().trim().isEmpty()) {
            message = "Data De Nascimento não preenchida";
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        // .BAD_REQUEST eh um ENUM nativo do Java para erro .valueOf(400) - BOAS PRATICAS
        
        udao.editUser(u);
    }
    
    public int deleteUser(Long id){
        return udao.deleteUser(id);
    }
    
    public UserDTO findById(Long id) {
        UserDTO u = udao.findById(id);
        if (u.getIdUser() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return u;
    }

    public void editProfile(UserDTO u) {
        String message = "";
        
        if (u.getNome() == null || u.getNome().trim().isEmpty()) {
            message = "Nome não preenchido";
        } else if (u.getEmail() == null || u.getEmail().trim().isEmpty()) {
            message = "E-mail não preenchido";
        } else if (u.getCpf() == null || u.getCpf().trim().isEmpty()) {
            message = "CPF não preenchido";
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        
        UserDTO findUser = udao.findById(u.getIdUser());
        if (findUser.getIdUser() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        
        // seta cargo com GET
        u.setCargo(findUser.getCargo());

        udao.editUser(u);
    }
    
}
