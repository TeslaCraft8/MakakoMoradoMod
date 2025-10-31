package net.teslacuck.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class WeightCalculatorLoadingScreen extends Screen {

    private final String namePlayerInput;
    private final int weightPlayerInput;

    public WeightCalculatorLoadingScreen(Text title, String playerInput, int weightPlayerInput) {
        super(title);
        this.namePlayerInput = playerInput;
        this.weightPlayerInput = weightPlayerInput;


    }


    int animationTick = 0;
    private final String[] loadingFrames = {". . .", "· . .", ". · .", ". . ·"};
    int currentFrame = 0;
    int screenTick = 0;
    int maxScreenTime = 200;


    @Override
    public  void  render(DrawContext context, int mouseX, int mouseY, float delta) {
        String textoTactico = "Calculando: " + loadingFrames[currentFrame];
        super.render(context, mouseX, mouseY, delta);
        int centerX = this.width/2;
        int centerY = this.height/2;
        int textLenght = textRenderer.getWidth(textoTactico);
        int xPos = centerX - (textLenght/2);


        context.drawText(this.textRenderer, textoTactico, xPos, centerY - 100 - this.textRenderer.fontHeight, 0xFFFFFFFF, true);
    }

    @Override
    public void tick(){
        screenTick++;
        animationTick++;
        if (animationTick >= 5){
            currentFrame++;
            animationTick= 0;
        }
        if (currentFrame >= loadingFrames.length) currentFrame = 0;

        if (screenTick >= maxScreenTime){
            MinecraftClient.getInstance().setScreen(
                    new WeightCalculatorScreen3(Text.literal("Weight Calculator screen3"), namePlayerInput, weightPlayerInput)
            );
        }
    }

}
