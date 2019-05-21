package rpis71.klimovich.oop.model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public interface Client extends Iterable<Account>,Comparable<Client> {
    boolean add(Account account) throws DublicateAccountNumberException;
    boolean add(int index,Account account) throws DublicateAccountNumberException;
    Account get(int index);
   default Account get(String accountNumber) throws InvalidAccountNumberException
    {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        for (Account account: getAccounts())
        {
            if (account.getNumber().equals(accountNumber))
                return account;
        }
        throw new NoSuchElementException();
    }
   default boolean hasAccount(String accountNumber) throws InvalidAccountNumberException
   {
       Objects.requireNonNull(accountNumber, "AccountNumber - null");
       for (Account account:getAccounts())
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
       Account[] newAccounts=getAccounts();
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
       for (Account account:getAccounts())
       {
           totalBalance+=account.getBalance();
       }
       return totalBalance;
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
        for (Account account:getAccounts())
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
