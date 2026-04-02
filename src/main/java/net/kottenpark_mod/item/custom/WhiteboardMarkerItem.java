package net.kottenpark_mod.item.custom;

import com.ibm.icu.number.Scale;
import net.kottenpark_mod.component.ModDataComponentTypes;
import net.kottenpark_mod.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.*;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class WhiteboardMarkerItem extends Item {
    LoreComponent defaultLore = LoreComponent.DEFAULT;
    public WhiteboardMarkerItem(Settings settings){
        super(settings
                .component(DataComponentTypes.LORE,LoreComponent.DEFAULT.with(Text.of("mama mia")))
        );
    }
    private List<String> BoardMarkers = new ArrayList<>();
    private Vec3d ClickedCoordinate = new Vec3d(0,0,0);
    private Vec3d PrefClickedCoordinate = new Vec3d(0,0,0);
    private Vec3d CoordinateDifference = new Vec3d(0,0,0);



    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

//    @Override
//    public ActionResult use(World world, PlayerEntity user, Hand hand) {
//        user.setCurrentHand(hand);
//        return ActionResult.CONSUME;
//    }

//    @Override
//    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
//        System.out.println("using(tick)");
//        super.usageTick(world, user, stack, remainingUseTicks);
//    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(user.isInPose(EntityPose.CROUCHING)){
            user.getItemCooldownManager().set(this.getDefaultStack(),20);
            if(!world.isClient()) {
                BoardMarkers.forEach(entityUUID -> {
                    if (world.getEntity(UUID.fromString(entityUUID)) != null) {
                        world.getEntity(UUID.fromString(entityUUID)).kill(((ServerWorld) world));
                    }
                });
            }
        } else {
            HitResult hit = user.raycast(10,0,false);
            Vec3d hitPosition = hit.getPos();

            if(hit.getType() == HitResult.Type.BLOCK) {
                ClickedCoordinate = hitPosition;
            }
        }

        user.setCurrentHand(hand);
        return ActionResult.CONSUME;
    }
    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient() && !((PlayerEntity) user).getItemCooldownManager().isCoolingDown(stack)) {
            PrefClickedCoordinate = ClickedCoordinate;
            HitResult hit = user.raycast(4,0,false);
            Vec3d hitPosition = hit.getPos();
            ClickedCoordinate = hitPosition;
            CoordinateDifference = PrefClickedCoordinate.subtract(ClickedCoordinate);


            if(hit.getType() == HitResult.Type.BLOCK) {
                DisplayEntity.BlockDisplayEntity displayEntity = EntityType.BLOCK_DISPLAY.spawn(((ServerWorld) world), ((BlockHitResult) hit).getBlockPos(), SpawnReason.SPAWN_ITEM_USE);
                if (displayEntity != null) {
                    BoardMarkers.add(displayEntity.getUuidAsString());

                    displayEntity.refreshPositionAndAngles(hitPosition, 0, 0);
                    AffineTransformation transformation = new AffineTransformation(
                            new Vector3f(-0.05F, -0.05F, -0.05F), null,
                            new Vector3f(0.1F, 0.1F, (float) CoordinateDifference.length()), null);
                    displayEntity.setTransformation(transformation);
                    displayEntity.move(MovementType.SELF, CoordinateDifference.multiply(0.5));
                    displayEntity.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES,PrefClickedCoordinate);

                    displayEntity.setBlockState(Blocks.BLACK_CONCRETE.getDefaultState());


                    displayEntity.setPosition(hitPosition);
                }
            }

        } else if (user.isInPose(EntityPose.CROUCHING)){
            ((PlayerEntity) user).getItemCooldownManager().set(this.getDefaultStack(),20);
        }
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if(user instanceof PlayerEntity) {
            ((PlayerEntity) user).getItemCooldownManager().set(stack,20);
        }
        return super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }
}
