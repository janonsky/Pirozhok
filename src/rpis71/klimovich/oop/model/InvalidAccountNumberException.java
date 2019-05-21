package rpis71.klimovich.oop.model;

//todo должно быть unchecked done
 public class InvalidAccountNumberException extends RuntimeException {
    public InvalidAccountNumberException()
    {
        super();
    }
    public InvalidAccountNumberException(String message, Exception exception){
        super(message, exception);
    }
    public InvalidAccountNumberException(String message){
        super(message);
    }
}
