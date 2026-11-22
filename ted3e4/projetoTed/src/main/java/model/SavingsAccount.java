package model;

import java.math.BigDecimal;

public class SavingsAccount {
    private Integer accountId;
    private BigDecimal yieldRate;

    public SavingsAccount(Integer accountId, BigDecimal yieldRate) {
        this.accountId = accountId;
        this.yieldRate = yieldRate;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(BigDecimal yieldRate) {
        this.yieldRate = yieldRate;
    }
}
