package rpis71.klimovich.oop.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPattern {
    private Pattern pattern=Pattern.compile("[4]{1}[045]{1}[0-9]{3}[8]{1}[1]{1}[0]{1}[0-9]{1}[0-9]{4}[0-9]{7}");
    public CheckPattern()
    {
    }
    public boolean checkNumber(String number)
    {
        Matcher matcher=pattern.matcher(number);
        return matcher.find();
    }
}
