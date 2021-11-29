package org.maxkizi.regiondictionary.model.base;

import java.io.Serializable;

public interface IDeletedEntity<I extends Serializable> extends IEntity<I> {
    boolean isDeleted();

    void setDeleted(boolean isDeleted);
}
