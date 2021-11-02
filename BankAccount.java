import java.io.FileWriter;
import java.io.Serializable;
import java.lang.Math;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class BankAccount implements Serializable {
    private String accountNo;
    private String firstName;
    private String secondName;
    private String nationalId;
    private int birthDate;
    private int birthMonth;
    private int birthYear;
    private double accountBalance;

    BankAccount(String firstName, String secondName,int birthDate,int birthMonth,int birthYear,String nationalId,double startingBalance){
        this.firstName = firstName;
        this.secondName = secondName;
        this.nationalId = nationalId;
        this.birthDate = birthDate;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.accountBalance = startingBalance;

        //create a random number
        this.accountNo= birthDate+""+(int)(Math.random()*(1000+1)+0)+""+birthYear;
//        this.accountNo = rand+""+birthDate+birthYear+birthYear;
    }

    public String getAccountNo(){return this.accountNo;}
    public double getAccountBalance(){return this.accountBalance;}
    public String getFirstName(){return this.firstName;}
    public String getSecondName(){return this.secondName;}
    public int getBirthYear(){return this.birthYear;}
    public int getBirthMonth(){return this.birthMonth;}
    public int getBirthDate(){return this.birthMonth;}
    public String getNationalId(){return this.nationalId;}

    public void setAccountBalance(double bal){this.accountBalance = bal;}

    public void printBankAccount(){
        System.out.println("************************************************");
        System.out.print("Account Number : ");
        System.out.println(this.accountNo);
        System.out.print("Full Name : ");
        System.out.println(this.firstName+" "+this.secondName);
        System.out.print("National Id : ");
        System.out.println(this.nationalId);
        System.out.print("Account Balance : ");
        System.out.println(this.accountBalance);
        System.out.println("************************************************");

    }
}