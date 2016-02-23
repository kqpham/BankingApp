package seneca.btp400;

import ontario.accounts.Account;
import ontario.accounts.Chequing;
import ontario.accounts.GIC;
import ontario.accounts.Savings;
import ontario.business.Bank;

import java.io.*;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Faiq on 2/17/2016.
 * -----------------------------
 * @author Faiq Malik - 063-430-128
 * @author Kevin Pham - 053-284-139
 */

public class BankingApp {
	
    static String[] accountValues;
    static String stringCondition;
    static Bank userBank;
      public static void main(String[] args)
      {
           boolean condition=false;
          Scanner input = new Scanner(System.in);
          System.out.print("Please enter the name of the Bank: ");
          String bankName = input.nextLine();
          if(!bankName.isEmpty()) {
              userBank = new Bank(bankName);
          }
          else
          {
              userBank=new Bank();
          }
          loadBank(userBank);
           while (!condition) {

               //Scanner name = new Scanner(System.in);
               displayMenu(userBank.getBankName());
              String inputVal = input.nextLine();
               switch(Integer.parseInt(inputVal)) {
                   //add an account
                   case 1:
                       System.out.println("Please enter information (e.g. SAV, name, account number, balance, interest rate) at one line \n Values: ");
                       String information = input.nextLine();
                       stringCondition = getDataValues("add,"+information);
                       String[] data = accountValues;
                       accountModifier(data,userBank);
                       break;
                   //remove an account
                   case 2:
                       System.out.print("Please enter account number to remove (Enter 'DIS' to display all accounts under " + userBank.getBankName()+" ) \nAccount Number: ");
                       String tempString = input.nextLine();
                       Account[] allAccounts = userBank.getAllAccounts();
                       if(tempString.toLowerCase().equals("dis"))
                       {
                           for (int i =0;i<allAccounts.length;i++)
                           {
                              System.out.print( allAccounts[i].toString());
                           }
                       }
                       else if(tempString.isEmpty())
                       {
                           System.out.println("Entry empty. Please try again.");
                       }
                       else
                       {
                          userBank.removeAccount(tempString);
                       }
                       break;
                   //search by account balance
                   case 3:
                       System.out.print("Enter Account balance to search: ");
                       try {
                           Double tempBal = input.nextDouble();
                           if(tempBal<0 || tempBal==0)
                           {
                               System.out.println("Negative or Zero value entered. Please retry.");
                           }
                           else
                           {
                               Account[] accountArray = userBank.searchByBalance(tempBal);
                               for (Account anAccountArray : accountArray) {
                                   displayAccount(anAccountArray);
                               }
                           }
                       }
                       catch (InputMismatchException exception)
                       {
                           System.out.println("Following entry is not a number. Please try again.");
                       }
                       break;
                   //search by name
                   case 4:
                       System.out.print("Enter Account Name to search: ");
                       try {
                           String tempName = input.nextLine();
                           if(tempName.isEmpty())
                           {
                               System.out.println("Entry empty. Please retry.");
                           }
                           else
                           {
                               Account[] accountArray = userBank.searchByAccountName(tempName);
                               for (Account anAccountArray : accountArray) {
                                   displayAccount(anAccountArray);
                               }
                           }
                       }
                       catch (InputMismatchException exception)
                       {
                           System.out.println("Following entry is not a name. Please try again.");
                       }
                       break;
                   case 5:
                       condition = true;
                       break;
                   default:
                       System.out.print("Incorrect entry. Please input number between 1-5 and retry\n");
                       break;
               }
           }
          System.out.println("Thank You!");
          PrintStream out = null;
          try {
              out = new PrintStream(new FileOutputStream("output.txt"),true);
              out.println(userBank.toString());
              Account[] accounts = userBank.getAllAccounts();
              for (int i =0 ; i<accounts.length;i++)
              {
                  out.println(accounts[i].toString());
              }

          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
          System.setOut(out);
          if (out != null) {
              out.close();
          }


      }

    /**
     * This method takes in an array of string data to be analyzed in the case statements
     *
     * @param data  Array of String data with a varied max size of 6 and min size of 5
     * @param bank  Bank object to be used to add data accordingly.
     */
    public static void accountModifier(String[] data,Bank bank)
    {

        switch(data[1].toLowerCase())
        {
            case "sav":
                if(!(data.length -2 < 5))
                {
                    if(data[2].isEmpty() || data[3].isEmpty() ||
                            Double.parseDouble(data[4].trim()) <0 || Double.parseDouble(data[5].trim())<0 ||
                            Double.parseDouble(data[4].trim()) ==0 || Double.parseDouble(data[5].trim())==0)
                    {
                        System.out.println("One of the entered values are incorrect or incomplete. Please retry. \n" +
                                "Format: name, account number, balance, interest rate");
                    }
                    else
                    {
                        if(stringCondition=="add") {
                            Savings savAcc = new Savings(data[2].trim(), data[3].trim(), Double.parseDouble(data[4].trim()), Double.parseDouble(data[5].trim()));
                            bank.addAccount(savAcc.getSavingsAccount());
                        }
                        if(stringCondition=="remove")
                        {
                            bank.removeAccount(data[3].trim());
                        }
                    }
                }
                else
                {
                    System.out.println("One of the entered values are missing. Please retry.\n"+
                            "Format: name, account number, balance, interest rate");
                }
                break;
            case "chq":
                if(!(data.length -2 < 6))
                {
                    if(
                            data[2].isEmpty() || data[3].isEmpty() ||
                            Double.parseDouble(data[4].trim()) <0 || Double.parseDouble(data[5].trim())<0 ||
                            Integer.parseInt(data[6].trim())<0 || Integer.parseInt(data[6].trim())==0|| Double.parseDouble(data[4].trim()) ==0 ||
                            Double.parseDouble(data[5].trim())==0)
                    {
                        System.out.println("One of the entered values are incorrect or incomplete. Please retry.\n" +
                                "Format: name, account number, current balance, service charge, max transactions");
                    }
                    else
                    {
                        Chequing chqAcc = new Chequing(data[2].trim(),data[3].trim(),Double.parseDouble(data[4]),Double.parseDouble(data[5]),Integer.parseInt(data[6]));
                        bank.addAccount(chqAcc.getChequingAccount());
                    }
                }
                else
                {
                    System.out.println("One of the entered values are missing. Please retry.\n" +
                            "Format: name, account number, current balance, service charge, max transactions");
                }
                break;
            case "gic":
                if(!(data.length -2 < 6))
                {
                    if(
                            data[2].isEmpty() || data[3].isEmpty() ||
                                    Double.parseDouble(data[4].trim()) <0 || Integer.parseInt(data[5].trim())<0 ||
                                    Double.parseDouble(data[6].trim())<0 ||
                                    Double.parseDouble(data[4].trim()) ==0 || Integer.parseInt(data[5].trim())==0 ||
                                    Double.parseDouble(data[6].trim())==0)
                    {
                        System.out.println("One of the entered values are incorrect or incomplete. Please retry.\n" +
                                "Format: name, account number, current balance, investment period in months,annual interest rate(in %)");
                    }
                    else
                    {
                        GIC gicAcc = new GIC(data[2].trim(),data[3].trim(),Double.parseDouble(data[4].trim()),Integer.parseInt(data[5].trim()),Double.parseDouble(data[6].trim()));
                        bank.addAccount(gicAcc.getAccountGIC());
                    }
                }
                else
                {
                    System.out.println("One of the entered values are missing. Please retry.\n" +
                            "Format: name, account number, current balance,investment peroid in months, annual interest rate(in %)");
                }
                break;
            default:
                System.out.println("Entered account type does not exist. Please use 'SAV' or 'CHQ' or 'GIC' ");
        }
    }

    /**
     * This method displays the menu options with the name of the bank entered by user
     * @param bankName      Bank name to be used. Default bank name used if empty.
     */
    static void displayMenu(String bankName)
    {
        System.out.println("\nWelcome to "+bankName +" Banking!\n" +
                "1. Add an account.\n" +
                "2. Remove an account.\n" +
                "3. Search by account balance.\n" +
                "4. Search by account name\n" +
                "5. Exit\n");
        System.out.print("Please enter your choice: ");
    }

    /**
     * Gets a string from the main and splits it into the static string array accountValues.
     * It takes the first value of the string array and returns it to be used as a condition
     * for accountModifier.
     * @param message   string message to be split
     * @return          returns string
     */
    static String getDataValues(String message)
    {
        String s;
        accountValues = message.split(",");
        s=accountValues[0];
        return s;
    }

    /**
     *
     * @param account
     */
    static void displayAccount(Account account) {
       System.out.print(account.toString());
    }
    /**
     * This method creates two Savings accounts, two Chequing accounts
     * and two GIC accounts with the bank.
     * @param bank      Bank object name to be created.
     */
    static void loadBank(Bank bank)
    {
        //Savings Accounts
        Savings savingsOne = new Savings("Kevin Pham","123456",356.00,1.25);
        Savings savingsTwo = new Savings("Soutrik Baruah","23697",569.25,1.89);

        //Chequing Accounts
        Chequing chequingOne = new Chequing("Kevin Pham","123457",98.96,0.20,25);
        Chequing chequingTwo = new Chequing("Faiq Malik","457892",239.89,0.15,24);

        //GIC Accounts
        GIC gicOne = new GIC("Faiq Malik","456987",2563.56,12,3.99);
        GIC gicTwo = new GIC("Isaac Clarke","987632",3697.89,36,3.99);

        /*
        Problem faced here
        Account addition object does not enter properly in Bank class. Additional data is omitted only
        Account data is present and seen even though the other data is there but not accessible.
        One of two solutions possible. Both require additional method addition in Bank
        Solution one: (Long) using polymorphism. this makes more sense as more data will be preserved
         1. Make one addAccount(Savings account)
         2. Make one addAccount(Chequing account)
         3. Make one addAccount(GIC account)

         Also some more public variables will have to be added into the bank class to accommodate data
         preservation otherwise only account information will be saved and nothing else will show.

        Solution two: (short) one method addition in each of the class Chequing, Savings and GIC
         1. Make one method that returns an Account object with the name, account number and balance.
            for addition into the bank but this will probably lose some data as data in the object
            being passed is a string for some reason.


        Note: Might be possible that both solutions are needed to be implemented to make this program work.
        */

        //Adding the accounts
        bank.addAccount(savingsOne.getSavingsAccount());
        bank.addAccount(savingsTwo.getSavingsAccount());
        bank.addAccount(chequingOne.getChequingAccount());
        bank.addAccount(chequingTwo.getChequingAccount());
        bank.addAccount(gicOne.getAccountGIC());
        bank.addAccount(gicTwo.getAccountGIC());

    }


}
