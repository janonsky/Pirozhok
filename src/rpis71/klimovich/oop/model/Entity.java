package rpis71.klimovich.oop.model;

//todo head не хранит value, имеет смысл только ссылка next, указывающая на 0-й элемент списка
public class Entity implements Client {
    private Node head;
    private Node tail;
    private String name;
    private int size;
    private int creditScore;
    public Entity(String name,int creditScore)
    {
        //todo инициализировать head
        this.name=name;
        this.creditScore=creditScore;
    }
    public Entity(Account[] accounts,String name,int creditScore)
    {
        this(name, creditScore);
        for (int i = 0; i < accounts.length; i++)
            add(accounts[i]);
        this.name = name;
        this.creditScore=creditScore;
    }

    @Override
    public boolean add(Account account) {
        return add(size, account);
    }
    @Override
    public boolean add(int index, Account account) {
        Node node = head.next;
        Node newNode = getNode(index);
        /*todo  1) список пуст size == 0
        2)index = 0
        3) index = size
        4) node = getNode(index-1)
        */
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
    public boolean hasAccount(String accountNumber) {
        return (getNodeByNumber(accountNumber)!=null);
    }

    @Override
    public Account set(int index, Account account) {
       Node node= getNode(index);
       Account removedAccount = node.value;
       node.value=account;
       return removedAccount;
    }

    @Override
    public Account remove(int index) {
        //todo сделай removeNode(index) с проверками, аналогичными add(index)
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
        //todo циклом по нодам, пока node.next.value.getAccountNumber() != true
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
    public Credit[] getCreditAccounts() { //???????
        return new Credit[0];
    }

    @Override
    public int indexOf(String accountNumber) {
        //todo циклом по нодам
        for (int i = 0; i < size; i++) {
            if (get(accountNumber).getNumber().equals(accountNumber))
                return i;
        }
        return -1;    }

    private Node getNode(int index){
        int numberNode=0;
        Node currentNode=head.next;
        while (numberNode<index)
        {
            currentNode=currentNode.next;
            numberNode++;
        }
        return currentNode; //todo проверь

    }

    private Node getNodeByNumber(String accountNumber) {
        Node node = head;
        Node temp=null; //todo имя - гавно
        int index = 0; //todo проверь
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
