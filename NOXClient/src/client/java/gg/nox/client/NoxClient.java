package gg.nox.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class NoxClient implements ClientModInitializer {
    public static boolean hud = true;
    public static boolean coordinates = true;
    public static boolean armor = true;
    public static boolean keystrokes = true;
    private static KeyMapping menuKey;

    @Override
    public void onInitializeClient() {
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
            "key.noxclient.menu", GLFW.GLFW_KEY_RIGHT_SHIFT, KeyMapping.Category.MISC));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.consumeClick()) client.setScreen(new NoxScreen());
        });
        HudRenderCallback.EVENT.register((graphics, tickCounter) -> renderHud(graphics));
    }

    private static void renderHud(GuiGraphics g) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options.hideGui) return;
        int y = 8;
        if (hud) {
            g.fill(7, 7, 150, 27, 0xB9000000);
            g.drawString(mc.font, "NØX CLIENT", 13, 13, 0xFFFFFFFF, false);
            g.drawString(mc.font, "FPS " + mc.getFps(), 13, 32, 0xFFFFFFFF, true);
            y = 47;
        }
        if (coordinates) {
            String coords = String.format("XYZ  %.1f / %.1f / %.1f",
                mc.player.getX(), mc.player.getY(), mc.player.getZ());
            g.fill(7, y, 190, y + 16, 0x99000000);
            g.drawString(mc.font, coords, 12, y + 4, 0xFFFFFFFF, false);
            y += 20;
        }
        if (armor) {
            int x = 8;
            for (int slot = 36; slot <= 39; slot++) {
                ItemStack stack = mc.player.getInventory().getItem(slot);
                if (!stack.isEmpty()) {
                    g.renderItem(stack, x, y);
                }
                x += 20;
            }
        }
        if (keystrokes) {
            int x = mc.getWindow().getGuiScaledWidth() - 74;
            int base = mc.getWindow().getGuiScaledHeight() - 58;
            drawKey(g, mc, "W", GLFW.GLFW_KEY_W, x + 24, base);
            drawKey(g, mc, "A", GLFW.GLFW_KEY_A, x, base + 20);
            drawKey(g, mc, "S", GLFW.GLFW_KEY_S, x + 24, base + 20);
            drawKey(g, mc, "D", GLFW.GLFW_KEY_D, x + 48, base + 20);
        }
    }

    private static void drawKey(GuiGraphics g, Minecraft mc, String label, int key, int x, int y) {
        boolean down = GLFW.glfwGetKey(mc.getWindow().handle(), key) == GLFW.GLFW_PRESS;
        g.fill(x, y, x + 22, y + 18, down ? 0xDDFFFFFF : 0xAA000000);
        int color = down ? 0xFF000000 : 0xFFFFFFFF;
        g.drawCenteredString(mc.font, label, x + 11, y + 5, color);
    }
}
