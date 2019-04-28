package rpis71.klimovich.oop.model;

//todo допиши done
public class CreditAccount extends AbstractAccount implements Credit  {
 double APR;

    public CreditAccount ()
    {
        this(" ",0,0.3);
    }
    public CreditAccount(String number,double balance,double APR)
    {
        super(number,balance);
        this.APR=APR;
    }
    @Override
    public double getAPR() {
        return APR;
    }

    @Override
    public void setAPR() {
        this.APR=APR;
    }
}
