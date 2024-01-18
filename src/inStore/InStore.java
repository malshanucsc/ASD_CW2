package src.inStore;

import src.Category;
import src.Transaction;

import java.time.LocalDateTime;
import java.util.*;

public class InStore {

    private InStore inStore;

    private final Map<Integer, Transaction> transactionMap;

    private final List<Category> categoryList;
    private InStore () {

        Category foodAndGrocery = new Category(1, "Food & Grocery");
        Category rentAndLease = new Category(2, "Rent/Lease");
        Category utility = new Category(3, "Utility");
        Category education = new Category(4, "Education");
        Category entertainment = new Category(5, "Entertainment");
        Category transport = new Category(6, "transport");
        Category insurance = new Category(7, "Insurance");
        Category other = new Category(8, "Other Miscellaneous");

        categoryList = Arrays.asList(foodAndGrocery, rentAndLease, utility, education, entertainment, transport, insurance, other);

        Transaction transaction1 = new Transaction(1, foodAndGrocery, LocalDateTime.now(), 1200.00, false, "Chicken");
        Transaction transaction2 = new Transaction(2, foodAndGrocery, LocalDateTime.now(), 2200.00, false, "Carrot");

        Transaction transaction3 = new Transaction(3, rentAndLease, LocalDateTime.now(), 35000.00, true, "Car Lease");
        Transaction transaction4 = new Transaction(4, rentAndLease, LocalDateTime.now(), 25000.00, true, "House Rental");

        Transaction transaction5 = new Transaction(5, utility, LocalDateTime.now(), 10000.00, false, "Electricity");
        Transaction transaction6 = new Transaction(6, utility, LocalDateTime.now(), 8000.00, false, "Tele Communication");

        Transaction transaction7 = new Transaction(7, education, LocalDateTime.now(), 25000.00, false, "University Tuition");

        Transaction transaction8 = new Transaction(8, entertainment, LocalDateTime.now(), 2000.00, true, "Netflix Subscription");
        Transaction transaction9 = new Transaction(9, entertainment, LocalDateTime.now(), 8000.00, true, "Yoga ");

        Transaction transaction10 = new Transaction(10, transport, LocalDateTime.now(), 20000.00, true, "Fuel");

        Transaction transaction11 = new Transaction(11, insurance, LocalDateTime.now(), 9000.00, true, "Health Insurance");
        Transaction transaction12 = new Transaction(12, insurance, LocalDateTime.now(), 40000.00, true, "Vehicle Insurance");

        Transaction transaction13 = new Transaction(13, other, LocalDateTime.now(), 1000.00, true, "Charity Payment");
        Transaction transaction14 = new Transaction(13, other, LocalDateTime.now(), 1000.00, true, "Fine Payment");

        transactionMap = new HashMap<>();
        transactionMap.put(transaction1.getId(), transaction1);
        transactionMap.put(transaction2.getId(), transaction2);
        transactionMap.put(transaction3.getId(), transaction3);
        transactionMap.put(transaction4.getId(), transaction4);
        transactionMap.put(transaction5.getId(), transaction5);
        transactionMap.put(transaction6.getId(), transaction6);
        transactionMap.put(transaction7.getId(), transaction7);
        transactionMap.put(transaction8.getId(), transaction8);
        transactionMap.put(transaction9.getId(), transaction9);
        transactionMap.put(transaction10.getId(), transaction10);
        transactionMap.put(transaction11.getId(), transaction11);
        transactionMap.put(transaction12.getId(), transaction12);
        transactionMap.put(transaction13.getId(), transaction13);
        transactionMap.put(transaction14.getId(), transaction14);

    }

    public InStore getInstore(){
        if(inStore == null){
            inStore = new InStore();
        }
        return inStore;
    }

    public Map<Integer, Transaction> getTransactionMap(){
        return transactionMap;
    }

    public List<Category> getCategoryList(){
        return categoryList;
    }

}