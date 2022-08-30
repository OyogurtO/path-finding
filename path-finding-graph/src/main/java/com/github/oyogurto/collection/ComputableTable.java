package com.github.oyogurto.collection;

import com.google.common.collect.Table;

import java.util.function.BiFunction;

/**
 * Extends of {@link Table} and provides functional methods like HashMap since java8.
 *
 * @author Jingchun.Zhou
 * @since 8/5/2022
 */
public interface ComputableTable<R, C, V> extends Table<R, C, V> {

    /**
     * Returns the value to which the specified cell is mapped, or
     * {@code defaultValue} if this table contains no mapping for the cell.
     *
     * @param row the row whose associated value is to be returned
     * @param col the col whose associated value is to be returned
     * @param defaultValue the default mapping of the cell
     * @return the value to which the specified cell is mapped, or
     * {@code defaultValue} if this table contains no mapping for the cell
     */
    default V getOrDefault(R row, C col, V defaultValue) {
        V v;
        return (((v = get(row, col)) != null) || contains(row, col))
                ? v
                : defaultValue;
    }
    /**
     * If the specified cell is not already associated with a value (or is mapped
     * to {@code null}) associates it with the given value and returns
     * {@code null}, else returns the current value.
     *
     * @implSpec
     * The default implementation is equivalent to, for this {@code
     * table}:
     *
     * <pre> {@code
     * V v = table.get(row, col);
     * if (v == null)
     *     v = table.put(row, col, value);
     *
     * return v;
     * }</pre>
     *
     *
     * @param row row with which the specified value is to be associated
     * @param col col with which the specified value is to be associated
     * @param value value to be associated with the specified cell
     * @return the previous value associated with the specified cell, or
     *         {@code null} if there was no mapping for the cell.
     *         (A {@code null} return can also indicate that the table
     *         previously associated {@code null} with the cell,
     *         if the implementation supports null values.)
     */
    V putIfAbsent(R row, C col, V value);

    /**
     * If the specified cell is not already associated with a value (or is mapped
     * to {@code null}), attempts to compute its value using the given mapping
     * function and enters it into this table unless {@code null}.
     *
     * <p>If the function returns {@code null} no mapping is recorded. If
     * the function itself throws an (unchecked) exception, the
     * exception is rethrown, and no mapping is recorded.  The most
     * common usage is to construct a new object serving as an initial
     * mapped value or memoized result, as in:
     *
     * <pre> {@code
     * table.computeIfAbsent(row, coll, (r,c)->new Value(f(r,c)));
     * }</pre>
     *
     * <p>Or to implement a multi-value table, {@code Table<R, C, Collection<V>>},
     * supporting multiple values per cell:
     *
     * <pre> {@code
     * table.computeIfAbsent(row, col, (r,c) -> new HashSet<V>()).add(v);
     * }</pre>
     *
     *
     * @implSpec
     * The default implementation is equivalent to the following steps for this
     * {@code table}, then returning the current value or {@code null} if now
     * absent:
     *
     * <pre> {@code
     * if (table.get(row, col) == null) {
     *     V newValue = mappingFunction.apply(row, col);
     *     if (newValue != null)
     *         table.put(row, col, newValue);
     * }
     * }</pre>
     *
     * @param row row with which the specified value is to be associated
     * @param col col with which the specified value is to be associated
     * @param mappingFunction the function to compute a value
     * @return the current (existing or computed) value associated with
     *         the specified cell, or null if the computed value is null
     */
    V computeIfAbsent(R row, C col, BiFunction<R, C, V> mappingFunction);
}
