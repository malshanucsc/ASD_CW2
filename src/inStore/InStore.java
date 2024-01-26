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

        transactionMap = new HashMap<>();

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
