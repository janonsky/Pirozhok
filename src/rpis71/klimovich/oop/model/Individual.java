package rpis71.klimovich.oop.model;

public class Individual {
    private Account[] accounts;
    private int size;
    private final static int CAPACITY_SIZE =16;

    public Individual ()
   {
       this(CAPACITY_SIZE);
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
        public Account[] increaseArray(int size)
        {
            if(size==accounts.length)
            {
                Account[] newAccounts=new Account[size*2];
                System.arraycopy(accounts,0,newAccounts,0,size);
                return newAccounts;
            }
            return accounts;
        }
        public boolean add(Account account)
        {
            this.accounts=increaseArray(size);
            accounts[size]=account;
            size++;
            return true;
        }
        public boolean add(int index,Account account)
        {
                this.accounts=increaseArray(size);
                for(int i=index;i<size;i++)
                {
                    accounts[i+1]=accounts[i];
                    accounts[index]= account;
                    size++;
                }
        return true;
        }
        public Account get(int index)
        {
                    return accounts[index];
        }
        public int serchByAccountNumber(String accountNumber)
        {
            for(int i=0;i<size;i++)
            {
                if(accounts[i].getNumber().equals(accountNumber))
                    return i;
            }
            return -1;
        }
        public Account get(String accountNumber)
        {
            int index=serchByAccountNumber(accountNumber);
                if(index!=-1)
                    return accounts[index];
                else
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
            Account account1=new Account();
            account1.setNumber(accounts[index].getNumber());
            account1.setBalance(accounts[index].getBalance());
            return account1;
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
            int index=serchByAccountNumber(accountNumber);
            if(index!=-1)
            {
                size--;
                System.arraycopy(accounts,index+1,accounts,index,size-1);
                accounts[size]=null;
                return accounts[index];
            }
             else
                 return null;
        }
        public int size()
        {
            return size;
        }
        public Account[] getAccounts()
        {
            Account[] accounts1=new Account[size];
            System.arraycopy(accounts, 0, accounts1, 0, size);
            return accounts1;
        }
        public Account[] sortedAccountByBalance()
        {
            Account[] sortArray = getAccounts();
            Account account;
            for (int i=0;i<size-1;i++)
                for (int j=0;j<size-i;j++)
                    if (sortArray[j].getBalance()> sortArray[j+1].getBalance()) {
                        account = sortArray[j];
                        sortArray[j] = sortArray[j + 1];
                        sortArray[j + 1] = account;
                    }
            return sortArray;
        }
        public double totalBalance()
        {
            double totalBalance=0;
            for(int i=0;i<size;i++)
                    totalBalance+=accounts[i].getBalance();
        return totalBalance;
        }
}
