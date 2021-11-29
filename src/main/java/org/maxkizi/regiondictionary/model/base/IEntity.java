package org.maxkizi.regiondictionary.model.base;

import java.io.Serializable;

public interface IEntity<I extends Serializable> {
    I getId();
}
