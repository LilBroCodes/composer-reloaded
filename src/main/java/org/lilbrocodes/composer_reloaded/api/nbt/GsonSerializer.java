package org.lilbrocodes.composer_reloaded.api.nbt;

import com.google.gson.JsonElement;

public interface GsonSerializer<T> {
    T read(JsonElement json);
    JsonElement writeToJson(T value);
}
