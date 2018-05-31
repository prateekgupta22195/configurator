package com.loconav.configurator;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.loconav.configurator.activity.EnterDeviceInfo;
import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;
import com.loconav.configurator.network.RetrofitCallback;
import com.loconav.configurator.network.rest.ApiClient;
import com.loconav.configurator.network.rest.ApiInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by prateek on 22/04/18.
 */

public class ActiveFragment extends BaseFragment {
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    @BindView(R.id.device_status_list) ListView deviceStatusListView;
    ArrayList<Device> deviceList = new ArrayList<>();
    DeviceListAdapter adapter ;
    DeviceHelper deviceHelper;
    Handler handler = new Handler();
    Runnable runnable;
    @BindView(R.id.fab) FloatingActionButton fab;


    @Override
    public int setViewId() {
        return R.layout.fragment_active;
    }

    @Override
    public void onFragmentViewInflated() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EnterDeviceInfo.class);
                startActivity(intent);
            }
        });
        deviceHelper = new DeviceHelper();
        deviceList.addAll(deviceHelper.getAllDevices("ACTIVE"));
        adapter = new DeviceListAdapter(getContext(), deviceList);
        deviceStatusListView.setAdapter(adapter);
    }

    @Override
    public void bindButterKnife(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupComponents() {}


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if(event.getAction() == MessageEvent.Action.REFRESH_DEVICE_STATUS) {
            refreshList();
        }
    }

    private void refreshList() {
        deviceList.clear();
        deviceList.addAll(new DeviceHelper().getAllDevices("ACTIVE"));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.e("runnable ", "run: ");
                List<String> lookupMissingDevices = deviceHelper.getAllUnavailLookupDevices();
                apiService.getDeviceStatus(lookupMissingDevices).enqueue(new RetrofitCallback<ResponseBody>() {
                    @Override
                    public void handleSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            JSONObject data = (JSONObject) jsonObject.get("data");
                            Iterator<String> iter = data.keys();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                try {
                                    Boolean value = (Boolean) data.get(key);
                                    Device device = deviceHelper.getDeviceByID(key);
                                    if(device!=null) {
                                        if(value)
                                            device.setLookupAvailable(1);
                                        else
                                            device.setLookupAvailable(0);
                                        deviceHelper.updateDevice(device, false);
                                    }
                                    Log.e("boolean ", value + "");
                                } catch (JSONException e) {
                                    // Something went wrong!
                                }
                            }
                            refreshList();
                            Log.e("response"," " + response.body().string() + jsonObject.get("success") + jsonObject.get("data"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void handleFailure(Call<ResponseBody> call, Throwable t) {}
                });

                runnable = this;
                handler.postDelayed(runnable, 10000);
            }

        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if(runnable!=null)
            handler.removeCallbacks(runnable);
    }
}
