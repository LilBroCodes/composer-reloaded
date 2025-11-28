package org.lilbrocodes.composer_reloaded.api.util.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.nbt.NbtSerializable;

public class SerializableIdentifier extends Identifier implements NbtSerializable<SerializableIdentifier> {
    public SerializableIdentifier(String namespace, String path) {
        super(namespace, path);
    }

    public SerializableIdentifier(NbtCompound tag) {
        this(tag.getString("namespace"), tag.getString("path"));
    }

    public SerializableIdentifier(Identifier identifier) {
        this(identifier.getNamespace(), identifier.getPath());
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        tag.putString("namespace", getNamespace());
        tag.putString("path", getPath());
        return tag;
    }
}
