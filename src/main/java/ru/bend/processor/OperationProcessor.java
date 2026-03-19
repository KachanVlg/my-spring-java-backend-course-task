package ru.bend.processor;

import ru.bend.model.OperationType;

public interface OperationProcessor {

    void processOperation();

    OperationType operationType();
}
