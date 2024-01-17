package src.budget;

import java.util.List;

public class Budget {

    private List<CategoryBudget> categoryBudgets;

    private double totalBudget;

    public Budget(List<CategoryBudget> categoryBudgets, double totalBudget) {
        this.categoryBudgets = categoryBudgets;
        this.totalBudget = totalBudget;
    }

    public List<CategoryBudget> getCategoryBudgets() {
        return categoryBudgets;
    }

    public void setCategoryBudgets(List<CategoryBudget> categoryBudgets) {
        this.categoryBudgets = categoryBudgets;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
}
