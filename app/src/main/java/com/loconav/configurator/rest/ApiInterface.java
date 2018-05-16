package com.loconav.configurator.rest;

import com.loconav.configurator.model.BulkLoopUpRes;
import com.loconav.configurator.model.Device;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by prateek on 5/3/18.
 */

public interface ApiInterface {
    @Headers("X-Auth-Token: b90190cf1616edd3270667c216696156")
    @GET("check_status_of_device_ids")
    Call<ResponseBody> getDeviceStatus(@Query("device_ids[]") List<String> array);
}
