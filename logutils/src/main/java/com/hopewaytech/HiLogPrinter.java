package com.hopewaytech;

import androidx.annotation.NonNull;

/**
 * @author ChenYiYao
 * @date 2021/4/28
 * des :
 */
public interface HiLogPrinter {
    void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString);
}
