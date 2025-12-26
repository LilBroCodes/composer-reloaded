package org.lilbrocodes.composer_reloaded.api.v1.feature.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.lilbrocodes.composer_reloaded.api.v1.nbt.GsonSerializer;

public enum BooleanConfigSerializer implements GsonSerializer<Boolean> {
    INSTANCE;

    @Override
    public Boolean read(JsonElement json) {
        return json.getAsBoolean();
    }

    @Override
    public JsonElement writeToJson(Boolean value) {
        return new JsonPrimitive(value);
    }
}

