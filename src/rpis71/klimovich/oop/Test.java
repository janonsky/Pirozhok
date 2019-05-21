package rpis71.klimovich.oop;
import rpis71.klimovich.oop.model.*;

import java.time.LocalDate;
import java.lang.Comparable;

public class Test {
    public static void main (String[] args) throws InvalidAccountNumberException {
        //System.out.println("Я сделяль!");
        //lab1tests();
        //lab2tests();
        //lab3tests();
        //lab4tests();
        //lab5tests();
        lab6tests();
    }

   /*public static void lab1tests()
    {
        DebitAccount userTwo=new DebitAccount("Brain1",1);
        DebitAccount userThree=new DebitAccount("Brain2",10);
        DebitAccount userFour=new DebitAccount("Brain3",100);
        DebitAccount userFife=new DebitAccount("Brain4",1000);
        DebitAccount[] debitAccounts={userTwo,userThree,userFour,userFife};
    }
    public static void lab2tests()
    {
        DebitAccount FirstAccount = new DebitAccount("214214", 2142);
        DebitAccount SecondAccount = new DebitAccount("686", 7867);
        System.out.println(FirstAccount.getBalance());
        System.out.println(SecondAccount.getNumber());
        DebitAccount[] debitAccounts = new DebitAccount[2];
        debitAccounts[0] = FirstAccount;
        debitAccounts[1] = SecondAccount;

    }
    public static void lab3tests()
    {
        DebitAccount userTwo=new DebitAccount("Brain1",1);
        DebitAccount userThree=new DebitAccount("Brain2",10);
        DebitAccount userFour=new DebitAccount("Brain3",100);
        DebitAccount userFife=new DebitAccount("Brain4",1000);
        DebitAccount[] accounts={userTwo,userThree,userFour,userFife};
        Individual individual=new Individual(accounts,"Alex",2);
        System.out.println(individual.getCreditScore());
        individual.addCreditScores(10);
        System.out.println(individual.getCreditScore());
        System.out.println(individual.getStatus());
        CreditAccount creditAccount=new CreditAccount("Tim",0,30);
        CreditAccount[] creditAccounts={creditAccount};
        Individual individual1=new Individual(creditAccounts,"Lina",0);
    }
    public static void lab4tests()
    {
        DebitAccount userTwo=new DebitAccount("Brain1",1);
        DebitAccount user=new DebitAccount("daubn",1);
        DebitAccount user1=new DebitAccount("123",1);
        DebitAccount userThree=new DebitAccount("Brain2",10);
        DebitAccount userFour=new DebitAccount("Brain3",100);
        DebitAccount userFife=new DebitAccount("Brain4",1000);
        DebitAccount[] accounts={userTwo,userThree,userFour,userFife};
        Individual individual=new Individual(accounts,"Alex",2);
        Entity entity=new Entity(accounts,"Alexandr",10);
       // System.out.println(entity.add(4,user));
        entity.add(5,user1);
        //System.out.println(individual.toString());
       // System.out.println(entity.toString());
        //System.out.println(entity.getNodeByNumber("123"));
        System.out.println(entity.toString());
    }*/
    public static void lab5tests() throws InvalidAccountNumberException {
        Account account=new CreditAccount("40123810712349876543",-123,0.2,LocalDate.now(),LocalDate.now().plusYears(1));
        DebitAccount account1=new DebitAccount("40123810712349876543",123,LocalDate.now(),LocalDate.now().plusYears(3));
        System.out.println(account1.getCreationDate());
        System.out.println(account1.getExpirationDate());
        System.out.println(account1.monthesQuantityBeforeExpiration());
        account1.setExpirationDate(LocalDate.now().plusYears(2));
        System.out.println(account1.getExpirationDate());
       // System.out.println(account.getCreationDate());
       // System.out.println(account.getExpirationDate());
       // System.out.println(account.monthesQuantityBeforeExpiration());
       // System.out.println(((CreditAccount) account).nextPaymentDate());
       // System.out.println(((CreditAccount) account).nextPaymentValue());
    }
    public static void lab6tests()
    {

    }
}

