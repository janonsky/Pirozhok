package rpis71.klimovich.oop.model;

import rpis71.klimovich.oop.model.Account;

public class Individual {
    private Account[] accounts=new Account[16];
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
       System.arraycopy(this.accounts,0,this.accounts,0,accounts.length);
    }
        public boolean add(Account account)
        {
            Account account1=new Account();
            if(size==accounts.length)
            {
                System.arraycopy(this.accounts,0,this.accounts,0,accounts.length*2);
            }
            for(int i=size;i<accounts.length;i++)
            {
                if(accounts[i]==null)
                {
                    account1.setNumber(account.getNumber());
                    accounts[i]=account1;
                    return true;
                }
            }
        return false;
        }
        public boolean add(int index,Account account)
        {
            Account account1=new Account();
            account1.setNumber(account.getNumber());//??
            if(size==accounts.length)
            {
                System.arraycopy(this.accounts,0,this.accounts,0,accounts.length*2);
            }
            for(int i=0;i<accounts.length;i++) {
                if ((index < accounts.length) && ( account1.getNumber() == null)) {
                    accounts[index]=account1;
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
           this.accounts[index]=account;
            return this.accounts[index];
        }
        public Account remove(int index)
        {
            for (int i = index; i<accounts.length-1; i++)
                accounts[i] = accounts[i + 1];
            accounts[accounts.length - 1] = null;
            return accounts[index];
        }
        public Account remove(String accountNumber) //???
        {
            int count=0;
            int index=0;
            for(var el:accounts)
            {
                if(el.getNumber().equals(accountNumber))
                {
                    index=count;
                    for (int i = index; i<accounts.length-1; i++)
                        accounts[i] = accounts[i + 1];
                    accounts[accounts.length - 1] = null;
                    return el;
                }
                count++;
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
            return accounts;//????
        }
        //public Account[] sortedAccountByBalance()
        //{
            
        //}
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
