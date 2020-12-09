package com.example.pdd.enums;

/**
 * @Author wxy
 * @Date 2020/10/29 10:03
 * @Version 1.0
 */
public enum MessageType {

    PDD_GOODS_GOODSONSHELF("PDD_GOODS_GOODSONSHELF", "商品上架消息"),
    PDD_GOODS_GOODSADD("PDD_GOODS_GOODSADD", "商品新建消息"),
    PDD_GOODS_GOODSUPDATE("PDD_GOODS_GOODSUPDATE", "商品更新消息"),
    PDD_CHAT_ORDERPROMISE("PDD_CHAT_ORDERPROMISE", "订单服务承诺消息"),
    PDD_REFUND_REFUNDCLOSED("PDD_REFUND_REFUNDCLOSED", "售后单关闭消息"),
    PDD_REFUND_REFUNDAGREEAGREEMENT("PDD_REFUND_REFUNDAGREEAGREEMENT", "同意退款协议消息"),
    PDD_TRADE_TRADELOGISTICSADDRESSCHANGED("PDD_TRADE_TRADELOGISTICSADDRESSCHANGED", "修改交易收货地址消息"),
    PDD_TRADE_TRADESUCCESS("PDD_TRADE_TRADESUCCESS", "交易成功消息"),
    PDD_TRADE_TRADESELLERSHIP("PDD_TRADE_TRADESELLERSHIP", "卖家发货消息"),
    PDD_TRADE_TRADECONFIRMED("PDD_TRADE_TRADECONFIRMED", "交易确认消息"),
    ;

    private final String name;
    private final String description;

    MessageType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
