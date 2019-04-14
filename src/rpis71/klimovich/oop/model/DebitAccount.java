package rpis71.klimovich.oop.model;

public class DebitAccount {
    private double balance;
    private String number;

    public DebitAccount(String number, int balance) {
        this.number = number;
        this.balance = balance;
    }
    public DebitAccount()
    {
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

