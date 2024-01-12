package com.ruoyi.qwk.domain;/**
 * @Description:
 * @author cjf
 * @date 2023-12-01
 */

import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author cjf
 * @date 2023/12/1
 */
public class BatchInsertSerialCard extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String wx;

    public List<BatchInsertSerialCardType> getCardList() {
        return cardList;
    }

    public void setCardList(List<BatchInsertSerialCardType> cardList) {
        this.cardList = cardList;
    }

    private List<BatchInsertSerialCardType> cardList;

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }


}
