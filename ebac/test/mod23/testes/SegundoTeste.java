package mod23.testes;

import org.junit.Assert;
import org.junit.Test;

public class SegundoTeste {
    @Test
    public void segundoTeste(){
        String name = "paulosa";
        Assert.assertEquals("paulosa", name);
    }
}
