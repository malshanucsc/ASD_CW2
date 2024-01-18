package src;

import java.util.concurrent.atomic.AtomicInteger;

public class Category {

    private int id;
    private String name;

    private final static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    public Category(String name) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
