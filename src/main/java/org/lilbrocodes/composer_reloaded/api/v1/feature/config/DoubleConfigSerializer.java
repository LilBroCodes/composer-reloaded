package org.lilbrocodes.composer_reloaded.api.v1.feature.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.lilbrocodes.composer_reloaded.api.v1.nbt.GsonSerializer;

public enum DoubleConfigSerializer implements GsonSerializer<Double> {
    INSTANCE;

    @Override
    public Double read(JsonElement json) {
        return json.getAsDouble();
    }

    @Override
    public JsonElement writeToJson(Double value) {
        return new JsonPrimitive(value);
    }
}
