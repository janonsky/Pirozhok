package rpis71.klimovich.oop.model;

public abstract class  AbstractAccount implements Account {
    private String number;
    private double balance;
    AbstractAccount()
    {
        this("",0);
    }
    AbstractAccount(String number,double balance)
    {
        this.number=number;
        this.balance=balance;
    }
    @Override
   public String getNumber()
    {
        return number;
    }
    @Override
   public void setNumber(String number)
    {
        this.number=number;
    }
    @Override
   public double getBalance()
   {
       return balance;
   }
  @Override
    public void setBalance(double balance)
  {
    this.balance=balance;
  }

}
