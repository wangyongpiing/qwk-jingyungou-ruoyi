package com.ruoyi.common.domain;/**
 * @Description:
 * @author cjf
 * @date 2023-06-29
 */

/**
 * @author cjf
 * @date 2023/6/29
 */
public class Result {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
