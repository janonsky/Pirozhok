package rpis71.klimovich.oop;

import rpis71.klimovich.oop.model.Account;

public interface CompareAccount extends Comparable<Account> {
    int compareTo(Account o);
}
