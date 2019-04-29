package rpis71.klimovich.oop.model;
import  java.lang.Cloneable;

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
    public String toString()
    {
        return String.format("number: " + number + " balance: " + balance);
    }
    public int hashCode()
    {
        return (Double.hashCode(balance)*number.hashCode());
    }
    public boolean equals(Object object)
    {
       if (object instanceof AbstractAccount &&(((AbstractAccount) object).number==this.number) && ((AbstractAccount) object).number.equals(this.number))
            return true;
         else
            return false;
    }
    protected Object clone()throws CloneNotSupportedException
    {
        return super.clone();
    }

}
