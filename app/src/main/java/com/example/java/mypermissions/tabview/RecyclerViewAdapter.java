package com.example.java.mypermissions.tabview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.java.mypermissions.R;

/**
 * Created by dingkai .
 * DATA: 2019/1/30 .
 * Description :
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context mContext;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.list_item_card_main, parent, false);
        return new ViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.mView;
        view.setOnClickListener (v -> Toast.makeText (mContext, "奔跑在孤傲的路上", Toast.LENGTH_SHORT).show ());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;

        public ViewHolder(View itemView) {
            super (itemView);
            mView = itemView;
        }
    }
}
