package rpis71.klimovich.oop.model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        Objects.requireNonNull(individual,"Individual - null");
        checkCapacity();
        clients[size]=individual;
        size++;
        return true;
    }
    public boolean add(int index,Client client)
    {
        if (index<0||index<size)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        Objects.requireNonNull(client,"Client - null");
        checkCapacity();
        System.arraycopy(clients, index, clients, index + 1, size - index);
        clients[index]= client;
        size++;
        return true;
    }
    public Client get(int index)
    {
        if (index<0||index<size)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        return clients[index];
    }
    public Client set(int index, Client individual)
   {
       if (index<0||index<size)
           throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
       Objects.requireNonNull(individual,"Individual - null");

       Client newClient=clients[index];
       clients[index]=individual;
       return newClient;
   }
    public Client remove(int index)
    {
        if (index<0||index<size)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
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
    public Account getClient(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"InvalidAccountNumberException - null");
        for(int i=0;i<size;i++)
        {
            if(clients[i].hasAccount(accountNumber))
            {
               return clients[i].get(accountNumber);
            }
        }
      throw new NoSuchElementException();
    }
    public Account removeClient(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"InvalidAccountNumberException - null");
        Account account=null;
        for(int i=0;i<size;i++)
        {
            if(clients[i].hasAccount(accountNumber))
               account=clients[i].remove(accountNumber);
        }
        if (account==null)
            throw new NoSuchElementException();
        return account;
    }
    public Account setAccount(String accountNumber,Account account) throws DublicateAccountNumberException, InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"InvalidAccountNumberException - null");
        Objects.requireNonNull(account,"account - null");
        Account removedAccount = null;
        for(int i=0;i<size;i++)
        {
            int index=clients[i].indexOf(accountNumber);
            if(index >=0)
                removedAccount=clients[i].set(index,account);
        }
        if (removedAccount==null)
            throw new NoSuchElementException();
        return removedAccount;
    }

    public Client[] getDebtors() {
        Client[] debtors = new Client[size];
        int countDebtors = 0;
        for (int i = 0; i < size; i++) {
            if (clients[i].getCreditScore() != ClientStatus.GOOD.getCreditScoreBound()) {
                debtors[countDebtors] = clients[i];
                countDebtors++;
            }
        }
        return Arrays.copyOf(debtors,countDebtors);
    }
    public Client[] getWickedDebtors()
    {
       return getDebetorsForStatus(ClientStatus.BAD);
    }

    private Client[] getDebetorsForStatus(ClientStatus status)
    {
        int countDebetors=0;
        for(int i=0;i<size;i++)
            if(clients[i].getCreditScore() <= status.getCreditScoreBound())
                countDebetors++;
        Client[] debetors=new Client[countDebetors];
        for(int i=0;i<countDebetors;i++)
            if (clients[i].getCreditScore()<=status.getCreditScoreBound())
                debetors[i]=clients[i];
            return debetors;
    }
    public boolean remove(Client client)
    {
        Objects.requireNonNull(client,"client - null");
        int index=indexOf(client);
        if (index!=-1) {
            remove(indexOf(client));
            return true;
        }
        else
            return false;
    }
    public int indexOf(Client client)
    {
        Objects.requireNonNull(client,"client - null");
        int index=0;
       for (int i=0;i<size;i++)
           if (clients[i].getName().equals(client.getName()))
                return i;
           return -1;
    }
    @Override
    public String toString()
    {
        StringBuilder sb= new StringBuilder();
        for (int i=0; i<this.clients.length;i++)
            sb.append( "< ").append( this.clients[i].toString()).append(">").append("\n"); //todo конкатенацию нафиг из билдера done
        return sb.toString();
    }
}
