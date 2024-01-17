package src;

import src.budget.Budget;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ExpenseTracker {

    private static HashMap<Integer, Transaction> transactionMap = new HashMap<>();

    private static List<Category> categoryList = new ArrayList<>();

    private static Budget overallBudget;

    private static final Logger logger = Logger.getLogger(ExpenseTracker.class.getName());

    public static void main(String[] args) {

        Category categoryFood = new Category(1, "Food");
        Transaction t1 = new Transaction(1, categoryFood, LocalDateTime.now(), 1000.00, false, "checken bucket");
        addTransaction(t1);
        viewTransactions();

    }

    private static void viewTransactions(){

        for(Transaction transaction: transactionMap.values()){
            logger.log(Level.INFO, transaction.toString());
        }

    }

    private static void addTransaction(Transaction transaction){
        transactionMap.put(transaction.getId(), transaction);
    }

    private static void editTransaction(Transaction transaction){
        transactionMap.replace(transaction.getId(), transaction);
    }

    private static void deleteTransaction(Transaction transaction){
        transactionMap.remove(transaction.getId());

    }

    private static void viewCategories(){
        for(Category category : categoryList){
            logger.log(Level.INFO, category.toString());
        }
    }

    private static void addCategory(Category category){

        categoryList.add(category);
    }

    private static void setBudget(Budget budget){
        overallBudget = budget;
    }

    private void trackProgress(){
        //implement
    }


}