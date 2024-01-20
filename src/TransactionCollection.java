package src;

import src.inStore.InStore;


public class TransactionCollection implements Collection{


    public TransactionCollection(){

    }

    @Override
    public TransactionIterator createTransactionIterator(InStore inStore) {
        return new TransactionIteratorImpl(inStore.getTransactionMap().values().stream().toList());
    }
}
