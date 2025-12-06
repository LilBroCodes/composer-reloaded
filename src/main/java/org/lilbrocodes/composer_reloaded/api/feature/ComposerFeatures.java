package org.lilbrocodes.composer_reloaded.api.feature;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import org.lilbrocodes.composer_reloaded.api.feature.state.FeatureState;

import java.util.*;
import java.util.function.Consumer;

public class ComposerFeatures {
    private static ComposerFeatures INSTANCE;

    private final Map<String, FeatureNode> namespaceRoots = new LinkedHashMap<>();
    private final Map<Identifier, FeatureNode> flatIndex = new LinkedHashMap<>();

    private ComposerFeatures() {}

    public static ComposerFeatures getInstance() {
        if (INSTANCE == null) INSTANCE = new ComposerFeatures();
        return INSTANCE;
    }

    private FeatureNode ensureNamespaceRoot(String namespace) {
        return namespaceRoots.computeIfAbsent(namespace, ns -> {
            Identifier rootId = new Identifier(namespace, "__features_root");
            FeatureNode root = new FeatureNode(rootId, null);
            flatIndex.put(rootId, root);
            return root;
        });
    }

    public List<FeatureHandle> getGroupsContaining(FeatureHandle feature) {
        List<FeatureHandle> groups = new ArrayList<>();
        for (FeatureNode g : flatIndex.values()) {
            if (g.children().contains(feature.node())) {
                groups.add(new FeatureHandle(g));
            }
        }
        return groups;
    }

    @Deprecated(since = "1.7.1")
    public static FeatureHandle register(Identifier id, boolean defaultEnabled) {
        return getInstance().registerInternal(id.getNamespace(), id.getPath(), b -> b.defaultEnabled(defaultEnabled));
    }

    public static FeatureHandle register(String namespace, String path, Consumer<FeatureBuilder> block) {
        return getInstance().registerInternal(namespace, path, block);
    }

    public static FeatureHandle group(String namespace, String path, Consumer<FeatureBuilder> block) {
        return getInstance().registerInternal(namespace, path, block);
    }

    private FeatureHandle registerInternal(String namespace, String path, Consumer<FeatureBuilder> block) {
        FeatureNode root = ensureNamespaceRoot(namespace);

        String normalized = path.replace('\\', '/');
        String[] parts = normalized.split("/");
        FeatureNode cur = root;
        StringBuilder running = new StringBuilder();

        for (String p : parts) {
            if (p == null || p.isEmpty()) continue;
            if (!running.isEmpty()) running.append('/');
            running.append(p);
            String fullPath = running.toString();
            Identifier nodeId = new Identifier(namespace, fullPath);
            FeatureNode next = cur.getChild(p).orElse(null);

            if (next == null) {
                next = cur.child(p, nodeId);

                FeatureBuilder builder = new FeatureBuilder(next);
                if (fullPath.equals(path)) {
                    block.accept(builder);
                }

                flatIndex.put(nodeId, next);
            }

            cur = next;
        }

        return new FeatureHandle(cur);
    }


    public static FeatureHandle get(Identifier id) {
        FeatureNode node = getInstance().flatIndex.get(id);
        return node == null ? null : new FeatureHandle(node);
    }

    public static FeatureHandle get(String namespace, String path) {
        return get(new Identifier(namespace, path));
    }

    public Map<Identifier, FeatureNode> getAll() {
        return Collections.unmodifiableMap(flatIndex);
    }

    public void afterInitialization(MinecraftServer server) {
        FeatureState state = FeatureState.get(server);

        Set<String> registeredStrings = new LinkedHashSet<>();
        flatIndex.keySet().forEach(id -> registeredStrings.add(id.toString()));

        state.pruneUnknown(registeredStrings);

        flatIndex.forEach((id, node) -> {
            if (node.defaultEnabled() != null) {
                state.ensureEnabledDefault(id.toString(), node.defaultEnabled());
            }
            node.configDefaults.forEach((k, cd) -> state.ensureDefaultConfig(id.toString(), k, cd.serializer, cd.defaultValue));
        });
    }

    public Set<String> getRegisteredNamespaces() {
        return Collections.unmodifiableSet(namespaceRoots.keySet());
    }

    public List<String> getFeaturePathsForNamespace(String namespace) {
        List<String> out = new ArrayList<>();
        Identifier prefix = new Identifier(namespace, "");
        flatIndex.forEach((id, node) -> {
            if (id.getNamespace().equals(namespace) && !Objects.equals(id.getPath(), "__features_root")) out.add(id.getPath());
        });
        return out;
    }

    public boolean featureMissing(Identifier id) {
        return !flatIndex.containsKey(id);
    }

    @SuppressWarnings("StaticMethodOnlyUsedInOneClass")
    public static List<FeatureHandle> getChildren(FeatureHandle parent) {
        List<FeatureHandle> handles = new ArrayList<>();
        for (FeatureNode childNode : parent.node().children()) {
            handles.add(new FeatureHandle(childNode));
        }
        return handles;
    }
}
