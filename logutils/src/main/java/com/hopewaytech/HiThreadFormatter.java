/**
 * @author ChenYiYao
 * @date 2021/4/28
 * des :
 */
package com.hopewaytech;

/**
 * @author ChenYiYao
 * @date 2021/4/28
 * des :
 */
public class HiThreadFormatter implements HiLogFormatter<Thread> {
    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
