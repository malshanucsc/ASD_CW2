package src;

import java.util.concurrent.atomic.AtomicInteger;

public class Category {

    private int id;
    private String name;

    private double categoryBudget;

    private final static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    public Category(String name) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.categoryBudget = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCategoryBudget() {
        return categoryBudget;
    }

    public void setCategoryBudget(double categoryBudget) {
        this.categoryBudget = categoryBudget;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryBudget=" + categoryBudget +
                '}';
    }
}
