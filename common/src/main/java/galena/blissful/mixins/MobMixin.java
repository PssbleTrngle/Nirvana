package galena.blissful.mixins;

import galena.blissful.index.BlissfulEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Mob.class)
public abstract class MobMixin {

    @Redirect(
            method = "serverAiStep()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;tickRunningGoals(Z)V",
                    ordinal = 0
            )
    )
    public void interruptTargetGoal(GoalSelector targetSelector, boolean argument) {
        @SuppressWarnings("DataFlowIssue")
        var self = (Mob) (Object) (this);
        if(self.hasEffect(BlissfulEffects.PEACE.get())) return;
        targetSelector.tickRunningGoals(argument);
    }

    @Redirect(
            method = "serverAiStep()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;tick()V",
                    ordinal = 0
            )
    )
    public void interruptTargetGoal(GoalSelector targetSelector) {
        @SuppressWarnings("DataFlowIssue")
        var self = (Mob) (Object) (this);
        if(self.hasEffect(BlissfulEffects.PEACE.get())) return;
        targetSelector.tick();
    }

}
