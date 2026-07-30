package awa.Aether_254.create_filtering_filter;

import com.simibubi.create.AllDataComponents;
import com.simibubi.create.content.logistics.filter.FilterItem;
import com.simibubi.create.content.logistics.filter.FilterItemStack;
import com.simibubi.create.content.logistics.filter.ListFilterItem;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

public final class FilteringFilterItem extends ListFilterItem {
    public FilteringFilterItem(Properties properties) {
        super(properties);
    }

    @Override
    public FilterItemStack makeStackWrapper(ItemStack filter) {
        return new DirectFilterStack(filter);
    }

    private static final class DirectFilterStack extends FilterItemStack {
        private final List<ItemStack> filters = new ArrayList<>();
        private final boolean blacklist;

        private DirectFilterStack(ItemStack filter) {
            super(filter);
            ItemContainerContents contents =
                filter.getOrDefault(AllDataComponents.FILTER_ITEMS, ItemContainerContents.EMPTY);
            contents.nonEmptyItemsCopy().forEach(filters::add);
            blacklist = filter.getOrDefault(AllDataComponents.FILTER_ITEMS_BLACKLIST, false);
        }

        @Override
        public boolean test(Level level, ItemStack candidate, boolean matchData) {
            if (!FilteringFilterConfig.get().enabled || !(candidate.getItem() instanceof FilterItem))
                return false;
            boolean matched = filters.stream()
                .filter(stack -> stack.getItem() instanceof FilterItem)
                .anyMatch(stack -> (FilteringFilterConfig.get().matchInternalData || matchData)
                    ? ItemStack.isSameItemSameComponents(stack, candidate)
                    : ItemStack.isSameItem(stack, candidate));
            return blacklist != matched;
        }

        @Override
        public boolean test(Level level, FluidStack stack, boolean matchData) {
            return false;
        }
    }
}
