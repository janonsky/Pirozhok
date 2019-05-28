package rpis71.klimovich.oop.model;

import java.util.*;

public class AccountManager implements Iterable<Client> {
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
         Objects.checkIndex(index,size);
        Objects.requireNonNull(client,"Client - null");
        checkCapacity();
        System.arraycopy(clients, index, clients, index + 1, size - index);
        clients[index]= client;
        size++;
        return true;
    }
    public Client get(int index)
    {
        Objects.checkIndex(index,size);
        return clients[index];
    }
    public Client set(int index, Client individual)
   {
       Objects.checkIndex(index,size);
       Objects.requireNonNull(individual,"Individual - null");

       Client newClient=clients[index];
       clients[index]=individual;
       return newClient;
   }
    public Client remove(int index)
    {
        Objects.checkIndex(index,size);
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
    public ArrayList<Client> sortedByBalanceClients()
    {
        ArrayList<Client> clientArrayList=new ArrayList<>();
        for (Client client:this)
        {
            clientArrayList.add(client);
        }
        Collections.sort(clientArrayList);
        return clientArrayList;
    }
    public Account getClient(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"InvalidAccountNumberException - null");
        for (Client client:clients)
        {
            if (client.hasAccount(accountNumber))
                return client.get(accountNumber);
        }
      throw new NoSuchElementException();
    }
    public Account removeClient(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"InvalidAccountNumberException - null");
        Account account;
        for (Client client:clients)
        {
            if (client.hasAccount(accountNumber))
            {
                account=client.remove(accountNumber);
                return account;
            }
        }
        throw new NoSuchElementException();
    }
    public Account setAccount(String accountNumber,Account account) throws DublicateAccountNumberException, InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"InvalidAccountNumberException - null");
        Objects.requireNonNull(account,"account - null");
        Account removedAccount;
        for(int i=0;i<size;i++)
        {
            int index=clients[i].indexOf(accountNumber);
            if(index >=0) {
                removedAccount = clients[i].set(index, account);
                return removedAccount;
            }
        }
        throw new NoSuchElementException();
    }

    public Set<Client> getDebtors() {
        HashSet<Client> clientHashSet=new HashSet<>();
        for (Client debetor:clients)
        {
            if (debetor.getCreditScore()!=ClientStatus.GOOD.getCreditScoreBound())
            {
                clientHashSet.add(debetor);
            }
        }
        return clientHashSet;
    }
    public Set<Client> getWickedDebtors()
    {
       return getDebetorsForStatus(ClientStatus.BAD);
    }

    private Set<Client> getDebetorsForStatus(ClientStatus status)
    {
        HashSet<Client> clientHashSet=new HashSet<>();
        for (Client client:this)
        {
            if (client.getCreditScore()<=status.getCreditScoreBound())
                clientHashSet.add(client);
        }
        return clientHashSet;
    }
    public boolean remove(Client client)
    {
        Objects.requireNonNull(client,"client - null");
            remove(indexOf(client));
            return true;
    }
    public int indexOf(Client client)
    {
        Objects.requireNonNull(client,"client - null");
        int index=0;
        for (Client newClient: clients)
        {
            if (newClient.getName().equals(client.getName()))
                return index;
            index++;
        }
           throw new NoSuchElementException();
    }
    @Override
    public String toString()
    {
        StringBuilder sb= new StringBuilder();
        for (Client client:clients)
            sb.append("<").append(client.toString()).append(">").append("\n");
        return sb.toString();
    }

    @Override
    public Iterator<Client> iterator() {
        return new ClientIterator();
    }
    private class ClientIterator implements Iterator<Client>
    {
        int index=0;
        public boolean hasNext() {
            return index < size;
        }
        public Client next()
        {
            if (!hasNext())
                throw new NoSuchElementException();
            return clients[index++];
        }
    }
}
