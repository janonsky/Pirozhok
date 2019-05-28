package rpis71.klimovich.oop.model;

import java.util.*;

public interface Client extends Comparable<Client>, Collection<Account> {
    boolean add(Account account);
    boolean add(int index,Account account) throws DublicateAccountNumberException;
    Account get(int index);
   default Account get(String accountNumber) throws InvalidAccountNumberException
    {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        for (Account account: this)
        {
            if (account.getNumber().equals(accountNumber))
                return account;
        }
        throw new NoSuchElementException();
    }
   default boolean hasAccount(String accountNumber) throws InvalidAccountNumberException
   {
       Objects.requireNonNull(accountNumber, "AccountNumber - null");
       for (Account account:this)
       {
           if (account.getNumber().equals(accountNumber))
               return true;
       }
       throw new NoSuchElementException();
   }
    Account set(int index,Account account) throws DublicateAccountNumberException;
    Account remove(int index);
    Account remove(String accountNumber) throws InvalidAccountNumberException;
    int size();
   default Account[] getAccounts()
   {
       Account[] newAccounts=new Account[size()];
       int index=0;
       for (Account account:this)
       {
           newAccounts[index]=account;
           index++;
       }
       return newAccounts;
   }
    default ArrayList<Account> sortedAccountByBalance()
    {
        ArrayList<Account> accountArrayList=new ArrayList<>(this);
        Collections.sort(accountArrayList);
        return accountArrayList;
    }
   default Double totalBalance()
   {
       double totalBalance=0;
       for (Account account:this)
       {
           totalBalance+=account.getBalance();
       }
       return totalBalance;
   }
   default int compareTo(Client o) {
       return Double.compare(this.totalBalance(),o.totalBalance());
   }
    String getName();
    void setName(String name);
    int getCreditScore();
    void addCreditScores(int creditScores);
   default ClientStatus getStatus()
   {
       if (getCreditScore() >= ClientStatus.GOOD.getCreditScoreBound())
           return ClientStatus.GOOD;
      else if (getCreditScore() >= ClientStatus.GOLD.getCreditScoreBound())
           return ClientStatus.GOLD;
      else if(getCreditScore() >= ClientStatus.PLATINUM.getCreditScoreBound())
           return ClientStatus.PLATINUM;
      else if(getCreditScore() >= ClientStatus.RISKY.getCreditScoreBound())
           return ClientStatus.RISKY;
      else if(getCreditScore() >= ClientStatus.BAD.getCreditScoreBound())
           return ClientStatus.BAD;
       return null;
   }
    default Collection<Credit> getCreditAccounts()
    {
        LinkedList<Credit> clientLinkedList=new LinkedList<>();
        for (Account account: this)
        {
            if (account instanceof Credit)
                clientLinkedList.add((Credit) account);
        }
        return clientLinkedList;
    }
    boolean remove(Account account) throws InvalidAccountNumberException;
    int indexOf(Account account) throws InvalidAccountNumberException; //todo default
    double debtTotal();
    int indexOf(String accountNumber) throws InvalidAccountNumberException; //todo default
}
