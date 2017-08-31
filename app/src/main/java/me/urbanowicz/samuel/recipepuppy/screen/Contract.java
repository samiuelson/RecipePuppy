package me.urbanowicz.samuel.recipepuppy.screen;

import java.util.List;

import me.urbanowicz.samuel.recipepuppy.data.entity.Recipe;

public interface Contract {
    interface View {
        void displayRecipes(List<Recipe> recipes);
    }

    interface Presenter {
        void onViewAttached(View v);
        void onViewDetached();
        void onSearchQueryModified(String query);
    }
}
