/**
 * @author ChenYiYao
 * @date 2021/4/29
 * des :
 */
package com.hopewaytech;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hopewaytech.logutils.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYiYao
 * @date 2021/4/29
 * des : 将log 显示界面
 */
public class HiViewPrinter implements HiLogPrinter {
    private RecyclerView mRecyclerView;
    private LogAdapter mLogAdapter;
    private HiViewPrinterProvider mProvider;


    public HiViewPrinter(Activity activity) {
        if (activity == null) {
            return;
        }
        View rootView = activity.findViewById(android.R.id.content);
        mRecyclerView = new RecyclerView(activity);
        mLogAdapter = new LogAdapter(LayoutInflater.from(mRecyclerView.getContext()));
        LinearLayoutManager lm = new LinearLayoutManager();
        mRecyclerView.setLayoutManager(lm);
        mRecyclerView.setAdapter(mLogAdapter);
        mProvider = new HiViewPrinterProvider();
    }

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag,
                      @NonNull String printString) {

    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
        private LayoutInflater mLayoutInflater;
        private List<HiLogMo> logs = new ArrayList<>();

        public LogAdapter(LayoutInflater inflater) {
            mLayoutInflater = inflater;

        }


        @NonNull
        @Override

        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = mLayoutInflater.inflate(R.layout.item_hilog, parent, false);

            return new LogViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            HiLogMo hiLogMo = logs.get(position);

            int highlightColor = getHighlightColor(hiLogMo.level);
            holder.tagView.setTextColor(highlightColor);
            holder.messageView.setTextColor(highlightColor);
            holder.tagView.setText(hiLogMo.getFlattened());
            holder.messageView.setText(hiLogMo.log);
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }


        /**
         * 跟进log级别获取不同的高了颜色
         *
         * @param logLevel log 级别
         * @return 高亮的颜色
         */
        private int getHighlightColor(int logLevel) {
            int highlight;
            switch (logLevel) {
                case HiLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case HiLogType.D:
                    highlight = 0xffffffff;
                    break;
                case HiLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case HiLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case HiLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }

    }


    private static class LogViewHolder extends RecyclerView.ViewHolder {

        TextView tagView;
        TextView messageView;


        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag);
            messageView = itemView.findViewById(R.id.message);
        }
    }


}
