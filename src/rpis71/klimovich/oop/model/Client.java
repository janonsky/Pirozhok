package rpis71.klimovich.oop.model;

public interface Client {
    boolean add(Account account) throws DublicateAccountNumberException;
    boolean add(int index,Account account) throws DublicateAccountNumberException;
    Account get(int index);
    Account get(String accountNumber) throws InvalidAccountNumberException;
    boolean hasAccount(String accountNumber) throws InvalidAccountNumberException;
    Account set(int index,Account account) throws DublicateAccountNumberException;
    Account remove(int index);
    Account remove(String accountNumber) throws InvalidAccountNumberException;
    int size();
    Account[] getAccounts();
    Account[] sortedAccountByBalance();
    double totalBalance();
    String getName();
    void setName(String name);
    int getCreditScore();
    void addCreditScores(int creditScores);
   default ClientStatus getStatus()
   {
       if (getCreditScore() >= ClientStatus.GOOD.getCreditScoreBound())
           return ClientStatus.GOOD;
      else if (getCreditScore() >= ClientStatus.GOLD.getCreditScoreBound())
           return ClientStatus.GOLD;
      else if(getCreditScore() >= ClientStatus.PLATINUM.getCreditScoreBound())
           return ClientStatus.PLATINUM;
      else if(getCreditScore() >= ClientStatus.RISKY.getCreditScoreBound())
           return ClientStatus.RISKY;
      else if(getCreditScore() >= ClientStatus.BAD.getCreditScoreBound())
           return ClientStatus.BAD;
       return null;
   }
    Account[] getCreditAccounts();

    boolean remove(Account account) throws InvalidAccountNumberException;
    int indexOf(Account account) throws InvalidAccountNumberException;
    double debtTotal();
    int indexOf(String accountNumber) throws InvalidAccountNumberException;
}
