package awa.Aether_254.create_filtering_filter;

import awa.Aether_254.create_filtering_filter.client.FilteringFilterConfigScreen;
import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(CreateFilteringFilter.MOD_ID)
public final class CreateFilteringFilter {
    public static final String MOD_ID = "create_filtering_filter";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_TABS.register("main",
        () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.create_filtering_filter"))
            .icon(() -> FilteringFilterItems.FILTERING_FILTER.get().getDefaultInstance())
            .displayItems((parameters, output) -> output.accept(FilteringFilterItems.FILTERING_FILTER.get()))
            .build());

    public CreateFilteringFilter(IEventBus modBus, ModContainer container) {
        FilteringFilterConfig.load();
        FilteringFilterItems.ITEMS.register(modBus);
        CREATIVE_TABS.register(modBus);
        if (FMLEnvironment.dist == Dist.CLIENT)
            FilteringFilterConfigScreen.register(container);
    }

}
