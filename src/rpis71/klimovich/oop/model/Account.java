package rpis71.klimovich.oop.model;

public class Account {
    private double balance;
    private String number;

    public Account(String number, int balance) {
        this.number = number;
        this.balance = balance;
    }
    public Account()
    {
        //todo number="", balance=0 done
        this("",0);
    }
    public String getNumber()
    {
                return number;
    }
    public void setNumber(String number)
    {
                this.number=number;
    }
    public double getBalance()
    {
                return balance;
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
}

