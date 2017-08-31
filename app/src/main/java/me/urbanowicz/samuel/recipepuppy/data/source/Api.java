package me.urbanowicz.samuel.recipepuppy.data.source;

import io.reactivex.Single;
import me.urbanowicz.samuel.recipepuppy.data.entity.RecipesResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("?") Single<RecipesResponse> searchRecipes(@Query("q") String query, @Query("p") int page);
}
