package com.loconav.configurator;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;

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


}
