package org.lilbrocodes.composer_reloaded.api.events.composite;

import com.google.gson.JsonObject;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lilbrocodes.composer_reloaded.api.registry.lazy.DeferredCompositeEventRegistry;
import org.lilbrocodes.composer_reloaded.api.util.builder.BuilderFields;
import org.lilbrocodes.composer_reloaded.internal.ComposerReloaded;

import java.util.Locale;

public class ComposerCompositeEvents {
    private static final DeferredCompositeEventRegistry REGISTRY = new DeferredCompositeEventRegistry(ComposerReloaded.MOD_ID);

    private static final Identifier EXPLOSION = REGISTRY.register("explosion", Explosion::read);
    private static final Identifier LIGHTNING = REGISTRY.register("lightning", Lightning::read);
    private static final Identifier SOUND = REGISTRY.register("sound", Sound::read);

    public static Explosion.Builder explosion() {
        return new Explosion.Builder();
    }

    public static Lightning.Builder lightning() {
        return new Lightning.Builder();
    }

    public static Sound.Builder sound() {
        return new Sound.Builder();
    }

    public record Sound(Identifier id, String category, float volume, float pitch) implements CompositeEvent {
        public static Sound read(JsonObject json) {
            return new Sound(Identifier.tryParse(json.get("sound").getAsString()), json.get("category").getAsString(), json.get("volume").getAsFloat(), json.get("pitch").getAsFloat());
        }

        @Override
        public void handle(World world, BlockPos pos) {
            world.playSound(null, pos, SoundEvent.of(id), SoundCategory.valueOf(category.toUpperCase(Locale.ROOT)), volume, pitch);
        }

        @Override
        public JsonObject write(JsonObject json) {
            json.addProperty("sound", id.toString());
            json.addProperty("category", category);
            json.addProperty("volume", volume);
            json.addProperty("pitch", pitch);
            return json;
        }

        @Override
        public Identifier getId() {
            return SOUND;
        }

        public static class Builder extends CompositeEventBuilder<Sound> {
            protected Builder() {

            }

            private Identifier id;
            private String category;
            private float volume;
            private float pitch;

            public Builder sound(Identifier id) {
                this.id = id;
                return this;
            }

            public Builder sound(SoundEvent sound) {
                return this.sound(sound./*? if minecraft: <=1.20.1 { */getId/*? } else {*//*id*//*?}*/());
            }

            public Builder sound(RegistryEntry.Reference<SoundEvent> sound) {
                return this.sound(sound.value());
            }

            public Builder category(String category) {
                this.category = category;
                return this;
            }

            public Builder volume(float volume) {
                this.volume = volume;
                return this;
            }

            public Builder pitch(float pitch) {
                this.pitch = pitch;
                return this;
            }

            @Override
            public Sound build() {
                BuilderFields.verify(this);
                return new Sound(id, category, volume, pitch);
            }
        }
    }

    public record Lightning(int strikes, double radius) implements CompositeEvent {
        public static Lightning read(JsonObject json) {
            return new Lightning(json.get("strikes").getAsInt(), json.get("radius").getAsDouble());
        }

        @Override
        public void handle(World world, BlockPos pos) {
            Vec3d center = Vec3d.ofBottomCenter(pos);

            for (int i = 0; i < strikes; i++) {
                double angle = (2.0 * Math.PI / strikes) * i;
                double x = center.x + Math.cos(angle) * radius;
                double z = center.z + Math.sin(angle) * radius;

                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightning.setPos(x, center.y, z);
                world.spawnEntity(lightning);
            }
        }

        @Override
        public JsonObject write(JsonObject json) {
            json.addProperty("strikes", strikes);
            json.addProperty("radius", radius);
            return json;
        }

        @Override
        public Identifier getId() {
            return LIGHTNING;
        }

        public static class Builder extends CompositeEventBuilder<Lightning> {
            protected Builder() {

            }

            private int strikes = 1;
            private double radius = 0;

            public Builder strikes(int v) {
                this.strikes = v;
                return this;
            }

            public Builder radius(double r) {
                this.radius = r;
                return this;
            }

            public Lightning build() {
                BuilderFields.verify(this);
                return new Lightning(strikes, radius);
            }
        }
    }

    public record Explosion(float power, boolean fire) implements CompositeEvent {
        public static Explosion read(JsonObject json) {
            return new Explosion(json.get("power").getAsFloat(), json.get("fire").getAsBoolean());
        }

        @Override
        public void handle(World world, BlockPos pos) {
            world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), power, fire, World.ExplosionSourceType.BLOCK);
        }

        @Override
        public JsonObject write(JsonObject json) {
            json.addProperty("power", power);
            json.addProperty("fire", fire);
            return json;
        }

        @Override
        public Identifier getId() {
            return EXPLOSION;
        }

        public static class Builder extends CompositeEventBuilder<Explosion> {
            protected Builder() {

            }

            private float power = 4f;
            private boolean fire = false;

            public Builder power(float p) {
                this.power = p;
                return this;
            }

            public Builder fire(boolean f) {
                this.fire = f;
                return this;
            }

            public Explosion build() {
                BuilderFields.verify(this);
                return new Explosion(power, fire);
            }
        }
    }

    @SuppressWarnings("EmptyMethod")
    public static void initialize() {

    }
}
