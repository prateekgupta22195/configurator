package com.loconav.configurator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by prateek on 22/04/18.
 */
public abstract class BaseFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(setViewId(), container, false);
        bindButterKnife(view);
        setupComponents();
        onFragmentViewInflated();
        return view;
    }

    public abstract int setViewId();

    public abstract void onFragmentViewInflated();

    public abstract void bindButterKnife(View view);

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public abstract void setupComponents();

    protected boolean isSafe() {
        return !(this.isRemoving() || this.getActivity() == null || this.isDetached() || !this.isAdded() || this.getView() == null);
    }

    public ActionBar getActionBar() {
        return  getAppCompatActivity().getSupportActionBar();
    }

    public AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }
}

