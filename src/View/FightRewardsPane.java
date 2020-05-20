package View;

import Controller.Actions.RelicActions;
import Models.Cards.AbstractCard;
import Controller.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static View.Main.game;

public class FightRewardsPane extends StackPane {

    static int width;
    static int height;
    ImageView back;
    Node fightRewards;
    ImageView proceed;
    ImageView rewardWarn;
    Pane pane;
    ImageView tick;
    ImageView rewardHeader;

    public FightRewardsPane(int width, int height){
        this.width = width;
        this.height = height;
        pane =  new Pane();
        fightRewards = rewards();
        proceed = new ImageView(new Image("goAhead.png"));
        rewardWarn = new ImageView(new Image("rewardWarn.png"));
        back = new ImageView(new Image("up.png"));
        tick = new ImageView(new Image("tick.png"));
        this.getChildren().add(pane);
    }

    public void initialize(){
        background();
        pane.getChildren().add(fightRewards);
        chosen();
        info();
        rewardHeader = new ImageView(new Image("rewardHeader.png"));
        this.getChildren().add(rewardHeader);
        setAlignment(rewardHeader, Pos.CENTER);
    }

    public void chosen(){
        tick.setPreserveRatio(true);
        tick.setFitHeight(height/7.0);
        tick.setX(width/2.0);
        tick.setY(height/2.0-height/5.0+ height/(35.0/10));
        tick.setOnMouseClicked( e -> {
            fightRewards.setVisible(false);
            tick.setVisible(false);
            rewardHeader.setVisible(false);
            proceed();
            rewardWarn.setVisible(false);

        });
        pane.getChildren().add( tick );
    }

    public void visible(boolean bool){
        this.setVisible(bool);
    }

    public void proceed(){
        proceed.setPreserveRatio(true);
        proceed.setFitHeight(height/7.0);
        proceed.setX(width/5.0+ width/3.0*2);
        proceed.setY(height/4.0*3);
        pane.getChildren().add( proceed );
        proceed.setOnMouseClicked(e -> {
            Main.mediaPlayer.addMenuMusic();
            game.getDungeon().ascend();

        });
    }

    private void info(){
        rewardWarn.setPreserveRatio(true);
        rewardWarn.setFitWidth(width/13.0);
        rewardWarn.setX(width/10.0*7-width/(106.0/10));
        rewardWarn.setY(height/6.0*2);
        pane.getChildren().add(rewardWarn);

    }

    static Node rewards() {

        Group g = new Group();
        VBox vbox = new VBox(height/35.0);

        // gold reward
        HBox box1 = new HBox();
        box1.setPadding(new Insets(height/30.0, 0, 0, 0));
        Text goldText = new Text();
        int gold = ((Fight)game.getDungeon().getCurrentRoom()).getGoldAmount();
        goldText.setText(gold + " gold");
        goldText.setFill(Color.BLACK);
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        ImageView goldImage = new ImageView(new Image("gold.png"));
        goldImage.setFitWidth(width/(325.0/10));
        goldImage.setFitHeight(height/(175.0/10));
        goldImage.setVisible(true);
        goldImage.setPreserveRatio(true);
        box1.getChildren().addAll(goldImage, goldText);
        vbox.getChildren().add(box1);

        // card reward
        Rectangle cardRect = new Rectangle();
        ArrayList<AbstractCard> cards = ((Fight)game.getDungeon().getCurrentRoom()).getCards();
        String label = "add a card to your deck";
        Text text = new Text(label);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, width/100.0));
        vbox.getChildren().add(text);

        // relic reward
        ArrayList<AbstractRelic> relics = ((Fight)game.getDungeon().getCurrentRoom()).getRelics();
        Text relicText = new Text();
        Text relicDesc = new Text();
        if (relics.size() != 0){
            String desc = relics.get(0).getDescription();
            String s = "get a relic";
            relicText.setText(s);
            relicText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, width/100.0));
            String name = relics.get(0).getName();
            relicDesc.setText(name+" : "+ desc);
            relicDesc.setFill(Color.WHITE);
            relicDesc.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, width/100.0));
        }
        else{
            relicText.setText("no relic rewards"+ "\n" +"you already have all relic ");
            relicText.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, width/100.0));
        }

        vbox.getChildren().add(relicText);


        // gold reward event listener
        goldImage.setOnMouseClicked(e -> {
            game.getPlayer().changeGold(((Fight) game.getDungeon().getCurrentRoom()).getGoldAmount());
            goldImage.setEffect( new DropShadow(10, Color.RED) );
            relicText.setDisable(true);
            text.setDisable(true);
            goldImage.setDisable(true);

        });


        // relic reward event listener
        HBox box2 = new HBox();
        if (relics.size() != 0){
            relicText.setOnMouseClicked(e -> {
                    String name = relics.get(0).getName();
                    name = name + ".png";
                    ImageView relicImage = new ImageView(new Image(name));
                    relicImage.setPreserveRatio(true);
                    relicImage.setFitHeight(height/7.0);
                    box2.setPadding(new Insets(height/2.0,width/13.0, height/7.0, width/(232.0/100) ));
                    box2.getChildren().addAll(relicImage, relicDesc);
                    g.getChildren().add(box2);
                    relicImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            RelicActions.addRelic(game.getPlayer(),relics.get(0));
                            relicImage.setVisible(false);
                            relicDesc.setVisible(false);
                            relicText.setDisable(true);
                            goldImage.setDisable(true);
                            text.setDisable(true);
                        }
                    });
                    relicText.setEffect( new DropShadow(10, Color.RED) );
                }

            );
        }


        // card reward event listener
        text.setOnMouseClicked(e -> {
                String name = cards.get(0).getName();
                name = name + ".png";
                cardRect.setX(width/(232.0/100));
                cardRect.setY(height/(33.0/10));
                cardRect.setFill(new ImagePattern(new Image(name)));
                cardRect.setWidth(width/(65/10.0));
                cardRect.setHeight(height/3.0);
                cardRect.setVisible(true);
                g.getChildren().add(cardRect);
                cardRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        game.getPlayer().masterDeck.addCard(cards.get(0));
                        cardRect.setVisible(false);
                        text.setDisable(true);
                        goldImage.setDisable(true);
                        relicText.setDisable(true);
                        text.setDisable(true);
                    }
                });
                text.setEffect( new DropShadow(10, Color.RED) );
            }
        );
        Rectangle rect = new Rectangle();
        rect.setX(width/2.0- width/13.0);
        rect.setY(height/2.0-(height/(46.0/10)));
        rect.setFill(new ImagePattern(new Image("rewardsBack.png")));
        rect.setStroke(Color.BLACK);
        rect.setWidth(width/(65.0/10));
        rect.setHeight(height/(233.0/100));
        rect.setVisible(true);

        vbox.setPadding(new Insets(height/(32.0/10),width/13.0, height/7.0, width/(232.0/100)));

        g.getChildren().add(rect);
        g.getChildren().add(vbox);
        return g;
    }

    private  void background(){
        back.setFitWidth(width);
        back.setFitHeight(height);
        back.setOpacity(0.90);
        pane.getChildren().add(back);
    }
}
