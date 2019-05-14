package rpis71.klimovich.oop.model;

import java.time.LocalDate;

public class DebitAccount extends AbstractAccount {

    public DebitAccount(String number, int balance, LocalDate creationDate,LocalDate expirationDate) throws InvalidAccountNumberException {
        super(number,balance,creationDate,expirationDate);
        if (balance<0)
            throw new IllegalArgumentException("NullPointerException");
    }
    public DebitAccount(String number,LocalDate expirationDate) throws InvalidAccountNumberException {
       super(number, expirationDate);
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

