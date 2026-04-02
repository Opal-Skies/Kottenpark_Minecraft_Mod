package net.kottenpark_mod.screen.custom;

import net.kottenpark_mod.KottenparkMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.TutorialToast;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;

import java.awt.*;

public class CustomScreen extends HandledScreen<CustomScreenHandler> {

    public static final Identifier GUI_INVENTORY_TEXTURE =
        Identifier.of(KottenparkMod.MOD_ID,"textures/gui/whiteboard/whiteboard_inventory_gui.png");
    public static final Identifier GUI_QUESTION_TEXTURE =
            Identifier.of(KottenparkMod.MOD_ID,"textures/gui/whiteboard/whiteboard_question_gui.png");

    private static final int BUTTON_WIDTH = 160;
    private static final int BUTTON_HEIGHT = 32;

    private final Text Title;
    private final Text Question;
    private final Text Answer_A;
    private final Text Answer_B;
    private final Text Answer_C;
    private int CorrectIndex;


    public CustomScreen(CustomScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.Title = Text.literal(handler.getSubject());
        this.Question = Text.literal(handler.getQuestionArray()[0]);
        this.Answer_A = Text.literal(handler.getQuestionArray()[1]);
        this.Answer_B = Text.literal(handler.getQuestionArray()[2]);
        this.Answer_C = Text.literal(handler.getQuestionArray()[3]);
        this.CorrectIndex = handler.getCorrectAnswer();
    }


    @Override
    protected void init() {
        super.init();


        TextWidget titleWidget = new TextWidget(this.Title, this.textRenderer);
        titleWidget.setPosition(width/2 - titleWidget.getWidth()/2, height/3 - titleWidget.getHeight()/2);
        this.addDrawable(titleWidget);

        TextWidget questionWidget = new TextWidget(this.Question, this.textRenderer);
        questionWidget.setPosition(width/2 - questionWidget.getWidth()/2, height/5*2 - questionWidget.getHeight()/2);
        this.addDrawable(questionWidget);


        ButtonWidget buttonAnswerA = ButtonWidget.builder(this.Answer_A, btn -> answer(1))
                .size(BUTTON_WIDTH,BUTTON_HEIGHT).position(width/5*2 - BUTTON_WIDTH/2, height/2 - BUTTON_HEIGHT/2).build();
        ButtonWidget buttonAnswerB = ButtonWidget.builder(this.Answer_B, btn -> answer(2))
                .size(BUTTON_WIDTH,BUTTON_HEIGHT).position(width/2 - BUTTON_WIDTH/2, height/2 + BUTTON_HEIGHT).build();
        ButtonWidget buttonAnswerC = ButtonWidget.builder(this.Answer_C, btn -> answer(3))
                .size(BUTTON_WIDTH,BUTTON_HEIGHT).position(width/5*3 - BUTTON_WIDTH/2, height/2 - BUTTON_HEIGHT/2).build();

        this.addDrawableChild(buttonAnswerA);
        this.addDrawableChild(buttonAnswerB);
        this.addDrawableChild(buttonAnswerC);

    }

    public void answer(int index){
        if (index == this.CorrectIndex){
            correctAnswer();
        }else{
            incorrectAnswer();
        }

    }
    public void correctAnswer() {
        SystemToast correctAnswerToast = new SystemToast(SystemToast.Type.PERIODIC_NOTIFICATION, Text.literal("Correct answer"),Text.literal("W"));
        this.client.getToastManager().add(correctAnswerToast);
        ItemScatterer.spawn(this.client.world, ((PlayerEntity) this.client.player), this.handler.getInventory());
        this.client.setScreen(null);

    }
    public void incorrectAnswer() {
        SystemToast incorrectAnswerToast = new SystemToast(SystemToast.Type.PERIODIC_NOTIFICATION, Text.literal("bbb"),Text.literal("bbb"));
        this.client.getToastManager().add(incorrectAnswerToast);

    }


    @Override
    public void render(DrawContext graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);
    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int x = (width - 420) / 2;
        int y = (height - 300) / 2;

        context.drawTexture(RenderPipelines.GUI_TEXTURED, GUI_QUESTION_TEXTURE, x, y, 0, 0, 420, 320, 512, 512);

    }
}