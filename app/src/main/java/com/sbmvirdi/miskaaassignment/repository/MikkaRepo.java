package com.sbmvirdi.miskaaassignment.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.sbmvirdi.miskaaassignment.Utils.RegionUtils;
import com.sbmvirdi.miskaaassignment.network.RegionApi;
import com.sbmvirdi.miskaaassignment.network.RetrofitInstance;
import com.sbmvirdi.miskaaassignment.ui.interfaces.LoadData;
import com.sbmvirdi.miskaaassignment.ui.modelClasses.Country;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MikkaRepo {
    public static MikkaRepo instance;
    private static final String TAG = MikkaRepo.class.getSimpleName();

    public static MikkaRepo getInstance() {
        if (instance == null) {
            instance = new MikkaRepo();
        }

        return instance;
    }

    public void getCountries(LoadData<List<Country>> loadData) {

        RegionApi api = RetrofitInstance.getInstance().create(RegionApi.class);
        Call<List<Country>> countryCall = api.getCountriesInRegion(RegionUtils.REGION);
        countryCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(@NonNull Call<List<Country>> call, @NonNull Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse:" + response.body());
                    List<Country> countryList = response.body();
                    loadData.onDataLoaded(countryList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Country>> call, @NonNull Throwable t) {
                t.printStackTrace();
                List<Country> countryList = new ArrayList<>();
                loadData.onDataLoaded(countryList);
            }
        });
    }
}

