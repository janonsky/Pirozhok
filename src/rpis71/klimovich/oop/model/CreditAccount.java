package rpis71.klimovich.oop.model;

public class CreditAccount extends AbstractAccount  {
 double APR;

    public CreditAccount(String number,double balance,double APR)
    {
        super(number,balance);
        this.APR=APR;
    }
    public CreditAccount ()
    {
        this(" ",0,0.3);
    }
}
