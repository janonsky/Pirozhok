package rpis71.klimovich.oop.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;

public abstract class  AbstractAccount implements Account {
    private String number;
    private double balance;
    private LocalDate creationDate;
    private LocalDate expirationDate;
    protected AbstractAccount(String number,LocalDate expirationDate) throws InvalidAccountNumberException {
        this(number,0,LocalDate.now(),expirationDate);
        CheckPattern.checkNumber(number);
    }
    protected AbstractAccount(String number,double balance,LocalDate creationDate,LocalDate expirationDate) throws InvalidAccountNumberException {
        Objects.requireNonNull(number,"number-null");
        Objects.requireNonNull(expirationDate,"Expirationdate-null");
        Objects.requireNonNull(creationDate,"Createdate-null");
        if (creationDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Illegal creationDate");
        if (creationDate.isAfter(expirationDate))
            throw new IllegalArgumentException("Illegal expirationDate");
        CheckPattern.checkNumber(number);
        this.number=number;
        this.balance=balance;
        this.creationDate=creationDate;
        this.expirationDate=expirationDate;
    }
    @Override
   public String getNumber()
    {
        return number;
    }
    @Override
   public void setNumber(String number) throws InvalidAccountNumberException {
        CheckPattern.checkNumber(number);
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
        return String.format("number: %s balance: %f creationDate: %tF expirationDate: %s",getNumber(),getBalance(),getCreationDate(),getExpirationDate());
    }
    public int hashCode()
    {
        return (Double.hashCode(balance)*number.hashCode()*creationDate.hashCode()*expirationDate.hashCode());
    }
    public boolean equals(Object object)
    {
          if (!(object instanceof Account))
              return false;
          return
                  ((AbstractAccount) object).balance == this.balance &&
                  ((AbstractAccount) object).number.equals(this.number)&&
                  ((AbstractAccount) object).expirationDate.equals(expirationDate) &&
                  ((AbstractAccount) object).creationDate.equals(this.creationDate);
    }
    public Account clone()throws CloneNotSupportedException
    {
        return (Account) super.clone();
    }
    @Override
    public LocalDate getCreationDate()
    {
        return this.creationDate;
    }
    @Override
    public LocalDate getExpirationDate()
    {
        return this.expirationDate;
    }
    @Override
    public void setExpirationDate(LocalDate expirationDate)
    {
        Objects.requireNonNull(expirationDate, "expirationDate - null");
        if (creationDate.isAfter(expirationDate))
            throw new IllegalArgumentException("IllegalExpirationDate");
        this.expirationDate = expirationDate;
    }@Override
    public int monthesQuantityBeforeExpiration()
   {
       Period period=Period.between(LocalDate.now(),expirationDate);
       //period.toTotalMonths(); //todo используй done
       if (LocalDate.now().getDayOfMonth()>25)
           return (int)period.toTotalMonths();
       else
           return (int)period.toTotalMonths()+1;
   }

}
