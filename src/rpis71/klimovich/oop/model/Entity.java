package rpis71.klimovich.oop.model;

import java.lang.reflect.Array;
import java.util.*;

public class Entity implements Client {
    private Node head;
    private Node tail;
    private String name;
    private int size;
    private int creditScore;

    public Entity(String name) throws InvalidAccountNumberException {

        CheckPattern.checkNumber(name);
        this.head = new Node(null, null);
        this.name = name;
    }

    public Entity(Account[] accounts, String name, int creditScore) throws DublicateAccountNumberException, InvalidAccountNumberException {
        this(name);
        for (int i = 0; i < accounts.length; i++)
            add(accounts[i]);
        this.creditScore = creditScore;
    }

    @Override
    public boolean add(Account account) {
        Objects.requireNonNull(account, "Account - null");
        checkDuplicateAccountForNumber(account);
        return add(size, account);
    }

    @Override
    public boolean add(int index, Account account) {
        Objects.checkIndex(index, size);
        Objects.requireNonNull(account, "Account - null");
        checkDuplicateAccountForNumber(account);
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
        Objects.checkIndex(index, size);
        return getNode(index).value;
    }

    @Override
    public Account get(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        CheckPattern.checkNumber(accountNumber);
        return getNodeByNumber(accountNumber).value;
    }

    @Override
    public boolean hasAccount(String accountNumber) throws InvalidAccountNumberException {
        return indexOf(accountNumber) != -1;
    }

    @Override
    public Account set(int index, Account account) throws DublicateAccountNumberException {
        Objects.checkIndex(index, size);
        Objects.requireNonNull(account, "Account - null");
        checkDuplicateAccountForNumber(account);
        Node node = getNode(index);
        Account removedAccount = node.value;
        node.value = account;
        return removedAccount;
    }

    @Override
    public Account remove(int index) {
        Objects.checkIndex(index, size);
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
    public boolean remove(Account account) {
        Objects.requireNonNull(account, "Account - null");
        if (indexOf(account)>0) {
            remove(indexOf(account));
            return true;
        }
        else
            return false;
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
       return -1;
    }

    @Override
    public double debtTotal() {
        Node node = head.next;
        double debtTotal = 0;
        for (int i = 0; i < size; i++) {
            if (node.value instanceof Credit)
                debtTotal += node.value.getBalance();
            node = node.next;
        }
        return debtTotal;
    }

    @Override
    public int indexOf(String accountNumber) {
        Objects.requireNonNull(accountNumber, "AccountNumber - null");
        CheckPattern.checkNumber(accountNumber);
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.getNumber().equals(accountNumber)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    @Override
    public boolean remove(Object o) {
        //todo это лучше не делать default и удалять без foreach done
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(o)) {
                remove(i);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node node=head.next;
        for (int i=0;i<size;i++)
        {
            if (!c.contains(node.value))
            {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
            //todo это надо переопределить в классах . массив - делаем все элементы null в нодах делаем все ссылки null done
        Node node=head.next;
            for (int i=0;i<size;i++)
            {
                node.value=null;
               node=node.next;
            }
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
        for (Account account:this)
        {
            sb.append(account.toString()).append("\n");
        }
        sb.append("total: ").append(totalBalance());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = getCreditScore() ^ name.hashCode();
        for (Account account: this)
        {
            hash ^= account.hashCode();
        }
        return hash;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Entity))
            return false;
        Entity obj = (Entity) object;
        if (!(((obj.name.equals(this.name)) && (obj.getCreditScore() == this.getCreditScore()) && (obj.size == this.size))))
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
    }

    private void checkDuplicateAccountForNumber(Account account) throws DublicateAccountNumberException {
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.getNumber().equals(account.getNumber()))
                throw new DublicateAccountNumberException("Account with this number already exists");
            node = node.next;
        }
    }

    @Override
    public Iterator<Account> iterator() {
        return new AccountIterator();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) a = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        //todo набиваем либо accounts либо a элементами this (через foreach разумеется)
        for (Account account : this)
        {

        }
        return null;
    }


    private class AccountIterator implements Iterator<Account> {
        Node node=head.next;
        int index=0;

        public boolean hasNext() {
            return index < size;
        }

        public Account next() {
            Account value = node.value;
               index++;
               node=node.next;
           return value;
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
