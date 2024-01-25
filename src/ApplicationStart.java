package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationStart {

    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Incorrect data type. Please enter an int input type.");
                scanner.next();
            }
            switch (choice) {
                case 1:
                    expenseTracker.viewTransactions();
                    break;
                case 2:
                    expenseTracker.addTransaction(scanner);
                    break;
                case 3:
                    expenseTracker.viewCategories();
                    break;
                case 4:
                    expenseTracker.addCategory(scanner);
                    break;
                case 5:
                    expenseTracker.setBudget(scanner);
                    break;
                case 6:
                    expenseTracker.trackProgress();
                    break;
                case 7:
                    System.out.println("Exiting the ExpenseTracker application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    /**
     * view the menu items
     */
    private static void displayMenu() {
        System.out.println("ExpenseTracker Menu:");
        System.out.println("1. View Transactions");
        System.out.println("2. Add Transaction");
        System.out.println("3. View Categories");
        System.out.println("4. Add Category");
        System.out.println("5. Set Budget");
        System.out.println("6. Track Current Progress");
        System.out.println("7. Exit");
        System.out.println("Enter your choice: ");
    }

}
