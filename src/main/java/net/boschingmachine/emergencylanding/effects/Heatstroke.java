package net.boschingmachine.emergencylanding.effects;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.Map;
public class Heatstroke extends MobEffect
{
    protected Heatstroke()
    {
        super(MobEffectCategory.HARMFUL, 14574868);
        addAttributeModifier(Attributes.MAX_HEALTH, "0840d5c7-fe21-4974-a263-4e0e5bcd6bcc", 0.25f, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void addAttributeModifiers(LivingEntity entity, AttributeMap map, int level)
    {
        super.addAttributeModifiers(entity, map, level);

        if (level >= 3)
        {
            entity.hurt(DamageSource.DRY_OUT, entity.getHealth() * 2.0f);
        }
        else if (entity.getHealth() > entity.getMaxHealth())
        {
            entity.hurt(DamageSource.DRY_OUT, entity.getHealth() - entity.getMaxHealth());
        }
    }

    @Override
    public double getAttributeModifierValue(int amp, AttributeModifier mod)
    {
        return -(amp + 1) * mod.getAmount();
    }
}
