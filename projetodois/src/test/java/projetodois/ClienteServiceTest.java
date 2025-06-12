package projetodois;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class ClienteServiceTest {

    private IClienteService clienteService;

    private Cliente cliente;

    public ClienteServiceTest() {
        IClienteDAO dao = new ClienteDAOMock();
        clienteService = new ClienteService(dao);
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

        clienteService.salvar(cliente);
    }

    @Test
    public void pesquisarCliente(){
        Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }
    @Test
    public void salvarCliente(){
        Boolean retorno = clienteService.salvar(cliente);
        org.junit.Assert.assertTrue(retorno);
    }
    @Test
    public void excluirCliente(){
        clienteService.excluir(cliente.getCpf());
    }
    @Test
    public void alterarCliente(){
        cliente.setNome("Paulosaa");
        clienteService.alterar(cliente);

        Assert.assertEquals("Paulosaa", cliente.getNome());
    }
}
