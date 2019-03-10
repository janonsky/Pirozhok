package rpis71.klimovich.oop;
import rpis71.klimovich.oop.model.Account;
import rpis71.klimovich.oop.model.AccountManager;
import rpis71.klimovich.oop.model.Individual;

public class Test {
    public static void main (String[] args) {
        //System.out.println("Я сделяль!");
        lab1tests();
    }

    public static void lab1tests()
    {
        Account userOne=new Account("chmo",10);
        Account userTwo=new Account("daun",1);
        Account userThree=new Account("loh",10000);
        Account userFour=new Account("eblan",100000);
        Account userFife=new Account("nishiy",0);

        Account[] accounts={userOne,userTwo,userThree,userFour,userFife};
        Individual individual=new Individual(accounts);//?????

        Account user1=new Account("dima",10);
        Account user2=new Account("masha",1);
        Account user3=new Account("sergey",10000);
        Account user4=new Account("sasha",100000);
        Account user5=new Account("dasha",0);

        Account[] accounts1={user1,user2,user3,user4,user5};
        Individual individual1=new Individual(accounts1);

        Individual[] individuals={individual};//???
        AccountManager accountManager=new AccountManager(individuals);
        individual.add(userOne);
        individual.add(userTwo);
        individual.add(3,userThree);
        individual.add(4,userFour);
        accountManager.add(individual);
        for (var el:accountManager.get(0).getAccounts())
        {
            if(el!=null)
            System.out.println(el.getNumber());
        }
       /* System.out.println(individual.add(userOne));
        System.out.println(individual.add(userTwo));
        System.out.println(individual.add(3,userThree));
        System.out.println(individual.add(4,userFour));
        System.out.println(individual.get("das"));
        System.out.println(individual.hasAccount("soseh"));
        System.out.println(individual.remove(3).getNumber());
        System.out.println(individual.remove("chmo").getNumber());
        System.out.println(individual.size());
        for(var el:individual.getAccounts())
        {
                    System.out.println(el.getNumber());
        }
        System.out.println(individual.totalBalance());*/

    }
}

