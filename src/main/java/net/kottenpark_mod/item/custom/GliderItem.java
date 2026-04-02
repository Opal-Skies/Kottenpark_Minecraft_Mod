package net.kottenpark_mod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public class GliderItem extends Item {
    public GliderItem(Settings settings){super(settings);}

    private boolean available = true;
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 80;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(!user.getItemCooldownManager().isCoolingDown(this.getDefaultStack())) {
            setAvailable(true);
        };
        if(isAvailable()) {
            setAvailable(false);
            user.setCurrentHand(hand);
            return ActionResult.CONSUME;
        }
        return ActionResult.PASS;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks >= 0 && !user.isOnGround()) {
            Vec3d glidingVelocity = new Vec3d(0.3*Math.cos(user.getPitch()*Math.PI/180)*Math.sin(-user.getYaw()*Math.PI/180),-0.05,0.3* Math.cos(user.getPitch()*Math.PI/180)*Math.cos(-user.getYaw()*Math.PI/180));
            user.setVelocity(glidingVelocity);
            user.fallDistance = 0.0f;
            ((PlayerEntity) user).getHungerManager().addExhaustion(0.1f);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ((PlayerEntity) user).getItemCooldownManager().set(stack,100);
        return super.finishUsing(stack, world, user);
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        return super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
    }
}
