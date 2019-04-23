package rpis71.klimovich.oop.model;

public class AccountManager {
    private Client[] clients;
    private int size;
    private final static int DEFAULT_CAPACITY =16;

    public AccountManager ()
    {
        this(DEFAULT_CAPACITY);
    }
    public AccountManager(int size) {
        this.clients = new Client[size];
    }
    public AccountManager(Client[] clients) {
        Client[] newClients;
        newClients = new Client[clients.length*2];
        System.arraycopy(clients, 0, newClients, 0, clients.length);
        this.size = clients.length;
        this.clients =  newClients;
    }

    private void checkCapacity(){
        if(size==clients.length)
        {
           Client[]newClient=new Client[clients.length*2];
            System.arraycopy(clients,0,newClient,0,size);
            clients=newClient;
        }
    }
    public boolean add(Client individual) {
        checkCapacity();
        clients[size]=individual;
        size++;
        return true;
    }
    public boolean add(int index,Client client)
    {
        checkCapacity();
        System.arraycopy(clients, index, clients, index + 1, size - index);
        clients[index]= client;
        size++;
        return true;
    }
    public Client get(int index)
    {
        return clients[index];
    }
    public Client set(int index, Client individual)
   {
       Client newClient=clients[index];
       clients[index]=individual;
       return newClient;
   }
    public Client remove(int index)
    {
        size--;
        System.arraycopy(clients,index+1,clients,index,size-index);
        clients[size]=null;
        return clients[index];
    }
    public int size()
    {
        return size;
    }
    public Client[] getClients()
    {
        Client[] newClients=new Client[size];
        System.arraycopy(clients,0,newClients,0,size);
        return newClients;
    }
    public Client[] sortedByBalanceClients()
    {
        Client[] newClients = getClients();
       Client individual;
        for (int i=0;i<size-1;i++)
            for (int j=0;j<size-i;j++)
                if (newClients[j].getAccounts()[i].getBalance()> newClients[j+1].getAccounts()[i].getBalance()) {
                    individual = newClients[j];
                    newClients[j] = newClients[j + 1];
                    newClients[j + 1] = individual;
                }
        return newClients;
    }
    public Account getAccount(String accountNumber)
    {
        for(int i=0;i<size;i++)
        {
            if(clients[i].hasAccount(accountNumber))
            {
               return clients[i].get(accountNumber);
            }
        }
        return null;
    }
    public Account removeAccount(String accountNumber)
    {
        Account account=null;
        for(int i=0;i<size;i++)
        {
            if(clients[i].hasAccount(accountNumber))
               account=clients[i].remove(accountNumber);
        }
        return account;
    }
    public Account setAccount(String accountNumber,Account account)
    {
        Account removedAccount = null;
        for(int i=0;i<size;i++)
        {
            int index=clients[i].indexOf(accountNumber);
            if(index >=0)
                removedAccount=clients[i].set(index,account);
        }
        return removedAccount;
       return null;
    }
    public Client[] getDebtors() {
        int countDebetors = 0;
        for (int i = 0; i < size; i++) {
            if (clients[i].getCreditScore() != 0)
                countDebetors++;
        }
        Client[] debtors = new Client[countDebetors];
        countDebetors = 0;
        for (int i = 0; i < size; i++) {
            if (clients[i].getCreditScore() != 0) {
                debtors[countDebetors] = clients[i];
                countDebetors++;
            }
        }
        return debtors;
    }
    public Client[] getWickedDebtors()
    {
        int countDebetors = 0;
        for (int i = 0; i < size; i++) {
            if (clients[i].getCreditScore() < -2)
                countDebetors++;
        }
        Client[] debetors = new Client[countDebetors];
        countDebetors = 0;
        for (int i = 0; i < size; i++) {
            if (clients[i].getCreditScore() < -2) {
                debetors[countDebetors] = clients[i];
                countDebetors++;
            }
        }
        return debetors;
    }

}
