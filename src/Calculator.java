import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private final double dollarCourse;
    private final double rubleCourse;
    private final String inputString;
    private static final String dollarLine = "toDollar";
    private static final String rubleLine = "toRuble";
    private static final String invalidSyntax = "Invalid input format";
    private static final String currencyDollar = "Dollar";
    private static final String currencyRuble = "Ruble";
    private static final String regexPatternDollar = "\\$\\d+";
    private static final String regexPatternRuble = "\\d+р";
    private static final String regexRubleSymbol = "р";
    private static final String regexDollarSymbol = "$";

    public Calculator(double dollarCourse, double rubleCourse, String inputString) {
        this.dollarCourse = dollarCourse;
        this.rubleCourse = rubleCourse;
        this.inputString = inputString;
    }

    public void processExchange() {
        double result;
        if (inputString.startsWith(dollarLine)) {
            toDollar();
        } else if (inputString.startsWith(rubleLine)) {
            toRuble();
        } else {
            System.out.println(invalidSyntax);
        }
    }

    private void toDollar() {
        String calcString = inputString;
        Pattern pattern = Pattern.compile(regexPatternRuble);
        Matcher matcher = pattern.matcher(calcString);
        matcher = pattern.matcher(calcString);

        while (matcher.find()) {
            String match = matcher.group();
            if (match.endsWith(regexRubleSymbol)) {
                int amount = Integer.parseInt(match.substring(0, match.length() - 1));
                calcString = calcString.replace(match, String.valueOf(amount * rubleCourse));
            }
        }

        CalculatorResult CalculatorResult = new CalculatorResult(calcString, currencyDollar);
    }

    private void toRuble() {
        String calcString = inputString;
        Pattern pattern = Pattern.compile(regexPatternDollar);
        Matcher matcher = pattern.matcher(calcString);

        while (matcher.find()) {
            String match = matcher.group();
            if (match.startsWith(regexDollarSymbol)) {
                int amount = Integer.parseInt(match.substring(1));
                calcString = calcString.replace(match, String.valueOf(amount * dollarCourse));
            }
        }

        CalculatorResult CalculatorResult = new CalculatorResult(calcString, currencyRuble);
    }
}