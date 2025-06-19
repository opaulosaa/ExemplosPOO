import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CarroTest {
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp() {
        sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Carro.class)
                .addAnnotatedClass(Marca.class)
                .addAnnotatedClass(Acessorio.class)
                .buildSessionFactory();
    }

    @AfterClass
    public static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void testPersistirCarroMarcaAcessorio() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Marca marca = new Marca();
        marca.setNome("Toyota");
        session.save(marca);

        Acessorio ar = new Acessorio();
        ar.setNome("Ar Condicionado");
        session.save(ar);

        Acessorio abs = new Acessorio();
        abs.setNome("Freio ABS");
        session.save(abs);

        Carro carro = new Carro();
        carro.setModelo("Corolla");
        carro.setMarca(marca);
        carro.setAcessorios(Arrays.asList(ar, abs));
        session.save(carro);

        session.getTransaction().commit();
        session.close();

        // Buscar e verificar
        session = sessionFactory.openSession();
        Carro carroBuscado = (Carro) session.createQuery("from Carro where modelo = :modelo")
                .setParameter("modelo", "Corolla")
                .uniqueResult();
        Assert.assertNotNull(carroBuscado);
        Assert.assertEquals("Toyota", carroBuscado.getMarca().getNome());
        List<Acessorio> acessorios = carroBuscado.getAcessorios();
        Assert.assertEquals(2, acessorios.size());
        session.close();
    }
} 