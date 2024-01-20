package src;

import java.util.List;

public class TransactionIteratorImpl implements TransactionIterator{

    private final List<Transaction> transactionList;
    private int iter = 0;

    public TransactionIteratorImpl(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public boolean hasNext() {

        return iter < transactionList.size() &&
                transactionList.get(iter) != null;
    }

    @Override
    public Transaction next() {
        Transaction transaction =  transactionList.get(iter);
        iter += 1;
        return transaction;
    }

}
