package com.amitaggrawal.thefactore.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.amitaggrawal.thefactore.data.remote.ListMyEntry;

import java.util.List;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */

@Dao
public interface MyDao {

    @Query("Select * FROM MyEntity")
    LiveData<List<ListMyEntry>> getData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetData(MyEntity... myEntities);

    @Delete
    void delete(MyEntity... entity);

}
