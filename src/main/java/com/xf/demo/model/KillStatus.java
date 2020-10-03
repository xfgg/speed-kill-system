package com.xf.demo.model;

/**
 * @author xfgg
 */

public enum KillStatus {
    /**
     * code表示状态码，name表示状态名称
     */
    IN_VALID(-1,"无效"),
    SUCCESS(0,"成功"),
    PAY(1,"已付款");
    private int code;
    private String name;
    KillStatus(){}
    KillStatus(int code, String name) {
        this.code=code;
        this.name=name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
