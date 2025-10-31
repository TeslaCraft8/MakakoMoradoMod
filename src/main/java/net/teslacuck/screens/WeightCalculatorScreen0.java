package net.teslacuck.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

public class WeightCalculatorScreen0 extends Screen {
    public WeightCalculatorScreen0(Text title) {
        super(title);
    }

    private TextFieldWidget input;
    @Override
    protected void init(){
        int centerX = this.width/2;
        int centerY = this.height/2;

        TextFieldWidget nombre = new TextFieldWidget(
                this.textRenderer,
                centerX - (100/2),
                centerY - 50,
                100,
                20,
                Text.literal("")
        );
        ButtonWidget done = ButtonWidget.builder(Text.of("Siguiente ->"), (btn) ->{
                MinecraftClient.getInstance().setScreen(
                        new WeightCalculatorScreen(Text.literal("Weight Calculator"))
                );

        }).dimensions(centerX - (100/2), centerY - (20/2), 100, 20).build();

        this.addDrawableChild(done);
    }

    @Override
    public  void  render(DrawContext context, int mouseX, int mouseY, float delta) {
        //mierda para centrar el texto
        String textoTactico = "Calculadora de peso";
        super.render(context, mouseX, mouseY, delta);
        int centerX = this.width/2;
        int centerY = this.height/2;
        int textLenght = textRenderer.getWidth(textoTactico);
        int xPos = centerX - (textLenght/2);
        //dibuja texto
        context.drawText(this.textRenderer, textoTactico, xPos, centerY - 50 - this.textRenderer.fontHeight, 0xFFFFFFFF, true);
    }
}
