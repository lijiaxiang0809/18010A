package com.example.shoppingcart;


import io.reactivex.Observable;
import okhttp3.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String foodurl="http://www.qubaobei.com/";
    @GET("ios/cf/dish_list.php?stage_id=1&limit=20")
    Observable<FoodBean> getfod(@Query("page") int page);

}
