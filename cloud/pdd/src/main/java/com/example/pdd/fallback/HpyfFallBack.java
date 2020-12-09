package com.example.pdd.fallback;


import com.example.common.support.ApiException;
import com.example.common.support.Result;
import com.example.pdd.feign.HpyfFeign;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsInformationGetResponse;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 和平药房消费-服务降级
 *
 * @Author wxy
 * @Date 2020/12/7 15:11
 * @Version 1.0
 */
@Slf4j
@Component
public class HpyfFallBack implements HpyfFeign {
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

    @Override
    public Result<Object> saveOrderMessage(String orderId) {
        log.info("时间%s,和平药房服务-订单消息入库,降级了！" + System.currentTimeMillis());
        this.rollback();
        return Result.downgrade();
    }

    @Override
    public Result<Object> goodsCheckCode(PddGoodsInformationGetResponse pddGoodsInformationGetResponse, Long onShelfTime, String mallId) {
        log.info("时间%s,和平药房服务-商品对码,降级了！" + System.currentTimeMillis());
        this.rollback();
        return Result.downgrade();
    }
}
