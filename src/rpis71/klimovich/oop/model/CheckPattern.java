package rpis71.klimovich.oop.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPattern {
    //todo херня. делай метод и паттерн статичными done
    private static Pattern pattern=Pattern.compile("[4]{1}[045]{1}[0-9]{3}810[0-9]{1}[0-9]{4}[0-9]{7}");
    public CheckPattern()
    {
    }
    //todo и вызывай метод как процедуру done
    public static void checkNumber(String number) throws InvalidAccountNumberException {
        Matcher matcher=pattern.matcher(number);
        if (matcher.find())
            throw new InvalidAccountNumberException();
    }
}
