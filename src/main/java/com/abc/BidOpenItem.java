package com.abc;

import lombok.Data;

/**
 * @author lb
 * @date 9/21/22
 */
@Data
public class BidOpenItem {

    /**
     * 一级指标
     */
    private String levelOne;
    /**
     * 二级指标
     */
    private String levelTwo;

    /**
     * 数据项
     */
    private String itemName;
    /**
     * 指标值
     */
    private String itemValue;
    /**
     * 单位
     */
    private String itemUnit;
    /**
     * 说明
     */
    private String description;
}
