package com.talklittle.support2510fcstaggeredgridlayoutmanagerprefetch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            int mItemCount = 50;

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View root = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);

                StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.height = 200;
                root.setLayoutParams(layoutParams);

                return new MyViewHolder(root);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                layoutParams.setFullSpan(position % 15 == 0);

                layoutParams.height = (int) (200 + (Math.random() * 1000));
                holder.itemView.setLayoutParams(layoutParams);

                holder.itemView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notifyItemChanged(position);
                    }
                }, (long) (Math.random() * 5000));

                ((MyViewHolder) holder).mTextView.setText("position = " + position);
            }

            @Override
            public int getItemCount() {
                return mItemCount;
            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
