import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileChecker {
    public static void main(String[] args) {
        String folderPath = "C:/";
        String fileName = "exchange rate.txt";

        if (isFileExists(folderPath, fileName)) {
            System.out.println("File exists!");

            try (Scanner scanner = new Scanner(new File(folderPath, fileName))) {
                double dollarCourse = 0;
                double rubleCourse = 0;
                String inputString = "";


                if (scanner.hasNextLine()) {
                    String line1 = scanner.nextLine();
                    dollarCourse = getValueFromDollarLine(line1);

                    Scanner scannerString = new Scanner(System.in);
                    System.out.print("Введите строку: ");
                    inputString = scannerString.nextLine();

                    if (dollarCourse != -1) {
                        System.out.println("Курс доллара: " + dollarCourse);
                    } else {
                        System.out.println("Invalid dollar line: " + line1);
                    }
                }

                if (scanner.hasNextLine()) {
                    String line2 = scanner.nextLine();
                    rubleCourse = getValueFromRubleLine(line2);
                    if (rubleCourse != -1) {
                        System.out.println("Курс рубля: " + rubleCourse);
                    } else {
                        System.out.println("Invalid ruble line: " + line2);
                    }
                }

                Calculator processor = new Calculator(dollarCourse, rubleCourse, inputString);
                processor.processExchange();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    private static boolean isFileExists(String folderPath, String fileName) {
        File file = new File(folderPath, fileName);
        return file.exists();
    }

    private static double getValueFromDollarLine(String line) {
        Pattern pattern = Pattern.compile("^\\$(\\d+(\\.\\d+)?)$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            return -1;
        }
    }

    private static double getValueFromRubleLine(String line) {
        Pattern pattern = Pattern.compile("^\\р(\\d+(\\.\\d+)?)$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return Double.parseDouble(matcher.group(1));
        } else {
            return -1;
        }
    }
}
