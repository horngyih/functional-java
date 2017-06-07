package com.ubicompsystem.common.util.functional;

/**
 * Created by horngyih on 6/8/2017.
 */
@SuppressWarnings("WeakerAccess")
public interface Reduction<T, V> {
     V reduce( T target, V previous );
}
