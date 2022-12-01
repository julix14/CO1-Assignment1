import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    final private static  String[] CATEGORIES = {"Food", "Transport", "Shopping", "Other"};

    private static String name;
    private static int[][] expanses;
    private static int budget;

    private static final List<String> menuItems = new ArrayList<>(List.of(
            "Display Daily Expanses",
            "Display Remaining Budget",
            "Display Expenses by Category",
            "Display relative Expanses by Category",
            "Display the Day with the highest Expenses",
            "Display the Day with the lowest Expenses",
            "Exit"));


    public static void main(String[] args) {
        //Greet the User and save his/her name
        System.out.println("Welcome to the expanses program!");
        name = getStringFromUserWithMessage("What is your name?");

        // Ask the user for the budget and expanses
        budget = getBudget();
        expanses = getExpanses();

        //Always be nice to the user :)
        System.out.printf(
                "Thanks for your input %s!\n" +
                "Enjoy the Options to analyse your expanses!", name);

        //Start the menu
        selectAction();

        //Exit the program
        System.out.printf("It was a pleasure to help you %s!", name);




    }

    // Helper methods for input validation
    public static int validateInputIsInt(String message) {
        String input;
        int tries = 0;
        do{
            //Get a String Input from the user
            input = getStringFromUserWithMessage(message);

            //To assert the input is an Integer, try to parse it to an Integer
            //If so, return the Integer
            //If not, print an error message and ask for input again
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input is not a number");
            }
            tries++;
        }while (tries < 3);
        System.out.println("You have entered an invalid input 3 times. Exiting the program");
        System.exit(1);
        return -1;
    }

    public static int validateInputIsInRange(String message, int min, int max) {
        String input;
        int tries = 0;
        do{
            //Get a String Input from the user
            input = getStringFromUserWithMessage(message);
            String regexString = "[" + min + "-" + max + "]";
            //Check if the input is a number and in the range
            if (!input.matches("^[0-9]+$")) {
                System.out.println("Input is not a number, please enter a number between " + min + " and " + max);
            } else if (!input.matches(regexString)) {
                System.out.println("Input is not in range, please enter a number between " + min + " and " + max);
            } else {
                return Integer.parseInt(input);
            }
            tries++;
        }while (tries < 3);

        System.out.println("You have entered an invalid input 3 times. Exiting the program");
        System.exit(1);
        return -1;
    }

    public static String getStringFromUserWithMessage(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextLine();
    }

    private static void waitForKeypress(){
        //Wait for the user to press a key
        System.out.println("\nPress 'Enter' to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    // Methods to get the budget and expanses from the user
    public static int[][] getExpanses() {
        //Get the number of days from the user
        int days = validateInputIsInt(name + ", please enter the number of days you want to enter expanses for");

        //Initialize the expanses array with the number of inserted days
        int[][] expanses = new int[days][CATEGORIES.length];
        for (int i = 0; i < days; i++) {
            //For each day, ask the user for the expanses for each category
            for (int j = 0; j < CATEGORIES.length; j++) {
                expanses[i][j] = validateInputIsInt(name +" enter expanses for " + CATEGORIES[j] + " for day " + (i + 1));
            }
        }
        return expanses;
    }

    public static int getBudget() {
        //Get the budget from the user
        return validateInputIsInt("Please enter your budget "+ name);
    }

    // Methods to create and
    public static void selectAction() {
        int selectedItem;
        do {
            //Print the Menu Items
            System.out.println("Menu-Items: \n");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.printf("[%d] -> %s\n", i, menuItems.get(i));
            }
            //Get the user input
            selectedItem = validateInputIsInRange("Please select one of the options", 0, menuItems.size() - 1);

            //Execute the selected action and if it's the exit action, break the loop and exit the program
            switch (selectedItem) {
                case 0 -> displayDailyExpanses();
                case 1 -> displayRemainingBudget();
                case 2 -> displayExpansesByCategory();
                case 3 -> displayRelativeExpansesByCategory();
                case 4 -> displayDayWithHighestExpanse();
                case 5 -> displayCategoryWithLowestExpanses();
            }
            waitForKeypress();
        } while (selectedItem != 6);
    }

    // Methods to execute the selected actions
    private static void displayDailyExpanses(){
        System.out.println("The sum of the expanses for each day is:");

        //Go through each day and each category and sum their values up
        //Then print the sum for that day
        for (int i = 0; i < expanses.length; i++) {
            int dailyExpanses = 0;
            for (int j = 0; j < expanses[i].length; j++) {
                dailyExpanses += expanses[i][j];
            }
            System.out.printf("Day %d: %d\n", i + 1, dailyExpanses);
        }
    }

    private static void displayRemainingBudget(){
        int totalExpanses = 0;

        //Go through each day and each category and sum their values up
        for (int[] day: expanses) {
            for (int expanse: day) {
                totalExpanses += expanse;
            }
        }

        //Print the remaining budget
        System.out.printf("The remaining budget is: %d€\n", budget - totalExpanses);
        System.out.printf("Or to say it in percentage: %d%%\n", (budget - totalExpanses) * 100 / budget);

    }

    private static int[][] getExpansesByCategory() {
        //Create an array to store the expanses by category
        int[] categoryExpanses = new int[CATEGORIES.length];
        int totalExpanses = 0;

        //Go through each day and each category and store the value in the categoryExpanses array
        //Also sum up the total expanses
        for( int[] day : expanses) {
            for (int i = 0; i < day.length; i++) {
                categoryExpanses[i] += day[i];
                totalExpanses += day[i];
            }
        }

        //return the categoryExpanses array and the total expanses in a 2D array
        return new int[][]{categoryExpanses, {totalExpanses}};
    }
    static final int CATEGORY_EXPANSES_POSITION = 0;
    static final int TOTAL_EXPANSES_POSITION = 1;
    private static void displayExpansesByCategory(){
        int[][] expansesByCategory = getExpansesByCategory();

        //Print the expanses by category
        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("The expanses for %s are: %d€\n", CATEGORIES[i], expansesByCategory[CATEGORY_EXPANSES_POSITION][i]);
        }
        System.out.printf("The total expanses for all categories are: %d€\n", expansesByCategory[TOTAL_EXPANSES_POSITION][0]);
    }

    private static void displayRelativeExpansesByCategory(){
        int[][] expansesByCategory = getExpansesByCategory();

        //Print the relative expanses by category
        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("The relative expanses for %s are: %d%%\n",  CATEGORIES[i], expansesByCategory[CATEGORY_EXPANSES_POSITION][i] * 100 / budget);
        }
        System.out.printf("The relative expanses for all categories are: %d%% from your budget\n", expansesByCategory[TOTAL_EXPANSES_POSITION][0] * 100 / budget);
    }

    private static void displayDayWithHighestExpanse(){
        int highestExpanses = 0;
        int dayWithHighestExpense = 0;

        //Go through each day and check if the expanse per day is higher than the "so far highest expanses"
        //If it is, overwrite the highestExpanses- and dayWithHighestExpanse-Variable
        for (int i = 0; i < expanses.length; i++) {
            int expansePerDay = 0;
            for (int expanse : expanses[i]) {
                expansePerDay += expanse;
            }
            if (expansePerDay > highestExpanses){
                highestExpanses = expansePerDay;
                dayWithHighestExpense = i+1;
            }
        }

        System.out.printf("The highest total expanse was %d€ on day number %d\n", highestExpanses, dayWithHighestExpense);
    }

    private static void displayCategoryWithLowestExpanses(){
        int[][] expansesByCategory = getExpansesByCategory();
        int lowestExpanse = expansesByCategory[0][0];
        int categoryWithLowestSum = 0;

        //Go through each day and each category and check if the expanse is lower than the "so far lowest expanses"
        //If it is, overwrite the lowest expanses variable
        for (int i = 0; i < expansesByCategory[0].length; i++) {
            if(lowestExpanse > expansesByCategory[0][i]){
                lowestExpanse = expansesByCategory[0][i];
                categoryWithLowestSum = i;
            }
        }

        System.out.printf("The category with the lowest expanses is: %s, with a total expense of %d€ \n", CATEGORIES[categoryWithLowestSum], lowestExpanse);
    }





}