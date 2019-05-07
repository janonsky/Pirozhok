package rpis71.klimovich.oop.model;

import java.util.Arrays;

public class Entity implements Client {
    private Node head;
    private Node tail;
    private String name;
    private int size;
    private int creditScore;
    public Entity(String name)
    {
        this.head=new Node(null,null);
        this.name=name;
    }
    public Entity(Account[] accounts,String name,int creditScore)
    {
        this(name);
        for (int i = 0; i < accounts.length; i++)
            add(accounts[i]);
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
        if (size==0) {
           head.next= newNode;
           tail=newNode;
           tail.next=head.next; //todo с хера head? последний элемент списка ссылается на нуевой, а не на голову done
           size++;
        }else if(index==0){
               newNode.next= node;
               head.next=newNode;
               tail.next=newNode;
               size++;//todo tail должен ссылаться на нулевой элемент, то есть на новый done
            }else if (index==size){
                    tail.next=newNode;
                    tail=newNode;
                    tail.next=head.next; //todo с хера head? последний элемент списка ссылается на нуевой, а не на голову done
                    size++;
                }else
                    {
                    node=getNode(index-1);
                        newNode.next=node.next;
                        node.next= newNode;
                        size++;
                    }
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
        Node removedNode=getNode(index);
        Node node=getNode(index-1);
        if (size!=0)
        {
            if(index==0)
                head.next=removedNode.next;
            else if (index==size)
            {//todo это действие выполняется в обоих ветвях if - вынеси его вне if done
                tail=node;
                tail.next=head.next;//todo с хера head? последний элемент списка ссылается на нуевой, а не на голову done
            }
            else {
                node.next=removedNode.next;
            }
            size--;
        }
        return removedNode.value;
    }

    @Override
    public Account remove(String accountNumber) {
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
        if (indexOf(account)!=-1)
        {
            remove(indexOf(account));
            return true;
        }
        else return false;
    }

    @Override
    public int indexOf(Account account) {
        Node node=head.next;
        for(int i=0;i<size;i++)
        {
            if (node.value.equals(account))
            return i;
            node=node.next;
        }
        return -1;
         //todo никаких массивов - циклом по нодам done
        //todo здесь проверяются не номера а сами аккаунты  accounts[i].equals(account) done
    }

    @Override
    public double debtTotal() {//todo ну общий долг - по кредитам пробегаемся и берем их amount-ы
        return 0;
    }

    @Override
    public int indexOf(String accountNumber) {
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
        return currentNode.next;
    }

    public Node getNodeByNumber(String accountNumber) {
        Node node = head.next;
        Node currentNode=null;
        int index = 0;
        while (index < size) {
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
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node node = head.next;
        for (int i = 0; i < size; i++) {
            sb.append(node.value.toString() + "\n");
            node = node.next;
        } //todo ААААААААААААААААААА УБИРАЙ КОНКАТЕНАЦИЮ В МЕТОДАХ БИЛДЕРА ААААААААААААААААААА - замени на несколько вызовов append() done
        return String.format("Client%n name : %s%n creditScore : %d%n %s total : %f ",getName(),getCreditScore(),sb.toString(),totalBalance());
        //todo ААААААААААААААААААА УБИРАЙ КОНКАТЕНАЦИЮ В МЕТОДАХ БИЛДЕРА ААААААААААААААААААА - замени на несколько вызовов append() done
    }
    @Override
    public int hashCode()
    {
        Node currentNode=head.next;
        int hash=Integer.hashCode(getCreditScore());
        for (int i = 0; i <size ; i++)
            hash^=currentNode.value.hashCode();
        return hash^name.hashCode();
    }
    public boolean equals(Object object)
    {
        boolean result = true;//todo имя - гавно done
            Entity obj=(Entity) object;
        //todo чтоб не делать 1001 каст, заведи переменную и запиши туда (Individual) object и обращайся к ней. done
        if (obj instanceof Entity && ((obj.name == this.name) /*todo ты че творишь а?*/ && (obj.getCreditScore()==this.getCreditScore()) && (obj.size==this.size))){
            Node node=head.next;
            Node newNode=obj.head.next;
            for(int i=0;i<size;i++){
                if(!newNode.value.equals(node.value))
                    result=false;
                node=node.next;
                newNode=newNode.next;
            }
        }else
            result=false;
        return result;
    }
    protected Object clone()throws CloneNotSupportedException
    {
       // Node node=new Node();

        return super.clone();//todo клонирование должно быть глубоким. То есть нужно склонировать всю нодовую структуру
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
