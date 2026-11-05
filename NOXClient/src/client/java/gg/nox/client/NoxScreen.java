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
        addRenderableWidget(toggleButton("HUD", () -> NoxClient.hud = !NoxClient.hud, cx, top));
        addRenderableWidget(toggleButton("Coordinates", () -> NoxClient.coordinates = !NoxClient.coordinates, cx, top + 26));
        addRenderableWidget(toggleButton("Armor Status", () -> NoxClient.armor = !NoxClient.armor, cx, top + 52));
        addRenderableWidget(toggleButton("Keystrokes", () -> NoxClient.keystrokes = !NoxClient.keystrokes, cx, top + 78));
        addRenderableWidget(Button.builder(Component.literal("Close"), b -> onClose())
            .bounds(cx - 105, top + 110, 210, 20).build());
    }

    private Button toggleButton(String name, Runnable action, int cx, int y) {
        return Button.builder(Component.literal(name + "  •  ON/OFF"), b -> action.run())
            .bounds(cx - 105, y, 210, 20).build();
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
