package com.web.filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Slf4j
public class TransactionContextHolder {

    private static final ThreadLocal<String> transactionContextHolder = new ThreadLocal<String>();

    public static void setTransactionId(String transactionId) {
        Assert.notNull(transactionId, "TransactionId cannot be null");
        transactionContextHolder.set(transactionId);
    }

    public static String getTransactionId() {
        log.debug("[transactionContextHolder.get()] : {}", transactionContextHolder.get());
        return transactionContextHolder.get();
    }

    public static void clearTransactionId() {
        transactionContextHolder.remove();
    }

}
