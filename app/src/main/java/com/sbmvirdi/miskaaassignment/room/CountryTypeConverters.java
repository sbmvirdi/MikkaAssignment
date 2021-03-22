package com.sbmvirdi.miskaaassignment.room;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sbmvirdi.miskaaassignment.ui.modelClasses.Language;

import java.lang.reflect.Type;
import java.util.List;


public class CountryTypeConverters {

    @TypeConverter
    public static List<Language> stringToLanguage(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Language>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String languagesToString(List<Language> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Language>>() {}.getType();
        return gson.toJson(list, type);
    }

    @TypeConverter
    public static List<String> stringToBorders(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public static String bordersToString(List<String> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.toJson(list, type);
    }

}
