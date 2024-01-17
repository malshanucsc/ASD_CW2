package src.budget;

public class CategoryBudget {

    private int categoryId;
    private double budget;

    public CategoryBudget(int categoryId, double budget) {
        this.categoryId = categoryId;
        this.budget = budget;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
