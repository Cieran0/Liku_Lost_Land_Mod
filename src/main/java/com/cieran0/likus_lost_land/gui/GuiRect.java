package com.cieran0.likus_lost_land.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class GuiRect {

    public int x;
    public int y;
    public int width;
    public int height;
    public int border_size;
    public int colour;
    public int text_colour;
    public int border_colour;
    public String text;

    GuiRect(int x, int y, int width, int height, int border_size, int colour, int border_colour, int text_colour, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.border_size = border_size;
        this.colour = colour;
        this.border_colour = border_colour;
        this.text_colour = text_colour;
        this.text = text;
    }

    public boolean inBounds(int x, int y) {
        return !(x < this.x || x > this.x+this.width || y < this.y || y>this.y+this.height);
    }

    public void draw(GuiGraphics pGuiGraphics) {
        pGuiGraphics.fill(x, y, x + width, y + height, border_colour); // Black background
        pGuiGraphics.fill(x+border_size, y+border_size, x + width - border_size, y + height - border_size, colour); // Black background
        pGuiGraphics.drawCenteredString(Minecraft.getInstance().font, text, x + width/2, y + (height)/2, text_colour);
    }

}
