package com.loconav.configurator;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.loconav.configurator.activity.EnterDeviceInfo;
import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by prateek on 22/04/18.
 */

public class ActiveFragment extends BaseFragment {

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
//                     TODO : call call and ask for status
            }
        });
    }
}
