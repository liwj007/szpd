package com.liwj.szpd.utils;

public enum RoleEnum {
    TREASURER("财务","treasurer"),CREATOR("高级用户","creator"),ORDINARY("","ordinary");
    private String name;
    private String type;

    RoleEnum(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public static String getName(String type) {
        for (RoleEnum c : RoleEnum.values()) {
            if (c.getType().equals(type)) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
