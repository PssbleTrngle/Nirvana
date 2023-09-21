package galena.blissful.world.item;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import galena.blissful.BlissfulConstants;
import galena.blissful.index.BlissfulEffects;
import galena.blissful.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.Stream;

public class JointItem extends SmokingItem {

    public static final ModelResourceLocation HAND_MODEL = new ModelResourceLocation(BlissfulConstants.MOD_ID, "joint_in_hand", "inventory");
    public static final ModelResourceLocation FLAT_MODEL = new ModelResourceLocation(BlissfulConstants.MOD_ID, "joint_flat", "inventory");


    public static Optional<ModelResourceLocation> getModel(ItemDisplayContext mode) {
        return switch (mode) {
            case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> Optional.of(FLAT_MODEL);
            case GUI, GROUND, FIXED -> Optional.of(FLAT_MODEL);
            default -> Optional.of(HAND_MODEL);
        };
    }

    public static void render(ItemStack stack, ItemDisplayContext mode, PoseStack pose, MultiBufferSource vertexConsumers, Renderer r) {
        getModel(mode).ifPresent(it -> {
            pose.pushPose();
            if (it == FLAT_MODEL) Lighting.setupForFlatItems();

            var renderer = Minecraft.getInstance().getItemRenderer();
            var model = renderer.getItemModelShaper().getModelManager().getModel(it);

            pose.translate(0.5, 0.5, 0.5);
            model.getTransforms().getTransform(mode).apply(false, pose);
            pose.translate(-0.5, -0.5, -0.5);

            RenderType renderType = ItemBlockRenderTypes.getRenderType(stack, false);
            var vertex = ItemRenderer.getFoilBufferDirect(vertexConsumers, renderType, true, stack.hasFoil());
            r.render(renderer, model, vertex);

            pose.popPose();
        });
    }

    public JointItem(Properties properties) {
        super(properties);
    }

    @Override
    Stream<MobEffectInstance> getEffects(ItemStack stack, @Nullable Level level, @Nullable LivingEntity entity) {
        return Stream.of(new MobEffectInstance(BlissfulEffects.PEACE.get(), 20 * Services.CONFIG.common().jointPeaceSeconds(), 0));
    }

    @Override
    double getRadius(ItemStack stack, @Nullable Level level, @Nullable LivingEntity entity) {
        return Services.CONFIG.common().jointRadius();
    }

    @FunctionalInterface
    public interface Renderer {
        void render(ItemRenderer renderer, BakedModel model, VertexConsumer vertexConsumer);
    }

}
