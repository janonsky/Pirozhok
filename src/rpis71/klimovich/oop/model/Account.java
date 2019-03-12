package rpis71.klimovich.oop.model;

public class Account {
    private double balance=0;
    private String number;

    public Account(String number, int balance) {
        this.number = number;
        this.balance = balance;
    }
    public Account()
    { }
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

