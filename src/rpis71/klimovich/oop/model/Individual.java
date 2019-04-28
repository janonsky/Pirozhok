package rpis71.klimovich.oop.model;

import java.util.Arrays;

public class Individual implements Client {
    private Account[] accounts;
    private int size;
    private final static int DEFAULT_CAPACITY = 16;
    private String name;
    private int creditScore;

    public Individual(String name,int creditScore) {
        this(DEFAULT_CAPACITY,name,creditScore);
    }

    public Individual(int size,String name,int creditScore) {
        this.accounts = new Account[size];
        this.name=name;
        this.creditScore=creditScore;
    }

    public Individual(Account[] accounts,String name,int creditScore) {
        Account[] newAccounts;
        newAccounts = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        this.size = accounts.length;
        this.accounts = newAccounts;
        this.name=name;
        this.creditScore=creditScore;
    }
    private void checkCapacity() {
        if (size == accounts.length) {
            Account[] newAccounts = new Account[accounts.length*2];
            System.arraycopy(accounts, 0, newAccounts, 0, size);
            accounts= newAccounts;
        }
    }

    public boolean add(Account account) {
        checkCapacity();
        accounts[size] = account;
        size++;
        return true;
    }

    public boolean add(int index, Account account) {
         checkCapacity();
            System.arraycopy(accounts, index, accounts, index + 1, size - index);
            accounts[index] = account;
            size++;
        return true;
    }

    public Account get(int index) {
        return accounts[index];
    }

    public int indexOf(String accountNumber) {
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber().equals(accountNumber))
                return i;
        }
        return -1;
    }

    public Account get(String accountNumber) {
        int index = indexOf(accountNumber);
        if (index != -1)
            return accounts[index];
        return null;
    }

    public boolean hasAccount(String accountNumber) {
         return (indexOf(accountNumber)!=-1);
    }

    public Account set(int index, Account newAccount) {
        Account account=accounts[index];
        accounts[index]=account;
        return account;
    }

    public Account remove(int index) {
        size--;
        System.arraycopy(accounts, index + 1, accounts, index, size - index);
        accounts[size] = null;
        return accounts[index];
    }

    public Account remove(String accountNumber) {
        int index = indexOf(accountNumber);
        if (index != -1) {
            return remove(index);
        }
    return null;
    }

    public int size() {
        return size;
    }

    public Account[] getAccounts() {
        Account[] newAccounts = new Account[size];
        System.arraycopy(accounts, 0, newAccounts, 0, size);
        return newAccounts;
    }

    public Account[] sortedAccountByBalance() {
        Account[] sortedAccounts = getAccounts();
        Account account;
        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i; j++)
                if (sortedAccounts[j].getBalance() > sortedAccounts[j + 1].getBalance()) {
                    account = sortedAccounts[j];
                    sortedAccounts[j] = sortedAccounts[j + 1];
                    sortedAccounts[j + 1] = account;
                }
        return sortedAccounts;
    }

    public double totalBalance() {
        double totalBalance = 0;
        for (int i = 0; i < size; i++)
            totalBalance += accounts[i].getBalance();
        return totalBalance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public int getCreditScore() {
        return creditScore;
    }

    @Override
    public void addCreditScores(int creditScores) {
       this.creditScore+=creditScores;
    }

    @Override
    public Account[] getCreditAccounts() {
        //todo циклом и для каждого аккаунта проверяем if (account[i] instanceOf Credit) done
        int countCreditAccount=0;
        Account[] accounts=new Account[size];
        for(int i=0;i<size;i++)
        {
            if (accounts[i] instanceof Credit)
                accounts[countCreditAccount]=accounts[i];
            countCreditAccount++;
        }
        return Arrays.copyOf(accounts,countCreditAccount);
    }
}
