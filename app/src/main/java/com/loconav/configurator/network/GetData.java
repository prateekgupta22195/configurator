package com.loconav.configurator.network;

/**
 * Created by prateek on 12/05/18.
 */
public interface GetData<T> {
    void onSuccess(T response);
    void onFailure();
}
