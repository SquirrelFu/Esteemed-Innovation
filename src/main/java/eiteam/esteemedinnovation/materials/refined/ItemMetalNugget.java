package eiteam.esteemedinnovation.materials.refined;

import eiteam.esteemedinnovation.commons.EsteemedInnovation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMetalNugget extends Item {
    public ItemMetalNugget() {
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == EsteemedInnovation.tab) {
            for (Types type : Types.values()) {
                items.add(new ItemStack(this, 1, type.getMeta()));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }

    public enum Types {
        COPPER_NUGGET(0),
        ZINC_NUGGET(1),
        BRASS_NUGGET(2),
        GILDED_IRON_NUGGET(3),
        IRON_NUGGET(4); // I hate you, Vanilla, and your lack of iron nuggets.

        private final int meta;

        Types(int meta) {
            this.meta = meta;
        }

        public int getMeta() {
            return meta;
        }
    }
}
