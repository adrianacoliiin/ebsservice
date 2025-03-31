package com.ti.soa.ebs_service.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Auth {
    private final String SECRET_KEY = "aJksd9QzPl+sVdK7vYc/L4dK8HgQmPpQ5K9yApUsj3w=";

    public boolean validateToken(String token) {
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            System.out.println("Token no proporcionado o mal formado.");
            return false; // El token no es válido si no se proporciona o no tiene el prefijo "Bearer"
        }

        try {
            String jwtToken = token.replace("Bearer ", "");
            if (jwtToken.split("\\.").length != 3) {
                System.out.println("Token JWT mal formado: debe tener 3 partes.");
                return false; // El token no tiene el formato adecuado
            }

            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();

            System.out.println("Token válido, usuario: " + claims.getSubject());
            return true;
        } catch (Exception e) {
            System.out.println("Error al validar token: " + e.getMessage());
            return false; // Token no válido
        }
    }
}