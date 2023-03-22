package com.food.ordering.system.domain.valueobject;

import java.util.Objects;

public abstract class BaseId<T> {

    private final T value;

    protected BaseId(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseId<?> baseID)) return false;
        return Objects.equals(getValue(), baseID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
