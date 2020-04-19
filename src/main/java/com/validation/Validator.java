package com.validation;

import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T item);
    boolean hasErrors();
}
