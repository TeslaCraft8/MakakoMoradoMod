package net.teslacuck.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.TextFieldWidget;

public class WeightCalculatorScreen extends Screen {
    public WeightCalculatorScreen(Text title) {
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

            String playerInput = nombre.getText();

            MinecraftClient.getInstance().setScreen(
                    new WeightCalculatorScreen2(Text.literal("Weight Calculator2"), playerInput)
            );


            this.client.getToastManager().add(
                            SystemToast.create(this.client,
                                    SystemToast.Type.TUTORIAL_HINT,
                                    Text.of("Tu nombre es:"),
                                    Text.of(playerInput))
                    );
        }).dimensions(centerX - (100/2), centerY - (20/2), 100, 20).build();

        nombre.setChangedListener((text) -> botonActivo(done, nombre));
        this.addDrawableChild(done );
        this.addDrawableChild(nombre);
        done.active = false;
    }

    @Override
    public  void  render(DrawContext context, int mouseX, int mouseY, float delta) {
        String textoTactico = "Inserta tu nombre";
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
            boton.active = true;
        }
    }
}
