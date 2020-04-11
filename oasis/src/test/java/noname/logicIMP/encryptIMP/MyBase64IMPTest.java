package noname.logicIMP.encryptIMP;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyBase64IMPTest {


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void decode() {
        String msg = "YWJjZGVm";
        String expected = "abcdef";
        String res = MyBase64IMP.decode(msg);
        assertEquals(expected, res);
    }
}