package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {
    private final Integer operationId;
    private final Integer accountId;
    private final String operation;
    private final BigDecimal value;
    private final LocalDateTime date;

    public Operation(Integer operationId, Integer accountId, String operation,
                     BigDecimal value, LocalDateTime date) {
        this.operationId = operationId;
        this.accountId = accountId;
        this.operation = operation;
        this.value = value;
        this.date = date;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getOperation() {
        return operation;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
