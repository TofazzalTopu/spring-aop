package com.info.aop.util;

import java.util.Arrays;
import java.util.Objects;

public class ParseUtil {

    private ParseUtil() {
    }

    public static boolean isNotNullAndNotEmpty(String value) {
        return Objects.nonNull(value) && !value.isEmpty();
    }

    public static boolean isNullOrEmpty(String value) {
        return Objects.isNull(value) || value.isEmpty();
    }

    public static boolean isNullOrEmpty(String... values) {
        return Arrays.stream(values).anyMatch(v -> v == null || v.isEmpty());
    }


}
