package rpis71.klimovich.oop.model;

public class Individual {
    private Account[] accounts;
    private int size;
    private final static int CAPACITY_SIZE = 16; //todo имя- гавно

    public Individual() {
        this(CAPACITY_SIZE);
    }

    public Individual(int size) {
        this.accounts = new Account[size];
    }

    public Individual(Account[] accounts) {
        Account[] newAccounts;
        newAccounts = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        this.size = accounts.length;
        this.accounts = newAccounts;
    }
    //todo подсмотри у Фунтикова
    public Account[] increaseArray(int size) {
        if (size == accounts.length) {
            Account[] newAccounts = new Account[size * 2];
            System.arraycopy(accounts, 0, newAccounts, 0, size);
            return newAccounts;
        }
        return accounts;
    }

    public boolean add(Account account) {
        this.accounts = increaseArray(size);
        accounts[size] = account;
        size++;
        return true;
    }

    public boolean add(int index, Account account) {
        this.accounts = increaseArray(size);
        //todo System.arraycopy
        for (int i = index; i < size; i++) {
            accounts[i + 1] = accounts[i];
        }
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
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber().equals(accountNumber))
                return true;
        }
        return false;
    }

    public Account set(int index, Account newAccount) {
        //todo следуй контракту - заменяй ссылку в массиве и ввозвращай замененную ссылку
        Account account;

        return account;
    }

    public Account remove(int index) {
        System.arraycopy(accounts, index + 1, accounts, index, size - 1);        //todo последний параметр не корректен
        size--;
        accounts[size] = null;
        return accounts[index];
    }

    public Account remove(String accountNumber) {
        int index = indexOf(accountNumber);
        if (index != -1) {
            //todo дублирование remove(index)
            size--;
            System.arraycopy(accounts, index + 1, accounts, index, size - 1);
            accounts[size] = null;
            return accounts[index];
        } else
            return null;
    }

    public int size() {
        return size;
    }

    public Account[] getAccounts() {
        Account[] accounts1 = new Account[size]; //todo ЦИФРЫ НЕ ИСПОЛЬЗУЮТСЯ ДЛЯ ДИФФЕРЕНЦИАЦИИ ИМЕН
        System.arraycopy(accounts, 0, accounts1, 0, size);
        return accounts1;
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
}
