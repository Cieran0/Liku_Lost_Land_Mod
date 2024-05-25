package com.cieran0.likus_lost_land.gui;

import com.cieran0.likus_lost_land.Config;
import com.cieran0.likus_lost_land.LikusLostLand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.systems.RenderSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LikusHeadScreen extends Screen {

    private final int imageWidth = 176;
    private final int imageHeight = 166;
    private int guiLeft;
    private int guiTop;

    private static final String STARTING_MESSAGE = "Hello, brave adventurer. I am Liku, a lost soul in need of your help. Will you embark on a quest to return me to my home at the End of the World?";

    private String liku_text = STARTING_MESSAGE;

    private int[] selectedQuestions = new int[4];

    private static Random rng = new Random();

    private List<GuiRect> buttons = new ArrayList<GuiRect>();

    public LikusHeadScreen() {
        super(Component.literal("Liku Screen"));
    }

    public void reloadQuestions() {
        for (int i = 0; i < selectedQuestions.length; i++) {
            selectedQuestions[i] = rng.nextInt(Config.Answers.size());
        }
    }

    public void reloadButtons() {
        buttons.clear();

        for (int i =0; i < 4; i++) {
            int w = (i%2==0)? this.width - this.width/4 -1 : this.width - this.width/8;
            int h = (i >= 2)? this.height - this.height/10 -1 : this.height - this.height/20;
            buttons.add(new GuiRect(
                    w,
                    h,
                    this.width/8,
                    this.height/20,
                    4,
                    0xFF818589,
                    0xFFFFFFFF,
                    0xFFFFFFFF,
                    Config.Questions.get(selectedQuestions[i])
                    ));
        }
    }

    @Override
    protected void init() {
        this.guiLeft = (this.width - this.imageWidth) / 2;
        this.guiTop = (this.height - this.imageHeight) / 2;
        reloadQuestions();
        reloadButtons();
        super.init();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics); // Render the background
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); // Reset color
        int left = this.guiLeft;
        int top = this.guiTop;

        // Draw a simple colored rectangle as background
        pGuiGraphics.fill(0, this.height - this.height/10, this.width, this.height, 0xFF000000); // Black background

        pGuiGraphics.drawString(Minecraft.getInstance().font, ("Liku: " +  liku_text), 0, this.height - this.height/10, 0xFFFFFFFF);

        for (int i =0; i<buttons.size(); i++) {
            buttons.get(i).draw(pGuiGraphics);
        }

        // Draw the title text
        //pGuiGraphics.drawCenteredString(this.font, this.title.getString(), this.width / 2, top + 10, 0xFFFFFF);

        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void resize(Minecraft pMinecraft, int pWidth, int pHeight) {
        super.resize(pMinecraft, pWidth, pHeight);
        reloadButtons();
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {

        for (int i =0; i < buttons.size(); i++) {
            if(buttons.get(i).inBounds((int)pMouseX,(int)pMouseY)) {
                LikusLostLand.LOGGER.info("Clicked: {}", i);
                liku_text = Config.Answers.get(selectedQuestions[i]);
                reloadQuestions();
                reloadButtons();
            }
        }

        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
