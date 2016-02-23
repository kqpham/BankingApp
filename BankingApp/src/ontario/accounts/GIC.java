package ontario.accounts;

import java.math.BigDecimal;

/**
 * Created by Faiq on 2/13/2016.
 */
public class GIC  extends Account
{
    private int investmentPeriod; //in months
    private double annualIntRate;
    private Account accountGIC;
    /**
     *Default empty constructor using the Account superclass
     */
    public GIC()
    {
        accountGIC = new Account();
        investmentPeriod = 12;
        annualIntRate = 0.0124;
    }

    /**
     * Constructor using the Account superclass, takes full name, account number, balance, investment period in months (whole number),
     * and the annual interest rate.
     * @param fullNameTemp          Full name (type String) for the account holder
     * @param accountNumberTemp     Account number (type String) assigned to the GIC object
     * @param currentBalTemp        Balance (type Double) to be entered into the GIC object
     * @param investmentPeriodTemp  Investment period in months (type Int) to be added into the GIC object
     * @param annualIntRateTemp     Annual interest rate in percentage to be entered (type Double)
     */
    public GIC(String fullNameTemp, String accountNumberTemp,double currentBalTemp, int investmentPeriodTemp, double annualIntRateTemp)
    {
        BigDecimal decimal = new BigDecimal(annualIntRateTemp/100);
        decimal=decimal.setScale(4,BigDecimal.ROUND_UP);
        annualIntRate = decimal.doubleValue();
        accountGIC = new Account(fullNameTemp,accountNumberTemp,currentBalTemp,investmentPeriodTemp,annualIntRate);
        investmentPeriod = investmentPeriodTemp;
    }

    /**
     * Override the deposit method. always returns false since no transactions can be made
     * @param amount    Dummy value. Makes no difference
     * @return          always returns false
     */
    @Override
    public boolean deposit(double amount) {
       return false;
    }

    /**
     * Override the withdraw method. always returns false since no transactions can be made.
     * @param amount    Dummy value. Makes no difference
     * @return          always returns false
     */
    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    /**
     * Method to return the balance of the GIC account at the time of maturity.
     * @return      returns double value.
     */
    public double getBalanceAtMaturity()
    {
        double futureValue;
        futureValue = Math.pow(accountGIC.getBalance() * (1+getAnnualInterestRate()),getInvestmentPeriod());
        return futureValue;
    }

    /**
     * Method to return the gic account information
     * @return  returns the account object
     */
    public Account getAccountGIC()
    {
        return this.accountGIC;
    }

    /**
     * Returns a modified string with the data of the account object. Uses the toString method of the Account superclass
     * @return          return the modified string with the data from Account.toString() method.
     */
    public String toString()
    {
        String stringVal;
        stringVal ="Account Type: GIC"+ accountGIC.toString();
        return stringVal;
    }

    /**
     * Compares the object entered with the current object. Uses the equals method of the Account superclass
     * @param object    Object to be compared
     * @return          return either true or false if the condition is met for the Accounts.equal() method
     */
    public boolean equals(Object object)
    {
        boolean result = false;
        if(object instanceof GIC)
        {
            GIC object2 = (GIC) object;

            if(     (object2.getAccountGIC().equals(getAccountGIC())) &&
                    (object2.getAnnualInterestRate()==((GIC) object).getAnnualInterestRate()) &&
                    (object2.getInvestmentPeriod() == ((GIC) object).getInvestmentPeriod()))
            {
                result = true;
            }

        }
        return  result;
    }
}
