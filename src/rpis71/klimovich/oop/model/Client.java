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
    @Override
   default Account[] toArray()
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
   default int indexOf(Account account) throws InvalidAccountNumberException
   {
       Objects.requireNonNull(account, "Account - null");
       int index=0;
       for (Account account1:this)
       {
           if (account1.equals(account))
               return index;
           index++;
       }
       return -1;
   }
    double debtTotal();
   default int indexOf(String accountNumber) throws InvalidAccountNumberException
   {
       Objects.requireNonNull(accountNumber, "AccountNumber - null");
       CheckPattern.checkNumber(accountNumber);
       int index=0;
       for (Account account1:this)
       {
           if (account1.getNumber().equals(accountNumber))
               return index;
           index++;
       }
       return -1;
   }

    @Override
     default boolean isEmpty() {
        return size()==0;
    }

    @Override
    default boolean contains(Object o) {

        for (Account account:this)
        {
            if (account.equals(o))
                return true;
        }
        return false;
    }
    @Override
     default boolean remove(Object o) {
       //todo это лучше не делать default и удалять без foreach
        for (Account account:this)
        {
            if (account.equals(o))
            {
                remove(account);
                return true;
            }
        }
        return false;
    }

    @Override
     default boolean containsAll(Collection<?> c) {
        for (Object obj : c)
            if (!contains(obj))
                return false;
        return true;
    }

    @Override
     default boolean addAll(Collection<? extends Account> c) {
        for (Account account : c) {
            add(account);
        }
        return true;
    }

    @Override
     default boolean removeAll(Collection<?> c) {
        for(Object obj:c)
        {
            if(contains(obj))
            {
                remove(obj);
                return true;
            }
        }
        return false;
    }

    //todo надо бы переопределить и не смешивать интератор и удаление
    @Override
     default boolean retainAll(Collection<?> c) {
       for(Object obj:c)
       {
           if(!c.contains(obj))
           {
               remove(obj);
               return true;
           }
       }
       return false;
    }

    @Override
     default void clear() {
       //todo это надо переопределить в классах . массив - делаем все элементы null в нодах делаем все ссылки null
        for (int i=0;i<size();i++)
            remove(i);
    }

}
