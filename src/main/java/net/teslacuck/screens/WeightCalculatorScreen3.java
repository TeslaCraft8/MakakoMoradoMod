package net.teslacuck.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;


public class WeightCalculatorScreen3 extends Screen {

    private final String namePlayerInput;
    private final int peso;

    public WeightCalculatorScreen3(Text title, String playerInput, int peso) {
        super(title);
        this.namePlayerInput = playerInput;
        this.peso = peso;
    }


    private TextFieldWidget input;
    @Override
    protected void init(){
        int centerX = this.width/2;
        int centerY = this.height/2;



        ButtonWidget done = ButtonWidget.builder(Text.of("Listo!"), (btn) ->{

            MinecraftClient.getInstance().setScreen(null);


        }).dimensions(centerX - (100/2), centerY - (20/2), 100, 20).build();

        this.addDrawableChild(done);

    }

    @Override
    public  void  render(DrawContext context, int mouseX, int mouseY, float delta) {
        String textoTactico = "Hola " + peso + "Kg, tu peso es de: " + namePlayerInput;
        super.render(context, mouseX, mouseY, delta);
        int centerX = this.width/2;
        int centerY = this.height/2;
        int textLenght = textRenderer.getWidth(textoTactico);
        int xPos = centerX - (textLenght/2);


        context.drawText(this.textRenderer, textoTactico, xPos, centerY - 100 - this.textRenderer.fontHeight, 0xFFFFFFFF, true);
    }
}
