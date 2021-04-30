/**
 * @author ChenYiYao
 * @date 2021/4/28
 * des :
 */
package com.hopewaytech;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * @author ChenYiYao
 * @date 2021/4/28
 * des :
 */
public class HiConsolePrinter implements HiLogPrinter {

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int countOfSub = len / config.MAX_LEN;
        if (countOfSub > 0) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(printString.substring(index, index + config.MAX_LEN));
                index += config.MAX_LEN;
            }
            if (index != len) {
                log.append(printString.substring(index, len));
            }
            Log.println(level, tag, log.toString());
        } else {
            Log.println(level, tag, printString);
        }
    }
}
