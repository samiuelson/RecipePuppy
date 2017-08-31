package me.urbanowicz.samuel.recipepuppy.screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.urbanowicz.samuel.recipepuppy.R;
import me.urbanowicz.samuel.recipepuppy.data.entity.Recipe;

public class SearchActivity extends AppCompatActivity implements Contract.View {

    private final RecipesAdapter adapter = new RecipesAdapter();
    private final Contract.Presenter presenter = new SearchPresenter();

    @BindView(R.id.search_view) EditText searchView;
    @BindView(R.id.recycler) RecyclerView recycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.onViewAttached(this);
        recycler.setAdapter(adapter);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onSearchQueryModified(editable.toString());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onViewDetached();
    }

    @Override
    public void displayRecipes(List<Recipe> recipes) {
        adapter.setRecipes(recipes);
    }

}
