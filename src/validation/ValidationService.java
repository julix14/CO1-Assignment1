package validation;

import input.UserInputService;

public class ValidationService {
    private final UserInputService userInputService = new UserInputService();

    public Integer validateInputIsInt(String message) {
        String input;
        do{
            input = userInputService.getStringFromUserWithMessage(message);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input is not a number");
            }
        }while (true);
    }

    public Integer validateInputIsInRange(String message, Integer min, Integer max) {
        Integer input;
        do {
            input = validateInputIsInt(message);
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("Input is not in range");
            }
        }while (true);
    }

}
