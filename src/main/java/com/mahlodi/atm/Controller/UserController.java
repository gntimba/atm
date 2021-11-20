package com.mahlodi.atm.Controller;


import com.mahlodi.atm.DTO.userDTO;
import com.mahlodi.atm.Exception.NotFoundException;
import com.mahlodi.atm.Service.UserService;
import com.mahlodi.atm.model.UserRequest;
import com.mahlodi.atm.model.UserResponse;
import com.mahlodi.atm.persistence.entity.User;
import com.mahlodi.atm.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    private JWTUtil util;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody userDTO user) {
        ResponseEntity<String> resp = null;
        try {
            Long id = service.save(user);
            resp = new ResponseEntity<String>(
                    "USer with  '" + id + "' created", HttpStatus.CREATED
            );
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to save User",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        ResponseEntity<?> resp = null;
        try {
            Optional<User> user = service.findById(id);
            resp = new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            throw nfe;
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to find User",
                    HttpStatus.NOT_FOUND);
        }
        return resp;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserRequest request) {

        //Validate username/password with DB(required in case of Stateless Authentication)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        String token = util.generateToken(request.getUsername());
        return ResponseEntity.ok(new UserResponse(token, "Token generated successfully!",service.findByemail(request.getUsername()).get()));
    }
}
