package org.maxkizi.regiondictionary.service.base;

import com.querydsl.core.types.Predicate;
import org.maxkizi.regiondictionary.model.base.IEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends IEntity<I>, I extends Serializable> {
    Page<T> findAll(Predicate predicate, Pageable pageable);

    List<T> findAll();

    Optional<T> get(Predicate predicate);

    Optional<T> get(I id);

    T save(T t);

    void delete(T t);

    void delete(I id);

    boolean isExist(I id);
}
