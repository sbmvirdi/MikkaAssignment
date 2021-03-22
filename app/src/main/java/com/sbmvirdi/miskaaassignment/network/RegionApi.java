package com.sbmvirdi.miskaaassignment.network;

import com.sbmvirdi.miskaaassignment.ui.modelClasses.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RegionApi {

    // endpoint to get countries in the specific region
    @GET("region/{region}")
    Call<List<Country>> getCountriesInRegion(@Path("region") String region);

}
