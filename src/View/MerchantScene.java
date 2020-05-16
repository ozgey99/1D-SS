package View;

import javafx.event.EventHandler;
import Models.Cards.AbstractCard;
import Controller.Dungeon.Room.Merchant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.animation.ScaleTransition;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import Models.Object.AbstractRelic;

import java.nio.file.Paths;
import java.util.ArrayList;

import static View.Main.game;
import Models.Creatures.Pet;
import Models.Actions.RelicActions;


public class MerchantScene extends RoomScene  {
    Pet p;
    Pane pane;
    private UpperPane gridUpper;
    RemoveCard removeCardPane;
    ArrayList<AbstractCard> cards;
    ArrayList<AbstractRelic> relics;
    ArrayList<Integer> cardPrices;
    ArrayList<Integer> relicPrices;
    ImageView turnBack;
    ImageView removeButton;
    ImageView nextButton;
    ImageView petWarning;
    ImageView petMessage;
    ImageView upgradeMessage;
    ImageView papirus;
    ImageView hadPet;
    ImageView ss_back;
    ImageView upgradedPet;
    ImageView back;
    ImageView pet;
    static boolean added;
    boolean petClicked;
    static int origWidth;
    static  int origHeight;
    static int petPrice = 30;


    public MerchantScene() {
        super();
        root.setMinSize( width, height);
        p = new Pet();
        added = false;
        petClicked = false;
        origWidth = width;
        origHeight = height;
        pane = new Pane();
        gridUpper = new UpperPane(width,height/9);
        removeCardPane  = new RemoveCard(width/3*2 , height/9*6);
        removeButton = new ImageView(new Image("removeButton.png"));
        hadPet = new ImageView(new Image("youHavePet.png"));
        upgradedPet = new ImageView(new Image("removeButton.png"));
        turnBack = new ImageView(new Image("goAhead.png"));
        pet = new ImageView(new Image("dragon.gif"));
        nextButton = new ImageView(new Image("goAhead.png"));
        ss_back = new ImageView(new Image("ss_back.jpg"));
        back = new ImageView(new Image("up.png"));

        papirus = new ImageView(new Image("papirus.png"));

        petWarning= new ImageView(new Image("warning.png"));
        petMessage= new ImageView(new Image("petMessage.png"));
        upgradeMessage= new ImageView(new Image("upgradeMessage.png"));
        System.out.println(((Merchant) game.getDungeon().getCurrentRoom()).getCards().toString());
        cards = ((Merchant) game.getDungeon().getCurrentRoom()).getCards();
        cardPrices = ((Merchant) game.getDungeon().getCurrentRoom()).getCardPrices();
        relics = ((Merchant) game.getDungeon().getCurrentRoom()).getRelics();
        relicPrices = ((Merchant) game.getDungeon().getCurrentRoom()).getRelicPrices();
        initialize();
    }

    @Override
    public void initialize(){

        initializeUpper();
        shopPet();
        addBackground();
        cardRemovalService();
        shopCards();
        shopRelics();
        proceed();
        root.getChildren().add(pane);
        pane.getChildren().add(gridUpper);
        removeCardPane.stack.getChildren().add( turnBack );

        pane.getChildren().add(petWarning);
        pane.getChildren().add(petMessage);
        pane.getChildren().add(upgradeMessage);
        pane.getChildren().add(upgradedPet);
        pane.getChildren().add(hadPet);
        hadPet.setVisible(false);
        upgradedPet.setVisible(false);
        petWarning.setVisible(false);
        petMessage.setVisible(false);
        upgradeMessage.setVisible(false);

    }

