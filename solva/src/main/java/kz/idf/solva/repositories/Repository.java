package kz.idf.solva.repositories;

import java.util.List;

public interface Repository <T>{
    T create(T value);
    List<T> findAll();
}
