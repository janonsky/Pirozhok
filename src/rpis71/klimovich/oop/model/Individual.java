package rpis71.klimovich.oop.model;

public class Individual {
    private Account[] accounts;
    private int size;
    private final static int ConstSIZE =16;

    public Individual ()
   {
       this(ConstSIZE);
   }
    public Individual (int size)
    {
        this.accounts=new Account[size];
    }
    public Individual (Account[] accounts)
    {
            Account[] newAccounts;
            newAccounts = new Account[accounts.length*2];
            System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
            this.size = accounts.length;
            this.accounts = newAccounts;
    }
        public boolean add(Account account)
        {
            if(size==accounts.length)
            {
                System.arraycopy(this.accounts,0,this.accounts,0,size*2);
                size++;
            }
            for(int i=0;i<accounts.length;i++)
            {
                if(accounts[i]==null)
                {
                    accounts[i]=account;
                    return true;
                }
            }
        return false;
        }
        public boolean add(int index,Account account)
        {
            if(size==accounts.length)
            {
                System.arraycopy(this.accounts,0,this.accounts,0,size*2);
                size++;
            }
                if (accounts[index]== null) {
                    accounts[index]=account;
                    return true;
                }
        return false;
        }
        public Account get(int index)
        {
                    return accounts[index];
        }
        public Account get(String accountNumber)
        {
            for(int i=0;i<size;i++)
            {
                if(accounts[i].getNumber().equals(accountNumber))
                    return accounts[i];
            }
        return null;
        }
        public boolean hasAccount(String accountNumber)
        {
            for(int i=0;i<size;i++)
            {
                if(accounts[i].getNumber().equals(accountNumber))
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
            size--;
            System.arraycopy(accounts,index+1,accounts,index,size-1);
            accounts[size]=null;
            return accounts[index];
        }
        public Account remove(String accountNumber)
        {
            for(int i=0;i<size;i++)
            {
                if(accounts[i].getNumber().equals(accountNumber))
                {
                   size--;
                   System.arraycopy(accounts,i+1,accounts,i,size-1);
                   accounts[size]=null;
                   return accounts[i];
                }
            }
            return null;
        }
        public int size()
        {
            return size;
        }
        public Account[] getAccounts()
        {
            Account[] accounts1=new Account[accounts.length];
            for(int i=0;i<size;i++)
            {
                   accounts1[i]=accounts[i];
            }
            return accounts1;
        }
        /*public Account[] sortedAccountByBalance()
        {

        }*/
        public double totalBalance()
        {
            double count=0;
            for(int i=0;i<size;i++)
            {
                    count+=accounts[i].getBalance();
            }
        return count;
        }
}
