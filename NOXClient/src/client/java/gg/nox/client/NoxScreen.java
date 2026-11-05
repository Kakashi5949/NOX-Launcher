package gg.nox.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class NoxScreen extends Screen {
    public NoxScreen() { super(Component.literal("NØX Client")); }

    @Override
    protected void init() {
        int cx = width / 2;
        int top = height / 2 - 72;
        addRenderableWidget(Button.builder(toggle("HUD", () -> NoxClient.hud = !NoxClient.hud),
            b -> {}).bounds(cx - 105, top, 210, 20).build());
        addRenderableWidget(Button.builder(toggle("Coordinates", () -> NoxClient.coordinates = !NoxClient.coordinates),
            b -> {}).bounds(cx - 105, top + 26, 210, 20).build());
        addRenderableWidget(Button.builder(toggle("Armor Status", () -> NoxClient.armor = !NoxClient.armor),
            b -> {}).bounds(cx - 105, top + 52, 210, 20).build());
        addRenderableWidget(Button.builder(toggle("Keystrokes", () -> NoxClient.keystrokes = !NoxClient.keystrokes),
            b -> {}).bounds(cx - 105, top + 78, 210, 20).build());
        addRenderableWidget(Button.builder(Component.literal("Close"), b -> onClose())
            .bounds(cx - 105, top + 110, 210, 20).build());
    }

    private static Component toggle(String name, Runnable action) {
        return Component.literal(name + "  •  toggle");
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(font, "NØX CLIENT", width / 2, height / 2 - 100, 0xFFFFFFFF);
        graphics.drawCenteredString(font, "Right Shift  |  Black & White", width / 2, height / 2 + 75, 0xFFAAAAAA);
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
