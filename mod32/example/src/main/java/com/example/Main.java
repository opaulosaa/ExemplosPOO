package com.example;

import org.hibernate.cfg.Configuration;

import com.example.model.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();

            Usuario user = new Usuario("Paulosa");
            em.persist(user);

            em.getTransaction().commit();
            System.out.println("Usuario salvo com o ID " + user.getId());

            Usuario usuarioSalvo = em.find(Usuario.class, user.getId());
            System.out.println("Usuario encontrado: " + usuarioSalvo.getName());
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            factory.close();
        }
    }

}
