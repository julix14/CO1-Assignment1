package expanses;

import validation.ValidationService;

import java.util.*;

public class ExpanseOutputService {
    final ValidationService validationService = new ValidationService();
    final private String[] CATEGORIES = {"Food", "Transport", "Shopping", "Other"};
    final List<String> menuItems;
    final int[][] expanses;

    public ExpanseOutputService(int[][] expanses) {

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

        this.expanses = expanses;
    }


    private void displayDailyExpanses(){
        System.out.println("Display Daily Expanses");
    }

    private void displayRemainingBudget(){
        System.out.println("Display Remaining Budget");
    }

    private void displayExpansesByCategory(){
        System.out.println("Display Expanses By Category");
    }

    private void displayRelativeExpansesByCategory(){
        System.out.println("Display Relative Expanses By Category");
    }

    private void displayDayWithHighestExpanses(){
        System.out.println("Display Day With Highest Expanses");
    }

    private void displayDayWithLowestExpanses(){
        System.out.println("Display Day With Lowest Expanses");
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
