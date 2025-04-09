package mod23.testes;

import org.junit.Assert;
import org.junit.Test;

public class PrimeiroTeste {

    @Test
    public void primeiroTeste(){
        String name = "paulosa";
        Assert.assertEquals("paulosa", name);
    }

    @Test
    public void testNotEquals(){
        String name = "paulosa";
        Assert.assertNotEquals("paulo", name);
    }
}
