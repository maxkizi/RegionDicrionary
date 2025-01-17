package org.maxkizi.regiondictionary.service.base;

import com.querydsl.core.types.Predicate;
import org.maxkizi.regiondictionary.model.base.IEntity;
import org.maxkizi.regiondictionary.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseService
        <T extends IEntity<I>, I extends Serializable, R extends BaseRepository<T, I>>
        implements BaseService<T, I> {
    public abstract R getRepository();

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        return getRepository().findAll(predicate, pageable);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Optional<T> get(Predicate predicate) {
        return getRepository().findOne(predicate);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Optional<T> get(I id) {
        return getRepository().findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public T save(T t) {
        return getRepository().save(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(T t) {
        getRepository().delete(t);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(I id) {
        getRepository().deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public boolean isExist(I id) {
        return getRepository().existsById(id);
    }
}
