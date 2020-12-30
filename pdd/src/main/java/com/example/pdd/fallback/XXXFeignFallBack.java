package com.example.pdd.fallback;

import com.example.common.support.ApiException;
import com.example.pdd.feign.XXXFeign;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 拼多多消费-服务降级
 *
 * @Author wxy
 * @Date 2020/12/7 15:11
 * @Version 1.0
 */
@Slf4j
@Component
public class XXXFeignFallBack implements XXXFeign {
    /**
     * feign调用接口fallback后，需要手动调用全局事务回滚
     */
    private void rollback() {
        try {
            String xid = RootContext.getXID();
            GlobalTransactionContext.reload(xid).rollback();
        } catch (TransactionException e) {
            throw new ApiException(10000, e.getMessage());
        }
    }
}
