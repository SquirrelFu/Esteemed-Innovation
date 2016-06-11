package flaxbeard.steamcraft.init.items.firearms;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import flaxbeard.steamcraft.Config;
import flaxbeard.steamcraft.Steamcraft;
import flaxbeard.steamcraft.api.book.BookRecipeRegistry;
import flaxbeard.steamcraft.init.items.CraftingComponentItems;
import flaxbeard.steamcraft.init.IInitCategory;
import flaxbeard.steamcraft.item.firearm.ItemFirearm;
import flaxbeard.steamcraft.item.firearm.ItemRocketLauncher;

import static flaxbeard.steamcraft.init.misc.OreDictEntries.*;

public class FirearmItems implements IInitCategory {
    public enum Items {
        MUSKET(new ItemFirearm(Config.musketDamage, 84, 0.2F, 5.0F, false, 1, INGOT_IRON), "musket"),
        PISTOL(new ItemFirearm(Config.pistolDamage, 42, 0.5F, 2.0F, false, 1, INGOT_IRON), "pistol"),
        ROCKET_LAUNCHER(new ItemRocketLauncher(2.0F, 95, 10, 3.5F, 4, INGOT_IRON), "rocketLauncher"),
        BLUNDERBUSS(new ItemFirearm(Config.blunderbussDamage, 95, 3.5F, 7.5F, true, 1, INGOT_BRASS), "blunderbuss");

        private Item item;
        public static Items[] LOOKUP = new Items[values().length];

        static {
            for (Items item : values()) {
                if (item.isEnabled()) {
                    LOOKUP[item.ordinal()] = item;
                }
            }
        }

        Items(Item item, String name) {
            if (isEnabled()) {
                item.setUnlocalizedName(Steamcraft.MOD_ID + ":" + name);
                item.setCreativeTab(Steamcraft.tab);
                item.setRegistryName(Steamcraft.MOD_ID, name);
                GameRegistry.register(item);
            }
            this.item = item;
        }

        public Item getItem() {
            return item;
        }

        public boolean isEnabled() {
            return this == ROCKET_LAUNCHER ? Config.enableRL : Config.enableFirearms;
        }
    }

    @Override
    public void oreDict() {}

    @Override
    public void recipes() {
        for (Items item : Items.LOOKUP) {
            switch (item) {
                case MUSKET: {
                    BookRecipeRegistry.addRecipe("musket", new ShapedOreRecipe(item.getItem(),
                      "b  ",
                      " bf",
                      "  s",
                      'b', CraftingComponentItems.Items.IRON_BARREL.createItemStack(),
                      'f', CraftingComponentItems.Items.FLINTLOCK.createItemStack(),
                      's', CraftingComponentItems.Items.GUN_STOCK.createItemStack()
                    ));
                }
                case PISTOL: {
                    BookRecipeRegistry.addRecipe("pistol", new ShapedOreRecipe(item.getItem(),
                      "b  ",
                      " pf",
                      " p ",
                      'b', CraftingComponentItems.Items.IRON_BARREL.createItemStack(),
                      'p', PLANK_WOOD,
                      'f', CraftingComponentItems.Items.FLINTLOCK.createItemStack()
                    ));
                }
                case ROCKET_LAUNCHER: {
                    addRocketLauncherRecipe("rocket1", PLATE_BRASS, PLATE_COPPER);
                    addRocketLauncherRecipe("rocket2", INGOT_BRASS, PLATE_COPPER);
                    addRocketLauncherRecipe("rocket3", PLATE_BRASS, INGOT_COPPER);
                    addRocketLauncherRecipe("rocket4", INGOT_BRASS, INGOT_COPPER);
                }
                case BLUNDERBUSS: {
                    BookRecipeRegistry.addRecipe("blunderbuss", new ShapedOreRecipe(item.getItem(),
                      "b  ",
                      " bf",
                      "  s",
                      'b', CraftingComponentItems.Items.BLUNDERBUSS_BARREL,
                      'f', CraftingComponentItems.Items.FLINTLOCK.createItemStack(),
                      's', CraftingComponentItems.Items.GUN_STOCK
                    ));
                }
            }
        }
    }

    private static void addRocketLauncherRecipe(String name, String brassOre, String copperOre) {
        BookRecipeRegistry.addRecipe(name, new ShapedOreRecipe(Items.ROCKET_LAUNCHER.getItem(),
          "bx ",
          "fic",
          " pi",
          'i', CraftingComponentItems.Items.IRON_BARREL.createItemStack(),
          'x', brassOre,
          'c', copperOre,
          'p', PLANK_WOOD,
          'b', CraftingComponentItems.Items.BLUNDERBUSS_BARREL.createItemStack(),
          'f', CraftingComponentItems.Items.FLINTLOCK.createItemStack()
        ));
    }
}