package com.sbmvirdi.miskaaassignment.ui.modelClasses;

import com.google.gson.annotations.SerializedName;

public class Language {

    @SerializedName("name")
    private String name;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
