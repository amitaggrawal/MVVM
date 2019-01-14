package com.amitaggrawal.thefactore.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created by Amit Aggrawal on 12-01-2019.
 */
@Database(entities = {MyEntity.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static final String LOG_TAG = MyDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "my-db";
    private static final Object LOCK = new Object();

    private static MyDatabase mDatabaseInstance;

    public static MyDatabase getInstance(Context context) {
        if (null == mDatabaseInstance) {
            synchronized (LOCK) {
                mDatabaseInstance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        MyDatabase.class,
                        DATABASE_NAME)
                        .build();

                Log.d(LOG_TAG, "New database created");
            }
        }
        return mDatabaseInstance;
    }

    public abstract MyDao myDao();


}
