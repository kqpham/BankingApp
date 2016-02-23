package ontario.accounts;

import junit.framework.TestCase;

/**
 * Created by Faiq on 2/21/2016.
 * -----------------------------
 * JUnit testing done by
 * @author Kevin Pham
 *
 *
 */
public class ChequingTest extends TestCase {

    private Chequing c1, c2;
    protected void setUp() throws Exception{
        super.setUp();
        c1 = new Chequing("Kevin", "abc123",120.59 ,1.0, 10);
        c2 = new Chequing("Faiq", "def456", 100.2, 2.0, 15);
    }

    public void testDeposit() throws Exception {
        assertEquals(true,c1.deposit(200));
    }

    public void testWithdraw() throws Exception {
        assertEquals(false,c2.withdraw(202));
    }

    public void testEquals() throws Exception {
        assertEquals(false, c1.equals(c2));
    }

    public void testToString() throws Exception {
        String Test = c1.toString();
        assertEquals(Test, c1.toString());
    }

}