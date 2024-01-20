package src;

import src.inStore.InStore;

public interface Collection {
    TransactionIterator createTransactionIterator(InStore inStore);
}
