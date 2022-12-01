import java.util.Scanner;

public class Main {
    final public static String[] CATEGORIES = {"Food", "Transport", "Shopping", "Other"};

    public static String name;
    public static int days;
    public static int[][] expanses;
    public static int budget;

    public static Scanner scanner = new Scanner(System.in);

    public static final String[] menuItems = {
            "Display Daily Expanses",
            "Display Remaining Budget",
            "Display Expenses by Category",
            "Display relative Expanses by Category",
            "Display the Day with the highest Expenses",
            "Display the Day with the lowest Expenses",
            "Exit"};


    public static void main(String[] args) {
        //Greet the User and save his/her name
        System.out.println("Welcome to the expanses program!");
        name = getStringFromUserWithMessage("What is your name? \n");

        // Ask the user for the budget and expanses
        days = getDaysToTrack();
        budget = getBudget();
        expanses = getExpanses();

        //Always be nice to the user :)
        System.out.print(
                "Thanks for your input " + name + "\n" +
                        "Enjoy the following Options to analyse your expanses!\n");

        //Start the menu
        selectAction();

        //Exit the program
        System.out.println("It was a pleasure to help you " + name + "!");
    }

    // Methods to get the days, budget and expanses from the user
    public static int getDaysToTrack() {
        //Get the number of days from the user
        System.out.println(name + ", for how many days do you want to track your expanses? ");
        return scanner.nextInt();
    }

    public static int getBudget() {
        //Get the budget from the user
        System.out.println(name + ", what was your budget for the " + days + " days? ");
        return scanner.nextInt();
    }
    public static int[][] getExpanses() {
        //Initialize the expanses array with the number of inserted days
        int[][] expanses = new int[days][CATEGORIES.length];
        System.out.printf("%s, please enter the expanses for each day and category%n", name);
        for (int i = 0; i < days; i++) {
            //For each day, ask the user for the expanses for each category
            System.out.println("Day " + (i + 1));
            for (int j = 0; j < CATEGORIES.length; j++) {
                System.out.println(CATEGORIES[j] + ": ");
                expanses[i][j] = scanner.nextInt();
            }
        }
        return expanses;
    }

    // Methods to create and display the menu
    public static void selectAction() {
        int selectedItem;
        do {
            //Print the Menu Items
            System.out.println("\nMenu-Items: ");
            for (int i = 0; i < menuItems.length; i++) {
                System.out.printf("[%d] -> %s\n", i, menuItems[i]);
            }
            //Get the user input
            System.out.println("Please select an option: ");
            selectedItem = scanner.nextInt();

            //Execute the selected action and if it's the exit action, break the loop and exit the program
            switch (selectedItem) {
                case 0 -> displayDailyExpanses();
                case 1 -> displayRemainingBudget();
                case 2 -> displayExpansesByCategory();
                case 3 -> displayRelativeExpansesByCategory();
                case 4 -> displayDayWithHighestExpanse();
                case 5 -> displayCategoryWithLowestExpanses();
                default -> System.out.println("Unfortunately your selection represents not a valid option!");
            }
            waitForKeypress();
        } while (selectedItem != 6);
    }

    // Methods to execute the selected actions
    public static void displayDailyExpanses() {
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

    public static void displayRemainingBudget() {
        int totalExpanses = 0;

        //Go through each day and each category and sum their values up
        for (int[] day : expanses) {
            for (int expanse : day) {
                totalExpanses += expanse;
            }
        }
        if (totalExpanses > budget) {
            //Print the remaining budget
            System.out.printf("You exceeded your budget by: %d€\n",  totalExpanses - budget);
            System.out.printf("Or to say it in percentage: %d%%\n", (totalExpanses - budget) * 100 / budget);
        } else {
            //Print the remaining budget
            System.out.printf("The remaining budget is: %d€\n", budget - totalExpanses);
            System.out.printf("Or to say it in percentage: %d%%\n", (budget - totalExpanses) * 100 / budget);
        }


    }

    public static int[][] getExpansesByCategory() {
        //Create an array to store the expanses by category
        int[] categoryExpanses = new int[CATEGORIES.length];
        int totalExpanses = 0;

        //Go through each day and each category and store the value in the categoryExpanses array
        //Also sum up the total expanses
        for (int[] day : expanses) {
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

    public static void displayExpansesByCategory() {
        int[][] expansesByCategory = getExpansesByCategory();

        //Print the expanses by category
        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("The expanses for %s are: %d€\n", CATEGORIES[i], expansesByCategory[CATEGORY_EXPANSES_POSITION][i]);
        }
        System.out.printf("The total expanses for all categories are: %d€\n", expansesByCategory[TOTAL_EXPANSES_POSITION][0]);
    }

    public static void displayRelativeExpansesByCategory() {
        int[][] expansesByCategory = getExpansesByCategory();

        //Print the relative expanses by category
        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("The relative expanses for %s are: %d%%\n", CATEGORIES[i], expansesByCategory[CATEGORY_EXPANSES_POSITION][i] * 100 / expansesByCategory[TOTAL_EXPANSES_POSITION][0]);
        }
    }

    public static void displayDayWithHighestExpanse() {
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

    public static void displayCategoryWithLowestExpanses() {
        int[][] expansesByCategory = getExpansesByCategory();
        int lowestExpanse = expansesByCategory[0][0];
        int categoryWithLowestSum = 0;

        //Go through each day and each category and check if the expanse is lower than the "so far lowest expanses"
        //If it is, overwrite the lowest expanses variable
        for (int i = 0; i < expansesByCategory[0].length; i++) {
            if (lowestExpanse > expansesByCategory[0][i]) {
                lowestExpanse = expansesByCategory[0][i];
                categoryWithLowestSum = i;
            }
        }
        System.out.printf("The category with the lowest expanses is: %s, with a total expense of %d€ \n", CATEGORIES[categoryWithLowestSum], lowestExpanse);
    }

    // Helper methods for input
    public static String getStringFromUserWithMessage(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }

    public static void waitForKeypress() {
        //Wait for the user to press a key
        System.out.println("\nPress 'Enter' to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}