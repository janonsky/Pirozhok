package rpis71.klimovich.oop.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

//todo чеки перенеси в приватные методы работы с нодами (если их вызываешь) done
public class Entity implements Client {
    private Node head;
    private Node tail;
    private String name;
    private int size;
    private int creditScore;

    public Entity(String name) throws InvalidAccountNumberException {

        CheckPattern.checkNumber(name);
        this.head = new Node(null, null);
        //todo проверка имени done
        this.name = name;
    }

    public Entity(Account[] accounts, String name, int creditScore) throws DublicateAccountNumberException, InvalidAccountNumberException {
        this(name);
        for (int i = 0; i < accounts.length; i++)
            add(accounts[i]);
        this.creditScore = creditScore;
    }

    @Override
    public boolean add(Account account) throws DublicateAccountNumberException {
        Objects.requireNonNull(account, "Account - null");
        checkDuplicateAccouuntForNumber(account);
        return add(size, account);
    }

    @Override
    public boolean add(int index, Account account) throws DublicateAccountNumberException {
        Objects.checkIndex(index, size); //todo done
        Objects.requireNonNull(account, "Account - null");
        checkDuplicateAccouuntForNumber(account);
        Node node = head.next;
        Node newNode = new Node(account, null);
        if (size == 0 && index == 0) {
            head.next = newNode;
            tail = newNode;
            tail.next = head.next;
            size++;
        } else if (index == 0) {
            newNode.next = node;
            head.next = newNode;
            tail.next = newNode;
            size++;
        } else if (index == size) {
            tail.next = newNode;
            tail = newNode;
            tail.next = head.next;
            size++;
        } else {
            node = getNode(index - 1);
            newNode.next = node.next;
            node.next = newNode;
            size++;
        }
        return true;
    }

    @Override
    public Account get(int index) {
        Objects.checkIndex(index, size); //todo done
        return getNode(index).value;
    }

    @Override
    public Account get(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        CheckPattern.checkNumber(accountNumber);
        // if (getNodeByNumber(accountNumber)!=null) //todo done
        return getNodeByNumber(accountNumber).value;
    }

    @Override
    public boolean hasAccount(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        CheckPattern.checkNumber(accountNumber);
        Node node = head.next;
        for (int i = 0; i < size; i++)
            if (node.value.getNumber().equals(accountNumber))
                return true;
        return false; //todo лучше ручками пробедаться и вернуть true или false done
    }

    @Override
    public Account set(int index, Account account) throws DublicateAccountNumberException {
        Objects.checkIndex(index, size);//todo done
        Objects.requireNonNull(account, "Account - null");
        checkDuplicateAccouuntForNumber(account);
        Node node = getNode(index);
        Account removedAccount = node.value;
        node.value = account;
        return removedAccount;
    }

    @Override
    public Account remove(int index) {
        Objects.checkIndex(index, size); //todo done
        Node node = getNode(index - 1);
        Node removedNode = node.next;
        if (size != 0) {
            if (index == 0)
                head.next = removedNode.next;
            else if (index == size) {
                tail = node;
                tail.next = head.next;
            } else {
                node.next = removedNode.next;
            }
            size--;
        }
        return removedNode.value;
    }

    @Override
    public Account remove(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        CheckPattern.checkNumber(accountNumber);
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.getNumber().equals(accountNumber)) {
                return remove(i);
            }
            node = node.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Account[] getAccounts() {
        Node node = head.next;
        Account[] accounts = new Account[size];
        for (int i = 0; i < size; i++) {
            accounts[i] = node.value;
            node = node.next;
        }
        return accounts;
    }

    @Override
    public Account[] sortedAccountByBalance() {
        Account[] sortedAccountsByBalance = getAccounts();
        Arrays.sort(sortedAccountsByBalance);
        return sortedAccountsByBalance;
    }

    @Override
    public Double totalBalance() {
        double totalBalance = 0;
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            totalBalance += node.value.getBalance();
            node = node.next;
        }
        return totalBalance;
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
    public Account[] getCreditAccounts() {
        int count = 0;
        Account[] newAccounts = new Account[size];
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value instanceof Credit) {
                newAccounts[count] = node.value;
                count++;
            }
            node = node.next;
        }
        return Arrays.copyOf(newAccounts, count);
    }

    @Override
    public boolean remove(Account account) {
        Objects.requireNonNull(account, "Account - null");
            remove(indexOf(account));
            return true;
    }

    @Override
    public int indexOf(Account account) {
        Objects.requireNonNull(account, "Account - null");
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(account))
                return i;
            node = node.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public double debtTotal() {
        Node node = head.next;
        double debtTotal = 0;
        for (int i = 0; i < size; i++) {
            //todo getBalance() только если value - кредит done
            if (node.value instanceof Credit)
                debtTotal += node.value.getBalance();
            node = node.next;
        }
        return debtTotal;
    }

    @Override
    public int indexOf(String accountNumber) {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.getNumber().equals(accountNumber)) {
                return i;
            }
            node = node.next;
        }
        throw new NoSuchElementException();
        //todo NoSuchElementException done
    }

    private Node getNode(int index) {
        Objects.checkIndex(-1, size);
        int numberNode = 0;
        Node currentNode = head.next;
        while (numberNode < index) {
            currentNode = currentNode.next;
            numberNode++;
        }
        return currentNode.next;
    }

    private Node getNodeByNumber(String accountNumber) {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.getNumber().equals(accountNumber))
                return node;
            node = node.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client\n")
                .append("name: ").append(getName());
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            sb.append(node.value.toString()).append("\n");
            node = node.next;
        }
        sb.append("total: ").append(totalBalance());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        Node currentNode = head.next;
        int hash = Integer.hashCode(getCreditScore());
        for (int i = 0; i < size; i++)
            hash ^= currentNode.value.hashCode();
        return hash ^ name.hashCode();
    }

    public boolean equals(Object object) {
        //todo см Individual done
        if (!(object instanceof Entity))
            return false;
        Entity obj = (Entity) object; //todo проверка типа перед кастом делается done
        if (!(((obj.name.equals(this.name)) /*todo ты че творишь а done*/ && (obj.getCreditScore() == this.getCreditScore()) && (obj.size == this.size))))
            return false;
        Node node = head.next;
        Node newNode = obj.head.next;
        for (int i = 0; i < size; i++) {
            if (!newNode.value.equals(node.value))
                return false;
            node = node.next;
            newNode = newNode.next;
        }
        return true;
    }

    public Object clone() throws CloneNotSupportedException
    {
        Entity clone = (Entity) super.clone();
        clone.head = head.clone();
        Node node = clone.head;
        for (int i = 0; i < size; i++) {
            node.next = node.next.clone();
            node = node.next;
        }
        node.next = clone.head.next;
        return clone;
        //todo в цикле node.next = node.next.clone() done??
    }

    private void checkDuplicateAccouuntForNumber(Account account) throws DublicateAccountNumberException {
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.getNumber().equals(account.getNumber()))
                throw new DublicateAccountNumberException("Account with this number already exists");
            node = node.next;
        }
    }

    @Override
    public Iterator<Account> iterator() {
        return null;
    }

    @Override
    public int compareTo(Client o) {
        return this.totalBalance().compareTo(o.totalBalance());
    }

    private class AccountIterator implements Iterable<Account> {

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

        private class Node {
            Node next;
            Account value;

            private Node(Account value, Node next) {
                this.value = value;
                this.next = next;
            }

            @Override
            public Node clone() throws CloneNotSupportedException {
                Node clone = (Node) super.clone();
                clone.value = value.clone();
                return clone;
            }
        }
    }
