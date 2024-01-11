package com.treeNode;

public enum UserTypeEnum {
    ADMIN("1","管理员"),
    NORMAL("2", "普通用户");

    private final String code;
    private final String message;

    private UserTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static UserTypeEnum getByCode(String code) {
        UserTypeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            UserTypeEnum userType = var1[var3];
            if (userType.getCode().equals(code)) {
                return userType;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return String.format("UserTypeEnum[code=%s,message=%s]", this.code, this.message);
    }

}
