package net.kottenpark_mod.block.entity.renderer;

import net.kottenpark_mod.KottenparkMod;
import net.kottenpark_mod.block.entity.custom.WhiteboardBlockEntity;
import net.kottenpark_mod.block.entity.model.WhiteboardModel;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.state.BlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.base.BoneSnapshots;
import software.bernie.geckolib.renderer.base.GeoRenderState;
import software.bernie.geckolib.renderer.base.RenderPassInfo;

public class WhiteboardBlockRenderer<T extends WhiteboardBlockEntity, R extends BlockEntityRenderState & GeoRenderState> extends GeoBlockRenderer<WhiteboardBlockEntity, @NonNull R> {

    public WhiteboardBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new WhiteboardModel());
    }

    @Override
    public void adjustRenderPose(RenderPassInfo<@NonNull R> renderPassInfo) {
        super.adjustRenderPose(renderPassInfo);
        renderPassInfo.poseStack().multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180 - renderPassInfo.renderState().getGeckolibData(DataTickets.ENTITY_BODY_YAW)));
    }
}
