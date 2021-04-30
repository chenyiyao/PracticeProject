package com.hopewaytech;

/**
 * @author ChenYiYao
 * @date 2021/4/28
 * des :
 */
public class HiStackTraceFormatter implements HiLogFormatter<StackTraceElement[]> {
    @Override
    public String format(StackTraceElement[] data) {
        StringBuffer sb = new StringBuffer(128);
        if (data == null || data.length == 0) {
            return null;
        } else if (data.length == 1) {
            return "\t" + data[0].toString();
        } else {
            for (int i = 0, len = data.length; i < len; i++) {
                if (i == 0) {
                    sb.append("stackTrace:  \n");
                } else if (i != len - 1) {
                    sb.append("\t├ ");
                    sb.append(data[i].toString());
                    sb.append("\n");
                } else {
                    sb.append("\t└ ");
                    sb.append(data[i].toString());
                }
            }
        }
        return sb.toString();
    }
}
