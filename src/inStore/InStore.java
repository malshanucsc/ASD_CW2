package src.inStore;

import src.Category;
import src.Transaction;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InStore {

    private static InStore inStore;

    private final Map<Integer, Transaction> transactionMap;

    private final Map<Integer, Category> categoryMap;

    private InStore() {

        Category foodAndGrocery = new Category("Food & Grocery");
        Category rentAndLease = new Category("Rent/Lease");
        Category utility = new Category("Utility");
        Category education = new Category("Education");
        Category entertainment = new Category("Entertainment");
        Category transport = new Category("Transport");
        Category insurance = new Category("Insurance");
        Category other = new Category("Other Miscellaneous");

        categoryMap = new HashMap<>();
        categoryMap.put(foodAndGrocery.getId(), foodAndGrocery);
        categoryMap.put(rentAndLease.getId(), rentAndLease);
        categoryMap.put(utility.getId(), utility);
        categoryMap.put(education.getId(), education);
        categoryMap.put(entertainment.getId(), entertainment);
        categoryMap.put(transport.getId(), transport);
        categoryMap.put(insurance.getId(), insurance);
        categoryMap.put(other.getId(), other);


        Transaction transaction1 = new Transaction(foodAndGrocery, LocalDateTime.now(), 1200.00, false, "Chicken");
        Transaction transaction2 = new Transaction(foodAndGrocery, LocalDateTime.now(), 2200.00, false, "Carrot");

        Transaction transaction3 = new Transaction(rentAndLease, LocalDateTime.now(), 35000.00, true, "Car Lease");
        Transaction transaction4 = new Transaction(rentAndLease, LocalDateTime.now(), 25000.00, true, "House Rental");

        Transaction transaction5 = new Transaction(utility, LocalDateTime.now(), 10000.00, false, "Electricity");
        Transaction transaction6 = new Transaction(utility, LocalDateTime.now(), 8000.00, false, "Tele Communication");

        Transaction transaction7 = new Transaction(education, LocalDateTime.now(), 25000.00, false, "University Tuition");

        Transaction transaction8 = new Transaction(entertainment, LocalDateTime.now(), 2000.00, true, "Netflix Subscription");
        Transaction transaction9 = new Transaction(entertainment, LocalDateTime.now(), 8000.00, true, "Yoga ");

        Transaction transaction10 = new Transaction(transport, LocalDateTime.now(), 20000.00, true, "Fuel");

        Transaction transaction11 = new Transaction(insurance, LocalDateTime.now(), 9000.00, true, "Health Insurance");
        Transaction transaction12 = new Transaction(insurance, LocalDateTime.now(), 40000.00, true, "Vehicle Insurance");

        Transaction transaction13 = new Transaction(other, LocalDateTime.now(), 1000.00, true, "Charity Payment");
        Transaction transaction14 = new Transaction(other, LocalDateTime.now(), 1000.00, true, "Fine Payment");

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

    public static InStore getInstore() {
        if (inStore == null) {
            inStore = new InStore();
        }
        return inStore;
    }

    public Map<Integer, Transaction> getTransactionMap() {
        return transactionMap;
    }

    public Map<Integer, Category> getCategoryMap() {
        return categoryMap;
    }

}
