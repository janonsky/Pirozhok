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
        //todo инициализировать head done
        this.head=this.tail=null;//??
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
        Node newNode= new Node(account,null);
        Node node = head.next;
        /*todo  1) список пуст size == 0 done
        2)index = 0
        3) index = size
        4) node = getNode(index-1)
        */
        if (size==0) {
           head.next= newNode;
        }else if(index==0){
               newNode.next= node;
               head.next=newNode;
            }else if (index==size){
                    newNode.next=head;
                    tail.next=newNode;
                    tail=newNode;
                }else
                    {
                    node=getNode(index-1);
                        newNode.next=node.next;
                        node.next= newNode;
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
        //todo сделай removeNode(index) с проверками, аналогичными add(index) done
        Node removedNode=getNode(index);
        Node node;
        if (size!=0)
        {
            if(index==0)
                head.next=removedNode.next;
            else if (index==size)
            {//??
                node=getNode(index-1);
                tail=node;
                tail.next=head;
            }
            else {
                node= getNode(index-1);
                node.next=removedNode.next;
            }
            size--;
        }
        return removedNode.value;
    }

    @Override
    public Account remove(String accountNumber) {
        //todo циклом по нодам, пока node.next.value.getAccountNumber() != true done
        Node node=head.next;
        for(int i=0;i<size;i++)
        {
            if (node.value.getNumber().equals(accountNumber))
            {
               return remove(i);
            }
            node=node.next;
        }
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
    public Credit[] getCreditAccounts() { //???????
        return new Credit[0];
    }

    @Override
    public boolean remove(Account account) {

    }

    @Override
    public int indexOf(Account account) {
        return 0;
    }

    @Override
    public double debtTotal() {
        return 0;
    }

    @Override
    public int indexOf(String accountNumber) {
        //todo циклом по нодам done
        Node node=head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.getNumber().equals(accountNumber))
            {
                return i;
            }
            node=node.next;
        }
        return -1;
    }

    private Node getNode(int index){
        int numberNode=0;
        Node currentNode=head.next;
        while (numberNode<index)
        {
            currentNode=currentNode.next;
            numberNode++;
        }
        return currentNode.next; //todo проверь done

    }

    private Node getNodeByNumber(String accountNumber) {
        Node node = head.next;
        Node currentNode=null; //todo имя - гавно dcne
        int index = 0; //todo проверь done
        while (index <= size) {
            if (node.value.getNumber().equals(accountNumber))
            {
                currentNode = node;
                break;
            }
            index++;
            node= node.next;
        }
        return currentNode;
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
