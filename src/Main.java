import expanses.ExpanseOutputService;
import expanses.ExpansesInputService;

public class Main {
    public static void main(String[] args) {
        ExpansesInputService expansesInputService = new ExpansesInputService();

        int budget = expansesInputService.getBudget();
        int[][] expanses = expansesInputService.getExpanses();

        ExpanseOutputService expanseOutputService = new ExpanseOutputService(expanses, budget);

        expanseOutputService.selectAction();

    }
}