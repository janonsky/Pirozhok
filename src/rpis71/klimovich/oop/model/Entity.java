package rpis71.klimovich.oop.model;

public class Entity implements Client {
    private Node head;
    private Node tail;
    private String name;
    private int size;
    private int creditScore;
    public Entity(String name,int creditScore)
    {
        this.head=head;
        this.tail=tail;
        this.name=name;
        this.creditScore=creditScore;
    }
    public Entity(Account[] accounts,String name,int creditScore)
    {
        this.tail = tail;
        this.head =head;
        for (int i = 0; i < accounts.length; i++)
            add(accounts[i]);
        this.name = name;
        this.creditScore=creditScore;
    }

    @Override
    public Boolean add(Account account) {
        Node node = new Node(account, null);
        if (tail==null)
            head=tail=node;
        else {
            node.next=tail.next;
            tail.next=node;
            tail=node;
        }
        size++;
        return true;
    }
    @Override
    public Boolean add(int index, Account account) {
        Node node = head.next;
        Node newNode = getNode(index);
        for (int i = 0; i < index; i++) {
            if (i == index - 1) {
                newNode=node.next;
                node.next=newNode;
            }
            node = node.next;
        }
        size++;
        return true;
    }

    @Override
    public Account get(int index) {
        return getNode(index).value;
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
    public Account remove(int index) {
        Node removedNode=getNode(index);
        Node node=head.next;
        for(int i=0;i<index;i++){
            if(i==index-1){
                Node prev=getNode(index-1);
                prev.next=prev.next.next;
                this.size--;
            }
            node=node.next;
        }
        return removedNode.value;
    }

    @Override
    public Account remove(String accountNumber) {
        int index=IndexOf(accountNumber);
       if(index!=-1)
           return remove(index);
     return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Account[] getAccounts() {
        Node node=head.next;
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
       Node node=head.next;
       for(int i=0;i<size;i++)
       {
           totalBalance+=node.value.getBalance();
           node=node.next;
       }
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
    public ClientStatus getStatus() {
        if (getCreditScore()>=0 && getCreditScore()<3)
            return ClientStatus.GOOD;
        if (getCreditScore()>=3 && getCreditScore()<5)
            return ClientStatus.GOLD;
        if(getCreditScore()>=5)
            return ClientStatus.PLATINUM;
        if(getCreditScore()>=-2 && getCreditScore()<0)
            return ClientStatus.RISKY;
        if(getCreditScore()<=-2)
            return ClientStatus.BAD;
        return null;
    }

    @Override
    public Credit[] getCreditAccounts() { //???????
        return new Credit[0];
    }
    private int IndexOf(String accountNumber)
    {
        for (int i = 0; i < size; i++) {
            if (get(accountNumber).getNumber().equals(accountNumber))
                return i;
        }
        return -1;
    }

    private Node getNode(int index){
        int numberNode=1;
        Node currentNode=head.next;
        while (numberNode<index)
        {
            currentNode=currentNode.next;
            numberNode++;
        }
        return currentNode.next;

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

    public class Node {
        Node next;
        Account value;

        public Node(Account value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
