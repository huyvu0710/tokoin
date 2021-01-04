package com.vuha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class STRINGUtil {

    public static List<String> separateWord(String word) {
        List<String> result = new ArrayList<>();
        if (StringUtils.isEmpty(word)) {
            return result;
        }

        result = new ArrayList<>(Arrays.asList(word.trim().split("\\s+")));
        if (!result.contains(word)) {
            result.add(word);
        }
        return result;
    }

}
