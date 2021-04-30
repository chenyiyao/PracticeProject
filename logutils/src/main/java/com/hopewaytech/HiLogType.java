/**
 * @author ChenYiYao
 * @date 2021/4/27
 * des :
 */
package com.hopewaytech;

import android.util.Log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ChenYiYao
 * @date 2021/4/27
 * des :
 */
public class HiLogType {

    @IntDef({V, D, I, W, E, A})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    public final static int V = Log.VERBOSE;
    public final static int D = Log.DEBUG;
    public final static int I = Log.INFO;
    public final static int W = Log.WARN;
    public final static int E = Log.ERROR;
    public final static int A = Log.ASSERT;


}
