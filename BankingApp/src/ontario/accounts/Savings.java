package ontario.accounts;

import java.math.BigDecimal;

/**
 * Created by Faiq on 2/13/2016.
 *
 */
public class Savings extends Account
{
    /*
    private String fullName;
    private String accountNumber;
    private double currentBal;
    */
    private Account savingsAccount;
    private double annualIntRate;

    /**
     * Constructor for the Savings account. First three parameters are used to create the account
     * object. The last parameter is for the interest and is not passed to the Account object.
     * @param fullNameTemp          Name to be entered.(Type String)
     * @param accountNumberTemp     Account number to be entered. (Type String)
     * @param currentBalTemp        Account balance to be entered. (Type Double)
     * @param annualIntRateTemp     Interest rate to be set for the Savings account. (Type Double)
     */
    public Savings(String fullNameTemp, String accountNumberTemp,double currentBalTemp, double annualIntRateTemp)
    {
        BigDecimal decimal = new BigDecimal(annualIntRateTemp/100);
        decimal=decimal.setScale(4,BigDecimal.ROUND_UP);
        annualIntRate = decimal.doubleValue();
        savingsAccount = new Account(fullNameTemp,accountNumberTemp,currentBalTemp,annualIntRate);

    }

    /**
     * Default constructor for the Savings account object. It uses the default constructor of the super
     * class Account and sets the interest rate to 0.3%
     */
    public Savings()
    {
        savingsAccount= new Account();
        annualIntRate = 0.003;
    }

    /**
     * Compares the object entered with the current object. Uses the equals method of the Account superclass
     * @param object    Object to be compared
     * @return          return either true or false if the condition is met for the Accounts.equal() method
     */
    public boolean equals(Object object)
    {
        boolean result = false;
        if(object instanceof Savings)
        {
            Savings object2 = (Savings) object;

            if(     (object2.getSavingsAccount().equals(getSavingsAccount())) &&
                    (object2.getAnnualInterestRate()==((Savings) object).getAnnualInterestRate()))
            {
                result = true;
            }

        }
        return  result;
    }

    /**
     * Returns a modified string with the data of the account object. Uses the toString method of the Account superclass
     * @return          return the modified string with the data from Account.toString() method.
     */

    public String toString()
    {
        String stringVal;
        stringVal ="Account Type: SAVINGS"+ savingsAccount.toString();
        return stringVal;
    }

    /**
     * Method to return the savings account information
     * @return      returns account object
     */
    public Account getSavingsAccount()
    {
        return this.savingsAccount;
    }

    public double getAnnualIntRate()
    {
        return this.annualIntRate;
    }
}
