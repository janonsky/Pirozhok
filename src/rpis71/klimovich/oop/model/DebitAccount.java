package rpis71.klimovich.oop.model;

public class DebitAccount extends AbstractAccount {

    public DebitAccount(String number, int balance) {
        super(number,balance);
    }
    public DebitAccount()
    {
       super();
    }
    public String toString() {
        return  String.format("Debit account - "+super.toString()); //todo это не format, это херня
    }
    public int hashCode()
    {
        return super.hashCode()*53;
    }
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

