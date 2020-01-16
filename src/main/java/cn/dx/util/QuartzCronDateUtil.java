package cn.dx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class QuartzCronDateUtil {

    /***
     *  功能描述：日期转换cron表达式时间格式
     * @param date 要转换的时间
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return 格式化后的时间
     */
    private static String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron ,eg.  "14 01 17 22 07 ? 2017"
     * @param date:时间点
     * @return cron表达式
     */
    public static String getCron(Date date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

    /**
     * 将cron表达式转为格式化date字符串
     * @param cron cron表达式
     * @return 格式化date字符串
     */
    public static String getFormatDate(String cron){
        SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy", Locale.getDefault());
        Date parse = null;
        try {
            parse = sdf.parse(cron);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDateByPattern(parse, "yyyy-MM-dd HH:mm:ss");
    }
}