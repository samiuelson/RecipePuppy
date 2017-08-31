package me.urbanowicz.samuel.recipepuppy.common;

import android.support.annotation.NonNull;

public interface Lazy<T> {
    @NonNull T get();
}
