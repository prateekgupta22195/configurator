package com.loconav.configurator;

/**
 * Created by prateek on 10/02/18.
 */

public class MessageEvent {
    /* Additional fields if needed */
    private Action _action;
    public static enum Action {REFRESH_DEVICE_STATUS}

    public Action getAction() {
        return _action;
    }

    public void setAction(Action action) {
        this._action = action;
    }

}
