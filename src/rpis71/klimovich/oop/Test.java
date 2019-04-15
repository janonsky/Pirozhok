package rpis71.klimovich.oop;
import  rpis71.klimovich.oop.model.Account;
import  rpis71.klimovich.oop.model.AccountManager;
import  rpis71.klimovich.oop.model.Individual;
import  rpis71.klimovich.oop.model.DebitAccount;

public class Test {
    public static void main (String[] args) {
        //System.out.println("Я сделяль!");
        lab1tests();
        lab2tests();
    }

    public static void lab1tests()
    {
        Account userOne=new Account("Alex",10);
        Account userTwo=new Account("Brain",1);
        Account userThree=new Account("Tim",10000);
        Account userFour=new Account("Eshkerell",100000);
        Account userFife=new Account("Dmitriy",0);

        Account[] accounts={userOne,userTwo,userThree,userFour,userFife};
        Individual individual=new Individual(accounts);
    }
    public static void lab2tests()
    {




    }
}

