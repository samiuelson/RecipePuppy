package me.urbanowicz.samuel.recipepuppy.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipesResponse {
    @SerializedName("results") private final List<Recipe> recipes;

    public RecipesResponse(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
