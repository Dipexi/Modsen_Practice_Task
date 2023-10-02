import java.text.DecimalFormat;
public class CalculatorResult {
    private String calcString;
    private String CurrencyString;


    public CalculatorResult(String calcString, String CurrencyString) {
        this.calcString = calcString;
        this.CurrencyString = CurrencyString;
        CutExchange(calcString, CurrencyString);
    }

    public void CutExchange(String calcString, String CurrencyString) {
        String substringToRemove = "toRuble";
        String substringToRemove0 = "toDollar";
        String substringToRemove1 = "\\(";
        String substringToRemove2 = "\\)";
        String substringToRemove3 = "\\$";
        String substringToRemove4 = "р";
        String CutedcalcString = calcString
                .replaceAll(substringToRemove, "")
                .replaceAll(substringToRemove0, "")
                .replaceAll(substringToRemove1, "")
                .replaceAll(substringToRemove2, "")
                .replaceAll(substringToRemove3, "")
                .replaceAll(substringToRemove4, "");
        CutedcalcString = CutedcalcString.replaceAll("\\s", "");

        String[] parts = CutedcalcString.split("(?=[-+])");

        double result = Double.parseDouble(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            char operator = parts[i].charAt(0);
            double operand = Double.parseDouble(parts[i].substring(1));

            if (operator == '+') {
                result += operand;
            } else if (operator == '-') {
                result -= operand;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedResult = df.format(result);

        if (CurrencyString.startsWith("Dollar")) {
            toDollarOutput(formattedResult);
        }
        if (CurrencyString.startsWith("Ruble")) {
            toRubleOutput(formattedResult);
        }
    }

    public void toRubleOutput(String formattedResult)
    {
        System.out.println("Результат: " + formattedResult + "р");
    }

    public void toDollarOutput(String formattedResult)
    {
        System.out.println("Результат: " + "$" + formattedResult);
    }

}