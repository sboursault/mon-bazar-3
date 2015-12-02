package com.sb.monbazar.utils;

public class Preconditions  {

    public static void checkNotBlank(String value, String msg) {
        com.google.appengine.repackaged.com.google.common.base.Preconditions.checkArgument(value != null && !value.trim().equals(""), msg);
    }

    public static void checkNotNull(Long value, String msg) {
        com.google.appengine.repackaged.com.google.common.base.Preconditions.checkNotNull(value, msg);
    }
}