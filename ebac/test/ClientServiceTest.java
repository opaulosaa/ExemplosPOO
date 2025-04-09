import dao.ClientDao;
import dao.ClientDaoMock;
import org.junit.Test;
import service.ClientService;
import org.junit.Assert;

public class ClientServiceTest {

    @Test
    public void salvarTest(){
        ClientDaoMock mockDao = new ClientDaoMock();
        ClientService service = new ClientService(mockDao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroNoSalvarTest(){
        ClientDao mockDao = new ClientDao();
        ClientService service = new ClientService(mockDao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test
    public void buscarTest() {
        ClientDaoMock mockDao = new ClientDaoMock();
        ClientService service = new ClientService(mockDao);
        String resultado = service.buscar();
        Assert.assertEquals("Cliente encontrado", resultado);
    }

    @Test(expected = RuntimeException.class)
    public void buscarErroTest() {
        ClientDao mockDao = new ClientDao();
        ClientService service = new ClientService(mockDao);
        service.buscar();
    }

    @Test
    public void excluirTest() {
        ClientDaoMock mockDao = new ClientDaoMock();
        ClientService service = new ClientService(mockDao);
        String resultado = service.excluir();
        Assert.assertEquals("Cliente excluído com sucesso", resultado);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void excluirErroTest() {
        ClientDao mockDao = new ClientDao();
        ClientService service = new ClientService(mockDao);
        service.excluir();
    }

    @Test
    public void atualizarTest() {
        ClientDaoMock mockDao = new ClientDaoMock();
        ClientService service = new ClientService(mockDao);
        String resultado = service.atualizar();
        Assert.assertEquals("Cliente atualizado com sucesso", resultado);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void atualizarErroTest() {
        ClientDao mockDao = new ClientDao();
        ClientService service = new ClientService(mockDao);
        service.atualizar();
    }
}
