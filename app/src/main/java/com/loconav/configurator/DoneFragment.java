package com.loconav.configurator;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

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

public class DoneFragment extends BaseFragment {

    @BindView(R.id.device_status_list)
    ListView deviceStatusListView;
    ArrayList<Device> deviceList = new ArrayList<>();
    DeviceListAdapter adapter ;
    DeviceHelper deviceHelper;

    @Override
    public int setViewId() {
        return R.layout.fragment_done;
    }

    @Override
    public void onFragmentViewInflated() {
        deviceHelper = new DeviceHelper();
        deviceList.addAll(deviceHelper.getAllDevices("DONE"));
        adapter = new DeviceListAdapter(getContext(), deviceList);
        deviceStatusListView.setAdapter(adapter);
    }

    @Override
    public void bindButterKnife(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setupComponents() {}

    private void refreshList() {
        deviceList.clear();
        deviceList.addAll(new DeviceHelper().getAllDevices("DONE"));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

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


}
