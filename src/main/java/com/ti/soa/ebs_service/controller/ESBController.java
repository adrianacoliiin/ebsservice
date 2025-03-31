package com.ti.soa.ebs_service.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import com.ti.soa.ebs_service.model.User;
import com.ti.soa.ebs_service.model.Cliente;
// import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.BodyInserters;
import com.ti.soa.ebs_service.utils.Auth;


@RestController
@RequestMapping("/api/v1/esb")
public class ESBController {

    private final WebClient webClient = WebClient.create();
    // private final String SECRET_KEY = "aJksd9QzPl+sVdK7vYc/L4dK8HgQmPpQ5K9yApUsj3w=";
    private final Auth auth = new Auth();

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String response = webClient.post()
                // .uri("http://localhost:3000/api/users/createuser")
                // .uri("http://users:3001/api/users/createuser")
                .uri("http://users.railway.internal:3001/api/users/login")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/users")
    public ResponseEntity<String> getUsers(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (!auth.validateToken(token)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }
        
        String response = webClient.get()
                // .uri("http://localhost:3000/api/users/all")
                // .uri("http://users:3001/api/users/all")
                .uri("http://users.railway.internal:3001/api/users/all")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (!auth.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
        String response = webClient.post()
                // .uri("http://localhost:3000/api/users/createuser")
                // .uri("http://users:3001/api/users/createuser")
                .uri("http://users.railway.internal:3001/api/users/createuser")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable("id") String id,
            @RequestBody User user,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (!auth.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
        String response = webClient.patch()
                // .uri("http://localhost:3000/api/users/updateuser/{id}", id)
                // .uri("http://users:3001/api/users/updateuser/{id}", id)
                .uri("http://users.railway.internal:3001/api/users/updateuser/{id}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (!auth.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
        String response = webClient.patch()
                // .uri("http://localhost:3000/api/users/deleteuser/{id}", id)
                // .uri("http://users:3001/api/users/deleteuser/{id}", id)
                .uri("http://users.railway.internal:3001/api/users/deleteuser/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }
    


    // C L I E N T E S ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @GetMapping("/clientes")
    public ResponseEntity<String> getClientes(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (!auth.validateToken(token)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }
        
        String response = webClient.get()
                // .uri("http://localhost:3001/api/clientes/all")
                // .uri("http://clientes:3003/api/clientes/all")
                .uri("http://clientes.railway.internal:3003/api/clientes/all")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/clientes")
    public ResponseEntity<String> createCliente(@RequestBody Cliente cliente, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (!auth.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
        String response = webClient.post()
                // .uri("http://localhost:3001/api/clientes/createcliente")
                // .uri("http://clientes:3003/api/clientes/createcliente")
                .uri("http://clientes.railway.internal:3003/api/clientes/createcliente")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(cliente)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/clientes/{id}")
    public ResponseEntity<String> updateCliente(
            @PathVariable("id") String id,
            @RequestBody Cliente cliente,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (!auth.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
        String response = webClient.patch()
                // .uri("http://localhost:3001/api/clientes/updateclientes/{id}", id)
                // .uri("http://clientes:3003/api/clientes/updateclientes/{id}", id)
                .uri("http://clientes.railway.internal:3003/api/clientes/updateclientes/{id}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, token)
                .body(BodyInserters.fromValue(cliente))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/clientes/delete/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable("id") String id, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        if (!auth.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
        String response = webClient.patch()
                // .uri("http://localhost:3001/api/clientes/deleteclientes/{id}", id)
                // .uri("http://clientes:3003/api/clientes/deleteclientes/{id}", id)
                .uri("http://clientes.railway.internal:3003/api/clientes/deleteclientes/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ResponseEntity.ok(response);
    }
}