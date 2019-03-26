package rpis71.klimovich.oop.model;

public class AccountManager {
    private Individual[] individuals;
    private int size;
    private final static int CAPACITY_SIZE =16; //todo имя- гавно

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

    //todo подсмотри у Фунтикова
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
        //todo System.arraycopy
        for(int i=index;i<size;i++)
        {
            individuals[i+1]=individuals[i];

        }
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
       //todo следуй контракту - заменяй ссылку в массиве и ввозвращай замененную ссылку
       Individual individual1=new Individual();
       individual1.get(index).setBalance(individual.get(index).getBalance());
       individual1.get(index).setNumber(individual.get(index).getNumber());
       return individual1;
   }
    public Individual remove(int index)
    {
        size--;
        System.arraycopy(individuals,index+1,individuals,index,size-1); //todo последний параметр не корректен
        individuals[size]=null;
        return individuals[index];
    }
    public int size()
    {
        return size;
    }
    public Individual[] getIndividuals()
    {
        Individual[] individuals1=new Individual[size]; //todo имя имя имя
        System.arraycopy(individuals,0,individuals1,0,size);
        return individuals1;
    }
    public Individual[] sortedByBalanceIndividuals()
    {
        Individual[] sortArray = getIndividuals(); //todo имя имя имя
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
        Account account=new Account(); //todo копировать объект не надо
        for(int i=0;i<size;i++)
        {
            if(individuals[i].hasAccount(accountNumber))
            {
                //todo нормальный get()
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
                //todo individuals[i].remove()
            }
        }
        return account;
    }
    public Account setAccount(String accountNumber,Account account)
    {
        Account account1=new Account(); //todo имя
        for(int i=0;i<size;i++)
        {
            int index=individuals[i].indexOf(accountNumber);
            if(index >=0)
            {
                account1=individuals[i].set(index,account);
                //todo копировать объект не надо
                account.setNumber(individuals[i].getAccounts()[i].getNumber());
                account.setBalance(individuals[i].getAccounts()[i].getBalance());
            }
        }
        return account1;
    }
}
