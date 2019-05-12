package rpis71.klimovich.oop.model;

import java.time.LocalDate;

public interface Account {
     String getNumber();
     void setNumber(String number) throws InvalidAccountNumberException;
     double getBalance();
     void setBalance(double balance);
     LocalDate getCreationDate();
     LocalDate getExpirationDate();
     void setExpirationDate(LocalDate expirationDate);
     int monthesQuantityBeforeExpiration();
}
