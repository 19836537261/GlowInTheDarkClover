package net.etechservicecn.glow_in_the_dark_clover.guis.item_scepter_gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.etechservicecn.glow_in_the_dark_clover.StartModApplication;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.lwjgl.glfw.GLFW;

public class ItemScepterScreen extends AbstractContainerScreen<ItemScepterMenu> {
    public static final ResourceLocation ITEM_SCEPTER_GUI_RESOURCE=new ResourceLocation(StartModApplication.MODID,"textures/gui/item/item_container.png");
    public static final Integer ESC_KEY_CODE=256;
    public static final Integer E_KEY_CODE=69;
    private int imgWidth=175;
    private int imgHeight=165;
    private int left;
    private int top;
    public ItemScepterScreen(ItemScepterMenu itemScepterMenu, Inventory player_inventory, Component title) {
        super(itemScepterMenu,player_inventory,title);
        this.imageWidth=imgWidth;
        this.imageHeight=imgHeight;
        this.inventoryLabelY=74;
        this.titleLabelY=5;
        this.titleLabelX=68;
    }

    @Override
    protected void init() {
        super.init();
        this.left=(this.width-imgWidth)/2;
        this.top=(this.height-imgHeight)/2;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouse_x, int mouse_y, float partial_ticks) {
        super.render(guiGraphics, mouse_x, mouse_y, partial_ticks);
        this.renderTooltip(guiGraphics,mouse_x,mouse_y);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partial_ticks, int mouse_x, int mouse_y) {
        this.renderBackground(guiGraphics);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,ITEM_SCEPTER_GUI_RESOURCE);
        guiGraphics.blit(ITEM_SCEPTER_GUI_RESOURCE,this.left,this.top,0,0,this.imgWidth,this.imgHeight);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouse_x, int mouse_y) {
        Component component=this.title;
        int center_x=(imgWidth-this.font.width(component))/2;
        guiGraphics.drawString(this.font,this.title,center_x,this.titleLabelY,0x404040,false);
        guiGraphics.drawString(this.font,this.playerInventoryTitle,this.inventoryLabelX,this.inventoryLabelY,0x404040,false);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        if (keyCode==GLFW.GLFW_KEY_ESCAPE||keyCode==GLFW.GLFW_KEY_E){
            this.minecraft.player.closeContainer();
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
