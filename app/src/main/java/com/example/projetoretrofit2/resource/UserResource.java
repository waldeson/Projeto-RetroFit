package com.example.projetoretrofit2.resource;

import com.example.projetoretrofit2.models.UserId;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserResource {

    @GET("albums")
    Call<List<UserId>> get();

    @POST("albums")
    Call<UserId> post(@Body UserId userId);

    @PUT("albums/{id}")
    Call<UserId> put(@Path("id") Integer id, @Body UserId userId);

    @DELETE("albums/{id}")
    Call<Void> delete(@Path("id") Integer id);
}
