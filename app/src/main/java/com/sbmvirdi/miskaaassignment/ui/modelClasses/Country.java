package com.sbmvirdi.miskaaassignment.ui.modelClasses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.sbmvirdi.miskaaassignment.room.CountryTypeConverters;

import java.util.List;

@TypeConverters(CountryTypeConverters.class)
@Entity
public class Country {

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    private String capital;

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    private String flag;

    @ColumnInfo(name = "region")
    @SerializedName("region")
    private String region;

    @ColumnInfo(name = "subregion")
    @SerializedName("subregion")
    private String subRegion;


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "population")
    @SerializedName("population")
    private Long population;

    @ColumnInfo(name = "borders")
    @SerializedName("borders")
    private List<String> borders;
    //call kr

    @ColumnInfo(name = "languages")
    @SerializedName("languages")
    private List<Language> languages;

    @Ignore
    public Country() {
    }

    public Country(String name, String capital, String flag, String region, String subRegion, Long population, List<String> borders, List<Language> languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

    public String getRegion() {
        return region;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public Long getPopulation() {
        return population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
