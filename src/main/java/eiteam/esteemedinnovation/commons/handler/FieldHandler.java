package eiteam.esteemedinnovation.commons.handler;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.fml.common.FMLLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// TODO: Rename to ReflectionHandler or ReflectionHelper, move to utils.
public class FieldHandler {
    public static Field lastBuyingPlayerField;
    public static Field timeUntilResetField;
    public static Field merchantField;
    public static Field buyingListField;
    public static Field isJumpingField;
    public static Method createStackedBlockMethod;

    public static Field getField(String fieldName, String obfName, Class clazz) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(obfName);
        } catch (NoSuchFieldException e) {
            FMLLog.warning("[EI] Unable to find field " + fieldName + " with its obfuscated " +
              "name. Trying to find it by its name " + fieldName);
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e1) {
                e1.printStackTrace();
                boolean builderHasAField = false;
                StringBuilder builder = new StringBuilder();
                for (Field field1 : clazz.getDeclaredFields()) {
                    if (builderHasAField) {
                        builder.append(", ");
                    }
                    builder.append(field1.getName());
                    builderHasAField = true;
                }
                FMLLog.warning("Unable to find " + fieldName + " field in " + clazz.getName() +
                  ".class. Available fields are: " + builder + ". Things are not going to work right.");
            }
        }
        return field;
    }

    public static Method getMethod(String methodName, String obfName, Class clazz, Class<?>... params) {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(obfName, params);
        } catch (NoSuchMethodException e) {
            FMLLog.warning("[EI] Unable to find method " + methodName + " with its obfuscated name. Trying to" +
              " find it by its name " + methodName);
            try {
                method = clazz.getDeclaredMethod(methodName, params);
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
                boolean builderHasAMethod = false;
                StringBuilder builder = new StringBuilder();
                for (Method method1 : clazz.getDeclaredMethods()) {
                    if (builderHasAMethod) {
                        builder.append(", ");
                    }
                    builder.append(method1.getName());
                    builderHasAMethod = true;
                }
                FMLLog.warning("[EI] Unable to find " + methodName + " method in " + clazz.getName() +
                  ".class. Available methods are: " + builder + ". Things are not going to work right.");
            }
        }
        return method;
    }

    private static void setAccessible(Field field) {
        if (field != null) {
            field.setAccessible(true);
        }
    }

    private static void setAccessible(Method method) {
        if (method != null) {
            method.setAccessible(true);
        }
    }

    public static void init() {
        FMLLog.info("[EI] Getting some fields through reflection.");
        lastBuyingPlayerField = getField("lastBuyingPlayer", "field_82189_bL", EntityVillager.class);
        timeUntilResetField = getField("timeUntilReset", "field_70961_j", EntityVillager.class);
        buyingListField = getField("buyingList", "field_70963_i", EntityVillager.class);
        isJumpingField = getField("isJumping", "field_70703_bu", EntityLivingBase.class);
        createStackedBlockMethod = getMethod("createStackedBlock", "func_180643_i", Block.class, IBlockState.class);

        setAccessible(lastBuyingPlayerField);
        setAccessible(timeUntilResetField);
        setAccessible(buyingListField);
        setAccessible(isJumpingField);
        setAccessible(createStackedBlockMethod);

        try {
            merchantField = getField("merchant", "field_147037_w", GuiMerchant.class);
            setAccessible(merchantField);
        } catch (NoClassDefFoundError ignore) {
            FMLLog.warning("[EI] GuiMerchant class not found. You are probably a server.");
        }
    }

    public static boolean getIsEntityJumping(EntityLivingBase obj) throws IllegalAccessException {
        return (boolean) isJumpingField.get(obj);
    }
}
