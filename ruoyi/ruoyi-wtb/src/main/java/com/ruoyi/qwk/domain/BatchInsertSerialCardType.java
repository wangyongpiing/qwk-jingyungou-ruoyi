package com.ruoyi.qwk.domain;/**
 * @Description:
 * @author cjf
 * @date 2023-12-01
 */

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.v3.oas.models.security.SecurityScheme;

import javax.validation.constraints.NotEmpty;

/**
 * @author cjf
 * @date 2023/12/1
 */
public class BatchInsertSerialCardType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String goodsType;

    public Long getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Long goodCount) {
        this.goodCount = goodCount;
    }

    private Long goodCount;

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
}
