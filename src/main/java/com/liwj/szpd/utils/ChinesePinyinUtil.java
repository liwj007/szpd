package com.liwj.szpd.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ChinesePinyinUtil {
    /**
     * 得到中文首字母,例如"专科"得到zk返回
     * @param str 中文字符串
     * @return
     */
    public static String getPinYinFirstHeadChar(String str) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);

        StringBuilder sb = new StringBuilder();
        char word = str.charAt(0);
        String[] pinyinArray = new String[0];
        try {
            pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word,format);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        if (pinyinArray != null) {
            sb.append(pinyinArray[0].charAt(0));
        } else {
            sb.append(word);
        }
        return sb.toString();
    }

}
