package awa.Aether_254.create_filtering_filter;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class FilteringFilterItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateFilteringFilter.MOD_ID);
    public static final DeferredItem<FilteringFilterItem> FILTERING_FILTER =
        ITEMS.register("filtering_filter", () -> new FilteringFilterItem(new Item.Properties().stacksTo(1)));

    private FilteringFilterItems() {
    }
}
