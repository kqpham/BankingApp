package ontario.accounts;

import junit.framework.TestCase;

/**
 * Created by Faiq on 2/21/2016.
 * -----------------------------
 * JUnit testing done by
 * @author Kevin Pham
 */
public class GICTest extends TestCase {

	private GIC G1;
	 protected void setUp() throws Exception{
	        super.setUp();
	        G1 = new GIC("test", "abc123", 1.0, 3, 1.5);
	        }
   

    public void testGetBalanceAtMaturity() throws Exception {
    double test= Math.pow(G1.getBalance() * (1+G1.getAnnualInterestRate()),G1.getInvestmentPeriod());
    	assertEquals(test, G1.getBalanceAtMaturity());

    }

   
    public void testToString() throws Exception {
    	String Tester = G1.toString();
    	assertEquals(Tester, G1.toString());
    }
}