package rpis71.klimovich.oop.model;

public class AccountManager {
    private Individual[] individuals;
    private int size;
    private final static int CAPACITY_SIZE =16;

    public AccountManager ()
    {
        this(CAPACITY_SIZE);
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
    public Individual[] increaseArray(int size)
    {
        if(size==individuals.length)
        {
           Individual[] newIndividual=new Individual[size*2];
            System.arraycopy(individuals,0,newIndividual,0,size);
            return newIndividual;
        }
        return individuals;
    }
    public boolean add(Individual individual) {
        this.individuals=increaseArray(size);
        individuals[size]=individual;
        size++;
        return true;
    }
    public boolean add(int index,Individual individual)
    {
        this.individuals=increaseArray(size);
        for(int i=index;i<size;i++)
        {
            individuals[i+1]=individuals[i];
            individuals[index]= individual;
            size++;
        }
        return true;
    }
    public Individual get(int index)
    {
        return individuals[index];
    }
    public Individual set(int index, Individual individual)
   {
       Individual individual1=new Individual();
       individual1.get(index).setBalance(individual.get(index).getBalance());
       individual1.get(index).setNumber(individual.get(index).getNumber());
       return individual1;
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
        System.arraycopy(individuals,0,individuals1,0,size);
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
            if(individuals[i].hasAccount(accountNumber))
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
            if(individuals[i].hasAccount(accountNumber))
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
        Account account1=new Account();
        for(int i=0;i<size;i++)
        {
            if(individuals[i].hasAccount(accountNumber))
            {
                int index=individuals[i].serchByAccountNumber(accountNumber);
                account1=individuals[i].set(index,account);
                account.setNumber(individuals[i].getAccounts()[i].getNumber());
                account.setBalance(individuals[i].getAccounts()[i].getBalance());
            }
        }
        return account1;
    }
}
