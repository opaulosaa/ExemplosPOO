package com.exemplo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.exemplo.model.Usuario;

public class Main {
    public static void main(String[] args) {
        try {
            // Configuração do Hibernate
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            
            // Criação da SessionFactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            
            // Criação de uma sessão
            Session session = sessionFactory.openSession();
            
            // Inicia uma transação
            session.beginTransaction();
            
            // Cria um novo usuário
            Usuario usuario = new Usuario();
            usuario.setNome("Teste");
            usuario.setEmail("teste@email.com");
            
            // Salva o usuário
            session.persist(usuario);
            
            // Commit da transação
            session.getTransaction().commit();
            
            // Fecha a sessão
            session.close();
            
            System.out.println("Tabela criada e usuário inserido com sucesso!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 