package net.teslacuck.screens;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.TextFieldWidget;

public class WeightCalcScreenManager extends Screen {
    public WeightCalcScreenManager(Text title) {
        super(title);
    }

    private TextFieldWidget playerInput;
    private ButtonWidget button;
    int actualScreen = 0;
    String nombre = "";
    int peso = 0;
    String inputConKg;
    boolean terminado = false;
    private final String[] buttonsTexts = {
            "Siguiente ->",
            "Listo",
            "Listo",
            "Siguiente ->",
            "Terminado"
    };
    private final String[] screenLabelsTexts = {
            "Calculadora de peso: calcula tu peso usando dos simples parametros",
            "¿Cual es tu nombre?",
            "¿Cual es tu peso?",
            "Cargando",
            ""
    };
    int textSwapTicks = 11;
    boolean textSwapToggle = false;
    int animationTick = 0;
    private final String[] loadingFrames = {"|", "/", "-", "\\"};
    int currentFrame = 0;
    int screenTick = 0;
    int realScreenTick = 0;
    int maxScreenTime = 200;

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        playerInput = new TextFieldWidget(this.textRenderer, centerX - 50, centerY - 20, 100, 20, Text.literal(""));
        button = ButtonWidget.builder(Text.of(buttonsTexts[actualScreen]), (btn) -> {
            switch (actualScreen) {
                case 0: button0(btn, playerInput);
                    break;
                case 1: button1(btn, playerInput);
                    break;
                case 2: button2(btn, playerInput);
                    break;
                case 3: button3(btn, playerInput);
                    break;
                case 4: button4(btn, playerInput);
                    break;
            }
            if (actualScreen < buttonsTexts.length) button.setMessage(Text.of(buttonsTexts[actualScreen]));
        }).dimensions(centerX - 40, centerY, 80, 20).build();

        playerInput.setChangedListener((texto) -> botonActivo(button, playerInput));
        this.addDrawableChild(button);
        this.addDrawableChild(playerInput);

        playerInput.visible = false;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        int centerX = this.width / 2;
        int centerY = this.height / 2;




        if (actualScreen == 4) screenLabelsTexts[4] = "Hola " + peso + "kg, tu peso es de: " + nombre + ".";


        int textWidth = textRenderer.getWidth(screenLabelsTexts[actualScreen]);

        int posX = centerX - textWidth / 2;

        context.drawText(this.textRenderer, screenLabelsTexts[actualScreen], posX, centerY - this.textRenderer.fontHeight - playerInput.getHeight() - 10, 0xFFFFFFFF, true);
        if (actualScreen == 2) {
            if (!playerInput.getText().isBlank()) {
                int textoAncho = this.textRenderer.getWidth(playerInput.getText());
                int kgX = playerInput.getX() + textoAncho + 4;
                int kgY = playerInput.getY() + 6;
                if (textSwapToggle){
                    context.drawText(this.textRenderer, "kg", kgX, kgY, 0xFF7F7F7F, true);
                }else {context.drawText(this.textRenderer, "kg", kgX, kgY, 0x447F7F7F, false);}
            }

        }
        if (actualScreen == 3 && screenTick >= maxScreenTime) button.visible = true;
    }

    @Override
    public void tick() {
        textSwapTicks--;
        if (textSwapTicks <= 0) {
            textSwapToggle = !textSwapToggle;
            textSwapTicks = 11;
        }
        if (actualScreen == 3) {
            screenTick++;
            animationTick++;
            if (animationTick >= 5) {
                currentFrame++;
                animationTick = 0;
            }
            if (currentFrame >= loadingFrames.length) currentFrame = 0;
            if (screenTick >= maxScreenTime) button.visible = true;
            if (screenTick >= maxScreenTime) button.active = true;
            screenLabelsTexts[3] = "Calculando: " + loadingFrames[currentFrame];
            if (screenTick >= maxScreenTime) {
                if (textSwapToggle){
                    screenLabelsTexts[3] = "· Listo! ·";
                }else screenLabelsTexts[3] = "- Listo! -";

            }
        }
    }

    private void button0(ButtonWidget button, TextFieldWidget textFlied) {
        button.active = false;
        textFlied.visible = true;
        textFlied.setText("");
        actualScreen++;
    }
    private void button1(ButtonWidget button, TextFieldWidget textFlied) {
        button.active = false;
        textFlied.visible = true;
        nombre = textFlied.getText();
        textFlied.setText("");
        actualScreen++;
    }
    private void button2(ButtonWidget button, TextFieldWidget textFlied) {
        button.visible = false;
        button.active = true;
        textFlied.visible = false;
        peso = Integer.parseInt(textFlied.getText());
        textFlied.setText("");
        screenTick = 0;
        actualScreen++;
    }
    private void button3(ButtonWidget button, TextFieldWidget textFlied) {
        button.visible = true;
        textFlied.visible = false;
        actualScreen++;
    }
    private void button4(ButtonWidget button, TextFieldWidget textFlied) {
        MinecraftClient.getInstance().setScreen(null);
    }

    private void botonActivo(ButtonWidget buttonToActivate, TextFieldWidget textfield){
        switch (actualScreen){
            case 1:
                if (textfield.getText().isBlank()){
                    buttonToActivate.active = false;
                }else buttonToActivate.active = true;
                break;
            case 2:
                if (textfield.getText().isBlank()){
                    buttonToActivate.active = false;
                }
                else
                {
                    if (textfield.getText().matches("\\d+")){
                        buttonToActivate.active = true;
                        buttonToActivate.setMessage(Text.of("Listo"));
                    }else {
                        buttonToActivate.active = false;
                        buttonToActivate.setMessage(Text.of("Inserta Numero"));
                    }
                }
                break;
        }
            inputConKg = textfield.getText() + "kg";

    }

}