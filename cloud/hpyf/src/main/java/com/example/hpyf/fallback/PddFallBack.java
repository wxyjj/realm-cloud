package com.example.hpyf.fallback;

import com.example.common.enums.StoreType;
import com.example.common.feign.dto.DecryptBatchDto;
import com.example.common.support.ApiException;
import com.example.common.support.Result;
import com.example.hpyf.feign.PddFeign;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsQuantityUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsSkuPriceUpdateRequest;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsQuantityUpdateResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsSkuPriceUpdateResponse;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 拼多多消费-服务降级
 *
 * @Author wxy
 * @Date 2020/12/7 15:11
 * @Version 1.0
 */
@Slf4j
@Component
public class PddFallBack implements PddFeign {

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
    public Result<PddGoodsSkuPriceUpdateResponse> updatePrice(PddGoodsSkuPriceUpdateRequest request, StoreType storeType) {
        log.info("时间%s,拼多多服务-更新价格,降级了！" + System.currentTimeMillis());
        this.rollback();
        return Result.downgrade();
    }

    @Override
    public Result<PddGoodsQuantityUpdateResponse> updateStock(PddGoodsQuantityUpdateRequest request, StoreType storeType) {
        log.info("时间%s,拼多多服务-更新库存,降级了！" + System.currentTimeMillis());
        this.rollback();
        return Result.downgrade();
    }

    @Override
    public Result<List<DecryptBatchDto>> decryptBatchByOrderId(String orderSn) {
        log.info("时间%s,拼多多服务-订单id获取解密数据,降级了！" + System.currentTimeMillis());
        return Result.downgrade();
    }
}
