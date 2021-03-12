package com.knife.base.utils;

public enum DataFormatEnum implements EnumUtils.CustomEnum<String>{
    ShortNumDate("yyMMdd"),

    NumDate("yyyyMMdd"),

    StrikeDate("yyyy-MM-dd"),

    NumDateTime("yyyyMMddHHmmss"),

    TwoYearNumDateTime("yyMMddHHmmss"),

    StrikeDateTime("yyyy-MM-dd HH:mm:ss"),

    MillisecondTime("yyyy-MM-dd HH:mm:ss SSS"),

    NumTime("HHmmss"),

    ColonTime("HH:mm:ss");

    private String value;
    DataFormatEnum(String value) {
        this.value = value;
    }

    @Override
    public String realVal() {
        return value;
    }
}
