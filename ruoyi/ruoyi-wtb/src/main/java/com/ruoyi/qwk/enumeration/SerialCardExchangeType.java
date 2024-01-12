package com.ruoyi.qwk.enumeration;

/**
 * @author cjf
 * @Description:
 * @date 2023-12-23
 */
public enum SerialCardExchangeType {
    card_one("277","35","1","平安无事牌"),

    card_two("302","350","10","真皮钱包"),

    card_three("303","3500","100","古玩字画");


    private String type;
    private String price;
    private String cardType;
    private String name;


    private SerialCardExchangeType(String type, String price, String cardType,String name){
        this.type = type;
        this.price = price;
        this.cardType = cardType;
        this.name = name;
    }

    // 普通方法
    public static SerialCardExchangeType getName(String type) {
        for (SerialCardExchangeType c : SerialCardExchangeType.values()) {
            if (c.getType().equals(type)) {
                return c;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
