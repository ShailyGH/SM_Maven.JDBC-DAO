package daos;

import java.util.*;

public interface DAO<T>
{
    public T findById(int id);
    public List<T> findAll();
    public T update(T object);
    public T create (T object);
    public void delete(int id);
}
