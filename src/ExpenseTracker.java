package src;

import src.inStore.InStore;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ExpenseTracker {

    static TransactionCollection transactionCollection;
    private final InStore inStore;  // Create an instance of InStore

    public ExpenseTracker() {
        inStore = InStore.getInstore();
        transactionCollection = new TransactionCollection();
    }

    /**
     * iterate though all transactions and view the transaction details
     */
    public void viewTransactions() {

        TransactionIterator iterator = transactionCollection.createTransactionIterator(inStore);
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            System.out.println(transaction.toString());
        }
    }

    /**
     * add a new transaction
     *
     * @param scanner system input object for user input
     */
    public void addTransaction(Scanner scanner) {
        // Add transaction to InStore
        System.out.println("Enter transaction details:");

        System.out.println("Enter category: ");
        viewCategories();
        System.out.println("Are you using existing category out of the above categories Y/N ?");

        Category category;

        while (true) {
            String isUsingExisting = scanner.next();
            if (isUsingExisting.equals("Y")) {

                System.out.println("Enter category Id?");
                int categoryId;
                while (true) {
                    try {
                        categoryId = scanner.nextInt();
                        if (inStore.getCategoryMap().getOrDefault(categoryId, null) != null) {
                            break;
                        } else {
                            viewCategories();
                            System.out.println("Incorrect ID. Please enter correct category id from above list");
                        }
                    } catch (InputMismatchException inputMismatchException) {
                        System.out.println("Incorrect data type. Please enter an int input type.");
                        scanner.next();
                    }
                }
                category = inStore.getCategoryMap().get(categoryId);
                break;

            } else if (isUsingExisting.equals("N")) {

                String categoryName = scanner.nextLine();
                category = new Category(categoryName);
                break;

            } else {
                System.out.println("Invalid input. Please enter the valid input(Y/N)");
            }
        }


        System.out.print("Enter amount: ");
        double amount;
        while (true) {
            try {
                amount = scanner.nextDouble();
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Incorrect data type. Please enter a double input type.");
                scanner.next();
            }
        }

        System.out.print("Is the transaction recurring? (true/false): ");

        boolean recurring;
        while (true) {
            try {
                recurring = scanner.nextBoolean();
                scanner.nextLine();
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Incorrect data type. Please enter (true/false) input type.");
                scanner.next();
            }
        }

        System.out.print("Enter note: ");
        String note = scanner.nextLine();

        Transaction transaction = new Transaction(category, LocalDateTime.now(), amount, recurring, note);
        inStore.getTransactionMap().put(transaction.getId(), transaction);
        System.out.println("Transaction added successfully.");
    }

    /**
     * view available categories
     */
    public void viewCategories() {
        // Get categories from InStore
        for (Category category : inStore.getCategoryMap().values()) {
            System.out.println(category.toString());
        }
    }

    /**
     * add a new Category
     *
     * @param scanner system input object for user input
     */
    public void addCategory(Scanner scanner) {
        // Add category to InStore
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryName);
        inStore.getCategoryMap().put(category.getId(), category);
        System.out.println("Category added successfully.");
    }

    /**
     * set budget for each category
     *
     * @param scanner system input object for user input
     */
    public void setBudget(Scanner scanner) {

        for (Category category : inStore.getCategoryMap().values()) {
            System.out.println("Enter the budget for " + category.getName() + " category");
            double categoryBudget;
            while (true) {
                try {
                    categoryBudget = scanner.nextDouble();
                    break;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("Incorrect data type. Please enter a double input type.");
                    scanner.next();
                }
            }
            category.setCategoryBudget(categoryBudget);
        }

        System.out.println("Budget set successfully.");
    }

    /**
     * summarize the transactions by each category
     *
     * @return map of summed transaction for each category name
     */
    private Map<String, Double> summarizeTransactions() {

        TransactionIterator iterator = transactionCollection.createTransactionIterator(inStore);
        Map<String, Double> summarizedTransactions = new HashMap<>();
        double overallTransactionAmount = 0.0;
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            String categoryName = transaction.getCategory().getName();

            double categoryTotalAmount = summarizedTransactions.getOrDefault(
                    categoryName, 0.0) + transaction.getAmount();
            summarizedTransactions.put(categoryName, categoryTotalAmount);
            overallTransactionAmount += transaction.getAmount();
        }
        summarizedTransactions.put("overall", overallTransactionAmount);
        return summarizedTransactions;
    }


    /**
     * shows the progress of the users based of the budget and current transactions
     */
    public void trackProgress() {
        System.out.println("Please make sure you have set the budget for each category");

        Map<String, Double> summarizedTransactions = summarizeTransactions();
        double overallBudget = 0;

        System.out.println("Your current progress for the budget can be seen as follows");

        for (Category category : inStore.getCategoryMap().values()) {
            String categoryName = category.getName();
            System.out.println("Budget for " + categoryName + ": " + category.getCategoryBudget() + " and current expenditure: " + summarizedTransactions.getOrDefault(categoryName, 0.0));
            overallBudget += category.getCategoryBudget();
        }
        System.out.println("Overall allocated budget: " + overallBudget + " and current expenditure: " + summarizedTransactions.getOrDefault("overall", 0.00));
    }
}