    public void initializeUpper()
    {
        gridUpper.initialize();
        gridUpper.setBackground( new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)) );
        gridUpper.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane.setConstraints(gridUpper, 0,0,1,1);
        gridUpper.setMinWidth(width);

    }

    public void turnBack(){

        turnBack.setPreserveRatio(true);
        turnBack.setFitHeight(height/7); //turnBack.setFitHeight(100);
        turnBack.setX(width/5);
        turnBack.setY(height/4*3);
        removeCardPane.stack.setAlignment(turnBack,Pos.BOTTOM_RIGHT);
        turnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removeCardPane.visible(false);
                initializeUpper();
            }
        });
        draw();
    }

    @Override
    public void draw() {initializeUpper();}


    public void cardRemovalService(){
        ImageView cost = new ImageView(new Image("cost.png"));
        ImageView warn = new ImageView(new Image("removalWarning.png")); // ---------
        removeButton.setPreserveRatio(true);
        removeButton.setFitHeight(height/5);
        removeButton.setX(width/10*7);
        removeButton.setY(height/2);
        cost.setPreserveRatio(true);
        cost.setFitHeight(height/17.0);
        cost.setX(width/10*7+height/15);
        cost.setY(height/2+height/6);
        pane.getChildren().add(cost);

        warn.setPreserveRatio(true);
        warn.setFitHeight(origWidth/13.0);
        warn.setX(width/10*7+origWidth/13.0);
        warn.setY(height/2-height/15);
        warn.setVisible(false);
        pane.getChildren().add(warn); // ----------

        int price = removeCardPane.getPrice();
        String p = ""+ price;
        Text text = new Text(p);
        text.setX(width/10*7 +75);
        text.setY(height/2+height/5);
        text.setFont(Font.font ("Verdana", height/48.0));
        text.setFill(Color.WHITE);
        pane.getChildren().add(text);


        pane.getChildren().add( removeButton );
        removeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(game.getPlayer().getGold() > removeCardPane.getPrice()){
                    removeCardPane.visible(true);
                    removeCardPane.addBackground();
                    removeCardPane.initialize();
                    if(!added){
                        root.getChildren().add(removeCardPane);
                        added = true;
                    }
                    turnBack();
                }
                else{
                    warn.setVisible(true);
                }
            }
        });

        removeButton.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(warn.isVisible())
                    warn.setVisible(false);
            }
        });
    }

    public void shopPet(){

        pet.setPreserveRatio(true);
        pet.setFitHeight(height/9.0);
        pet.setX(width/10.0*7-width/13.0);
        pet.setY(height/5.0*2+height/7.0);
        if(!pane.getChildren().contains(pet))
            pane.getChildren().add( pet );

        ImageView cost = new ImageView(new Image("cost.png"));
        cost.setPreserveRatio(true);
        cost.setFitHeight(height/17.0);
        cost.setX(width/10.0*7-width/15.0);
        cost.setY(height/5*2+height/4.0 + height/36.0);
        if(!pane.getChildren().contains(cost))
            pane.getChildren().add(cost);

        upgradedPet.setPreserveRatio(true);
        upgradedPet.setFitWidth(origWidth/13.0);
        upgradedPet.setX(width/10*7-width/(106.0/10));
        upgradedPet.setY(height/5*2+height/7.0 - height/9.0);

        hadPet.setPreserveRatio(true);
        hadPet.setFitWidth(origWidth/13.0);
        hadPet.setX(width/10*7-width/(106.0/10));
        hadPet.setY(height/5*2+height/7.0 - height/9.0);

        petWarning.setPreserveRatio(true);
        petWarning.setFitWidth(origWidth/13.0);
        petWarning.setX(width/10*7-width/(106.0/10));
        petWarning.setY(height/5*2+height/7.0 - height/9.0);

        petMessage.setPreserveRatio(true);
        petMessage.setFitWidth(origWidth/13.0);
        petMessage.setX(width/10*7-width/(106/10));
        petMessage.setY(height/5*2+height/7.0 - height/9.0);

        upgradeMessage.setPreserveRatio(true);
        upgradeMessage.setFitWidth(origWidth/13.0);
        upgradeMessage.setX(width/10*7-width/(106/10));
        upgradeMessage.setY(height/5*2+height/7.0 - height/9.0);

        Text text = new Text(" " +petPrice);
        text.setX(width/10*7-width/20.0);
        text.setY(height/5*2+height/4.0 + height/36.0);
        text.setFont(Font.font ("Verdana", height/48.0));
        text.setFill(Color.WHITE);
        if(!pane.getChildren().contains(text))
            pane.getChildren().add(text);

        pet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("gold before pet: "+ game.getPlayer().getGold());


                if(game.getPlayer().getPet() == null){
                    if(game.getPlayer().getGold() < petPrice){
                        if(petMessage.isVisible())
                            petMessage.setVisible(false);
                        petWarning.setVisible(true);
                    }
                    else{
                        pet.setEffect( new DropShadow(30, Color.YELLOW) );
                        System.out.println("gold after pet:" + game.getPlayer().getGold());
                        hadPet.setVisible(true);
                        petMessage.setVisible(false);
                        game.getPlayer().buyPet(p);
                        game.getPlayer().changeGold(-petPrice);
                        gridUpper.draw();

                    }
                }

                else{
                    if(game.getPlayer().getGold() > petPrice){
                        // message = upgraded
                        pet.setEffect( new DropShadow(30, Color.YELLOW) );
                        System.out.println("gold after pet:" + game.getPlayer().getGold());
                        game.getPlayer().getPet().upgrade();
                        game.getPlayer().changeGold(-petPrice);
                        gridUpper.draw();
                    }

                }

                petPrice += 10;
                petClicked = true;
            }
        });

        pet.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(!petClicked){ // if not clicked
                    ScaleTransition relicTransition = new ScaleTransition(Duration.millis(200), pet);
                    relicTransition.setToX(1.5f);
                    relicTransition.setToY(1.5f);
                    relicTransition.setCycleCount(1);
                    relicTransition.setAutoReverse(true);
                    relicTransition.play();
                    ScaleTransition relicCostTransition = new ScaleTransition(Duration.millis(200), cost);
                    relicCostTransition.setToX(1.5f);
                    relicCostTransition.setToY(1.5f);
                    relicCostTransition.setCycleCount(1);
                    relicCostTransition.setAutoReverse(true);
                    relicCostTransition.play();

                    if(game.getPlayer().getPet() == null){
                        petMessage.setVisible(true);
                    }
                    else
                        upgradeMessage.setVisible(true);
                }

                if(petClicked && game.getPlayer().getPet() == null){
                    petMessage.setVisible(true);
                }

            }
        });

        pet.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), pet);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
                ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(200), cost);
                scaleTransition1.setToX(1);
                scaleTransition1.setToY(1);
                scaleTransition1.setAutoReverse(true);
                scaleTransition1.play();
                if(petMessage.isVisible())
                    petMessage.setVisible(false);

                if(petWarning.isVisible())
                    petWarning.setVisible(false);

                if(upgradeMessage.isVisible())
                    upgradeMessage.setVisible(false);

                if(hadPet.isVisible())
                    hadPet.setVisible(false);

                if(upgradedPet.isVisible())
                    upgradedPet.setVisible(false);
            }
        });
        if(petClicked)
            pet.setDisable(true);

    }

    public void proceed(){

        nextButton.setPreserveRatio(true);
        nextButton.setFitHeight(height/11.0);
        nextButton.setX(width-width/3.0); //nextButton.setX(width-400);
        nextButton.setY(height/8*6);
        pane.getChildren().add( nextButton );
        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                game.getDungeon().ascend();
            }
        });
    }

    static Node warning(int x, int y){
        Group g = new Group();
        ImageView warning = new ImageView(new Image("warning.png"));
        warning.setVisible(true);
        warning.setPreserveRatio(true);
        warning.setX(x);
        warning.setY(y);
        warning.setFitWidth(origWidth/13.0);
        g.getChildren().add(warning);
        return g;
    }

    public void shopCards(){
        int space = width/4;

        boolean saleAdded = false;
        int rand = (int) (Math.random() * 5);

        cards = ((Merchant) game.getDungeon().getCurrentRoom()).getCards();

        for (int i = 0; i < cards.size(); i++){
            int price = cardPrices.get(i);
            String name = cards.get(i).getName();
            System.out.println("card "+i +" "+name);
            name = name + ".png";
            Rectangle rect = new Rectangle();
            rect.setFill(new ImagePattern(new Image(name)));
            rect.setX(space);
            rect.setY(height/5.0);
            rect.setWidth(width/(128.0/10)); // rect.setWidth(100);
            rect.setHeight(height/(466.0/100)); //rect.setHeight(150);
            rect.setVisible(true);
            Text saleText = new Text("SALE");
            if(!saleAdded && rand == i ){
                saleAdded = true;
                price = price/2;
                saleText.setX(space+15);
                saleText.setY(height/5-5);
                saleText.setFont(Font.font ("family", height/24.0));
                saleText.setFill(Color.RED);
                pane.getChildren().add(saleText);
            }
            pane.getChildren().add(rect);

            ImageView cost = new ImageView(new Image("cost.png"));
            cost.setPreserveRatio(true);
            cost.setFitHeight(height/(175/10));//cost.setFitHeight(40);
            cost.setX(space+(width/130)); //cost.setX(space+10);
            cost.setY(height/5+height/(46/10)); //cost.setY(height/5+140);
            pane.getChildren().add(cost);
            Text text = new Text(" " +price);
            text.setX(space+width/(325/10)); //text.setX(space+40);
            text.setY(height/5+height/4); //text.setY(height/5+170);
            text.setFont(Font.font ("Verdana", height/48.0));
            text.setFill(Color.WHITE);
            pane.getChildren().add(text);

            int warningLocX = space+(width/130) - width/13; // cardX - warningWidth
            int warningLocY = height/4; //int warningLocY = height/5-100;

            Node warn = warning(warningLocX, warningLocY);
            pane.getChildren().add(warn);
            warn.setVisible(false);

            boolean sale_added = saleAdded;
            int cardPrice = price;
            int j = i;
            rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {

                    if (game.getPlayer().getGold() >= cardPrice) {

                        game.getPlayer().changeGold(-cardPrice);
                        gridUpper.draw();

                        game.getPlayer().masterDeck.addCard(cards.get(j));
                        pane.getChildren().remove(rect);
                        pane.getChildren().remove(text);
                        pane.getChildren().remove(cost);
                        if(sale_added == true){
                            pane.getChildren().remove(saleText);
                        }


                        System.out.println("========AFTER CLICK MASTER DECK=========");
                        for(int k=0; k<game.getPlayer().masterDeck.getSize(); k++){
                            System.out.println(game.getPlayer().masterDeck.getCard(k).getName());
                        }

                    } else {
                        warn.setVisible(true);
                        System.out.println("You don't have enough gold for card");
                    }

                }
            });

            rect.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                    scaleTransition.setToX(1.5f);
                    scaleTransition.setToY(1.5f);
                    scaleTransition.setCycleCount(1);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
                    ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(200), cost);
                    scaleTransition1.setToX(1.5f);
                    scaleTransition1.setToY(1.5f);
                    scaleTransition1.setCycleCount(1);
                    scaleTransition1.setAutoReverse(true);
                    scaleTransition1.play();
                }
            });

            rect.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
                    ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(200), cost);
                    scaleTransition1.setToX(1);
                    scaleTransition1.setToY(1);
                    scaleTransition1.setAutoReverse(true);
                    scaleTransition1.play();
                    warn.setVisible(false);

                }
            });

            space = space +  width/(108/10); //space = space + 120;
        }

    }

    static Node relicDescription(ArrayList<AbstractRelic> relic,int ind, int x, int y) {
        Group g = new Group();

        String desc = relic.get(ind).getDescription();
        int len = desc.length();

        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setWidth(len*(origWidth/213));
        rect.setHeight(origHeight/35); //rect.setHeight(20);
        rect.setVisible(true);

        Text relicText = new Text(desc);
        relicText.setX(x+5);
        relicText.setY(y+13);
        relicText.setFont(Font.font ("Verdana", origHeight/72));
        relicText.setFill(Color.WHITE);

        g.getChildren().add(rect);
        g.getChildren().add(relicText);

        return g;
    }

    public void shopRelics(){
        int space = width/4;
        relics = ((Merchant) game.getDungeon().getCurrentRoom()).getRelics();

        for (int i = 0; i < relics.size(); i++){

            int price = relicPrices.get(i);
            String name = relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(height/14); //relicImage.setFitHeight(50);
            relicImage.setX(space);
            relicImage.setY(height/5*2+height/7); //relicImage.setY(height/5*2+100);
            pane.getChildren().add( relicImage );

            ImageView cost = new ImageView(new Image("cost.png"));
            cost.setPreserveRatio(true);
            cost.setFitHeight(height/17); //cost.setFitHeight(40);
            cost.setX(space);
            cost.setY(height/5*2+height/(46/10)); //cost.setY(height/5*2+150);
            pane.getChildren().add(cost);
            Text text = new Text(" " +price);
            text.setX(space+width/43); //text.setX(space+30);
            text.setY(height/5*2+height/4); //text.setY(height/5*2+170);
            text.setFont(Font.font ("Verdana", height/48.0));
            text.setFill(Color.WHITE);
            pane.getChildren().add(text);

            int warningLocX = space-width/26; //int warningLocX = space-50;
            int warningLocY = height/5*2+5;

            Node warn = warning(warningLocX, warningLocY);
            pane.getChildren().add(warn);
            warn.setVisible(false);

            int j = i;
            relicImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    System.out.println("current gold: "+ game.getPlayer().getGold());
                    if (game.getPlayer().getGold() >= relicPrices.get(j)) {
                        game.getPlayer().changeGold(-(relicPrices.get(j)));
                        RelicActions.addRelic(game.getPlayer(),relics.get(j));
                        gridUpper.draw();
                        pane.getChildren().remove(relicImage);
                        pane.getChildren().remove(text);
                        pane.getChildren().remove(cost);
                    }
                    else {
                        warn.setVisible(true);
                    }

                }
            });

            //Node desc = relicDescription(relics,i, space+50, height/5*2+130);
            Node desc = relicDescription(relics,i, space+width/26, height/5*2+height/(53/10));
            pane.getChildren().add(desc);
            desc.setVisible(false);
            relicImage.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {

                    ScaleTransition relicTransition = new ScaleTransition(Duration.millis(200), relicImage);
                    relicTransition.setToX(1.5f);
                    relicTransition.setToY(1.5f);
                    relicTransition.setCycleCount(1);
                    relicTransition.setAutoReverse(true);
                    relicTransition.play();
                    ScaleTransition relicCostTransition = new ScaleTransition(Duration.millis(200), cost);
                    relicCostTransition.setToX(1.5f);
                    relicCostTransition.setToY(1.5f);
                    relicCostTransition.setCycleCount(1);
                    relicCostTransition.setAutoReverse(true);
                    relicCostTransition.play();
                    desc.setVisible(true);

                }
            });

            relicImage.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), relicImage);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
                    ScaleTransition scaleTransition1 = new ScaleTransition(Duration.millis(200), cost);
                    scaleTransition1.setToX(1);
                    scaleTransition1.setToY(1);
                    scaleTransition1.setAutoReverse(true);
                    scaleTransition1.play();
                    warn.setVisible(false);
                    desc.setVisible(false);
                }
            });

            space = space + width/(108/10); //space = space + 120;
        }
    }

    private void addBackground() {
        back.setFitWidth(width);
        back.setFitHeight(height);
        papirus.setFitWidth(width-width/(43/10)); //image.setFitWidth(width-300);
        papirus.setFitHeight(height);
        root.getChildren().add(ss_back);
        root.getChildren().add(back);
        root.getChildren().add(papirus);

    }

}

