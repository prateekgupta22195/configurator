package com.loconav.configurator.activity;

import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loconav.configurator.DeviceListAdapter;
import com.loconav.configurator.LookUpEntry;
import com.loconav.configurator.MessageEvent;
import com.loconav.configurator.app.BaseActivity;
import com.loconav.configurator.R;
import com.loconav.configurator.db.DeviceHelper;
import com.loconav.configurator.model.Device;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView deviceStatusListView;
    ArrayList<Device> deviceList = new ArrayList<>();
    DeviceListAdapter adapter ;
    DeviceHelper deviceHelper;
    private Toolbar toolbar;

    @Override
    public Integer setView() {return R.layout.activity_main;}

    @Override
    public void activityCreated() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EnterDeviceInfo.class);
                startActivityForResult(intent, 1);
            }
        });
//        setNavigationMenu();
        deviceHelper = new DeviceHelper();
        deviceStatusListView = (ListView) findViewById(R.id.device_status_list);
        deviceList.addAll(deviceHelper.getAllDevices("ACTIVE"));
        adapter = new DeviceListAdapter(this, deviceList);
        deviceStatusListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {/* Do something */
//        bottomBar.selectTabAtPosition(event.getState());
        if(event.getAction() == MessageEvent.Action.REFRESH_DEVICE_STATUS) {
            refreshList();
        }
    }

    private void refreshList() {
        deviceList.clear();
        deviceList.addAll(new DeviceHelper().getAllDevices("ACTIVE"));
        adapter.notifyDataSetChanged();
        // code to refresh data
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(resultCode == 1) {
            refreshList();
        }
    }

    private void setNavigationMenu() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search_device) {
            Intent i = new Intent(getApplicationContext(), LookUpEntry.class);
            startActivity(i);
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }
}
