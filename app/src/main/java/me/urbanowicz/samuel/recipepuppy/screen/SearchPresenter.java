package me.urbanowicz.samuel.recipepuppy.screen;

import android.util.Log;

import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.urbanowicz.samuel.recipepuppy.data.entity.Recipe;
import me.urbanowicz.samuel.recipepuppy.data.source.RecipesRepository;
import me.urbanowicz.samuel.recipepuppy.data.source.Repository;

public class SearchPresenter implements Contract.Presenter {
    private static final String TAG = "SearchPresenter";

    private final Repository<Recipe> recipeRepository = new RecipesRepository();
    private final CompositeDisposable disposables = new CompositeDisposable();

    private Contract.View view;

    @Override
    public void onViewAttached(Contract.View v) {
        this.view = v;
    }

    @Override
    public void onViewDetached() {
        disposables.clear();
        view = null;
    }

    @Override
    public void onSearchQueryModified(String query) {
        if (query.isEmpty()) {
            view.displayRecipes(Collections.emptyList());
        } else {
            Disposable d =
                    recipeRepository.search(query)
                            .subscribeOn(Schedulers.io())
                            .take(20)
                            .toList()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(recipes -> view.displayRecipes(recipes), e -> Log.e(TAG, e.getMessage()));
            disposables.add(d);
        }
    }
}
