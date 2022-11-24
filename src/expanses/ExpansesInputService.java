package expanses;

import validation.ValidationService;

public class ExpansesInputService {
    //Create a new instance of the ValidationService and the Categories-Array
    final private ValidationService validationService = new ValidationService();
    final private String[] CATEGORIES = {"Food", "Transport", "Shopping", "Other"};

    final String name;

    public ExpansesInputService(String name) {
        this.name = name;
    }

    public int[][] getExpanses() {
        //Get the number of days from the user
        int days = validationService.validateInputIsInt(name + ", please enter the number of days you want to enter expanses for");

        //Initialize the expanses array with the number of inserted days
        int[][] expanses = new int[days][CATEGORIES.length];
        for (int i = 0; i < days; i++) {
            //For each day, ask the user for the expanses for each category
            for (int j = 0; j < CATEGORIES.length; j++) {
                expanses[i][j] = validationService.validateInputIsInt(name +" enter expanses for " + CATEGORIES[j] + " for day " + (i + 1));
            }
        }
        return expanses;
    }

    public int getBudget() {
        //Get the budget from the user
        return validationService.validateInputIsInt("Please enter your budget "+ name);
    }



}
