package src;

import src.budget.Budget;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class ExpenseTracker {

    private HashMap<Integer, Transaction> transactionMap;

    private List<Category> categoryList;

    private Budget budget;

    private final Logger logger = Logger.getLogger(ExpenseTracker.class.getName());

    public static void main(String[] args) {

    }

    private void viewTransactions(){

        for(Transaction transaction: transactionMap.values()){
            logger.log(Level.INFO, transaction.toString());
        }

    }

    private void addTransaction(Transaction transaction){
        transactionMap.put(transaction.getId(), transaction);
    }

    private void editTransaction(Transaction transaction){
        transactionMap.replace(transaction.getId(), transaction);
    }

    private void deleteTransaction(Transaction transaction){
        transactionMap.remove(transaction.getId());

    }

    private void viewCategories(){
        for(Category category : categoryList){
            logger.log(Level.INFO, category.toString());
        }
    }

    private void addCategory(Category category){

        categoryList.add(category);
    }

    private void setBudget(Budget budget){
        this.budget = budget;
    }

    private void trackProgress(){
        //implement
    }


}