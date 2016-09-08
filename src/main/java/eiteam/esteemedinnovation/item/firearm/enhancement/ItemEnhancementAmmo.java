package eiteam.esteemedinnovation.item.firearm.enhancement;

import eiteam.esteemedinnovation.EsteemedInnovation;
import eiteam.esteemedinnovation.api.enhancement.IEnhancementRocketLauncher;
import eiteam.esteemedinnovation.entity.projectile.EntityRocket;
import eiteam.esteemedinnovation.init.items.firearms.FirearmItems;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ItemEnhancementAmmo extends Item implements IEnhancementRocketLauncher {
    @Override
    public boolean canApplyTo(ItemStack stack) {
        return stack.getItem() == FirearmItems.Items.ROCKET_LAUNCHER.getItem();
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EsteemedInnovation.upgrade;
    }

    @Override
    public String getID() {
        return "ammo";
    }

    @Override
    public ResourceLocation getIcon(Item item) {
        return new ResourceLocation(EsteemedInnovation.MOD_ID, "weaponRocketLauncherAmmo");
    }

    @Override
    public String getName(Item item) {
        return "item.esteemedinnovation:rocketLauncherAmmo";
    }

    @Override
    public float getAccuracyChange(Item weapon) {
        return 0;
    }

    @Override
    public int getReloadChange(Item weapon) {
        return 20;
    }

    @Override
    public int getClipSizeChange(Item weapon) {
        return 2;
    }

    @Override
    public float getExplosionChange(Item weapon) {
        return 0;
    }

    @Override
    public int getFireDelayChange(ItemStack weapon) {
        return 0;
    }

    @Override
    public EntityRocket changeBullet(EntityRocket bullet) {
        return bullet;
    }

}