package cn.webdemo.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 63042 on 2019/6/24.
 *
 */
public class DateUtils {
    // 日期转字符串
    public static String date2String(Date date, String patt){
        SimpleDateFormat smf = new SimpleDateFormat(patt);
        String format = smf.format(date);
        return format;
    }

    // 字符串日期转时间
    public static Date string2Date(String str, String patt) throws ParseException {
        SimpleDateFormat smf = new SimpleDateFormat(patt);
        Date date = smf.parse(str);
        return date;
    }
}
