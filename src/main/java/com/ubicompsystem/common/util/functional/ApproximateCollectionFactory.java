package com.ubicompsystem.common.util.functional;

import java.util.*;

/**
 * Created by horngyih on 6/8/2017.
 */
@SuppressWarnings("WeakerAccess")
public class ApproximateCollectionFactory {
    public static <T> Set<T> copySet( Set target ){
        Set<T> result = null;
        if( target != null ){
            if( target instanceof TreeSet ){
                result = new TreeSet<T>();
            } else if( target instanceof LinkedHashSet){
                result = new LinkedHashSet<T>();
            }
        }
       return ( result != null )? result : new HashSet<T>();
    }

    public static <T> List<T> copyList( List target ){
        List<T> result = null;
        if( target != null ){
            if( target instanceof Vector ){
                if( target instanceof Stack ){
                    result = new Stack<T>();
                } else {
                    result = new Vector<T>();
                }
            } else if( target instanceof LinkedList ){
                result = new LinkedList<T>();
            }
        }

        return (result != null )? result : new ArrayList<T>();
    }

    public static <T> Collection<T> copyCollection( Collection target ){
        Collection<T> result = null;
        if( target instanceof List ){
            result = copyList( (List) target );
        } else if( target instanceof Set ){
            result = copySet( (Set) target );
        }

        return ( result != null )? result : new ArrayList<T>();
    }
}
