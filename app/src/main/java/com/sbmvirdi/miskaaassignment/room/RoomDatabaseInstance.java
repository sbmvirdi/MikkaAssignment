package com.sbmvirdi.miskaaassignment.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.sbmvirdi.miskaaassignment.ui.modelClasses.Country;

@Database(entities = {Country.class},exportSchema = false,version = 1)
public abstract class RoomDatabaseInstance extends RoomDatabase {

    public abstract CountryDao countryDao();
    private static RoomDatabaseInstance instance;

    public static RoomDatabaseInstance getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDatabaseInstance.class,
                    "country-db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
