import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Calculator {
    private double dollarCourse;
    private double rubleCourse;
    private String inputString;

    public Calculator(double dollarCourse, double rubleCourse, String inputString) {
        this.dollarCourse = dollarCourse;
        this.rubleCourse = rubleCourse;
        this.inputString = inputString;
    }

    public void processExchange() {

        if (inputString.startsWith("toDollar")) {
            double result = toDollar();
        } else if (inputString.startsWith("toRuble")) {
            double result = toRuble();
        } else {
            System.out.println("Invalid input format");
        }

    }
    private double toDollar() {
        String CurrencyString = "Dollar";
        String calcString = inputString;

        Pattern pattern = Pattern.compile("\\d+р");
        Matcher matcher = pattern.matcher(calcString);

        matcher = pattern.matcher(calcString);
        while (matcher.find()) {
            String match = matcher.group();
            if (match.endsWith("р")) {
                int amount = Integer.parseInt(match.substring(0, match.length() - 1));
                calcString = calcString.replace(match, String.valueOf(amount * rubleCourse));
            }
        }

        CalculatorResult CalculatorResult = new CalculatorResult(calcString, CurrencyString);

        return 0;
    }


    private double toRuble() {
        String CurrencyString = "Ruble";
        String calcString = inputString;

        Pattern pattern = Pattern.compile("\\$\\d+");
        Matcher matcher = pattern.matcher(calcString);

        while (matcher.find()) {
            String match = matcher.group();
            if (match.startsWith("$")) {
                int amount = Integer.parseInt(match.substring(1));
                calcString = calcString.replace(match, String.valueOf(amount * dollarCourse));
            }
        }

        CalculatorResult CalculatorResult = new CalculatorResult(calcString, CurrencyString);

        return 0;
    }

}