package rpis71.klimovich.oop.model;

public class Entity implements Client {
    private Node head;
    private Node tail;
    private String name;
    private int size;
    public Entity(Node head, Node tail)
    {
        this.head=head;
        this.tail=tail;
    }
    public Entity()
    @Override
    public Boolean add(Account account) {
        return null;
    }

    @Override
    public Boolean add(int index, Account account) {
        return null;
    }

    @Override
    public Account get(int index) {
        return null;
    }

    @Override
    public Account get(String accountNumber) {
        return null;
    }

    @Override
    public Boolean hasAccount(String accountNumber) {
        return null;
    }

    @Override
    public Account set(int index, Account account) {
        return null;
    }

    @Override
    public Account remove(int index) {
        return null;
    }

    @Override
    public Account remove(String accountNumber) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Account[] getAccounts() {
        return new Account[0];
    }

    @Override
    public Account[] sortedAccountByBalance() {
        return new Account[0];
    }

    @Override
    public double totalBalance() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }
}
