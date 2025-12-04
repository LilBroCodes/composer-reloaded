package org.lilbrocodes.composer_reloaded.common.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lilbrocodes.composer_reloaded.ComposerReloaded;
import org.lilbrocodes.composer_reloaded.api.datafix.DataFixerRegistry;
import org.lilbrocodes.composer_reloaded.api.datafix.impl.SimpleItemFixer;

import java.io.InputStreamReader;
import java.util.Map;

public class SimpleItemFixerLoader implements SimpleSynchronousResourceReloadListener {
    public static final Logger LOGGER = LogManager.getLogger(ComposerReloaded.class);
    private static final Identifier ID = ComposerReloaded.identify("simple_item_fixer");

    @Override
    public void reload(ResourceManager manager) {
        DataFixerRegistry.ITEM.clear();
        DataFixerRegistry.ITEM.setLoadingFiles();

        var resources = manager.findResources("simple_item_fixer", path -> path.getPath().endsWith(".json"));

        for (Map.Entry<Identifier, Resource> entry : resources.entrySet()) {
            Identifier id = entry.getKey();
            try (var reader = new InputStreamReader(entry.getValue().getInputStream())) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
                SimpleItemFixer fixer = new SimpleItemFixer(json.get("pattern").getAsString(), Identifier.tryParse(json.get("replacement").getAsString()), json.get("copy_nbt").getAsBoolean());
                DataFixerRegistry.ITEM.register(id, fixer);
            } catch (Exception e) {
                LOGGER.error("Failed to load simple item fixer {}", id, e);
            }
        }

        DataFixerRegistry.ITEM.finishedLoadingFiles();
    }

    @Override
    public Identifier getFabricId() {
        return ID;
    }
}
