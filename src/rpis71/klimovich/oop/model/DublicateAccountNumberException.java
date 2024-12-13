package rpis71.klimovich.oop.model;

public class DublicateAccountNumberException extends RuntimeException {
    public DublicateAccountNumberException(){
        super();
    }
    public DublicateAccountNumberException(String message){
        super(message);
    }
    public DublicateAccountNumberException(String message,Exception exception){
        super(message,exception);
    }
}
