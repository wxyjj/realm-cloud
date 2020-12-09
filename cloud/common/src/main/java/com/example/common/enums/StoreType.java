package com.example.common.enums;

/**
 * @Author wxy
 * @Date 2020/11/27 16:59
 * @Version 1.0
 */
public enum StoreType {

    HPYF("和平药房店铺id", "263128501"),
    WXYF("万鑫药房店铺id", "852475856"),
    ;

    private final String name;
    private final String description;

    StoreType(String name, String description) {
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
