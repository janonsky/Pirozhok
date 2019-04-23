package rpis71.klimovich.oop.model;

//todo допиши
public class CreditAccount extends AbstractAccount implements Credit  {
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
