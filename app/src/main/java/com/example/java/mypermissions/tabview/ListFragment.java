package com.example.java.mypermissions.tabview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.java.mypermissions.R;

/**
 * Created by dingkai .
 * DATA: 2019/1/30 .
 * Description :
 */
public class ListFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate (R.layout.list_fragment, container, false);
        mRecyclerView.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        mRecyclerView.setLayoutManager (new LinearLayoutManager (mRecyclerView.getContext ()));
        mRecyclerView.setAdapter (new RecyclerViewAdapter(getActivity ()));
    }
}
