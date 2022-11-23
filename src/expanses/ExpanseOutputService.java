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
    private void displayExpansesByCategory(){
        int[][] expansesByCategory = getExpansesByCategory();

        //Print the expanses by category
        for (String category : CATEGORIES) {
            System.out.printf("The expanses for %s are: %d\n", category, expansesByCategory[0][1]);
        }
        System.out.printf("The expanses for all categories are: %d%%\n", expansesByCategory[1][0]);
    }

    private void displayRelativeExpansesByCategory(){
        int[][] expansesByCategory = getExpansesByCategory();

        //Print the relative expanses by category
        for (String category : CATEGORIES) {
            System.out.printf("The relative expanses for %s are: %d\n", category, expansesByCategory[0][1]);
        }
        System.out.printf("The relative expanses for all categories are: %d%%\n", expansesByCategory[1][0] * 100 / budget);
    }

    private void displayDayWithHighestExpanses(){
        int highestExpanses = 0;

        //Go through each day and each category and check if the expanse is higher than the "so far highest expanses"
        //If it is, overwrite the highest expanses variable
        for (int[] days : expanses) {
            for (int expanse : days) {
                if (expanse > highestExpanses) {
                    highestExpanses = expanse;
                }
            }
        }

        System.out.printf("The day with the highest expanses is: %d\n", highestExpanses);
    }

    private void displayDayWithLowestExpanses(){
        int lowestExpanse = 0;

        //Go through each day and each category and check if the expanse is lower than the "so far lowest expanses"
        //If it is, overwrite the lowest expanses variable
        for (int[] days : expanses) {
            for (int expanse : days) {
                if (expanse > lowestExpanse) {
                    lowestExpanse = expanse;
                }
            }
        }

        System.out.printf("The day with the lowest expanses is: %d \n", lowestExpanse);
    }

    private void exit(){
        //Exit the program
        System.out.println("Goodbye!");
    }

    public void selectAction() {
        int selectedItem;
        do {
            //Print the Menu Items
            System.out.println("\n Menu-Items: \n");
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
                case 4 -> this.displayDayWithHighestExpanses();
                case 5 -> this.displayDayWithLowestExpanses();
                case 6 -> this.exit();
            }
        } while (selectedItem != 6);
    }

}
