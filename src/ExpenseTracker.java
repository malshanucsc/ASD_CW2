package src;

import src.budget.Budget;
import src.budget.CategoryBudget;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpenseTracker {

    private static HashMap<Integer, Transaction> transactionMap = new HashMap<>();
    private static List<Category> categoryList = new ArrayList<>();
    private static Budget overallBudget;
    private static final Logger logger = Logger.getLogger(ExpenseTracker.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category categoryFood = new Category("Food");
        Transaction sampleTransaction1 = new Transaction(categoryFood, LocalDateTime.now(), 50.0, false, "Dinner");
        Transaction sampleTransaction2 = new Transaction(categoryFood, LocalDateTime.now(), 30.0, false, "Groceries");

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
                    logger.log(Level.INFO, "Exiting the ExpenseTracker application.");
                    System.exit(0);
                    break;
                default:
                    logger.log(Level.WARNING, "Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void displayMenu() {
        logger.log(Level.INFO, "ExpenseTracker Menu:");
        logger.log(Level.INFO, "1. View Transactions");
        logger.log(Level.INFO, "2. Add Transaction");
        logger.log(Level.INFO, "3. View Categories");
        logger.log(Level.INFO, "4. Add Category");
        logger.log(Level.INFO, "5. Set Budget");
        logger.log(Level.INFO, "6. Exit");
        logger.log(Level.INFO, "Enter your choice:");
    }

    private static void viewTransactions() {
        for (Transaction transaction : transactionMap.values()) {
            logger.log(Level.INFO, transaction.toString());
        }
    }

    private static void addTransaction(Scanner scanner) {
        logger.log(Level.INFO, "Enter transaction details:");

        logger.log(Level.INFO, "Enter category:");
        String categoryName = scanner.next();
        Category category = new Category(categoryName);

        logger.log(Level.INFO, "Enter amount:");
        double amount = scanner.nextDouble();

        logger.log(Level.INFO, "Is the transaction recurring? (true/false):");
        boolean recurring = scanner.nextBoolean();

        logger.log(Level.INFO, "Enter note:");
        String note = scanner.next();

        Transaction transaction = new Transaction(category, LocalDateTime.now(), amount, recurring, note);
        addTransaction(transaction);
    }

    private static void viewCategories() {
        for (Category category : categoryList) {
            logger.log(Level.INFO, category.toString());
        }
    }

    private static void addCategory(Scanner scanner) {
        logger.log(Level.INFO, "Enter category name:");
        String categoryName = scanner.next();
        Category category = new Category(categoryName);
        addCategory(category);
    }

    private static void setBudget(Scanner scanner) {
        logger.log(Level.INFO, "Enter overall budget amount:");
        double overallBudgetAmount = scanner.nextDouble();

        // Assuming you have a List<CategoryBudget> to set category-wise budgets
        List<CategoryBudget> categoryBudgets = new ArrayList<>();  // You may initialize this list as needed

        Budget budget = new Budget(categoryBudgets, overallBudgetAmount);
        overallBudget = budget;

        logger.log(Level.INFO, "Overall budget set successfully.");
}

    // Other methods like editTransaction, deleteTransaction, trackProgress, etc., can be implemented here

    private static void addTransaction(Transaction transaction) {
        transactionMap.put(transaction.getId(), transaction);
        logger.log(Level.INFO, "Transaction added successfully.");
    }

    private static void addCategory(Category category) {
        categoryList.add(category);
        logger.log(Level.INFO, "Category added successfully.");
    }
}
