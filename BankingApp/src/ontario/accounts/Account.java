package ontario.accounts;

import java.math.BigDecimal;

/**
 * An Account class which creates accounts with the following methods
 *
 * setFullName:  Set full name of the account holder
 * setAccountNumber : sets the account number
 * setBalance: sets the balance in the account
 *
 * getFullNam: gets the name for the account
 * getAccountNumber: gets the account number of the account
 * getBalance: gets the account balance of the account object
 * @author Faiq
 *
 */
public class Account {

    private String fullName;
    private String accountNumber;
    private double balance;
    private double serviceCharge;
    private double annualInterestRate;
    private int maxTransactions;
    private int investmentPeriod;


    /**
     * Starter Account constructor
     * @param f_name    name for the account object
     * @param acc_num   account number of the account object
     * @param bal       the balance for the account object
     */
    public Account(String f_name, String acc_num, double bal) {
        setFullName(f_name);
        setAccountNumber(acc_num);
        setBalance(bal);

    }

    /**
     * account for chequing type
     * @param f_name                name for the account object
     * @param acc_num               account number of the account object
     * @param bal                   the balance for the account object
     * @param serviceChargeTemp     service charge to set
     * @param maxTransactionsTemp   max transactions to set
     */
    public Account(String f_name, String acc_num, double bal, double serviceChargeTemp, int maxTransactionsTemp) {
        setFullName(f_name);
        setAccountNumber(acc_num);
        setBalance(bal);
        setServiceCharge(serviceChargeTemp);
        setMaxTransactions(maxTransactionsTemp);

    }

    /**
     * account for savings type
     * @param f_name                    name for the account object
     * @param acc_num                   account number of the account object
     * @param bal                       the balance for the account object
     * @param annualInterestRateTemp    annual interest rate to set
     */
    public Account(String f_name, String acc_num, double bal, double annualInterestRateTemp) {
        setFullName(f_name);
        setAccountNumber(acc_num);
        setBalance(bal);
        setAnnualInterestRate(annualInterestRateTemp);

    }

    /**
     * account for GIC type
     * @param f_name                    name for the account object
     * @param acc_num                   account number of the account object
     * @param bal                       the balance for the account object
     * @param investmentPeriodTemp      investment period to set
     * @param annualInterestRateTemp    annual interest rate to set
     */
    public Account(String f_name, String acc_num, double bal, int investmentPeriodTemp, double annualInterestRateTemp) {
        setFullName(f_name);
        setAccountNumber(acc_num);
        setBalance(bal);
        setInvestmentPeriod(investmentPeriodTemp);
        setAnnualInterestRate(annualInterestRateTemp);

    }

    /**
     * Default empty constructor
     */
    public Account() {
        this("", "", 0.0);
    }

    /**
     * name setter for the account object
     * @param name  name to be set
     */
    public void setFullName(String name) {
        this.fullName = name;
    }

    /**
     * account number setter for the account object
     * @param num   account number to set
     */
    public void setAccountNumber(String num) {
        this.accountNumber = num;
    }

    /**
     * account balance setter for the account object
     * @param bal   balance to be entered
     */
    public void setBalance(double bal) {
        this.balance = bal;
    }

    /**
     * account name getter for the current account object
     * @return      return current full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * account number getter for the current account object
     * @return      return current account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * account balance getter for the current account object
     * @return      return current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * compares if an object is equal to the current object or not
     * @param r     object to be compared
     * @return      boolean return value
     */
    public boolean equals(Object r) {
        boolean result =false;
        if(r instanceof Account)
        {
            Account rTemp = (Account) r;
            if((rTemp.fullName==fullName) && (rTemp.accountNumber == accountNumber) && (rTemp.balance == balance))
            {
                result =true;
            }
        }
            return result;
    }

    /**
     * Creates a string with the values of the current account object to return
     * @return      return string with the current account data
     */
    public String toString() {
        String s;
        s = "\nFull Name: " + getFullName() + "\n" +
                "Account Number: " + getAccountNumber() + "\n" +
                "Balance: " + getBalance() + "\n";

        if(getServiceCharge()!=0)
        {
            s = s + "Service Charge: " + getServiceCharge() + "\n";
        }
        if(getMaxTransactions()!=0)
        {
            s = s + "Max Transactions: " + getMaxTransactions() + "\n";
        }
        if(getInvestmentPeriod()!=0)
        {
            s = s + "Investment Period: " + getInvestmentPeriod() + "\n";
        }
        if(getAnnualInterestRate()!=0)
        {
            s = s + "Annual Interest Rate: " + getAnnualInterestRate() + "\n";
        }

        return s;
    }

    /**
     * This method modifies the amount of the account object for an increase
     * @param amount    Enter amount to be added
     * @return          Return either true or false based on the conditions
     */
    public boolean deposit(double amount)
    {
        boolean condition;
        if(amount < 0 || amount == 0 )
        {
            condition =false;
        }
        else
        {
            BigDecimal balance = new BigDecimal(getBalance());
            BigDecimal transaction = new BigDecimal(amount);
            BigDecimal newBal = balance.add(transaction);
            setBalance(newBal.doubleValue());
            condition = true;
        }

        return condition;

    }

    /**
     * This method modifies the amount of the account object for a decrease
     * @param amount    Enter amount to be deducted
     * @return          Return either true or false based on the conditions
     */
    public boolean withdraw(double amount)
    {
        boolean condition;
        if(amount < 0 || amount==0||getBalance()<amount)
        {
            condition = false;
        }
        else
        {
            BigDecimal balance = new BigDecimal(getBalance());
            BigDecimal transaction = new BigDecimal(amount);
            BigDecimal newBal = balance.subtract(transaction);
            setBalance(newBal.doubleValue());
            condition=true;
        }
        return condition;
    }


    /**
     * sets the service charge
     * @param serviceCharge service charge to set of type double
     */
    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }


    /**
     * sets the max transactions
     * @param maxTransactions   max transactions to set of type int
     */
    public void setMaxTransactions(int maxTransactions) {
        this.maxTransactions = maxTransactions;
    }

    /**
     * sets annual interest rate
     * @param annualInterestRate    interest rate to set of type double
     */
    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    /**
     * sets investment period in months
     * @param investmentPeriod  investment period to set of type int
     */
    public void setInvestmentPeriod(int investmentPeriod) {
        this.investmentPeriod = investmentPeriod;
    }

    /**
     * returns the service charge
     * @return  returns value of type double
     */
    public double getServiceCharge() {
        return serviceCharge;
    }

    /**
     * returns the annual interst rate
     * @return  returns value of type double
     */
    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    /**
     * returns the max transactions
     * @return  returns value of type int
     */
    public int getMaxTransactions() {
        return maxTransactions;
    }

    /**
     * returns the investment period
     * @return  returns value of type int
     */
    public int getInvestmentPeriod() {
        return investmentPeriod;
    }
}
