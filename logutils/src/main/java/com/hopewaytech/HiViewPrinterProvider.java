/**
 * @author ChenYiYao
 * @date 2021/4/29
 * des :
 */
package com.hopewaytech;

import android.view.View;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author ChenYiYao
 * @date 2021/4/29
 * des :
 */
public class HiViewPrinterProvider {
    private FrameLayout rootView;
    private View floatingView;
    private boolean isOpen;
    private FrameLayout logView;
    private RecyclerView mRecyclerView;

    public HiViewPrinterProvider(FrameLayout rootView, RecyclerView recyclerView) {
        this.rootView = rootView;
        this.mRecyclerView = recyclerView;
    }

    private static final String TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW";
    private static final String TAG_LOG_VIEW = "TAG_LOG_VIEW";

    public void showFloatingView() {

    }

}
