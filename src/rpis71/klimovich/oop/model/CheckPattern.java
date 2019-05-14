package rpis71.klimovich.oop.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPattern {
    //todo херня. делай метод и паттерн статичными
    private Pattern pattern=Pattern.compile("[4]{1}[045]{1}[0-9]{3}[8]{1}[1]{1}[0]{1}[0-9]{1}[0-9]{4}[0-9]{7}");
    public CheckPattern()
    {
    }
    //todo и вызывай метод как процедуру
    public void checkNumber(String number) throws InvalidAccountNumberException {
        Matcher matcher=pattern.matcher(number);
        if (matcher.find())
            throw new InvalidAccountNumberException();
    }
}
