package src.wallet.src.Interface;

import java.util.List;

public interface CrudOperation <T> {
    List<T> findAll();
    T save(T toSave);
    T delete(T toDelete);
}
