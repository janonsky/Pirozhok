package rpis71.klimovich.oop.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.IllegalFormatCodePointException;
import java.util.regex.Pattern;

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
        //todo переделай
        return getBalance()*(1+APR*(getExpirationDate().getYear()-getCreationDate().getYear()))/(getExpirationDate().getMonthValue()-getCreationDate().getMonthValue());
    }
    @Override
    public LocalDate nextPaymentDate() {
        //todo берем текущую даты и сравниваем дни для нее.
        LocalDate now = LocalDate.now();
        if (now.getDayOfMonth()<25)
            return date;
        else
        {
            date.plusMonths(1);
            return date;
        }
    }

    public String toString()
    {
        return String.format("Credit account - %s APR: %f",super.toString(),getAPR()); //todo это не формат done
    }
    public int hashCode()
    {
        return (super.hashCode()*Double.hashCode(APR))*71;
    }
    public boolean equals(Object object)
    {
        //todo Double.compare()
        return (object instanceof CreditAccount && ((CreditAccount)object).getNumber().equals(this.getNumber())&& ((CreditAccount)object).getBalance()==this.getBalance()&& ((CreditAccount)object).getAPR()==this.getAPR());
    }
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int monthesQuantityBeforeExpiration() {
        //todo месяцы не верно
        Period period=Period.between(getCreationDate(),getExpirationDate());
        if (LocalDate.now().getDayOfMonth()>25)
            return period.getYears()*12;
        else
            return period.getYears()*12+1;
    }
}
