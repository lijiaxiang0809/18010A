package com.example.shoppingcart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="--Main--" ;
    private RecyclerView mRv;
    private ArrayList<FoodBean.DataBean> mList;
    private FoodAdapter adapter;
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.foodurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        build.create(ApiService.class).getfod(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoodBean foodBean) {
                        List<FoodBean.DataBean> data = foodBean.getData();
                        mList.addAll(data);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: e="+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new GridLayoutManager(this,2));
        mList = new ArrayList<>();
        adapter = new FoodAdapter(this,mList);
        mRv.setAdapter(adapter);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);



            }
        });
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) { super.onScrollStateChanged(recyclerView, newState);
//            //状态加载，显示条目+2，重新加载
//            if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem+2 >= manager.getItemCount()){ currentPage++;
//                showPhotoWindow();
//            } } @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) { super.onScrolled(recyclerView, dx, dy);
//            lastVisibleItem = manager.findLastVisibleItemPosition();
//        } });

    }
}
