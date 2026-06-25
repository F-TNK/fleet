/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senai.frota.service;

import com.senai.frota.model.AuthDTO;
import com.senai.frota.model.UserDTO;
import com.senai.frota.repository.UserDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author ftana
 */
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    private UserDAO repository;
    AuthDTO auth = new AuthDTO();
    
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    
    public String gerarToken(UserDTO user) {
        if ((user.getIdUser() == 0 || user.getIdUser() == null) ||
                user.getNome().isEmpty() ||
                user.getEmail().isEmpty() ||
                user.getSenha().isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Um ou mais campos faltantes");
        }
        return Jwts.builder()
                .subject(user.getNome())
                .claim("id", user.getIdUser())
                .claim("nome", user.getNome())
                .claim("role", user.getCargo())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3000000))
                .signWith(getSignKey())
                .compact();
    }
    
    
    
    public boolean validarToken(String token) {
        try {
            // Cria um parser JWT com a chave secreta para validação
            Jwts.parser()
                    .setSigningKey(getSignKey())
                    .build()
                    // Analisa e valida o token (lança exceção se inválido ou expirado)
                    .parseClaimsJws(token);
            // Se chegou aqui, o token é válido
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Se qualquer exceção ocorrer, o token é inválido ou expirou
            return false;
        }
    }
    public Claims extrairClaims(String token) {
        // Analisa o token com a chave secreta
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                // Retorna o corpo do token (claims)
                .getBody();
    }
}