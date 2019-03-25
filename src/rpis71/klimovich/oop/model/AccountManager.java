package rpis71.klimovich.oop.model;

public class AccountManager {
    private Individual[] individuals;
    private int size;
    private final static int ConstSIZE =16;

    public AccountManager ()
    {
        this(ConstSIZE);
    }
    public AccountManager(int size) {
        this.individuals = new Individual[size];
    }
    public AccountManager(Individual[] individuals) {
        Individual[] newIndividual;
        newIndividual = new Individual[individuals.length*2];
        System.arraycopy(individuals, 0, newIndividual, 0, individuals.length);
        this.size = individuals.length;
        this.individuals = newIndividual;
    }

    public boolean add(Individual individual) { //проверить либо size либо individuals.length
        if (size == individuals.length) {
            System.arraycopy(this.individuals, 0, this.individuals, 0, size * 2);
            size++;
        }
        for (int i = 0; i < individuals.length; i++) {
                individuals[i] = individual;
                size++;
                return true;

        }
        return false;
    }
    public boolean add(int index,Individual individual)
    {
        if (size == individuals.length) {
            System.arraycopy(this.individuals, 0, this.individuals, 0, size * 2);
            size++;
        }
       if (individuals[index]==null)
       { individuals[index]=individual;
       size++;
         return true;
       }
       return false;
    }
    public Individual get(int index)
    {
        return individuals[index];
    }
    public Individual set(int index, Individual individual)
   {
       this.individuals[index]=individual;
       return this.individuals[index];
   }
    public Individual remove(int index)
    {
        size--;
        System.arraycopy(individuals,index+1,individuals,index,size-1);
        individuals[size]=null;
        return individuals[index];
    }
    public int size()
    {
        return size;
    }
    public Individual[] getIndividuals()
    {
        Individual[] individuals1=new Individual[size];
        for(int i=0;i<size;i++)
                individuals1[i]=individuals[i];
        return individuals1;
    }
    public Individual[] sortedByBalanceIndividuals()
    {
        Individual[] sortArray = getIndividuals();
       Individual individual;
        for (int i=0;i<size-1;i++)
            for (int j=0;j<size-i;j++)
                if (sortArray[j].getAccounts()[i].getBalance()> sortArray[j+1].getAccounts()[i].getBalance()) {
                    individual = sortArray[j];
                    sortArray[j] = sortArray[j + 1];
                    sortArray[j + 1] = individual;
                }
        return sortArray;
    }
    public Account getAccount(String accountNumber)
    {
        Account account=new Account();
        for(int i=0;i<size;i++)
        {
            if(individuals[i].getAccounts()[i].getNumber().equals(accountNumber))
            {
                account.setNumber(individuals[i].get(i).getNumber());
                account.setBalance(individuals[i].get(i).getBalance());
            }
        }
        return account;
    }
    public Account removeAccount(String accountNumber)
    {
        Account account=new Account();
        for(int i=0;i<size;i++)
        {
            if(individuals[i].getAccounts()[i].getNumber().equals(accountNumber))
            {
                size--;
                System.arraycopy(individuals,i+1,individuals,i,size-1);
                individuals[size]=null;
                account.setNumber(individuals[i].get(i).getNumber());
                account.setBalance(individuals[i].get(i).getBalance());
            }
        }
        return account;
    }
    public Account setAccount(String accountNumber,Account account)
    {
        for(int i=0;i<size;i++)
        {
            if(individuals[i].getAccounts()[i].getNumber().equals(accountNumber))
            {
                account.setNumber(individuals[i].getAccounts()[i].getNumber());
                account.setBalance(individuals[i].getAccounts()[i].getBalance());
            }
        }
        return account;
    }
}
