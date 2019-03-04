package rpis71.klimovich.oop;
import rpis71.klimovich.oop.model.Account;
public class Test {
    public static void main (String[] args) {
        System.out.println("Я сделяль!");
    }

    public static void lab1tests()
    {
        Account userOne=new Account("chmo",100);
        Account userTwo=new Account("daun",1000);
        Account userThree=new Account("loh",10000);
        Account userFour=new Account("eblan",100000);
        Account userFife=new Account("nishiy",0);
        Account[] accounts=new Account[5];
        //Account[] accounts={userOne,userTwo,userThree,userFour,userFife};

    }
}

