package rpis71.klimovich.oop;
import rpis71.klimovich.oop.model.*;

public class Test {
    public static void main (String[] args) {
        //System.out.println("Я сделяль!");
        //lab1tests();
        //lab2tests();
        //lab3tests();
        lab4tests();
    }

    public static void lab1tests()
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
        //Individual individual=new Individual(accounts,"Alex",2);
        Entity entity=new Entity(accounts,"Alexandr",10);
        System.out.println(entity.add(4,user));
        entity.add(5,user1);
        for(var el : entity.getAccounts())
            System.out.println(el.getNumber());
        System.out.println(entity.toString());
        //System.out.println(entity.getNodeByNumber("123"));
    }
}

