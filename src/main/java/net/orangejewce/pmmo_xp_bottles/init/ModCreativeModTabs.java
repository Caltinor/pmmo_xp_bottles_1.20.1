package net.orangejewce.pmmo_xp_bottles.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.orangejewce.pmmo_xp_bottles.pmmobottlesMod;
import net.orangejewce.pmmo_xp_bottles.items.XpBottleItem;

@Mod.EventBusSubscriber(modid = pmmobottlesMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, pmmobottlesMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> NEW_TAB = CREATIVE_MODE_TABS.register("bottles_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.EXPERIENCE_BOTTLE))
                    .title(Component.translatable("itemGroup.pmmo_xp_bottles"))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
        eventBus.addListener(ModCreativeModTabs::addItemsToTabs);
    }

    @SubscribeEvent
    public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == NEW_TAB.get()) {
            PmmoXpBottlesModItems.ALL_BOTTLES.keySet().stream().sorted().toList().stream()
                    .map(key -> PmmoXpBottlesModItems.ALL_BOTTLES.get(key).get().getDefaultInstance())
                    .forEach(event::accept);
        }
    }
}
