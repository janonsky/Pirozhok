package rpis71.klimovich.oop.model;

public class AccountManager {
    private Individual[] individuals;
    private int size;
    private final static int DEFAULT_CAPACITY =16;

    public AccountManager ()
    {
        this(DEFAULT_CAPACITY);
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

    //todo подсмотри у Фунтикова done
    private void checkCapacity(){
        if(size==individuals.length)
        {
           Individual[] newIndividual=new Individual[individuals.length*2];
            System.arraycopy(individuals,0,newIndividual,0,size);
           individuals=newIndividual;
        }
    }
    public boolean add(Individual individual) {
        checkCapacity();
        individuals[size]=individual;
        size++;
        return true;
    }
    public boolean add(int index,Individual individual)
    {
        checkCapacity();
        System.arraycopy(individuals, index, individuals, index + 1, size - index);
        individuals[index]= individual;
        size++;
        return true;
    }
    public Individual get(int index)
    {
        return individuals[index];
    }
    public Individual set(int index, Individual individual)
   {
       Individual newIndividual=individuals[index];
       individuals[index]=individual;
       return newIndividual;
   }
    public Individual remove(int index)
    {
        size--;
        System.arraycopy(individuals,index+1,individuals,index,size-index);
        individuals[size]=null;
        return individuals[index];
    }
    public int size()
    {
        return size;
    }
    public Individual[] getIndividuals()
    {
        Individual[] newIndividuals=new Individual[size];
        System.arraycopy(individuals,0,newIndividuals,0,size);
        return newIndividuals;
    }
    public Individual[] sortedByBalanceIndividuals()
    {
        Individual[] newIndividuals = getIndividuals();
       Individual individual;
        for (int i=0;i<size-1;i++)
            for (int j=0;j<size-i;j++)
                if (newIndividuals[j].getAccounts()[i].getBalance()> newIndividuals[j+1].getAccounts()[i].getBalance()) {
                    individual = newIndividuals[j];
                    newIndividuals[j] = newIndividuals[j + 1];
                    newIndividuals[j + 1] = individual;
                }
        return newIndividuals;
    }
    public Account getAccount(String accountNumber)
    {
        for(int i=0;i<size;i++)
        {
            if(individuals[i].hasAccount(accountNumber))
            {
               return individuals[i].get(accountNumber);
            }
        }
        return null;
    }
    public Account removeAccount(String accountNumber)
    {
        Account account=new Account();
        for(int i=0;i<size;i++)
        {
            if(individuals[i].hasAccount(accountNumber))
            {
               account=individuals[i].remove(accountNumber);
            }
        }
        return account;
    }
    public Account setAccount(String accountNumber,Account account)
    {
        Account removedAccount = null;
        for(int i=0;i<size;i++)
        {
            int index=individuals[i].indexOf(accountNumber);
            if(index >=0)
            {
                removedAccount=individuals[i].set(index,account);
            }
        }
        return removedAccount;
    }
}
