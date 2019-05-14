package rpis71.klimovich.oop.model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

//todo чеки перенеси в приватные методы работы с нодами (если их вызываешь)
public class Entity implements Client {
    private Node head;
    private Node tail;
    private String name;
    private int size;
    private int creditScore;
    public Entity(String name)
    {
        this.head=new Node(null,null);
        //todo проверка имени
        this.name=name;
    }
    public Entity(Account[] accounts,String name,int creditScore) throws DublicateAccountNumberException
    {

        this(name);
        for (int i = 0; i < accounts.length; i++)
            add(accounts[i]);
        this.creditScore=creditScore;
    }

    @Override
    public boolean add(Account account) throws DublicateAccountNumberException {
        Objects.requireNonNull(account,"Account - null");
        checkDuplicateAccouuntForNumber(account);
        return add(size, account);
    }
    @Override
    public boolean add(int index, Account account) throws DublicateAccountNumberException {
        if (index<0||index<size)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException"); //todo
        Objects.requireNonNull(account,"Account - null");
        checkDuplicateAccouuntForNumber(account);
        Node node=head.next;
        Node newNode= new Node(account,null);
        if (size==0 && index==0) {
           head.next= newNode;
           tail=newNode;
           tail.next=head.next;
           size++;
        }else if(index==0){
               newNode.next= node;
               head.next=newNode;
               tail.next=newNode;
               size++;
            }else if (index==size){
                    tail.next=newNode;
                    tail=newNode;
                    tail.next=head.next;
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
        if (index<0||index<size)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException"); //todo
        return getNode(index).value;
    }

    @Override
    public Account get(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"AccountNumber - null");
        CheckPattern pattern=new CheckPattern();
        if (!(pattern.checkNumber(accountNumber)))
            throw new InvalidAccountNumberException();
        if (getNodeByNumber(accountNumber)!=null) //todo
            return getNodeByNumber(accountNumber).value;
        else
            throw new NoSuchElementException();
    }

    @Override
    public boolean hasAccount(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"AccountNumber - null");
        CheckPattern pattern=new CheckPattern();
        if (!(pattern.checkNumber(accountNumber)))
            throw new InvalidAccountNumberException();
        return  (getNodeByNumber(accountNumber)!=null); //todo лучше ручками пробедаться и вернуть true или false
    }

    @Override
    public Account set(int index, Account account) throws DublicateAccountNumberException {
        if (index<0||index<size)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");//todo
        Objects.requireNonNull(account,"Account - null");
        checkDuplicateAccouuntForNumber(account);
       Node node= getNode(index);
       Account removedAccount = node.value;
       node.value=account;
       return removedAccount;
    }

    @Override
    public Account remove(int index) {
        if (index<0||index<size)
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException"); //todo

        Node node=getNode(index-1);
        Node removedNode=node.next;
        if (size!=0)
        {
            if(index==0)
                head.next=removedNode.next;
            else if (index==size)
            {
                tail=node;
                tail.next=head.next;
            }
            else {
                node.next=removedNode.next;
            }
            size--;
        }
        return removedNode.value;
    }

    @Override
    public Account remove(String accountNumber) throws InvalidAccountNumberException {
        Objects.requireNonNull(accountNumber,"AccountNumber - null");
        CheckPattern pattern=new CheckPattern();
        if (!(pattern.checkNumber(accountNumber))) throw new InvalidAccountNumberException();
        Node node=head.next;
        for(int i=0;i<size;i++)
        {
            if (node.value.getNumber().equals(accountNumber))
            {
               return remove(i);
            }
            node=node.next;
        }
        throw new NoSuchElementException();
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
        Objects.requireNonNull(name,"name - null");
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
        Objects.requireNonNull(account,"Account - null");
        if (indexOf(account)!=-1)
        {
            remove(indexOf(account));
            return true;
        }
        else return false;
    }

    @Override
    public int indexOf(Account account) {
        Objects.requireNonNull(account,"Account - null");
        Node node=head.next;
        for(int i=0;i<size;i++)
        {
            if (node.value.equals(account))
            return i;
            node=node.next;
        }
        return -1;
    }

    @Override
    public double debtTotal() {
      Node node=head.next;
      double debtTotal=0;
      for (int i=0;i<size;i++)
      {
          //todo getBalance() только если value - кредит
          debtTotal+=node.value.getBalance();
          node=node.next;
      }
        return debtTotal;
    }

    @Override
    public int indexOf(String accountNumber) {
        Objects.requireNonNull(accountNumber,"AccountNumber - null");
        Node node=head.next;
        for (int i = 0; i < size; i++) {
            if (node.value.getNumber().equals(accountNumber))
            {
                return i;
            }
            node=node.next;
        }
        return -1;
        //todo NoSuchElementException
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

    private Node getNodeByNumber(String accountNumber) {
        Objects.requireNonNull(accountNumber,"AccountNumber - null");
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
        //todo см Individual
        boolean result = true;//todo имя - гавно done
        if (object instanceof Entity )
        {
            Entity obj=(Entity) object; //todo проверка типа перед кастом делается done
            if (((obj.name.equals(this.name)) /*todo ты че творишь а done*/ && (obj.getCreditScore()==this.getCreditScore()) && (obj.size==this.size))){
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
        }
        else
            result=false;
        return result;
    }
    protected Object clone()throws CloneNotSupportedException
    {
        Entity clone=(Entity)super.clone();

        //todo в цикле node.next = node.next.clone()

    }
    private void checkDuplicateAccouuntForNumber(Account account) throws DublicateAccountNumberException {
        Node node=head.next;
        for (int i=0;i<size;i++)
        {
            if (node.value.getNumber().equals(account.getNumber()))
                throw new DublicateAccountNumberException("Account with this number already exists");
            node=node.next;
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
            Node node = (Node)super.clone();
            node.value = node.value.clone();
            return Node;
        }
    }
}
