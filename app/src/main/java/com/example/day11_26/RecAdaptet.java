package com.example.day11_26;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RecAdaptet extends RecyclerView.Adapter {
    private ArrayList<Banerbean.DataBean>data;
    private ArrayList<ResultsBean>results;    private Context context;

    public RecAdaptet(ArrayList<Banerbean.DataBean> data, ArrayList<ResultsBean> results, Context context) {
        this.data = data;
        this.results = results;
        this.context = context;
    }

    public ArrayList<Banerbean.DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<Banerbean.DataBean> data) {
        this.data = data;
    }

    public ArrayList<ResultsBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsBean> results) {
        this.results = results;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if (type==1){
            View view1 = View.inflate(context, R.layout.item1, null);
            ViewHolder1 viewHolder1 = new ViewHolder1(view1);
            return  viewHolder1;
        }else {
            View view2 = View.inflate(context, R.layout.item2, null);
            ViewHolder2 viewHolder2 = new ViewHolder2(view2);
            return  viewHolder2;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type==1){
            ViewHolder1 holder1= (ViewHolder1) viewHolder;
            holder1.banner.setImages(data).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Banerbean.DataBean da= (Banerbean.DataBean) path;
                    Glide.with(context).load(da.getImagePath()).into(imageView);

                }
            }).start();
        }else {
            if (data.size()>0){
                int poition=i-1;
                ViewHolder2 holder2= (ViewHolder2) viewHolder;
                holder2.title.setText(results.get(poition).getDesc());
                Glide.with(context).load(results.get(poition).getUrl()).into(holder2.img);

            }
        }

    }

    @Override
    public int getItemCount() {
      if (data.size()>0){
          return results.size()+1;
      }else {
          return results.size();
      }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0&data.size()>0){
            return 1;
        }else {
            return 2;
        }
    }

    class  ViewHolder1 extends  RecyclerView.ViewHolder{
        Banner banner;
        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
    class  ViewHolder2 extends  RecyclerView.ViewHolder{
        ImageView img;
        TextView title;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);

        }
    }

}
