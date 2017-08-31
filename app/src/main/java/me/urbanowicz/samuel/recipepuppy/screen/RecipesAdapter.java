package me.urbanowicz.samuel.recipepuppy.screen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.urbanowicz.samuel.recipepuppy.R;
import me.urbanowicz.samuel.recipepuppy.data.entity.Recipe;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.Holder> {
    private List<Recipe> recipes = new ArrayList<>();

    public void setRecipes(List<Recipe> recipes) {
        this.recipes.clear();
        this.recipes.addAll(recipes);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bindView(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.title) TextView title;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Recipe r) {
            title.setText(r.getTitle());
        }
    }
}
