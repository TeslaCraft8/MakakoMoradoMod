package net.teslacuck.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class WeightCalculatorScreen2 extends Screen {

    private final String namePlayerInput;

    public WeightCalculatorScreen2(Text title, String playerInput) {
        super(title);
        this.namePlayerInput = playerInput;
    }


    private TextFieldWidget input;
    @Override
    protected void init(){
        int centerX = this.width/2;
        int centerY = this.height/2;

        TextFieldWidget peso = new TextFieldWidget(
                this.textRenderer,
                centerX - (100/2),
                centerY - 50,
                100,
                20,
                Text.literal("")
        );
        ButtonWidget done = ButtonWidget.builder(Text.of("Calcular ->"), (btn) ->{


            int weightPlayerInput = Integer.parseInt(peso.getText());

            MinecraftClient.getInstance().setScreen(
                    new WeightCalculatorLoadingScreen(Text.literal("Weight CalculatorLoadingScreen"), namePlayerInput, weightPlayerInput)
            );

        }).dimensions(centerX - (100/2), centerY - (20/2), 100, 20).build();

        peso.setChangedListener((text) -> botonActivo(done, peso));
        this.addDrawableChild(done );
        this.addDrawableChild(peso);
        done.active = false;
    }

    @Override
    public  void  render(DrawContext context, int mouseX, int mouseY, float delta) {
        String textoTactico = "Inserta tu peso:";
        super.render(context, mouseX, mouseY, delta);
        int centerX = this.width/2;
        int centerY = this.height/2;
        int textLenght = textRenderer.getWidth(textoTactico);
        int xPos = centerX - (textLenght/2);


        context.drawText(this.textRenderer, textoTactico, xPos, centerY - 100 - this.textRenderer.fontHeight, 0xFFFFFFFF, true);
    }

    private void botonActivo(ButtonWidget boton, TextFieldWidget field){
        if (field.getText().isBlank()){
            boton.active = false;
        }else{
            if (field.getText().matches("\\d+")){
                boton.active = true;
                boton.setMessage(Text.of("siguiente ->"));
            }else {
                boton.active = false;
                boton.setMessage(Text.of("inserta un numero"));
            }

        }
    }
}
