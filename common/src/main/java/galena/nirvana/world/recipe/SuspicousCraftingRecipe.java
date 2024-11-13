package galena.nirvana.world.recipe;

import com.google.gson.JsonObject;
import galena.nirvana.index.NirvanaItems;
import galena.nirvana.index.NirvanaRecipeTypes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SuspiciousEffectHolder;
import org.jetbrains.annotations.Nullable;

public class SuspicousCraftingRecipe extends CustomRecipe {

    private final Item result;
    private final Item base;
    private final int requiredFlowers;
    private final int requiredWeed;

    public SuspicousCraftingRecipe(ResourceLocation id, CraftingBookCategory category, Item result, Item base, int requiredFlowers, int requiredWeed) {
        super(id, category);
        this.result = result;
        this.base = base;
        this.requiredFlowers = requiredFlowers;
        this.requiredWeed = requiredWeed;
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        @Nullable Item flowerType = null;
        var flowerCount = 0;
        var weedCount = 0;
        var hasBase = false;

        for (int i = 0; i < container.getContainerSize(); ++i) {
            var stack = container.getItem(i);

            if (NirvanaItems.WEED.isIn(stack)) {
                weedCount++;
            } else if (stack.is(ItemTags.SMALL_FLOWERS)) {
                if (flowerType == null) flowerType = stack.getItem();

                if (stack.is(flowerType)) flowerCount++;
                else return false;
            } else if (stack.is(base) && !hasBase) {
                hasBase = true;
            } else if (!stack.isEmpty()) {
                return false;
            }
        }

        return hasBase && weedCount == requiredWeed && flowerCount == requiredFlowers;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        var result = new ItemStack(this.result);

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
        return NirvanaRecipeTypes.SUSPICIOUS_RECIPE_SERIALIZER.get();
    }

    public static class Serializer implements RecipeSerializer<SuspicousCraftingRecipe> {

        @Override
        public SuspicousCraftingRecipe fromJson(ResourceLocation id, JsonObject json) {
            var category = CraftingBookCategory.CODEC.byName(GsonHelper.getAsString(json, "category", null), CraftingBookCategory.MISC);
            var item = GsonHelper.getAsItem(json, "result");
            var base = GsonHelper.getAsItem(json, "base");
            var requiredFlowers = GsonHelper.getAsInt(json, "flowers", 1);
            var requiredWeed = GsonHelper.getAsInt(json, "weed", 1);
            return new SuspicousCraftingRecipe(id, category, item, base, requiredFlowers, requiredWeed);
        }

        @Override
        public SuspicousCraftingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
            var category = buffer.readEnum(CraftingBookCategory.class);
            var item = buffer.readById(BuiltInRegistries.ITEM);
            var base = buffer.readById(BuiltInRegistries.ITEM);
            var requiredFlowers = buffer.readInt();
            var requiredWeed = buffer.readInt();
            return new SuspicousCraftingRecipe(id, category, item, base, requiredFlowers, requiredWeed);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, SuspicousCraftingRecipe recipe) {
            buffer.writeEnum(recipe.category());
            buffer.writeId(BuiltInRegistries.ITEM, recipe.result);
            buffer.writeId(BuiltInRegistries.ITEM, recipe.base);
            buffer.writeInt(recipe.requiredFlowers);
            buffer.writeInt(recipe.requiredWeed);
        }

    }

}
