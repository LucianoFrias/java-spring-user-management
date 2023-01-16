package com.cursospringboot.Curso.dao;

import com.cursospringboot.Curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        List<Usuario> result =  entityManager.createQuery(query).getResultList();
        return result;
    }

    @Override
    public void deleteUsuario(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registerUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario getUserByLogin(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> list = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();


        if (list.isEmpty())
        {
            return null;
        }
        String hashedPassword = list.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if(argon2.verify(hashedPassword, usuario.getPassword()))
        {
            return list.get(0);
        }

        return null;
    }
}
