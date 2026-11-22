package model;

import java.math.BigDecimal;

public class CheckingAccountModel {
    private Integer accountId;
    private BigDecimal limit;

    public CheckingAccountModel(Integer accountId, BigDecimal limit) {
        this.accountId = accountId;
        this.limit = limit;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }
}
