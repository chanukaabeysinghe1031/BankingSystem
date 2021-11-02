import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;  // Import the Scanner class
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class BankingSystem {
    public static void main(String[] args) {

        // Delete Account Details File
        File myObj = new File("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/BankAccountsDetails.txt");
        if (myObj.delete()) {
            System.out.println("Deleted the Bank Account Details file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }

        //Creating Files
        try {
            File myObj1 = new File("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/BankAccountsDetails.txt");
            File myObj2 = new File("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/accounts.txt");
            File myObj3 = new File("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/Withdrawals.txt");
            File myObj4 = new File("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/Deposits.txt");
            File myObj5 = new File("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/Transfers.txt");
            if (myObj1.createNewFile()) {
                System.out.println("Bank Account Details File created: " + myObj1.getName());
            } else {
                System.out.println("Bank Account Details File already exists.");
            }
            if (myObj2.createNewFile()) {
                System.out.println("Accounts File created: " + myObj2.getName());
            } else {
                System.out.println("Accounts File already exists.");
            }
            if (myObj3.createNewFile()) {
                System.out.println("Withdrawals File created: " + myObj3.getName());
            } else {
                System.out.println("Withdrawals File already exists.");
            }
            if (myObj4.createNewFile()) {
                System.out.println("Deposits File created: " + myObj4.getName());
            } else {
                System.out.println("Deposits File already exists.");
            }
            if (myObj5.createNewFile()) {
                System.out.println("Transfers File created: " + myObj5.getName());
            } else {
                System.out.println("Transfers File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/accounts.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            bankAccounts = (ArrayList<BankAccount>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("No previous bank accounts");
        } catch (IOException e) {
            System.out.println("No previous bank accounts");
        } catch (ClassNotFoundException e) {
            System.out.println("No previous bank accounts");
        }

        ;
        while (true){
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            int selection=0; // To store the menu selection of the user
            System.out.println();
            System.out.flush();
            System.out.println("************************************************");
            System.out.println("*************** Banking System *****************");
            System.out.println("******************** MENU **********************");
            System.out.println("1. New Account");
            System.out.println("2. Close Account");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Deposit Money");
            System.out.println("5. Request Balance");
            System.out.println("6. Transfer Money");
            System.out.println("7. Print Account");
            System.out.println("8. Exit");
            System.out.println("************************************************");
            System.out.println();

            boolean validSelection = false;
            while(!validSelection){
                try {
                    System.out.print("PLease Select the Menu Number : ");
                    selection = scanner.nextInt();
                    if(selection>=1&&selection<=8){
                        validSelection=true;
                    }
                }catch (InputMismatchException e){
                    scanner.next();
                }
            }
           switch (selection){
               case 1 :
                   // Get User Details
                   System.out.print("Enter First Name : ");
                   String firstName = scanner.next();
                   System.out.print("Enter Second Name : ");
                   String secondName = scanner.next();
                   boolean valid = false;
                   int birthDate=-1,birthMonth=-1,birthYear=-1;
                   while(!valid){
                       try{
                           System.out.print("Enter Birth Date : ");
                           birthDate = scanner.nextInt();
                           if(birthDate>=1&&birthDate<=31){
                               valid=true;
                           }
                       }catch (InputMismatchException e){
                           scanner.next();
                       }
                   }
                   valid=false;
                   while(!valid){
                       try{
                           System.out.print("Enter Birth Month : ");
                           birthMonth= scanner.nextInt();
                           if(birthMonth>=1&&birthMonth<=12){
                               valid=true;
                           }
                       }catch (InputMismatchException e){
                           scanner.next();
                       }
                   }
                   valid=false;
                   while(!valid){
                       try{
                           System.out.print("Enter Birth Year : ");
                           birthYear = scanner.nextInt();
                           if(birthYear>=1900&&birthYear<=2100){
                               valid=true;
                           }
                       }catch (InputMismatchException e){
                           scanner.next();
                       }
                   }

                   String nationalId = "";
                   valid=false;
                   while(!valid){
                       boolean validNationalId =true;
                       System.out.print("Enter National Id : ");
                       nationalId = scanner.next();
                       boolean existing = false;
                       for(int i=0;i<bankAccounts.size();i++){
                           if(bankAccounts.get(i).getNationalId().equals(nationalId)){
                               System.out.println("You have already have a bank account with this national id number");
                               existing=true;
                           }
                       }
                       if(nationalId.length()==10){
                           if(nationalId.charAt(9)=='v'||nationalId.charAt(9)=='V'){
                             for(int i=0;i<9;i++){
                                 if(!Character.isDigit(nationalId.charAt(i))){
                                     validNationalId=false;
                                 }
                             }
                           }else{
                               validNationalId=false;
                           }
                       }else{
                           validNationalId=false;
                       }

                       if(validNationalId&&!existing){
                           valid=true;
                       }
                   }

                   double startingBalance = 0;
                   valid=false;
                   while(!valid){
                       try{
                           System.out.print("Enter Starting Balance in LKR : ");
                           startingBalance = scanner.nextDouble();
                           if(startingBalance>0){
                               valid=true;
                           }
                       }catch (InputMismatchException e){
                           scanner.next();
                       }
                   }

                   // Create a new bank account
                   BankAccount newbankAccount = new BankAccount(firstName,secondName,birthDate,birthMonth,birthYear,nationalId,startingBalance);
                   System.out.println("Bank account was succresfully created.");
                   newbankAccount.printBankAccount();// display the details of the bank account
                   bankAccounts.add(newbankAccount); // Add the bank account to the list
                   break;

               case 2:
                   // Close the account
                   System.out.println("Please enter your bank account number to close : ");
                   String closingBankAccountNo = scanner.next();
                   boolean foundAccount = false;
                   for(int i=0;i<bankAccounts.size();i++){
                       if(closingBankAccountNo.equals(bankAccounts.get(i).getAccountNo())){
                           foundAccount=true;
                           bankAccounts.remove(i);// delete the bank account from the list
                       }
                   }
                   if(!foundAccount){
                       System.out.println("No account was found with the account number");
                   }else{
                       boolean notDeleted = true;
                       for(int i=0;i<bankAccounts.size();i++){
                           if(bankAccounts.get(i).equals(closingBankAccountNo)){
                               System.out.println();
                               notDeleted=true;
                           }
                       }
                       if(notDeleted==false){
                           System.out.println("Successfully closed the account");
                       }else{
                           System.out.println("Successfully closed the account");
                       }
                   }
                   break;

               case 3 :
                   // Withdraw money
                   System.out.print("Enter the account no : ");
                   String withdrawAccountNo = scanner.next();
                   double withdrawAmount=0;
                   valid=false;
                   while(!valid){
                       try{
                           System.out.print("Enter the amount to withdraw : ");
                           withdrawAmount = scanner.nextDouble();
                           if(withdrawAmount>0){
                               valid=true;
                           }
                       }catch (InputMismatchException e){
                           scanner.next();
                       }
                   }
                   boolean found = false;
                   for(int i=0;i<bankAccounts.size();i++){
                       if(bankAccounts.get(i).getAccountNo().equals(withdrawAccountNo)){
                           found=true;
                           if(withdrawAmount<=bankAccounts.get(i).getAccountBalance()){
                               double newAccountBal = bankAccounts.get(i).getAccountBalance()-withdrawAmount;
                               bankAccounts.get(i).setAccountBalance(newAccountBal);
                               System.out.println("Successfully Withdraw");
                               bankAccounts.get(i).printBankAccount();

                               try {
                                   FileWriter myWriter = new FileWriter("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/Withdrawals.txt",true);
                                   myWriter.write("Account No : "+withdrawAccountNo+"\n");
                                   myWriter.write("Withdrawal Amount : "+withdrawAmount+"\n");
                                   myWriter.write("Account Balance : "+bankAccounts.get(i).getAccountBalance()+"\n");
                                   myWriter.write("*****************************************************************\n");
                                   myWriter.close();
                                   System.out.println("Successfully wrote to the Withdrawals file.");
                               } catch (IOException e) {
                                   System.out.println("An error occurred.");
                                   e.printStackTrace();
                               }

                           }else{
                               System.out.println("Sorry not sufficient account balance!");
                           }
                       }
                   }
                   if(!found){
                       System.out.println("Sorry no account was found with the account number.");
                   }
                   break;
               case 4:
                   // Deposit money
                   System.out.print("Enter the account no : ");
                   String depositAccountNo = scanner.next();
                   double depositAmount=0;
                   valid=false;
                   while(!valid){
                       try{
                           System.out.print("Enter the amount to Deposit : ");
                           depositAmount = scanner.nextDouble();
                           if(depositAmount>0){
                               valid=true;
                           }
                       }catch (InputMismatchException e){
                           scanner.next();
                       }
                   }
                   found = false;
                   for(int i=0;i<bankAccounts.size();i++){
                       if(bankAccounts.get(i).getAccountNo().equals(depositAccountNo)){
                           double newAccountBal = bankAccounts.get(i).getAccountBalance()+depositAmount;
                           bankAccounts.get(i).setAccountBalance(newAccountBal);
                           System.out.println("Successfully Deposited");
                           bankAccounts.get(i).printBankAccount();
                           found=true;
                           try {
                               FileWriter myWriter = new FileWriter("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/Deposits.txt",true);
                               myWriter.write("Account No : "+depositAccountNo+"\n");
                               myWriter.write("Withdrawal Amount : "+depositAmount+"\n");
                               myWriter.write("Account Balance : "+bankAccounts.get(i).getAccountBalance()+"\n");
                               myWriter.write("*****************************************************************\n");
                               myWriter.close();
                               System.out.println("Successfully wrote to the Deposits file.");
                           } catch (IOException e) {
                               System.out.println("An error occurred.");
                               e.printStackTrace();
                           }


                       }
                   }
                   if(!found){
                       System.out.println("Sorry no account was found with the account number.");
                   }
                   break;

               case 5:
                   // Request balance
                   System.out.print("Enter the account no : ");
                   String requestBalanceAccountNo = scanner.next();
                   found = false;
                   for(int i=0;i<bankAccounts.size();i++){
                       if(bankAccounts.get(i).getAccountNo().equals(requestBalanceAccountNo)){
                          found=true;
                          System.out.println("Account balance is LKR "+bankAccounts.get(i).getAccountBalance());
                       }
                   }
                   if(!found){
                       System.out.println("Sorry no account was found with the account number.");
                   }
                   break;

               case 6:
                   // Transfer money
                   System.out.print("Enter the source account no : ");
                   String transferFromAccountNo = scanner.next();
                   System.out.print("Enter the target account no : ");
                   String transferToAccountNo = scanner.next();
                   double transferAmount = 0 ;

                   valid=false;
                   while(!valid){
                       try{
                           System.out.print("Enter the amount to transfer : ");
                           transferAmount = scanner.nextDouble();
                           if(transferAmount>0){
                               valid=true;
                           }
                       }catch (InputMismatchException e){
                           scanner.next();
                       }
                   }

                   boolean foundTarget=false,foundSource=false;
                   for(int i=0;i<bankAccounts.size();i++){
                       if(bankAccounts.get(i).getAccountNo().equals(transferFromAccountNo)){
                           if(transferAmount<=bankAccounts.get(i).getAccountBalance()){
                               foundSource=true;
                               double newTargetAccountBal =0;
                               for(int j=0;j<bankAccounts.size();j++){
                                   if(bankAccounts.get(j).getAccountNo().equals(transferToAccountNo)){
                                       double newAccountBal = bankAccounts.get(j).getAccountBalance()+transferAmount;
                                       bankAccounts.get(j).setAccountBalance(newAccountBal);
                                       System.out.println("Successfully received money from the account "+ transferFromAccountNo);
                                       bankAccounts.get(j).printBankAccount();
                                       foundTarget=true;
                                       newTargetAccountBal = newAccountBal;
                                   }
                               }

                               if(foundTarget){
                                   double newAccountBal = bankAccounts.get(i).getAccountBalance()-transferAmount;
                                   bankAccounts.get(i).setAccountBalance(newAccountBal);
                                   System.out.println("Successfully transferred money to account ");
                                   bankAccounts.get(i).printBankAccount();
                                   try {
                                       FileWriter myWriter = new FileWriter("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/Transfers.txt",true);
                                       myWriter.write("Account No Transfer From : "+transferFromAccountNo+"\n");
                                       myWriter.write("Account No Transfer To : "+transferToAccountNo+"\n");
                                       myWriter.write("Withdrawal Amount : "+transferAmount+"\n");
                                       myWriter.write("Account Balance of Sender : "+bankAccounts.get(i).getAccountBalance()+"\n");
                                       myWriter.write("Account Balance of Receiver : "+newTargetAccountBal+"\n");
                                       myWriter.write("*****************************************************************\n");
                                       myWriter.close();
                                       System.out.println("Successfully wrote to the Withdrawals file.");
                                   } catch (IOException e) {
                                       System.out.println("An error occurred.");
                                       e.printStackTrace();
                                   }
                               }else{
                                   System.out.println("Sorry no target account was found with the account number.");
                               }

                           }else{
                               System.out.println("Sorry not sufficient account balance!");
                           }
                       }
                   }
                   if(!foundSource){
                       System.out.println("Sorry no source account was found with the account number.");
                   }
                   break;
               case 7:
                   System.out.print("Enter the account no : ");
                   String requestPrintAccountNo = scanner.next();
                   found = false;
                   for(int i=0;i<bankAccounts.size();i++){
                       if(bankAccounts.get(i).getAccountNo().equals(requestPrintAccountNo)){
                           found=true;
                           bankAccounts.get(i).printBankAccount();
                       }
                   }
                   if(!found){
                       System.out.println("Sorry no account was found with the account number.");
                   }
                   break;
               case 8:
                   System.out.println("************************************************");
                   System.out.println("******* Exiting From The Banking System ********");
                   System.out.println("***************** Thank You ********************");
                   System.out.println("************************************************");
                   break;
               default:
                   System.out.println("Please Enter a Valid Number From 1 to 7 : ");
           }
           if(selection==8){
               break;
           }
        }
        try {
            FileOutputStream fos = new FileOutputStream("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/accounts.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(bankAccounts);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<bankAccounts.size();i++){
            try {
                BankAccount ba=bankAccounts.get(i);
                FileWriter myWriter = new FileWriter("/Users/chanukaabeysinghe/IdeaProjects/BankingSystem/src/BankAccountsDetails.txt",true);
                myWriter.write("Account No : "+ba.getAccountNo()+"\n");
                myWriter.write("First Name : "+ba.getFirstName()+"\n");
                myWriter.write("Second Name : "+ba.getSecondName()+"\n");
                myWriter.write("National Id : "+ba.getNationalId()+"\n");
                myWriter.write("Birthday : "+ba.getBirthDate()+"/"+ba.getBirthMonth()+"/"+ba.getBirthYear()+"\n");
                myWriter.write("Account Balance : "+ba.getAccountBalance()+"\n");
                myWriter.write("*****************************************************************\n");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
