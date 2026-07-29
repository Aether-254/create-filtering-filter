package awa.Aether_254.create_filtering_filter.client;

import awa.Aether_254.create_filtering_filter.FilteringFilterConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public final class FilteringFilterConfigScreen {
    private FilteringFilterConfigScreen() {
    }

    public static void register(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, (mod, parent) -> create(parent));
    }

    private static Screen create(Screen parent) {
        FilteringFilterConfig.Data config = FilteringFilterConfig.get();
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent)
            .setTitle(Component.literal("Create: Filtering Filter"));
        ConfigCategory category = builder.getOrCreateCategory(Component.literal("Matching"));
        ConfigEntryBuilder entries = builder.entryBuilder();
        category.addEntry(entries.startBooleanToggle(Component.literal("Enabled"), config.enabled)
            .setDefaultValue(true).setSaveConsumer(value -> config.enabled = value).build());
        category.addEntry(entries.startBooleanToggle(Component.literal("Match filter internal data"),
                config.matchInternalData).setDefaultValue(false)
            .setTooltip(Component.literal("Disabled matches only the filter item type; enabled also compares its configuration."))
            .setSaveConsumer(value -> config.matchInternalData = value).build());
        builder.setSavingRunnable(FilteringFilterConfig::save);
        return builder.build();
    }
}
