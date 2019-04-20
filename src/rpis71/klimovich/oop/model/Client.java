package rpis71.klimovich.oop.model;

public interface Client {
    Boolean add(Account account);
    Boolean add(int index,Account account);
    Account get(int index);
    Account get(String accountNumber);
    Boolean hasAccount(String accountNumber);
    Account set(int index,Account account);
    Account remove(int index);
    Account remove(String accountNumber);
    int size();
    Account[] getAccounts();
    Account[] sortedAccountByBalance();
    double totalBalance();
    String getName();
    void setName(String name);
    int getCreditScore();
    void addCreditScores(int creditScores);
    ClientStatus getStatus();
    Credit[] getCreditAccounts();
}
