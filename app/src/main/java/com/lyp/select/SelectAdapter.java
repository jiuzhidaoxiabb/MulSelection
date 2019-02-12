package com.lyp.select;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lyp
 * @classname SelectAdapter
 * @describe 实现选择的适配器类
 * @date 2019/2/12
 */
public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mList;
    private SparseBooleanArray mBooleanArray = new SparseBooleanArray();

    public SelectAdapter(List<String> list) {
        if (list == null) {
            throw new IllegalArgumentException("model Data must not be null");
        }
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, viewGroup,
                false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mTextTitle.setText(mList.get(i));
        viewHolder.mCheckBox.setChecked(isItemChecked(i));
        // checkBox 监听
        viewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(i)) {
                    setItemChecked(i, false);
                } else {
                    setItemChecked(i, true);
                }
            }
        });
        // 条目view监听
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(i)) {
                    setItemChecked(i, false);
                } else {
                    setItemChecked(i, true);
                }
                notifyItemChanged(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox mCheckBox;
        TextView mTextTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCheckBox = itemView.findViewById(R.id.select_checkbox);
            mTextTitle = itemView.findViewById(R.id.text_title);
        }
    }
    /**
     * 获得选中条目的结果
     *
     * @return
     */
    public List<String> getSelectedItem() {
        List<String> selectList = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            if (isItemChecked(i)) {
                selectList.add(mList.get(i));
            }
        }
        return selectList;
    }

    /**
     * 设置给定位置条目的选择状态
     *
     * @param position
     * @param isChecked
     */
    private void setItemChecked(int position, boolean isChecked) {
        mBooleanArray.put(position, isChecked);
    }

    /**
     * 根据位置判断条目是否选中
     *
     * @param position
     * @return
     */
    private boolean isItemChecked(int position) {
        return mBooleanArray.get(position);
    }
}
