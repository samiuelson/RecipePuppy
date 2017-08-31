package me.urbanowicz.samuel.recipepuppy.data.source;

import io.reactivex.Flowable;

public interface Repository<T> {
    Flowable<T> search(String query);
}
