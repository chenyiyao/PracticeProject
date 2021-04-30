/**
 * @author ChenYiYao
 * @date 2021/4/27
 * des :
 */
package com.hopewaytech;


import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * @author ChenYiYao
 * @date 2021/4/27
 * des : log门面
 * 提供打印入口方法
 */
public class HiLog {
    private static final String LOG_PACKAGE;

    static {
        String className = HiLog.class.getName();
        LOG_PACKAGE = className.substring(0, className.lastIndexOf('.') + 1);
    }

    public static void v(Object... content) {
        vt(LOG_PACKAGE, content);
    }

    public static void vt(String tag, Object... content) {
        log(HiLogType.V, tag, content);
    }

    public static void v(String... content) {
        vt(LOG_PACKAGE, content);
    }

    public static void vt(String tag, String... content) {
        log(HiLogType.V, tag, content);
    }

    public static void d(Object... content) {
        dt(LOG_PACKAGE, content);
    }

    public static void dt(String tag, Object... content) {
        log(HiLogType.D, tag, content);
    }

    public static void d(String... content) {
        dt(LOG_PACKAGE, content);
    }

    public static void dt(String tag, String... content) {
        log(HiLogType.D, tag, content);
    }

    public static void i(Object... content) {
        it(LOG_PACKAGE, content);
    }

    public static void it(String tag, Object... content) {
        log(HiLogType.I, tag, content);
    }

    public static void i(String... content) {
        it(LOG_PACKAGE, content);
    }

    public static void it(String tag, String... content) {
        log(HiLogType.I, tag, content);
    }

    public static void w(Object... content) {
        wt(LOG_PACKAGE, content);
    }

    public static void wt(String tag, Object... content) {
        log(HiLogType.W, tag, content);
    }

    public static void w(String... content) {
        it(LOG_PACKAGE, content);
    }

    public static void wt(String tag, String... content) {
        log(HiLogType.W, tag, content);
    }

    public static void e(Object... content) {
        et(LOG_PACKAGE, content);
    }

    public static void et(String tag, Object... content) {
        log(HiLogType.E, tag, content);
    }

    public static void e(String... content) {
        et(LOG_PACKAGE, content);
    }

    public static void et(String tag, String... content) {
        log(HiLogType.E, tag, content);
    }


    public static void a(Object... content) {
        et(LOG_PACKAGE, content);
    }

    public static void at(String tag, Object... content) {
        log(HiLogType.A, tag, content);
    }

    public static void a(String... content) {
        et(LOG_PACKAGE, content);
    }

    public static void at(String tag, String... content) {
        log(HiLogType.A, tag, content);
    }

    public static void log(@HiLogType.TYPE int type, @NonNull String tag, Object... objects) {
        log(HiLogManager.getInstance().getConfig(), type, tag, objects);
    }

    public static void log(@NonNull HiLogConfig config, @HiLogType.TYPE int type, @NonNull String tag, Object... contents) {
        if (!config.enable()) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (config.includeThread()) {
            //打印线程信息
            String threadInfo = HiLogConfig.HI_THREAD_FORMATTER.format(Thread.currentThread());
            sb.append(threadInfo).append("\n");
        }

        if (config.StackTraceDepth() > 0) {
            StackTraceElement[] stackTraceElements = HiStackTraceUtil.getCroppedRealStackTrack(new Throwable().getStackTrace(),
                    LOG_PACKAGE, config.StackTraceDepth());

            String stackTrace = HiLogConfig.Hi_STACK_TRACE_FORMATTER.format(stackTraceElements);
            sb.append(stackTrace).append("\n");
        }
        String body = parseBody(contents, config);
        if (body != null) {
            //替换转义字符\
            body.replace("\\\"", "\\\"");
        }
        sb.append(body);
        List<HiLogPrinter> printers = config.printers() != null ?
                Arrays.asList(config.printers()) : HiLogManager.getInstance().getPrinters();

        if (printers == null) {
            return;
        }
        //打印log
        for (HiLogPrinter printer : printers) {
            printer.print(config, type, tag, sb.toString());
        }

    }

    private static String parseBody(@NonNull Object[] contents, @NonNull HiLogConfig config) {
        if (config.injectJsonParser() != null) {
            if (contents.length == 1 && contents[0] instanceof String) {
                return (String) contents[0];
            }
            return config.injectJsonParser().toJson(contents);
        }
        StringBuilder sb = new StringBuilder();
        for (Object o : contents) {
            sb.append(o.toString()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
