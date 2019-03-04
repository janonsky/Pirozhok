package rpis71.klimovich.oop.model;

import rpis71.klimovich.oop.model.Account;

public class Individual {
    private Account[] accounts;
    private int size;
    private final static int DEFAULT_SIZE=16;

   public Individual ()
   {
       this(DEFAULT_SIZE);
   }
    public Individual (int size)
    {
        this.accounts=new Account[size];
    }
    public Individual (Account[] accounts)
    {
        Account[] newAccounts=null;
        System.arraycopy(this.accounts,0,this.accounts,0,accounts.length*2);//???????????????
    }
        public boolean add(Account account)
        {
            for(int i=size;i<accounts.length;i++)
            {
                if(accounts[i].getNumber()==null)
                {
                    accounts[i].setNumber(account.getNumber());
                    return true;
                }
            }
        return false;
        }
        public boolean add(int index,Account account)
        {
            for(int i=0;i<accounts.length;i++) {
                if ((index < accounts.length) && (accounts[i].getNumber() == null)) {
                    accounts[index].setNumber(account.getNumber());
                    return true;
                }
            }
        return false;
        }
        public Account get(int index)
        {
                if(index<accounts.length)
                    return accounts[index];
                return null;
        }
        public Account get(String accountNumber)
        {
            for(var el:accounts)
            {
                if(el.getNumber().equals(accountNumber))
                    return el;
            }
        return null;
        }
        public boolean hasAccount(String accountNumber)
        {
            for(var el:accounts)
            {
                if(el.getNumber().equals(accountNumber))
                    return true;
            }
            return false;
        }
        public Account set(int index,Account account)
        {
            accounts[index].setBalance(account.getBalance());
            accounts[index].setNumber(account.getNumber());
            return accounts[index];
        }
        public Account remove(int index)
        {
            accounts[index].setNumber("null");
            return accounts[index];
        }
        public Account remove(String accountNumber)
        {
            for(var el:accounts)
            {
                if(el.getNumber().equals(accountNumber))
                {
                    el.setNumber("null");
                    return el;
                }
            }
            return null;
        }
        public int size()
        {
            int count=0;
            for(var el:accounts)
            {
                if(el.getNumber()!=null)
                    count++;
            }
            return count;
        }
        public Account[] getAccounts()
        {

        }
        public Account[] sortedAccountByBalance()
        {}
        public double totalBalance()
        {
            double count=0;
            for(var el:accounts)
            {
                if(el.getNumber()!=null)
                    count+=el.getBalance();
            }
        return count;
        }
}
