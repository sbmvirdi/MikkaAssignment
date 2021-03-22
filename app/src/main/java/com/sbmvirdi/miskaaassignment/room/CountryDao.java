package com.sbmvirdi.miskaaassignment.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sbmvirdi.miskaaassignment.ui.modelClasses.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCountries(List<Country> countries);

    @Query("SELECT * FROM country")
    List<Country> getAllCountries();

    @Query("DELETE FROM country")
    void deleteCountries();
}
