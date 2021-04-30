/**
 * @author ChenYiYao
 * @date 2021/4/29
 * des :
 */
package com.hopewaytech;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

/**
 * @author ChenYiYao
 * @date 2021/4/29
 * des :
 */
public class HiLogMo {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);

    public long timeMillis;
    public int level;
    public String tag;
    public String log;

    public HiLogMo(long timeMillis, int level, String tag, String log) {
        this.timeMillis = timeMillis;
        this.level = level;
        this.tag = tag;
        this.log = log;
    }

    public String flattenedLog() {
        return getFlattened() + "\n" + log;
    }

    public String getFlattened() {
        return format(timeMillis) + '|' + level + '|' + tag + "|:";
    }

    private String format(long timeMillis) {
        return sdf.format(timeMillis);
    }
}
