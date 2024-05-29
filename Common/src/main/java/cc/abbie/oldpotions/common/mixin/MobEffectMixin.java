package cc.abbie.oldpotions.common.mixin;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

import static cc.abbie.oldpotions.common.OldPotionsCommon.config;

@Mixin(MobEffect.class)
public abstract class MobEffectMixin {
    // Taken from 1.19.3, array index corresponds to potion id - 1
    @Unique
    private static final Map<String, Integer> colors = new HashMap<>() {{
        put("minecraft:speed", 8171462);
        put("minecraft:slowness", 5926017);
        put("minecraft:haste", 14270531);
        put("minecraft:mining_fatigue", 4866583);
        put("minecraft:strength", 9643043);
        put("minecraft:instant_health", 16262179);
        put("minecraft:instant_damage", 4393481);
        put("minecraft:jump_boost", 2293580);
        put("minecraft:nausea", 5578058);
        put("minecraft:regeneration", 13458603);
        put("minecraft:fire_resistance", 14981690);
        put("minecraft:water_breathing", 3035801);
        put("minecraft:invisibility", 8356754);
        put("minecraft:blindness", 2039587);
        put("minecraft:night_vision", 0x1F1FA1);
        put("minecraft:hunger", 5797459);
        put("minecraft:weakness", 0x484D48);
        put("minecraft:poison", 5149489);
        put("minecraft:wither", 3484199);
        put("minecraft:health_boost", 3484199);
        put("minecraft:absorption", 0x2552A5);
        put("minecraft:saturation", 16262179);
        put("minecraft:glowing", 9740385);
        put("minecraft:levitation", 0xCEFFFF);
        put("minecraft:luck", 0x339900);
        put("minecraft:unluck", 12624973);
        put("minecraft:slow_falling", 16773073);
        put("minecraft:conduit_power", 1950417);
        put("minecraft:dolphins_grace", 8954814);
        put("minecraft:bad_omen", 745784);
        put("minecraft:hero_of_the_village", 0x44FF44);
        put("minecraft:darkness", 2696993);
    }};

    @Inject(method = "getColor", at = @At("HEAD"), cancellable = true)
    private void oldpotions$modifyColor(CallbackInfoReturnable<Integer> cir) {
        if (!config.oldColors) return;
        ResourceLocation key = BuiltInRegistries.MOB_EFFECT.getKey((MobEffect) (Object) this);
        if (key == null) return;
        Integer color = colors.get(key.toString());
        if (color == null) return;
        cir.setReturnValue(color);
    }
}
