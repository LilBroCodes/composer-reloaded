package org.lilbrocodes.composer_reloaded.api.v1.events.composite;

import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface CompositeEvent {
    String DATA = "data";
    String ID = "id";

    void handle(World world, BlockPos pos);

    Identifier getId();

    default JsonObject serialize() {
        JsonObject json = new JsonObject();
        json.addProperty(ID, getId().toString());
        json.add(DATA, write(new JsonObject()));
        return json;
    }

    default JsonObject write(JsonObject json) {
        return json;
    }
}

