package expanses;

import validation.ValidationService;

import java.util.*;

public class ExpanseOutputService {
    final ValidationService validationService = new ValidationService();
    final private String[] CATEGORIES = {"Food", "Transport", "Shopping", "Other"};
    final List<String> menuItems;
    final int[][] expanses;

    final int budget;

    public ExpanseOutputService(int[][] expanses, int budget) {
        //Build up the menu
        menuItems = new ArrayList<>();
        Collections.addAll(menuItems,
                "Display Daily Expanses",
                "Display Remaining Budget",
                "Display Expenses by Category",
                "Display relative Expanses by Category",
                "Display the Day with the highest Expenses",
                "Display the Day with the lowest Expenses",
                "Exit");
        //Store the expanses
        this.expanses = expanses;
        this.budget = budget;
    }


    private void displayDailyExpanses(){
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

    private void displayRemainingBudget(){
        int totalExpanses = 0;

        //Go through each day and each category and sum their values up
        for (int[] day: expanses) {
            for (int expanse: day) {
                totalExpanses += expanse;
            }
        }

        //Print the remaining budget
        System.out.printf("The remaining budget is: %d\n", budget - totalExpanses);
        System.out.printf("Or to say it in percentage: %d%%\n", (budget - totalExpanses) * 100 / budget);

    }

    private int[][] getExpansesByCategory() {
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
    final int CATEGORY_EXPANSES_POSITION = 0;
    final int TOTAL_EXPANSES_POSITION = 1;
    private void displayExpansesByCategory(){
        int[][] expansesByCategory = getExpansesByCategory();

        //Print the expanses by category
        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("The expanses for %s are: %d\n", CATEGORIES[i], expansesByCategory[CATEGORY_EXPANSES_POSITION][i]);
        }
        System.out.printf("The total expanses for all categories are: %d\n", expansesByCategory[TOTAL_EXPANSES_POSITION][0]);
    }

    private void displayRelativeExpansesByCategory(){
        int[][] expansesByCategory = getExpansesByCategory();

        //Print the relative expanses by category
        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("The relative expanses for %s are: %d%%\n",  CATEGORIES[i], expansesByCategory[CATEGORY_EXPANSES_POSITION][i] * 100 / budget);
        }
        System.out.printf("The relative expanses for all categories are: %d%%\n", expansesByCategory[TOTAL_EXPANSES_POSITION][0] * 100 / budget);
    }

    private void displayDayWithHighestExpanse(){
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

        System.out.printf("The highest total expanse was %d on day number %d\n", highestExpanses, dayWithHighestExpense);
    }

    private void displayCategoryWithLowestExpanses(){
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

        System.out.printf("The category with the lowest expanses is: %s, with a total expense of %d \n", CATEGORIES[categoryWithLowestSum], lowestExpanse);
    }

    private void exit(){
        //Exit the program
        System.out.println("Goodbye!");
    }

    private void waitForKeypress(){
        //Wait for the user to press a key
        System.out.println("\nPress 'Enter' to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void selectAction() {
        int selectedItem;
        do {
            //Print the Menu Items
            System.out.println("Menu-Items: \n");
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.printf("[%d] -> %s\n", i, menuItems.get(i));
            }
            //Get the user input
            selectedItem = validationService.validateInputIsInRange("Please select one of the options", 0, menuItems.size() - 1);

            //Execute the selected action and if it's the exit action, break the loop and exit the program
            switch (selectedItem) {
                case 0 -> this.displayDailyExpanses();
                case 1 -> this.displayRemainingBudget();
                case 2 -> this.displayExpansesByCategory();
                case 3 -> this.displayRelativeExpansesByCategory();
                case 4 -> this.displayDayWithHighestExpanse();
                case 5 -> this.displayCategoryWithLowestExpanses();
                case 6 -> this.exit();
            }
            waitForKeypress();
        } while (selectedItem != 6);
    }

}
