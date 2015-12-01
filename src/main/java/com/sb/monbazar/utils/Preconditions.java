package com.sb.monbazar.utils;

public class Preconditions  {

    public static void checkNotBlank(String value, String msg) {
        com.google.appengine.repackaged.com.google.common.base.Preconditions.checkArgument(value != null && !value.trim().equals(""), msg);
    }
}
