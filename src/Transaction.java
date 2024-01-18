package src;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {
    private int id;
    private Category category;
    private LocalDateTime dateTime;
    private double amount;
    private boolean recurring;
    private String note;

    private final static AtomicInteger ID_GENERATOR = new AtomicInteger(1);

    public Transaction(Category category, LocalDateTime dateTime, double amount, boolean recurring, String note) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.category = category;
        this.dateTime = dateTime;
        this.amount = amount;
        this.recurring = recurring;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", category=" + category +
                ", dateTime=" + dateTime +
                ", amount=" + amount +
                ", recurring=" + recurring +
                ", note='" + note + '\'' +
                '}';
    }
}
