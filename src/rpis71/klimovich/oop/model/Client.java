package rpis71.klimovich.oop.model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

//todo все foreach через this done
public interface Client extends Iterable<Account>,Comparable<Client> {
    boolean add(Account account) throws DublicateAccountNumberException;
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
       //todo лютая фигня done
       return newAccounts;
   }
    default Account[] sortedAccountByBalance()
    {
        Account[] newAccounts=getAccounts();
        Arrays.sort(newAccounts);
        return newAccounts;
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
   //todo compareTоже default done
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
    default Account[] getCreditAccounts()
    {
        int countCreditAccount = 0;
        Account[] accounts = new Account[size()];
        for (Account account:this)
            if (account instanceof Credit)
                accounts[countCreditAccount]=account;
            countCreditAccount++;
        return Arrays.copyOf(accounts, countCreditAccount);
    }
    boolean remove(Account account) throws InvalidAccountNumberException;
    int indexOf(Account account) throws InvalidAccountNumberException;
    double debtTotal();
    int indexOf(String accountNumber) throws InvalidAccountNumberException;
}
