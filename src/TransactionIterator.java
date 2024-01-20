package src;

public interface TransactionIterator {

    boolean hasNext();

    Transaction next();
}
