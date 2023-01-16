package com.cursospringboot.Curso.controllers;

import com.cursospringboot.Curso.dao.UserDAO;
import com.cursospringboot.Curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController
public class AuthController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody Usuario usuario)
    {
        Usuario loggedUser = userDAO.getUserByLogin(usuario);

        if (loggedUser != null)
        {
            String userData = usuario.getId() + usuario.getEmail();

            String token = Base64.getEncoder().encodeToString(userData.getBytes());

            return token;
        }

        return "FAILED";
    }

}
