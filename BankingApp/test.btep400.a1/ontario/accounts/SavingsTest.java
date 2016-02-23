package ontario.accounts;

import junit.framework.TestCase;

/**
 * Created by Faiq on 2/21/2016.
 * -----------------------------
 * JUnit testing done by
 * @author Kevin Pham
 */
public class SavingsTest extends TestCase {

    private Savings s1, s2;
    protected void setUp() throws Exception{
        super.setUp();
        s1 = new Savings("Kevin", "abc123", 1.0, 1.5);
        s2 = new Savings("Faiq", "def456", 5.0, 0.5);
        }

    public void testEquals() throws Exception {
    	
    	assertEquals(true, s1.equals(s2));
    }

    public void testToString() throws Exception {
        String test = s1.toString();
    	assertEquals(test,s1.toString());
    
    }
   
}