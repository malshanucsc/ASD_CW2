package src;

import src.budget.Budget;
import src.budget.CategoryBudget;
import src.inStore.InStore;

import java.time.LocalDateTime;
import java.util.*;

public class ExpenseTracker {

    private static final InStore inStore = new InStore().getInstore(); // Create an instance of InStore
    static TransactionCollection transactionCollection = new TransactionCollection();

    public static void main(String[] args) {
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
                    resetBudget();
                    break;
                case 7:
                    trackProgress();
                    break;
                case 8:
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
        System.out.println("6. Reset Budget");
        System.out.println("7. Track Current Progress");
        System.out.println("8. Exit");
        System.out.println("Enter your choice: ");
    }

    private static void viewTransactions() {

        TransactionIterator iterator = transactionCollection.createTransactionIterator(inStore);
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            System.out.println(transaction.toString());
        }
    }

    private static void addTransaction(Scanner scanner) {
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

    private static void viewCategories() {
        // Get categories from InStore
        for (Category category : inStore.getCategoryMap().values()) {
            System.out.println(category.toString());
        }
    }

    private static void addCategory(Scanner scanner) {
        // Add category to InStore
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryName);
        inStore.getCategoryMap().put(category.getId(), category);
        System.out.println("Category added successfully.");
    }

    private static void setBudget(Scanner scanner) {
        // Set budget in InStore
        System.out.print("Enter overall budget amount: ");
        Budget userBudget;
        double overallBudgetAmount;
        while (true) {
            try {
                overallBudgetAmount = scanner.nextDouble();
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Incorrect data type. Please enter a double input type.");
                scanner.next();
            }
        }

        List<CategoryBudget> categoryBudgets = new ArrayList<>();

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
            categoryBudgets.add(new CategoryBudget(category, categoryBudget));
        }


        userBudget = new Budget(categoryBudgets, overallBudgetAmount);

        inStore.setBudget(userBudget);

        System.out.println("Budget set successfully.");
    }

    private static Map<String, Double> summarizeTransactions() {

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


    private static void trackProgress() {
        Budget budget = inStore.getBudget();
        if (budget == null) {
            System.out.println("Please set the budget first");
            return;
        }

        System.out.println("Your current progress for the budget can be seen as follows");
        Map<String, Double> summarizedTransactions = summarizeTransactions();


        System.out.println("Overall allocated budget: " + budget.getTotalBudget() + " and current expenditure: " + summarizedTransactions.getOrDefault("overall", 0.00));


        for (CategoryBudget categoryBudget : budget.getCategoryBudgets()) {
            String categoryName = categoryBudget.getCategory().getName();
            System.out.println("Budget for " + categoryName + ": " + categoryBudget.getBudget() + " and current expenditure: " + summarizedTransactions.getOrDefault(categoryName, 0.0));
        }

    }

    private static void resetBudget() {
        inStore.setBudget(null);
        System.out.println("Budget reset completed");
    }


}
