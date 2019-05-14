package com.liwj.szpd.utils;

public enum PermitEnum {
    ADDPROJECT("创建项目","1001");

    private String name;
    private String code;

    PermitEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(String code) {
        for (PermitEnum c : PermitEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
