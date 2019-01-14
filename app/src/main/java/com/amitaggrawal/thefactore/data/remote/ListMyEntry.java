package com.amitaggrawal.thefactore.data.remote;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by Amit Aggrawal on 13-01-2019.
 */
public class ListMyEntry {

    private int id;
    private String title;
    @ColumnInfo(name = "status")
    private String status;

    public ListMyEntry(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
