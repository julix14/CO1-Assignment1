package validation;

import input.UserInputService;

public class ValidationService {
    private final UserInputService userInputService = new UserInputService();

    public Integer validateInputIsInt(String message) {
        String input;
        do{
            //Get a String Input from the user
            input = userInputService.getStringFromUserWithMessage(message);

            //To assert the input is an Integer, try to parse it to an Integer
            //If so, return the Integer
            //If not, print an error message and ask for input again
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
            //Get an Integer Input from the user
            input = validateInputIsInt(message);

            //To assert the input is in range, check if it is bigger than the minimum and smaller than the maximum
            //If the input is in the given range, return it
            //If not, print an error message and ask for input again
            if (input >= min && input <= max) {
                return input;
            } else {
                System.out.println("Input is not in range");
            }
        }while (true);
    }

}
