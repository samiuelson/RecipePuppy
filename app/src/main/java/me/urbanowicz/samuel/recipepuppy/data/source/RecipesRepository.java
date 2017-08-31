package me.urbanowicz.samuel.recipepuppy.data.source;

import android.support.annotation.NonNull;

import io.reactivex.Flowable;
import io.reactivex.Single;
import me.urbanowicz.samuel.recipepuppy.common.Lazy;
import me.urbanowicz.samuel.recipepuppy.data.entity.Recipe;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipesRepository implements Repository<Recipe> {

    private final Lazy<Api> apiLazy;

    public RecipesRepository() {
        apiLazy = createApiLazy();
    }

    @Override
    public Flowable<Recipe> search(String query) {
        final Api api = apiLazy.get();
        final Flowable<Recipe> stream =
                Single.merge(api.searchRecipes(query, 1), api.searchRecipes(query, 2))
                        .flatMap(response -> Flowable.fromIterable(response.getRecipes()));
        return stream;
    }

    private static Lazy<Api> createApiLazy() {
        final Lazy<Api> apiLazy = new Lazy<Api>() {
            private Api api = null;

            @NonNull
            @Override
            public Api get() {
                if (api == null) {
                    this.api = new Retrofit.Builder()
                                    .baseUrl("http://www.recipepuppy.com/api/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .build()
                                    .create(Api.class);
                }
                return api;
            }
        };
        return apiLazy;
    }
}
