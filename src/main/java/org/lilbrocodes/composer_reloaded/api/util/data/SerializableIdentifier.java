package org.lilbrocodes.composer_reloaded.api.util.data;

import com.google.gson.JsonObject;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.nbt.JsonSerializable;
import org.lilbrocodes.composer_reloaded.api.nbt.NbtSerializable;

public class SerializableIdentifier extends Identifier implements NbtSerializable<SerializableIdentifier>, JsonSerializable<SerializableIdentifier> {
    public SerializableIdentifier(String namespace, String path) {
        super(namespace, path);
    }

    public SerializableIdentifier(NbtCompound tag) {
        this(tag.getString("namespace"), tag.getString("path"));
    }

    public SerializableIdentifier(JsonObject object) {
        this(object.get("namespace").getAsString(), object.get("path").getAsString());
    }

    public SerializableIdentifier(Identifier identifier) {
        this(identifier.getNamespace(), identifier.getPath());
    }

    public static SerializableIdentifier of(Identifier identifier) {
        return new SerializableIdentifier(identifier);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        tag.putString("namespace", getNamespace());
        tag.putString("path", getPath());
        return tag;
    }

    @Override
    public JsonObject write(JsonObject object) {
        object.addProperty("namespace", getNamespace());
        object.addProperty("path", getPath());
        return object;
    }
}
