package model;

import java.math.BigDecimal;

public record AccountListItem(Integer accountId, String number, String owner, BigDecimal balance, String accountType,
                              BigDecimal limit, BigDecimal yieldRate) {
}
