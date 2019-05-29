package com.example.shoppingcart;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    private Context mContext;
    private List<FoodBean.DataBean> mList;

    public FoodAdapter(Context context, List<FoodBean.DataBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_food, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FoodBean.DataBean dataBean = mList.get(i);
        viewHolder.mTvId.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.mTvId.setText(dataBean.getId());
        viewHolder.mTvPrice.setText(dataBean.getCollect_num());
        viewHolder.mTvname.setText(dataBean.getTitle());
        RoundedCorners roundedCorners = new RoundedCorners(30);
        RequestOptions requestOptions = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(mContext).load(dataBean.getPic()).apply(requestOptions).into(viewHolder.mImgLeft);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgLeft;
        TextView mTvId;
        CheckBox mCb;
        TextView mTvPrice,mTvname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImgLeft = (ImageView) itemView.findViewById(R.id.img_left);
            this.mTvId = (TextView) itemView.findViewById(R.id.tv_id);
            this.mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            this.mCb = (CheckBox) itemView.findViewById(R.id.cb);
            this.mTvname = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

}
