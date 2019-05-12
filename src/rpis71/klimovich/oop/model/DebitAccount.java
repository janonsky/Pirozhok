package rpis71.klimovich.oop.model;

import java.time.LocalDate;

public class DebitAccount extends AbstractAccount {

    public DebitAccount(String number, int balance, LocalDate creationDate,LocalDate expirationDate) throws InvalidAccountNumberException {
        super(number,balance,creationDate,expirationDate);
        CheckPattern pattern=new CheckPattern();
        if (pattern.checkNumber(number)) throw new InvalidAccountNumberException();
        if (balance<0)
            throw new IllegalArgumentException("NullPointerException");
    }
    public DebitAccount(String number,LocalDate expirationDate) throws InvalidAccountNumberException {
       super(number, expirationDate);
        CheckPattern pattern=new CheckPattern();
        if (pattern.checkNumber(number)) throw new InvalidAccountNumberException();
    }
    public String toString() {
        return  String.format("Debit account - %s", super.toString());
    }
    public int hashCode()
    {
        return super.hashCode()*53;
    }
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

