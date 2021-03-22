package com.sbmvirdi.miskaaassignment.viewModels;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sbmvirdi.miskaaassignment.repository.MikkaRepo;
import com.sbmvirdi.miskaaassignment.room.RoomDatabaseInstance;
import com.sbmvirdi.miskaaassignment.ui.modelClasses.Country;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Country>> countryList = new MutableLiveData<>();
    private MikkaRepo mRepo;
    private RoomDatabaseInstance mDatabase;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Integer> dataSource = new MutableLiveData<>();

    public MainActivityViewModel() {
        mRepo = MikkaRepo.getInstance();
    }

    public void getCountries(Context context) {
        mDatabase = RoomDatabaseInstance.getAppDatabase(context);
        if (isNetworkConnected(context)) {
            isLoading.setValue(true);
            // get the list of countries from the endpoint
            mRepo.getCountries(list -> {
                // set the value of dataSource to 0 which specifies that data is loaded from network
                dataSource.setValue(0);
                // save the list to room database
                mDatabase.countryDao().insertCountries(list);
                // fetch all the results from room database
                countryList.setValue(mDatabase.countryDao().getAllCountries());
                isLoading.setValue(false);
            });
        }else{
            isLoading.setValue(true);
            // set the value of dataSource to 1 which specifies that data is loaded from room database
            dataSource.setValue(1);
            // fetch all the results from room database
            countryList.setValue(mDatabase.countryDao().getAllCountries());
            isLoading.setValue(false);
        }

    }

    public void deleteAllCountriesFromDatabase() {
        // Delete all the entries from room database
        isLoading.setValue(true);
        mDatabase.countryDao().deleteCountries();
        countryList.setValue(new ArrayList<>());
        isLoading.setValue(false);
    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public LiveData<List<Country>> getCountryList() {
        return countryList;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Integer> getDataSource() {
        return dataSource;
    }
}
