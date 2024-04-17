package galena.nirvana.world.recipe;

import galena.nirvana.index.NirvanaItems;
import galena.nirvana.index.NirvanaRecipeTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import org.jetbrains.annotations.Nullable;

public class HerbalSalveRecipe extends CustomRecipe {

    public HerbalSalveRecipe(ResourceLocation resourceLocation, CraftingBookCategory craftingBookCategory) {
        super(resourceLocation, craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        @Nullable Item flowerType = null;
        var flowerCount = 0;
        var weedCount = 0;
        var hasBowl = false;

        for (int i = 0; i < container.getContainerSize(); ++i) {
            var stack = container.getItem(i);

            if (NirvanaItems.WEED.isIn(stack)) {
                weedCount++;
            } else if (stack.is(ItemTags.SMALL_FLOWERS)) {
                if (flowerType == null) flowerType = stack.getItem();

                if (stack.is(flowerType)) flowerCount++;
                else return false;
            } else if (stack.is(Items.BOWL) && !hasBowl) {
                hasBowl = true;
            } else if (!stack.isEmpty()) {
                return false;
            }
        }

        return hasBowl && weedCount == 3 && flowerCount == 3;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        var result = NirvanaItems.HERBAL_SALVE.asStack();

        for(int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                SuspiciousEffectHolder suspiciousEffectHolder = SuspiciousEffectHolder.tryGet(stack.getItem());
                if (suspiciousEffectHolder != null) {
                    SuspiciousStewItem.saveMobEffect(result, suspiciousEffectHolder.getSuspiciousEffect(), suspiciousEffectHolder.getEffectDuration());
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public boolean canCraftInDimensions(int i, int j) {
        return i >= 3 && j >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NirvanaRecipeTypes.HERBAL_SALVE_RECIPE_SERIALIZER.get();
    }
}
