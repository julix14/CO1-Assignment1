package expanses;

import validation.ValidationService;

public class ExpansesInputService {
    private final ValidationService validationService = new ValidationService();
    final private String[] CATEGORIES = {"Food", "Transport", "Shopping", "Other"};

    public int[][] getExpanses() {
        int days = validationService.validateInputIsInt("Enter number of days");
        int[][] expanses = new int[days][CATEGORIES.length];
        for (int i = 0; i < days; i++) {
            for (int j = 0; j < CATEGORIES.length; j++) {
                expanses[i][j] = validationService.validateInputIsInt("Enter expanses for " + CATEGORIES[j] + " for day " + (i + 1));
            }
        }
        return expanses;
    }

    public int getBudget() {
        return validationService.validateInputIsInt("Enter budget");
    }



}
