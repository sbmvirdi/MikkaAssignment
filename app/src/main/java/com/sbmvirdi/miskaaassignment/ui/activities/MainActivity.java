package com.sbmvirdi.miskaaassignment.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sbmvirdi.miskaaassignment.R;
import com.sbmvirdi.miskaaassignment.Utils.Constants;
import com.sbmvirdi.miskaaassignment.Utils.RegionUtils;
import com.sbmvirdi.miskaaassignment.adapters.CountryAdapter;
import com.sbmvirdi.miskaaassignment.databinding.ActivityMainBinding;
import com.sbmvirdi.miskaaassignment.network.RegionApi;
import com.sbmvirdi.miskaaassignment.network.RetrofitInstance;
import com.sbmvirdi.miskaaassignment.ui.modelClasses.Country;
import com.sbmvirdi.miskaaassignment.viewModels.MainActivityViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding activityMainBinding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        setSupportActionBar(activityMainBinding.toolbar);

        // setting the layout for recycler view
        activityMainBinding.countryRecycler.setLayoutManager(new LinearLayoutManager(this));

        // initializing the view model
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // getting the countries
        viewModel.getCountries(getApplicationContext());

        // setting a observer on countries
        viewModel.getCountryList().observe(this,list->{
            CountryAdapter adapter = new CountryAdapter(list,this);
            activityMainBinding.countryRecycler.setAdapter(adapter);
        });

        // observer on loading boolean
        viewModel.getIsLoading().observe(this,loading->{
            Log.e(TAG, "getIsLoading(): "+loading );
            if (loading){
                Log.e(TAG, "getIsLoading(): progress bar visible");
                // hiding the recycler view and showing the progress bar
                activityMainBinding.countryRecycler.setVisibility(View.GONE);
                activityMainBinding.progressBar.setVisibility(View.VISIBLE);
            }else{
                Log.e(TAG, "getIsLoading(): recyclerview visible");
                // showing the recycler view and hiding the progress bar
                activityMainBinding.countryRecycler.setVisibility(View.VISIBLE);
                activityMainBinding.progressBar.setVisibility(View.GONE);
            }
        });

        // observer on dataSource to specify the source of data
        viewModel.getDataSource().observe(this,source->{
            if (source == 0){
                // data is loaded form network
                activityMainBinding.dataSource.setText(Constants.DATA_SOURCE_NETWORK);
            }else if (source == 1){
                // data is loaded form network
                activityMainBinding.dataSource.setText(Constants.DATA_SOURCE_ROOM);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // creating an options menu
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // if the clear database is selected from the menu
        if (item.getItemId() == R.id.clear){

            // clear all the countries from the database
            viewModel.deleteAllCountriesFromDatabase();
        }
        return true;
    }

    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == MainActivityViewModel.class) {
            return (T) new MainActivityViewModel();
        }
        return null;
    }
}