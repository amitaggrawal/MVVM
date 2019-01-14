package com.amitaggrawal.thefactore.data.remote;

import com.amitaggrawal.thefactore.data.local.MyEntity;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
public class RemoteResponse {
    private MyEntity[] myEntityItems;

    public RemoteResponse(MyEntity[] items) {
        myEntityItems = items;
    }

    public MyEntity[] getData() {
        return myEntityItems;
    }
}
