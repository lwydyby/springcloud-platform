package com.gameley.gameleyauth.utils;

public class StringUtil {
    /**
     * 判断字符串是否为null
     *
     * @param string
     * @return
     */
    public static boolean checkNull(String string) {
        return string == null || "".equals(string.trim())
                || "null".equalsIgnoreCase(string.trim());
    }

}
