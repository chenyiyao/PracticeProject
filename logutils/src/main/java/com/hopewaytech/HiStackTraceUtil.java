package com.hopewaytech;

/**
 * @author ChenYiYao
 * @date 2021/4/28
 * des : 栈信息工具
 */
class HiStackTraceUtil {

    public static StackTraceElement[] getCroppedRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage, int maxDepth) {
        return cropStackTrace(getRealStackTrack(stackTrace, ignorePackage), maxDepth);
    }

    private static StackTraceElement[] getRealStackTrack(StackTraceElement[] stackTrace, String ignorePackage) {
        int ignoreDepth = 0;
        int allDepth = stackTrace.length;
        String className;
        for (int i = allDepth - 1; i >= 0; i--) {
            className = stackTrace[i].getClassName();
            if (ignorePackage != null && className.startsWith(ignorePackage)) {
                ignoreDepth = i + 1;
                break;
            }
        }

        int realDepth = allDepth - ignoreDepth;
        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth);
        return realStack;
    }


    /**
     * 将数组裁剪至指定最大长度
     *
     * @param callStack 源数据
     * @param maxDepth  最大长度
     * @return 裁剪后数组
     */
    private static StackTraceElement[] cropStackTrace(StackTraceElement[] callStack, int maxDepth) {
        int realDepth = callStack.length;
        if (maxDepth > 0) {
            realDepth = Math.min(realDepth, maxDepth);
        }

        StackTraceElement[] realStack = new StackTraceElement[realDepth];
        System.arraycopy(callStack, 0, realStack, 0, realDepth);
        return realStack;
    }

}
