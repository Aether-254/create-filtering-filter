package awa.Aether_254.create_filtering_filter;

import awa.Aether_254.create_filtering_filter.client.FilteringFilterConfigScreen;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(CreateFilteringFilter.MOD_ID)
public final class CreateFilteringFilter {
    public static final String MOD_ID = "create_filtering_filter";

    public CreateFilteringFilter(IEventBus modBus, ModContainer container) {
        FilteringFilterConfig.load();
        FilteringFilterItems.ITEMS.register(modBus);
        modBus.addListener(this::creativeTabs);
        if (FMLEnvironment.dist == Dist.CLIENT)
            FilteringFilterConfigScreen.register(container);
    }

    private void creativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
            event.accept(FilteringFilterItems.FILTERING_FILTER.get());
    }
}
