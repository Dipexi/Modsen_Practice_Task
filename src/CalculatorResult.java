import java.text.DecimalFormat;

public class CalculatorResult {
    private final String calcString;
    private final String CurrencyString;
    private static final String substringToRemove = "toRuble";
    private static final String substringToRemove0 = "toDollar";
    private static final String substringToRemove1 = "\\(";
    private static final String substringToRemove2 = "\\)";
    private static final String substringToRemove3 = "\\$";
    private static final String regexEmptySymbol = "\\s";
    private static final String regexMathSymbols = "(?=[-+])";
    private static final char operatorPlus = '+';
    private static final char operatorMinus = '-';
    private static final String currencyDollar = "Dollar";
    private static final String currencyRuble = "Ruble";
    private static final String regexRubleSymbol = "р";
    private static final String regexDollarSymbol = "$";
    private static final String resultString = "Результат: ";
    private static final String decimalFormat = "#.##";

    public CalculatorResult(String calcString, String CurrencyString) {
        this.calcString = calcString;
        this.CurrencyString = CurrencyString;
        CutExchange(calcString, CurrencyString);
    }

    public void CutExchange(String calcString, String CurrencyString) {
        String CutedcalcString = calcString
                .replaceAll(substringToRemove, "")
                .replaceAll(substringToRemove0, "")
                .replaceAll(substringToRemove1, "")
                .replaceAll(substringToRemove2, "")
                .replaceAll(substringToRemove3, "")
                .replaceAll(regexRubleSymbol, "");
        CutedcalcString = CutedcalcString.replaceAll(regexEmptySymbol, "");
        String[] parts = CutedcalcString.split(regexMathSymbols);
        double result = Double.parseDouble(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            char operator = parts[i].charAt(0);
            double operand = Double.parseDouble(parts[i].substring(1));

            if (operator == operatorPlus) {
                result += operand;
            } else if (operator == operatorMinus) {
                result -= operand;
            }
        }

        DecimalFormat df = new DecimalFormat(decimalFormat);
        String formattedResult = df.format(result);

        if (CurrencyString.startsWith(currencyDollar)) {
            toDollarOutput(formattedResult);
        }
        if (CurrencyString.startsWith(currencyRuble)) {
            toRubleOutput(formattedResult);
        }
    }

    public void toRubleOutput(String formattedResult)
    {
        System.out.println(resultString + formattedResult + regexRubleSymbol);
    }

    public void toDollarOutput(String formattedResult)
    {
        System.out.println(resultString + regexDollarSymbol + formattedResult);
    }
}