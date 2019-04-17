package rpis71.klimovich.oop.model;

public class Entity implements Client {

    public class Node {
        Node next;
        Account value;

        public Node(Account value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
    private Node head;
    private Node tail;
    private String name;
    private int size;
    public Entity(String name)
    {
        this.head=null;
        this.tail=null;
        this.name=name;
    }
    public Entity(Account[] accounts,String name)
    {

    }

    @Override
    public Boolean add(Account account) {
        //добовление в конец
        Node node = new Node(account, null);
        if (tail==null) head=tail=node;
        else {
            node.next=tail.next;
            tail.next=node;
            tail=node;
        }
        size++;
        return true;
    }

    private Node getNode(int index){
        int numderNode=1;
        Node currentNode=head.next;
        while (numderNode<index)
        {
            currentNode=currentNode.next;
            numderNode++;
        }
        return currentNode.next;

    }
    @Override
    public Boolean add(int index, Account account) {
        Node node = new Node(account, null), nextNode,currentNode;
        if (tail == null) {
            head.next = node;
            tail = node;
        } else if (index == 0) return false;
        else if (index == 1)  //уточнить считается ли head за элемент списка
        {
            nextNode = head.next;
            node.next = nextNode;
            head.next = node;
        } else if (index == size) {
            node.next = head;
            tail.next = node;
            tail = node;
        } else
        {

        }
        return null;
    }

    @Override
    public Account get(int index) {
        return getNode(index).value;
    }

    private Node getNodeByNumber(String accountNumber) {
        Node node = head;
        Node temp=null;
        int index = 1;
        while (index <= size) {
            if (node.value.getNumber().equals(accountNumber))
            {
                temp = node;
                break;
            }
            index++;
            node= node.next;
        }
    return temp;
    }
    @Override
    public Account get(String accountNumber) {
        return getNodeByNumber(accountNumber).value;
    }

    @Override
    public Boolean hasAccount(String accountNumber) {
        return (getNodeByNumber(accountNumber)!=null);
    }

    @Override
    public Account set(int index, Account account) {
       Account removedAccount=get(index);
       Node node= getNode(index);
       node.value=account;
       return removedAccount;
    }

    @Override
    public Account remove(int index) { //hz
        return null;
    }

    @Override
    public Account remove(String accountNumber) { //hz
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Account[] getAccounts() {
        Node node=head;
        Account[] accounts=new Account[size];
        for(int i=0;i<size;i++)
        {
            accounts[i]=node.value;
            node=node.next;
        }
        return accounts;
    }

    @Override
    public Account[] sortedAccountByBalance() {
        Account[] sortedAccountsByBalance = getAccounts();
        Account account;
        for (int i = 0; i < size - 1; i++)
            for (int j = 0; j < size - i; j++)
                if (sortedAccountsByBalance[j].getBalance() > sortedAccountsByBalance[j + 1].getBalance()) {
                    account = sortedAccountsByBalance[j];
                    sortedAccountsByBalance[j] = sortedAccountsByBalance[j + 1];
                    sortedAccountsByBalance[j + 1] = account;
                }
        return sortedAccountsByBalance;
    }
    @Override
    public double totalBalance() {
       double totalBalance=0;
       Node node=head;
       for(int i=0;i<size;i++)
       {
           totalBalance+=node.value.getBalance();
           node=node.next;
       }
       return totalBalance;
    }

    @Override
    public String getName() {
        return name;//??????
    }

    @Override
    public void setName(String name) {
        this.name=name;//????????
    }
}
