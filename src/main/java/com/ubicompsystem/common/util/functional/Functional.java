package com.ubicompsystem.common.util.functional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by horngyih on 6/8/2017.
 */
@SuppressWarnings("WeakerAccess")
public class Functional {
    public static <T> Collection<T> filter(Collection<T> source, Collection<T> target, Predicate<T> predicate ){
        Collection<T> result = ApproximateCollectionFactory.<T>copyCollection( target );
        if( source != null && predicate != null ){
            for( T item : source ){
                if( predicate.predicate( item ) ){
                    result.add( item );
                }
            }
        }
        return result;
    }

    public static <T, V> V reduce( Collection<T> source, V initialValue, Reduction<T, V> reducer ){
        V result = initialValue;
        if( source != null && reducer != null ){
           for( T item : source ){
               result = reducer.reduce( item, result );
           }
        }
        return result;
    }

    public static <T, V> Map<V, Collection<T>> collate(Collection<T> source, Map<V, Collection<T>> target, Collection<T> listTemplate, Function<T,V> collater ){
        target = ( target != null )? target : new HashMap<V, Collection<T>>();
        Map<V, Collection<T>> result = target;
        if( source != null && collater != null ){
            for( T item : source ){
                V context = collater.exec( item );
                Collection<T> contextList = result.get( context );
                if( contextList == null ){
                    contextList = ApproximateCollectionFactory.<T>copyCollection(listTemplate);
                    result.put( context, contextList );
                }
                contextList.add( item );
            }
        }
        return result;
    }

    public static <T> Collection<T> map( Collection<T> source, Collection<T> target, Function<T, T> function ){
        Collection<T> result = ApproximateCollectionFactory.<T>copyCollection( target );
        if( source != null && function != null ){
            for( T item : source ){
               T mapped = function.exec( item );
               if( mapped != null ){
                   result.add( mapped );
               }
            }
        }
        return result;
    }

    public static <T, V> Collection<V> mapTo( Collection<T> source, Collection<V> target, Function<T, V> function ){
        Collection<V> result = ApproximateCollectionFactory.<V>copyCollection(target);
        if( source != null && function != null ){
            for( T item : source ){
                V mapped = function.exec( item );
                if( mapped != null ){
                    result.add( mapped );
                }
            }
        }
        return result;
    }
}
