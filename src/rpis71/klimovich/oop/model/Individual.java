package rpis71.klimovich.oop.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Individual implements Client {
    private Account[] accounts;
    private int size;
    private final static int DEFAULT_CAPACITY = 16;
    private String name;
    private int creditScore;

    public Individual(String name, int creditScore) throws InvalidAccountNumberException {
        this(DEFAULT_CAPACITY, name, creditScore);
    }

    public Individual(int size, String name, int creditScore) throws InvalidAccountNumberException {
        CheckPattern.checkNumber(name);
        this.accounts = new Account[size];
        this.name = name;
        this.creditScore = creditScore;
    }

    public Individual(Account[] accounts, String name, int creditScore) {
        Account[] newAccounts;
        newAccounts = new Account[accounts.length * 2];
        System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
        this.size = accounts.length;
        this.accounts = newAccounts;
        this.name = name;
        this.creditScore = creditScore;
    }

    private void checkCapacity() {
        if (size == accounts.length) {
            Account[] newAccounts = new Account[accounts.length * 2];
            System.arraycopy(accounts, 0, newAccounts, 0, size);
            accounts = newAccounts;
        }
    }

    public boolean add(Account account) throws DublicateAccountNumberException {
        Objects.requireNonNull(account, "Account - null");
        checkDuplicateAccouuntForNumber(account);
        checkCapacity();
        accounts[size] = account;
        size++;
        return true;
    }

    public boolean add(int index, Account account) throws DublicateAccountNumberException {
        Objects.checkIndex(index,size);
        Objects.requireNonNull(account, "Account - null");
        checkDuplicateAccouuntForNumber(account);
        checkCapacity();
        System.arraycopy(accounts, index, accounts, index + 1, size - index);
        accounts[index] = account;
        size++;
        return true;
    }

    public Account get(int index) {
        Objects.checkIndex(index,size);
        return accounts[index];
    }

    public int indexOf(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
       CheckPattern.checkNumber(accountNumber);
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber().equals(accountNumber))
                return i;
        }
        throw new NoSuchElementException();
    }

    public Account set(int index, Account newAccount) throws DublicateAccountNumberException {
        Objects.checkIndex(index,size);
        Objects.requireNonNull(newAccount, "Account - null");
        checkDuplicateAccouuntForNumber(newAccount);
        Account account = accounts[index];
        accounts[index] = account;
        return account;
    }

    public Account remove(int index) {
        Objects.checkIndex(index,size);
        size--;
        System.arraycopy(accounts, index + 1, accounts, index, size - index);
        accounts[size] = null;
        return accounts[index];
    }

    public Account remove(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        //todo проверка -1 и выброс исключения
        int index = indexOf(accountNumber);
            return remove(index);
    }

    public int size() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        Objects.requireNonNull(name, "name - null");
        this.name = name;
    }

    @Override
    public int getCreditScore() {
        return creditScore;
    }

    @Override
    public void addCreditScores(int creditScores) {
        this.creditScore += creditScores;
    }

    @Override
    public boolean remove(Account account) throws InvalidAccountNumberException {
        Objects.requireNonNull(account, "Account - null");
            remove(indexOf(account));
            return true;
    }

    @Override
    public int indexOf(Account account) throws InvalidAccountNumberException {
        Objects.requireNonNull(account, "Account - null");
        for (int i = 0; i < size; i++) {
            if (accounts[i].equals(account))
                return i;
        }
        return -1;
    }

    @Override
    public double debtTotal() {
        Account[] creditAccounts = getCreditAccounts();
        double debtTotal = 0;
        for (int i = 0; i < size; i++)
            debtTotal += creditAccounts[i].getBalance();
        return debtTotal;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client\n")
                .append(" name: ").append(getName());
        for (int i = 0; i < size; i++) {
            sb.append(accounts[i].toString()).append("\n");
        }
        sb.append("total: ").append(totalBalance());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = getCreditScore();
        for (int i = 0; i < size; i++) {
            hash ^= accounts[i].hashCode();
        }
        return hash ^ name.hashCode();
    }

    public boolean equals(Object object) {
        if (!(object instanceof Individual))
            return false;

        Individual obj = (Individual) object;
        if (!((obj.name.equals(this.name)) && (obj.creditScore == this.creditScore) && (this.size == (obj.size))))
            return false;

        for (int i = 0; i < size; i++) {
            if (!obj.accounts[i].equals(accounts[i]))
                return false;
        }
        return true;
    }

    public Object clone() throws CloneNotSupportedException {
        Individual individual = (Individual) super.clone();
        individual.accounts = accounts.clone();
        for (int i = 0; i < size; i++) {
            individual.accounts[i] = this.accounts[i].clone();
        }
        return individual;
    }

    private void checkDuplicateAccouuntForNumber(Account account) throws DublicateAccountNumberException {
        for (int i = 0; i < size; i++)
            if (accounts[i].getNumber().equals(account.getNumber()))
                throw new DublicateAccountNumberException("Account with this number already exists");
    }
    @Override
    public int compareTo(Client o) {
        return this.totalBalance().compareTo(o.totalBalance());
    }

    @Override
    public Iterator<Account> iterator() {
        return null;
    }
    private class AccountIterator implements Iterable<Account>
    {
        int index=0;
        public boolean hasNext() {
            return index < size;
        }

        public Account next() {
          return null;
        }

        @Override
        public Iterator<Account> iterator() {
            return null;
        }
    }
}
