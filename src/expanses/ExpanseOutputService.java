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
        for (int i = 0; i < expanses.length; i++) {
            int dailyExpanses = 0;
            for (int j = 0; j < expanses[i].length; j++) {
                dailyExpanses += expanses[i][j];
            }
            System.out.printf("Day %d: %d, ", i + 1, dailyExpanses);
        }
    }

    private void displayRemainingBudget(){
        int totalExpanses = 0;

        for (int[] day: expanses) {
            for (int expanse: day) {
                totalExpanses += expanse;
            }
        }

        System.out.printf("The remaining budget is: %d", budget - totalExpanses);
        System.out.printf("Or to say it in percentage: %d%%", (budget - totalExpanses) * 100 / budget);

    }

    private void displayExpansesByCategory(){
        int[] categoryExpanses = new int[CATEGORIES.length];
        for( int[] day : expanses) {
            for (int i = 0; i < day.length; i++) {
                categoryExpanses[i] += day[i];
            }
        }

        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("The expanses for %s are: %d", CATEGORIES[i], categoryExpanses[i]);
        }
    }

    private void displayRelativeExpansesByCategory(){
        int[] categoryExpanses = new int[CATEGORIES.length];
        for( int[] day : expanses) {
            for (int i = 0; i < day.length; i++) {
                categoryExpanses[i] += day[i];
            }
        }

        for (int i = 0; i < CATEGORIES.length; i++) {
            System.out.printf("The expanses for %s are: %d%%", CATEGORIES[i], categoryExpanses[i] * 100 / budget);
        }
    }

    private void displayDayWithHighestExpanses(){
        int highestExpanses = 0;

        for (int[] days : expanses) {
            for (int expanse : days) {
                if (expanse > highestExpanses) {
                    highestExpanses = expanse;
                }
            }
        }

        System.out.printf("The day with the highest expanses is: %d", highestExpanses);
    }

    private void displayDayWithLowestExpanses(){
        int lowestExpanse = 0;

        for (int[] days : expanses) {
            for (int expanse : days) {
                if (expanse > lowestExpanse) {
                    lowestExpanse = expanse;
                }
            }
        }

        System.out.printf("The day with the lowest expanses is: %d", lowestExpanse);
    }

    private void exit(){
        System.out.println("Exit");
    }

    private void selectAction(List<String> menuItems) {
        int selectedItem;
        do {
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.printf("[%d] -> %s\n", i, menuItems.get(i));
            }
            selectedItem = validationService.validateInputIsInRange("Select a method to run", 0, menuItems.size() - 1);
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
