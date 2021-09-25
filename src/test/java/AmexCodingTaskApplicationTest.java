import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AmexCodingTaskApplicationTest {

    AmexCodingTaskApplication acta;

    @Before
    public void SetUp(){
        acta = new AmexCodingTaskApplication();
    }

    @Test
    public void amexCodingTaskApplicationTest_NotNull(){
        Assert.assertNotNull(acta);
    }
}
