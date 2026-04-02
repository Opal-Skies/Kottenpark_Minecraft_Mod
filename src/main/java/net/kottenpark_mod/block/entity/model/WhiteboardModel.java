package net.kottenpark_mod.block.entity.model;

import net.kottenpark_mod.KottenparkMod;
import net.kottenpark_mod.block.entity.custom.WhiteboardBlockEntity;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public class WhiteboardModel extends DefaultedBlockGeoModel<WhiteboardBlockEntity> {
    public WhiteboardModel() {
        super(Identifier.of(KottenparkMod.MOD_ID, "whiteboard_block_entity"));
    }

    @Override
    public void addAdditionalStateData(WhiteboardBlockEntity animatable, @Nullable Object relatedObject, GeoRenderState renderState) {
        super.addAdditionalStateData(animatable, relatedObject, renderState);
        renderState.addGeckolibData(DataTickets.ENTITY_BODY_YAW, animatable.getRotationDegrees(animatable.getCachedState()));
    }
}
