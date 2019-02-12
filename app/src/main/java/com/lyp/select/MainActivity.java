package com.lyp.select;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lyp
 * @classname MainActivity
 * @describe RecyclerView结合CheckBox实现单选和复选
 * @date 2019/2/12
 */
public class MainActivity extends AppCompatActivity {

    private static final int ITEM_SIZE = 20;

    private Button mButton;
    private RecyclerView mRecyclerView;
    private SelectAdapter mAdapter;

    List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initData();
        initView();
    }

    private void findView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mButton = findViewById(R.id.button);
    }

    private void initData() {
        for (int i = 0; i < ITEM_SIZE; i++) {
            mList.add("条目" + i);
        }
    }

    private void initView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SelectAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        // 设置分割线
        setItemDecoration();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.getSelectedItem();
                for (int i = 0; i < mAdapter.getSelectedItem().size(); i++) {
                    System.out.println("获取到的item条目：" + mAdapter.getSelectedItem().get(i));
                }

            }
        });
    }

    /**
     * 设置分割线
     */
    private void setItemDecoration() {
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }
}
