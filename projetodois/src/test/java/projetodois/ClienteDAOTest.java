package projetodois;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class ClienteDAOTest {

    private IClienteDAO clienteDAO;

    private Cliente cliente;

    public ClienteDAOTest(){
        clienteDAO = new ClienteDAOMock();
    }

    @Before
    public void init(){
        cliente = new Cliente();
        cliente.setCpf(1234567890L);
        cliente.setNome("Paulosa");
        cliente.setCidade("Fortal");
        cliente.setEnd("End");
        cliente.setEstado("CE");
        cliente.setNumero(10);
        cliente.setTel(12223432345L);

        clienteDAO.salvar(cliente);
    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = clienteDAO.buscarPorCPF(cliente.getCpf());

        Assert.assertNotNull(clienteConsultado);
    }

}
