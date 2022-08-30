package com.github.oyogurto.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * Implementation of {@link ComputableTable}.
 * @author Jingchun.Zhou
 * @since 8/5/2022
 */
public class HashBasedComputableTable<R, C, V> implements ComputableTable<R, C, V> {
    private final HashBasedTable<R, C, V> table;

    private HashBasedComputableTable(HashBasedTable<R, C, V> table) {
        this.table = table;
    }

    public static <R, C, V> HashBasedComputableTable<R, C, V> create() {
        return new HashBasedComputableTable<>(HashBasedTable.create());
    }
    public static <R, C, V> HashBasedComputableTable<R, C, V> create(int expectedRows, int expectedCellsPerRow) {
        return new HashBasedComputableTable<>(HashBasedTable.create(expectedRows, expectedCellsPerRow));
    }

    @Override
    public V putIfAbsent(R row, C col, V value) {
        V v = table.get(row, col);
        if (v == null) {
            v = table.put(row, col, value);
        }
        return v;
    }

    @Override
    public V computeIfAbsent(R row, C col, BiFunction<R, C, V> mappingFunction) {
        if (table.get(row, col) == null) {
            V newValue = mappingFunction.apply(row, col);
            if (newValue !=null) {
                table.put(row, col, newValue);
            }
        }
        return table.get(row, col);
    }

    @Override
    public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
        return table.contains(rowKey, columnKey);
    }

    @Override
    public boolean containsRow(@Nullable Object rowKey) {
        return table.containsRow(rowKey);
    }

    @Override
    public boolean containsColumn(@Nullable Object columnKey) {
        return table.containsColumn(columnKey);
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return table.containsValue(value);
    }

    @Override
    public @Nullable V get(@Nullable Object rowKey, @Nullable Object columnKey) {
        return table.get(rowKey, columnKey);
    }

    @Override
    public boolean isEmpty() {
        return table.isEmpty();
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public void clear() {
        table.clear();
    }

    @Override
    public @Nullable V put(@NonNull R rowKey, @NonNull C columnKey, @NonNull V value) {
        return table.put(rowKey, columnKey, value);
    }

    @SuppressWarnings("all")
    @Override
    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        for (Table.Cell<? extends R, ? extends C, ? extends V> cell : table.cellSet()) {
            put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
        }
    }

    @Override
    public @Nullable V remove(@Nullable Object rowKey, @Nullable Object columnKey) {
        return table.remove(rowKey, columnKey);
    }

    @Override
    public Map<C, V> row(@NonNull R rowKey) {
        return table.row(rowKey);
    }

    @Override
    public Map<R, V> column(@NonNull C columnKey) {
        return table.column(columnKey);
    }

    @Override
    public Set<Cell<R, C, V>> cellSet() {
        return table.cellSet();
    }

    @Override
    public Set<R> rowKeySet() {
        return table.rowKeySet();
    }

    @Override
    public Set<C> columnKeySet() {
        return table.columnKeySet();
    }

    @Override
    public Collection<V> values() {
        return table.values();
    }

    @Override
    public Map<R, Map<C, V>> rowMap() {
        return table.rowMap();
    }

    @Override
    public Map<C, Map<R, V>> columnMap() {
        return table.columnMap();
    }
}
