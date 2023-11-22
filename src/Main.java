import java.util.Scanner;

public class Main {
    private static final String usersInputNotification = "Введите строку: ";

    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        System.out.print(usersInputNotification);
        String inputString = scannerString.nextLine();
        FileChecker FileChecker = new FileChecker();
        FileChecker.processFile(inputString);
    }
}