package application;

import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;



public class Main extends Application {

    private String quotesFilePath = "C:\\Users\\ramos\\Desktop\\CodeAlpha\\QuoteGenerator\\Quotes.csv";
    private Quote currentQuote;
    private Label quoteLabel;
    private Label authorLabel;
	@Override
	public void start(Stage primaryStage) throws IOException {
	
		quoteLabel = new Label();
		authorLabel = new Label();
		quoteLabel.setTextFill(Color.DARKBLUE);
		authorLabel.setTextFill(Color.DARKBLUE);
		Button genQuoteButton = new Button("Generate Quote");
		genQuoteButton. setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
		genQuoteButton.setOnAction(event->{generateRandomQuote();});
		VBox qBox = new VBox(10,quoteLabel,authorLabel);
		qBox.setPrefWidth(100);// prefWidth
		qBox.setPrefHeight(200);// prefHeight
		qBox.autosize();
		BackgroundFill backgroundFill1 = new BackgroundFill(Color.LAVENDERBLUSH,new CornerRadii(100),new Insets(0));
		Background background1 = new Background(backgroundFill1);
		qBox.setBackground(background1);
		qBox.setAlignment(Pos.CENTER);
		qBox.autosize();
		VBox root = new VBox(40,qBox, genQuoteButton);
		root.setAlignment(Pos.CENTER);
		BackgroundFill backgroundFill2 = new BackgroundFill(Color.LAVENDER,new CornerRadii(10),new Insets(10));
		Background background2 = new Background(backgroundFill2);
		root.setBackground(background2);
		Scene scene = new Scene(root,600,400);
		primaryStage.setTitle("Random Quote Generator");
		primaryStage.setScene(scene);
		primaryStage.show();
		generateRandomQuote();
	}
	
    public void generateRandomQuote() {
        try (BufferedReader br = new BufferedReader(new FileReader(quotesFilePath))) {
            currentQuote = generateQuote(br);
            quoteLabel.setText(currentQuote.getQuote());
            authorLabel.setText("Author: "+currentQuote.getAuthor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
		launch(args);
	}


    private Quote generateQuote(BufferedReader br) throws IOException {
        Random rand = new Random();
        int randomNum1 = rand.nextInt(40)+1;
        int randomNum2 = rand.nextInt(40)+1;
        while (true) {
            if (randomNum1 != randomNum2) {
                try {
                    br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                randomNum1 =rand.nextInt(40)+1;
            } else {
                String line = br.readLine();
                while (line != null) {
                    String[] sp = line.split(",");
                    Quote res = new Quote(sp[0], sp[1]);
                    return res;
                 }
             }
         }
     }
 }  
class Quote{
	private String author;
	private String quote;
	
	Quote(String a, String q) {
		this.author=a;
		this.quote=q;
	}

	public String getAuthor() {
		return author;
	}

	public String getQuote() {
		return quote;
	}


	
}
	
	
	
	
	
	
