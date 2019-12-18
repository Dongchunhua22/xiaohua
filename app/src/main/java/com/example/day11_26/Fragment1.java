package com.example.day11_26;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment1 extends Fragment {
    private View view;
    private RecyclerView mRecy;
    private ArrayList<Banerbean.DataBean> da;
    private ArrayList<ResultsBean> re;
    private RecAdaptet recAdaptet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, null);
        initView(view);
        initRecData();
        initBanner();
        initRecy();
        return view;
    }

    private void initRecy() {
        mRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecy.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        da = new ArrayList<>();
        re = new ArrayList<>();
        recAdaptet = new RecAdaptet(da, re, getActivity());
        mRecy.setAdapter(recAdaptet);


    }

    private void initBanner() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Banerbean banerbean = new Gson().fromJson(string, Banerbean.class);
                final List<Banerbean.DataBean> data = banerbean.getData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        da.addAll(data);
                        recAdaptet.notifyDataSetChanged();

                    }
                });
            }
        });
    }

    private void initRecData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                RecBean recBean = new Gson().fromJson(string, RecBean.class);
                final List<ResultsBean> results = recBean.getResults();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        re.addAll(results);
                        recAdaptet.notifyDataSetChanged();

                    }
                });
            }
        });


    }

    private void initView(View view) {
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
    }
}
