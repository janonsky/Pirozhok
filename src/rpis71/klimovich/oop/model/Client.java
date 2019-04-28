package rpis71.klimovich.oop.model;

public interface Client {
    boolean add(Account account);
    boolean add(int index,Account account);
    Account get(int index);
    Account get(String accountNumber);
    boolean hasAccount(String accountNumber);
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
    //todo вынеси как default метод интерфейса done
    //todo конструкция else-if аккурат для таких случаев подходит + метод нада использовать done
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

    int indexOf(String accountNumber);
}
