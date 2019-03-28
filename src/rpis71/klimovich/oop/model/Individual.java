package rpis71.klimovich.oop.model;

public class Individual {
    private Account[] accounts;
    private int size;
    private final static int DEFAULT_CAPACITY = 16; //todo имя- гавно done

    public Individual() {
        this(DEFAULT_CAPACITY);
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
    //todo подсмотри у Фунтикова done
    public void increaseArray() {
        if (size == accounts.length) {
            Account[] newAccounts = new Account[accounts.length*2];
            System.arraycopy(accounts, 0, newAccounts, 0, size);
            accounts= newAccounts;
        }
    }

    public boolean add(Account account) {
        increaseArray();
        accounts[size] = account;
        size++;
        return true;
    }

    public boolean add(int index, Account account) {
         increaseArray();
        //todo System.arraycopy done
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
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber().equals(accountNumber))
                return true;
        }
        return false;
    }

    public Account set(int index, Account newAccount) {
        //todo следуй контракту - заменяй ссылку в массиве и ввозвращай замененную ссылку done
        Account account=accounts[index];
        accounts[index]=account;
        return account;
    }

    public Account remove(int index) {
        System.arraycopy(accounts, index + 1, accounts, index, size - index);        //todo последний параметр не корректен done
        size--;
        accounts[size] = null;
        return accounts[index];
    }

    public Account remove(String accountNumber) {
        int index = indexOf(accountNumber);
        if (index != -1) {
            //todo дублирование remove(index) done
            return remove(index);
        }
    return null;
    }

    public int size() {
        return size;
    }

    public Account[] getAccounts() {
        Account[] newAccounts = new Account[size]; //todo ЦИФРЫ НЕ ИСПОЛЬЗУЮТСЯ ДЛЯ ДИФФЕРЕНЦИАЦИИ ИМЕН done
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
}
