package com.abc;

import lombok.Data;

/**
 * @author lb
 * @date 9/21/22
 */
@Data
public class BidOpenVO {

    /**
     * 资本充足率
     */
    private BidOpenItem capitalRatio;

    /**
     * 不良贷款率 Non-performing Loan
     */
    private BidOpenItem nplRatio;


    /**
     * 拨备覆盖率
     */
    private BidOpenItem provisionCoverageRatio;

    /**
     * 流动性覆盖率
     */
    private BidOpenItem liquidityCoverageRatio;
    /**
     * 流动性比例
     */
    private BidOpenItem liquidityRatio;

    /**
     * 本期存款利率
     */
    private BidOpenItem currentDepositRate;


    /**
     * PBC 央行　The people's bank of china
     * 同期央行存款利率
     */
    private BidOpenItem pbcDepositRate;

    /**
     * 在陕西省资产规模情况
     */
    private BidOpenItem assetsInShaanxi;


    /**
     * 在陕西省纳税规模情况　合计
     */
    private BidOpenItem totalTax;

    /**
     * 在陕西省纳税规模情况　个人所得税 Individual Income Tax
     */
    private BidOpenItem iit;

    /**
     * 在陕西省纳税规模情况　企业所得税 Corporate Income Tax
     */
    private BidOpenItem cit;

    /**
     * 在陕西省纳税规模情况　增值税　　Value-added Tax
     */
    private BidOpenItem vat;


    /**
     * 一般性存款余额
     */
    private BidOpenItem generalDepositBalance;

    /**
     * 国债
     */
    private BidOpenItem nationalDebt;
    /**
     * 政府债
     */
    private BidOpenItem governmentDebt;


}
