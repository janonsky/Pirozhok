package rpis71.klimovich.oop.model;

import java.time.LocalDate;
import java.time.Period;

public class CreditAccount extends AbstractAccount implements Credit  {
private double APR;

    public CreditAccount () throws InvalidAccountNumberException {
        this("",0,0.3,LocalDate.now(),LocalDate.now().plusYears(5));//дата окончания????
    }
    public CreditAccount(String number, double balance, double APR,LocalDate creationDate,LocalDate expirationDate) throws InvalidAccountNumberException {
        super(number,balance,creationDate,expirationDate);
        this.APR=APR;
        if (balance>0)
            throw new IllegalArgumentException("NullPointerException");
    }
    @Override
    public double getAPR() {
        return APR;
    }

    @Override
    public void setAPR(double APR) {
        this.APR=APR;
    }

    @Override
    public double nextPaymentValue() {
        //todo переделай done
        double period = Period.between(LocalDate.now(), getExpirationDate()).toTotalMonths();
        return getBalance() * (1 + getAPR() * (period / 12)) / (period);
    }
    @Override
    public LocalDate nextPaymentDate() {
        LocalDate now = LocalDate.now();
        if (now.getDayOfMonth()<25)
            return LocalDate.of(now.getYear(), now.getMonth(), 25);
        else
            return LocalDate.of(now.getYear(),now.getMonth().plus(1),25);
    }

    public String toString()
    {
        return String.format("Credit account - %s APR: %f",super.toString(),getAPR());
    }
    public int hashCode()
    {
        return (super.hashCode()*Double.hashCode(APR))*71;
    }
    public boolean equals(Object object)
    {
        //todo super.equals?
        return (object instanceof CreditAccount &&
                ((CreditAccount)object).getNumber().equals(this.getNumber())&&
                Double.compare(this.getBalance(), ((CreditAccount)object).getBalance()) == 0 &&
                ((CreditAccount)object).getAPR()==this.getAPR()); //todo
    }
    public Account clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int monthesQuantityBeforeExpiration() {
        //todo месяцы не верно done
        Period period=Period.between(LocalDate.now(),getExpirationDate());
        if (LocalDate.now().getDayOfMonth()>25)
            return (int)period.toTotalMonths();
        else
            return (int)period.toTotalMonths()+1;
    }
}
