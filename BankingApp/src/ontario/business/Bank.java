package ontario.business;

//import java.util.*;

import ontario.accounts.Account;

import java.util.ArrayList;

/**
 *Simple Bank program which uses array list to populate the data
 * Most of the previous code has been kept intact but one method needed slight modification
 * due to the use of array list.
 *
 * Method which required modification was addAccount as the previous for loop with the pre set array
 * was not allowing the addition of the object into the acc array list.
 *
 * After modifying the addAccount while keeping the rest of the code intact has not changed the
 * final output of the program.
 *
 */


public class Bank {

      private String name;
      private ArrayList <Account> acc = new <Account> ArrayList();

    /**
     * Description of Bank() default Constructor
     *
     * it uses no parameters as it is a constructor
     */
    public Bank() {
        this.name = "Seneca@York";
    }

    /**
     * Description of Bank(String nameTemp) Constructor with parameters
     *
     * @param nameTemp      Send in the name of the Bank Object to be created
     */
    public Bank(String nameTemp) {
        this.name = nameTemp;
        //numAcc = numAccTemp;
        //acc = new Account[numAccTemp];
    }

    /**
     *Description of equals(Object val)
     *
     * @param val           send in object to be compared
     *
     * @return         returns bool value of true or false.
     */
    public boolean equals(Object val) {
        Boolean result=false;
        if(val instanceof Bank) {
            Bank valTemp = (Bank) val;
            if((valTemp.name == name) && (valTemp.acc.size()==acc.size())) {
                result = true;
            }

        }
        return result;
    }

    /**
     *Description of toString()
     *
     * @return          returns the string with the name of the Bank object and the size of the number
     *                  of accounts in it.
     */
    public String toString() {
        String s;

        s = "Bank Name         : " + getBankName()+
                "\nNumber of Accounts: " + acc.size() + "\n";

        return s;
    }
    /**
     *Description of addAccount(Account obj)
     *
     * @param obj       insert the account object to be created
     *
     * @return          returns bool value 'false' if the account number already exists
     *                  returns bool value 'true' if the account does not exist
     */
    public Boolean addAccount(Account obj) {
        Boolean bool = true;
        //return false if the obj is null or the account number
        //already exists
        if (obj == null) {
            bool = false;
        }

        if (obj != null) {
            for(int i=0;i<acc.size();i++) {
                if (acc.get(i).getAccountNumber().equals(obj.getAccountNumber())) {
                    bool = false;
                    System.out.println("Account already exists.\n");
                }
            }
            if (bool) {
                acc.add(obj);
                System.out.println("Account addition successful.\n");
            }
        }
        return bool;
    }


    /**
     * description of removeAccount(String numAccTemp)
     *
     * remove account with matching account number. display the account that matched and set to null.
     *
     * @param numAccTemp        Provide the string number to match into the existing bank account numbers
     *
     * @return                  returns string of the account number whcih was removed or was not found.
     */
    public Account[] removeAccount(String numAccTemp) {
        boolean check=false;
        Account[] arrTemp = null;
        if (numAccTemp == null) {
            return null;
        } else {
            for (int i = 0; i < acc.size(); i++) {
                if (acc.get(i) != null) {
                    if (numAccTemp.equals(acc.get(i).getAccountNumber())) {
                        acc.remove(i);
                        check=true;
                    }
                }
            }

        }
        if(check==false)
        {
            System.out.print("\nAccount: " + numAccTemp + " does not exist");
        }
        else
        {
            arrTemp = new Account[acc.size()];
            for (int i = 0; i < acc.size(); i++)
            {
                if (acc.get(i) != null)
                {
                    arrTemp[i] = acc.get(i);
                }
            }
        }
        return arrTemp;
    }

    /**
     * Description of searchByBalance(double bal)
     *
     * @param bal       send the balance parameter to find the accounts with the sent bal value
     * @return          Returns an array of Account objects that match the balance parameter
     */
    public Account[] searchByBalance(double bal) {

        Account[] arr, arrTemp = null;
        int k = 0, j = 0;
        Boolean condition = false;
        arrTemp = new Account[acc.size()];
        if (bal != 0) {
            for (int i = 0; i < acc.size(); i++) {
                if (acc.get(i) != null) {
                    if (acc.get(i).getBalance() == bal) {
                        arrTemp[i] = acc.get(i);
                        condition = true;
                        j++;
                    }
                }
            }
        }
        if (condition == true) {
            arr = new Account[j];
            System.out.println("\nAccounts Found:\n");
            for (int i = 0; i < acc.size(); i++) {
                if (arrTemp[i] != null) {
                    arr[k] = arrTemp[i];
                    System.out.println(arr[k].toString());
                    k++;
                }
            }
            //arr_v.clear();     //remove all elements and clear vector array.
            return arr;
        } else {
            System.out.println("Account(s) with balance: " + bal + " not found.");
            return null;
        }

    }

    /**
     *  Description of getAllAccounts()
     *
     * @return          Returns an array of all accounts currently present in the Bank object
     */
    public Account[] getAllAccounts()
    {
        Account[] arrTempVal;
        arrTempVal = new Account[acc.size()];
        acc.toArray(arrTempVal);
        return arrTempVal;
    }

    /**
     * Searches the account objects Name property and returns an array of accounts that match the string name
     * @param accountName   String to be matched
     * @return              returns an array of Account objects
     */
    public Account[] searchByAccountName(String accountName)
    {
        Account[] arrayTemp;
        ArrayList <Account> listTemp = new ArrayList<>();

        for (int i=0;i<acc.size();i++)
        {
            if(acc.get(i).getFullName() == accountName)
            {
                listTemp.add(acc.get(i));
            }
        }
        arrayTemp = new Account[listTemp.size()];
        acc.toArray(arrayTemp);
        return arrayTemp;
    }

    /**
     * Returns the name of the current Bank object
     * @return  Returns bank name string.
     */
    public String getBankName()
    {
       return this.name;
    }

}
