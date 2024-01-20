package src;

import src.budget.Budget;
import src.budget.CategoryBudget;
import src.inStore.InStore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {

    private static HashMap<Integer, Transaction> transactionMap = new HashMap<>();
    private static List<Category> categoryList = new ArrayList<>();
    private static Budget overallBudget;
    private static InStore inStore = new InStore().getInstore(); // Create an instance of InStore

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewTransactions();
                    break;
                case 2:
                    addTransaction(scanner);
                    break;
                case 3:
                    viewCategories();
                    break;
                case 4:
                    addCategory(scanner);
                    break;
                case 5:
                    setBudget(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the ExpenseTracker application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("ExpenseTracker Menu:");
        System.out.println("1. View Transactions");
        System.out.println("2. Add Transaction");
        System.out.println("3. View Categories");
        System.out.println("4. Add Category");
        System.out.println("5. Set Budget");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void viewTransactions() {
        // Get transactions from InStore
        for (Transaction transaction : inStore.getTransactionMap().values()) {
            System.out.println(transaction.toString());
        }
    }

    private static void addTransaction(Scanner scanner) {
        // Add transaction to InStore
        System.out.println("Enter transaction details:");
        System.out.print("Enter category: ");
        String categoryName = scanner.next();
        Category category = new Category(categoryName);

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        System.out.print("Is the transaction recurring? (true/false): ");
        boolean recurring = scanner.nextBoolean();

        System.out.print("Enter note: ");
        String note = scanner.next();

        Transaction transaction = new Transaction(category, LocalDateTime.now(), amount, recurring, note);
        inStore.getTransactionMap().put(transaction.getId(), transaction);
        System.out.println("Transaction added successfully.");
    }

    private static void viewCategories() {
        // Get categories from InStore
        for (Category category : inStore.getCategoryList()) {
            System.out.println(category.toString());
        }
    }

    private static void addCategory(Scanner scanner) {
        // Add category to InStore
        System.out.print("Enter category name: ");
        String categoryName = scanner.next();
        Category category = new Category(categoryName);
        inStore.getCategoryList().add(category);
        System.out.println("Category added successfully.");
    }

    private static void setBudget(Scanner scanner) {
        // Set budget in InStore
        System.out.print("Enter overall budget amount: ");
        double overallBudgetAmount = scanner.nextDouble();
        List<CategoryBudget> categoryBudgets = new ArrayList<>();  

        Budget budget = new Budget(categoryBudgets, overallBudgetAmount);
        overallBudget = budget;

        System.out.println("Overall budget set successfully.");
    }


}
