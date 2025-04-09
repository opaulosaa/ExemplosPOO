import org.testng.Assert;
import org.testng.annotations.Test;
import service.ContratoService;
import service.IContratoService;

public class ContratoServiceTest {

    @Test
    public void salvarTeste(){
        IContratoService service = new ContratoService();
        String retorno = service.salvar();
        Assert.assertEquals("sucesso", retorno);
    }
}
