package com.sbmvirdi.miskaaassignment.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.request.RequestOptions;
import com.sbmvirdi.miskaaassignment.Utils.GlideApp;
import com.sbmvirdi.miskaaassignment.databinding.CountryRowBinding;
import com.sbmvirdi.miskaaassignment.ui.modelClasses.Country;
import com.sbmvirdi.miskaaassignment.ui.modelClasses.Language;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private final List<Country> mList;
    private final Context mContext;

    public CountryAdapter(List<Country> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(CountryRowBinding.inflate(LayoutInflater.from(mContext),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{

        private CountryRowBinding binding;

        public CountryViewHolder(@NonNull CountryRowBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void setData(Country country){
            binding.countryName.setText(country.getName());

            GlideApp.with(mContext).load(country.getFlag()).apply(RequestOptions.centerCropTransform())
                    .into(binding.countryFlag);
            binding.countryRegionAndSubRegion.setText(country.getRegion()+","+country.getSubRegion());
            binding.countryPopulation.setText(country.getPopulation().toString());
            binding.countryBorders.setText(country.getBorders().toString());
            StringBuilder languagesString = new StringBuilder();
            for (int i=0;i<country.getLanguages().size();++i){
                Language language = country.getLanguages().get(i);
                if (i == country.getLanguages().size()-1){
                    languagesString.append(language.getName());
                }else {
                    languagesString.append(language.getName()+",");
                }
            }

            binding.countryLanguages.setText(languagesString.toString());
        }
    }
}
