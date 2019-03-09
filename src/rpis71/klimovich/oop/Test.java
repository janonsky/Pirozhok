package rpis71.klimovich.oop;
import rpis71.klimovich.oop.model.Account;
import rpis71.klimovich.oop.model.Individual;

public class Test {
    public static void main (String[] args) {
        //System.out.println("Я сделяль!");
        lab1tests();
    }

    public static void lab1tests()
    {
        Account userOne=new Account("chmo",100);
        Account userTwo=new Account("daun",1000);
        Account userThree=new Account("loh",10000);
        Account userFour=new Account("eblan",100000);
        Account userFife=new Account("nishiy",0);
        Account user=new Account("dalbaeb",10);
        //Account[] accounts=new Account[5];
        Account[] accounts={userOne,userTwo,userThree,userFour,userFife};
        Individual ind=new Individual(accounts);
       System.out.println(ind.add(5,user));
    }
}

