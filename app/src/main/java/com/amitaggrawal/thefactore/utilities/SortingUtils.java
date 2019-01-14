package com.amitaggrawal.thefactore.utilities;

import com.amitaggrawal.thefactore.data.remote.ListMyEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amit Aggrawal on 13-01-2019.
 */
public class SortingUtils {

    public static List<ListMyEntry> sortByProgress(List<ListMyEntry> data) {

        List<ListMyEntry> newList = new ArrayList<ListMyEntry>();

        for (ListMyEntry entry : data) {
            if (entry.getStatus().equals("progress")) {
                newList.add(entry);
            }
        }

        data.removeAll(newList);
        newList.addAll(data);
        return newList;
    }

    public static List<ListMyEntry> sortByNew(List<ListMyEntry> data) {

        List<ListMyEntry> newList = new ArrayList<ListMyEntry>();

        for (ListMyEntry entry : data) {
            if (entry.getStatus().equals("new")) {
                newList.add(entry);
            }
        }

        data.removeAll(newList);
        newList.addAll(data);
        return newList;
    }

    public static List<ListMyEntry> sortByCompleted(List<ListMyEntry> data) {

        List<ListMyEntry> newList = new ArrayList<ListMyEntry>();

        for (ListMyEntry entry : data) {
            if (entry.getStatus().equals("completed")) {
                newList.add(entry);
            }
        }

        data.removeAll(newList);
        newList.addAll(data);
        return newList;
    }

}

