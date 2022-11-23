import expanses.ExpanseOutputService;
import expanses.ExpansesInputService;
import input.UserInputService;

public class Main {
    public static void main(String[] args) {
        // Create instances of the  UserInputService and expansesInputService
        UserInputService userInputService = new UserInputService();
        ExpansesInputService expansesInputService = new ExpansesInputService();

        //Greet the User and save his/her name
        System.out.println("Welcome to the expanses program!");
        String name = userInputService.getStringFromUserWithMessage("What is your name?");

        // Ask the user for the budget and expanses
        int budget = expansesInputService.getBudget();
        int[][] expanses = expansesInputService.getExpanses();

        //Always be nice to the user :)
        System.out.printf(
                "Thanks for your input %s!\n" +
                "Enjoy the Options to analyse your expanses!", name);

        // Create an instance of the output service and pass the expanses and budget to it
        ExpanseOutputService expanseOutputService = new ExpanseOutputService(expanses, budget);

        //Start the menu
        expanseOutputService.selectAction();

        //Exit the program
        System.out.printf("It was a pleasure to help you %s!", name);

    }
}