package src.wallet.src.CrudOperation;

import src.wallet.src.Interface.CrudOperation;
import src.wallet.src.Model.Transaction;

import java.util.List;

public class TransactionCrudOperation  implements CrudOperation<Transaction> {

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public Transaction save(Transaction toSave) {
        return null;
    }

    @Override
    public Transaction delete(Transaction toDelete) {
        return null;
    }
}
