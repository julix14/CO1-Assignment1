package input;

import java.util.Scanner;

public class UserInputService {
    private final Scanner scanner = new Scanner(System.in);
    public String getStringFromUserWithMessage(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
