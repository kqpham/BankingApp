package ontario.accounts;

import java.text.DecimalFormat;

/**
 * Created by Faiq on 2/13/2016.
 */
public class Chequing extends Account {

    private double serviceCharge;
    private int maxTransactions;
    private int tempMaxTransactions;
    private int counter =0;
    private double[] transactions;
    private Account chequingAccount;

    /**
     * Creates a Chequing Account object of the superclass Account and uses the constructor from the superclass
     *
     * @param fullNameTemp          Full name to be set for the Chequing Account. (Type String)
     * @param accountNumberTemp     Account number to be set for the Chequing Account. (Type String)
     * @param currentBalTemp        Current balance to be set for the Chequing Account. (Type Double)
     * @param serviceChargeTemp     Service charge to be set for the Chequing Account. (Type Double)
     * @param maxTransactionsTemp   Max transactions to be set for the Chequing Account. (Type Int)
     */
    public Chequing(String fullNameTemp, String accountNumberTemp,double currentBalTemp, double serviceChargeTemp,int maxTransactionsTemp)
    {
        //igDecimal decimal = new BigDecimal(serviceChargeTemp);
        DecimalFormat df = new DecimalFormat(".##");
        serviceCharge=Double.valueOf(df.format(serviceChargeTemp));
        chequingAccount = new Account(fullNameTemp,accountNumberTemp,currentBalTemp,serviceCharge,maxTransactionsTemp);
        maxTransactions =maxTransactionsTemp;
        tempMaxTransactions = maxTransactionsTemp;
        transactions = new double[maxTransactions];
    }

    /**
     * Default constructor for the Chequing account object. It uses the default constructor of the super
     * class Account and initializes service charge to $0.25
     */
    public Chequing()
    {
        chequingAccount = new Account();
        serviceCharge = 0.25;
    }

    /**
     * Updates the balance of the chequing account with the amount entered and increases it along with a service charge
     * @param amount    Enter amount to be added
     * @return          return true or false based on the Superclass Account.deposit() conditions
     */
    @Override
    public boolean deposit(double amount) {
        if(amount<0 || amount==0)
        {
           return false;
        }
        else
        {
            transactions[counter] = ((amount-serviceCharge));
            counter++;
            maxTransactions--;
            return super.deposit(amount - serviceCharge);
        }
    }

    /**
     * Updates the balance of the chequing account with the amount entered and deducts it along with a service charge
     * @param amount    Enter amount to be deducted
     * @return          return true or false based on the Superclass Account.withdraw() conditions
     */
    @Override
    public boolean withdraw(double amount) {
        if(amount<0 || amount==0 || maxTransactions < 1)
        {
            return false;
        }
        else
        {   transactions[counter]=(-1*(amount-serviceCharge));
            counter++;
            maxTransactions--;
            return super.withdraw(amount-serviceCharge);
        }
    }

    /**
     * Compares the object entered with the current object. Uses the equals method of the Account superclass
     * @param object    Object to be compared
     * @return          return either true or false if the condition is met for the Accounts.equal() method
     */
    public boolean equals(Object object)
    {
        boolean result = false;
        if(object instanceof Chequing)
        {
            Chequing object2 = (Chequing) object;

            if(     (object2.getChequingAccount().equals(getChequingAccount())) &&
                    (object2.getServiceCharge()==((Chequing) object).getServiceCharge()) &&
                    (object2.getMaxTransactions()==((Chequing) object).getMaxTransactions()))
            {
                result = true;
            }

        }
        return  result;
    }

    /**
     * Returns a modified string containing the data for the chequing account including all the
     * transactions, number of transactions left and total service charge
     * @return      returns string with the data of the chequing account
     */

    public String toString()
    {
        String stringVal,transactionsTempString="";
        for (int i=0;i<maxTransactions;i++)
        {
            transactionsTempString = transactions[i] + ",";
        }

        stringVal = "Account Type: CHEQUING \n"+
                    "Total service charge: $"+(tempMaxTransactions-maxTransactions)*serviceCharge+ "\n"+
                    "Max number of transactions: "+maxTransactions+"\n"+
                    "List of transactions: "+ transactionsTempString +"\n"+
                    chequingAccount.toString();
        return stringVal;
    }

    /**
     * Method to return the chequing account information
     * @return  returns account object.(Type Account)
     */
    public Account getChequingAccount()
    {
        return this.chequingAccount;
    }

}
