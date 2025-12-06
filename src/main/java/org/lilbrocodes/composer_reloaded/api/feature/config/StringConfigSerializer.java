package org.lilbrocodes.composer_reloaded.api.feature.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.lilbrocodes.composer_reloaded.api.nbt.GsonSerializer;

public enum StringConfigSerializer implements GsonSerializer<String> {
    INSTANCE;

    @Override
    public String read(JsonElement json) {
        return json.getAsString();
    }

    @Override
    public JsonElement writeToJson(String value) {
        return new JsonPrimitive(value);
    }
}
