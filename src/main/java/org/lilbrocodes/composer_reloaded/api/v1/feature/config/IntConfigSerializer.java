package org.lilbrocodes.composer_reloaded.api.v1.feature.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.lilbrocodes.composer_reloaded.api.v1.nbt.GsonSerializer;

public enum IntConfigSerializer implements GsonSerializer<Integer> {
    INSTANCE;

    @Override
    public Integer read(JsonElement json) {
        return json.getAsInt();
    }

    @Override
    public JsonElement writeToJson(Integer value) {
        return new JsonPrimitive(value);
    }
}
