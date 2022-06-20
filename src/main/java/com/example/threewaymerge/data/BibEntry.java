package com.example.threewaymerge.data;

import java.util.*;

public class BibEntry {
    private final Map<String, String> fields = new HashMap<>();

    public BibEntry(Map<String, String> fields) {
        this.fields.putAll(fields);
    }

    public void setField(String name, String value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);

        fields.put(name, value);
    }

    public String getField(String name) {
        Objects.requireNonNull(name);

        return fields.get(name);
    }

    public int fieldCount() {
        return fields.size();
    }

    public static boolean isMiltiline(String field) {
        return field.equalsIgnoreCase("abstract") || field.equalsIgnoreCase("booktitle");
    }

    public Set<String> getFields() {
        return fields.keySet();
    }
}
