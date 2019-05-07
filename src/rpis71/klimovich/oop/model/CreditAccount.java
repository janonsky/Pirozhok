package rpis71.klimovich.oop.model;

public class CreditAccount extends AbstractAccount implements Credit  {
private double APR;

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
    public void setAPR(double APR) {
        this.APR=APR;
    }
    public String toString()
    {
        return String.format("Credit account - "+super.toString()+"APR - "+this.APR); //todo это не формат
    }
    public int hashCode()
    {
        return (super.hashCode()*Double.hashCode(APR))*71;
    }
    public boolean equals(Object object)
    {
        //todo где проверки?
        if (this==object)
    return true;
        else
            return false;
    }
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
