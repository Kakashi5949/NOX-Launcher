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
        int top = height / 2 - 90;
        int left = cx - 215;
        int right = cx + 5;
        addRenderableWidget(toggleButton("HUD", () -> NoxClient.hud = !NoxClient.hud, left, top, () -> NoxClient.hud));
        addRenderableWidget(toggleButton("Coordinates", () -> NoxClient.coordinates = !NoxClient.coordinates, right, top, () -> NoxClient.coordinates));
        addRenderableWidget(toggleButton("Armor HUD", () -> NoxClient.armor = !NoxClient.armor, left, top + 28, () -> NoxClient.armor));
        addRenderableWidget(toggleButton("Keystrokes", () -> NoxClient.keystrokes = !NoxClient.keystrokes, right, top + 28, () -> NoxClient.keystrokes));
        addRenderableWidget(toggleButton("Speed HUD", () -> NoxClient.speed = !NoxClient.speed, left, top + 56, () -> NoxClient.speed));
        addRenderableWidget(Button.builder(Component.literal("CLOSE"), b -> onClose())
            .bounds(cx - 105, top + 100, 210, 22).build());
    }

    private Button toggleButton(String name, Runnable action, int x, int y, java.util.function.BooleanSupplier state) {
        Button button = Button.builder(label(name, state.getAsBoolean()), b -> {
            action.run();
            b.setMessage(label(name, state.getAsBoolean()));
        }).bounds(x, y, 210, 22).build();
        return button;
    }

    private static Component label(String name, boolean enabled) {
        return Component.literal(name + "   [" + (enabled ? "ON" : "OFF") + "]");
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics, mouseX, mouseY, delta);
        graphics.fill(width / 2 - 225, height / 2 - 112, width / 2 + 225, height / 2 + 45, 0xC9000000);
        graphics.drawCenteredString(font, "NØX CLIENT", width / 2, height / 2 - 104, 0xFFFFFFFF);
        graphics.drawCenteredString(font, "VISUALS  |  PLAYER  |  HUD", width / 2, height / 2 + 62, 0xFFAAAAAA);
        graphics.drawCenteredString(font, "Right Shift  •  Black & White", width / 2, height / 2 + 76, 0xFF777777);
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    public boolean isPauseScreen() { return false; }
}
