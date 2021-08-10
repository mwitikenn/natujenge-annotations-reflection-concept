package com.meliora.natujenge.repositories;

public interface CrudRepository<T, D> {

    public void save(T t);

    public T findByID(D d);
}
