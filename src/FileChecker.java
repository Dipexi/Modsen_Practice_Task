import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileChecker {
    private static final String folderPath = "C:/";
    private static final String fileName = "exchange rate.txt";
    private static final String regexDollar = "^\\$(\\d+(\\.\\d+)?)$";
    private static final String regexRuble = "^\\р(\\d+(\\.\\d+)?)$";
    private static final String exceptionDollarLine = "Invalid dollar line: ";
    private static final String exceptionRubleLine = "Курс рубля: ";
    private static final String courseDollar = "Курс доллара: ";
    private static final String courseRuble = "Invalid ruble line: ";
    private static final String existenceVerificationErrorMassage = "File does not exist.";

    public void processFile(String inputString) {
        double dollarCourse = 0;
        double rubleCourse = 0;
        String fileDollarLine;
        String fileRubleLine;

        if (!isFileExists(folderPath, fileName)) {
            System.out.println(existenceVerificationErrorMassage);
            System.exit(0);
        }

        try (Scanner scanner = new Scanner(new File(folderPath, fileName))) {
            if (scanner.hasNextLine()) {
                fileDollarLine = scanner.nextLine();
                dollarCourse = getValueFromDollarLine(fileDollarLine, regexDollar);

                if (dollarCourse != -1) {
                    System.out.println(courseDollar + dollarCourse);
                } else {
                    System.out.println(exceptionDollarLine + fileDollarLine);
                }
            }

            if (scanner.hasNextLine()) {
                fileRubleLine = scanner.nextLine();
                rubleCourse = getValueFromRubleLine(fileRubleLine, regexRuble);

                if (rubleCourse != -1) {
                    System.out.println(exceptionRubleLine + rubleCourse);
                } else {
                    System.out.println(courseRuble + fileRubleLine);
                }
            }

            Calculator processor = new Calculator(dollarCourse, rubleCourse, inputString);
            processor.processExchange();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isFileExists(String folderPath, String fileName) {
        File file = new File(folderPath, fileName);
        return file.exists();
    }

    private double getValueFromDollarLine(String line, String regexDollar) {
        Pattern pattern = Pattern.compile(regexDollar);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            return -1;
        }
    }

    private double getValueFromRubleLine(String line, String regexRuble) {
        Pattern pattern = Pattern.compile(regexRuble);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            return -1;
        }
    }
}