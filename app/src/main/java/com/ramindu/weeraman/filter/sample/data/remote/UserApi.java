package com.ramindu.weeraman.filter.sample.data.remote;

import com.ramindu.weeraman.filter.sample.data.model.UserItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UserApi {

    @GET("/matches.json")
    Observable<List<UserItem>> getProductList();
}
