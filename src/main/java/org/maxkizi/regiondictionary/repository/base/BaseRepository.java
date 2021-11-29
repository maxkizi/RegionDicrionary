package org.maxkizi.regiondictionary.repository.base;

import com.querydsl.core.types.EntityPath;
import org.maxkizi.regiondictionary.model.base.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T extends IEntity<I>, I extends Serializable, Q extends EntityPath<T>>
        extends JpaRepository<T, I>, QuerydslPredicateExecutor<T> {
}
