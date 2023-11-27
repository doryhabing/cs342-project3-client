import java.util.HashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.lang.StringBuilder;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class ClientGUI extends Application{
    TextField port_prompt;
    Text gameTitle, chooseCategory, guessPrompt, secretWordDisplay, letterNum, incorrectGuess, win, loss, remainingGuesses, stats;
    Button clientChoice, startBtn, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, cat1, cat2, cat3;
    HashMap<String, Scene> sceneMap;
    HBox letterBox1, letterBox2, letterBox3, clientBtn, statsBox;
    VBox buttonBox;
    Scene startScene;
    BorderPane startPane;
    Client clientConnection;
    StringBuilder secretWord;
    ListView<String> listItems, listItems2;
    String category1, category2, category3;
    PauseTransition pause2, pause3, pause4;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        primaryStage.setTitle("ClientGUI");

        // creating guess letter buttons
        this.a = new Button("a");
        this.a.setPrefSize(60, 60);
        this.b = new Button("b");
        this.b.setPrefSize(60, 60);
        this.c = new Button("c");
        this.c.setPrefSize(60, 60);
        this.d = new Button("d");
        this.d.setPrefSize(60, 60);
        this.e = new Button("e");
        this.e.setPrefSize(60, 60);
        this.f = new Button("f");
        this.f.setPrefSize(60, 60);
        this.g = new Button("g");
        this.g.setPrefSize(60, 60);
        this.h = new Button("h");
        this.h.setPrefSize(60, 60);
        this.i = new Button("i");
        this.i.setPrefSize(60, 60);
        this.j = new Button("j");
        this.j.setPrefSize(60, 60);
        this.k = new Button("k");
        this.k.setPrefSize(60, 60);
        this.l = new Button("l");
        this.l.setPrefSize(60, 60);
        this.m = new Button("m");
        this.m.setPrefSize(60, 60);
        this.n = new Button("n");
        this.n.setPrefSize(60, 60);
        this.o = new Button("o");
        this.o.setPrefSize(60, 60);
        this.p = new Button("p");
        this.p.setPrefSize(60, 60);
        this.q = new Button("q");
        this.q.setPrefSize(60, 60);
        this.r = new Button("r");
        this.r.setPrefSize(60, 60);
        this.s = new Button("s");
        this.s.setPrefSize(60, 60);
        this.t = new Button("t");
        this.t.setPrefSize(60, 60);
        this.u = new Button("u");
        this.u.setPrefSize(60, 60);
        this.v = new Button("v");
        this.v.setPrefSize(60, 60);
        this.w = new Button("w");
        this.w.setPrefSize(60, 60);
        this.x = new Button("x");
        this.x.setPrefSize(60, 60);
        this.y = new Button("y");
        this.y.setPrefSize(60, 60);
        this.z = new Button("z");
        this.z.setPrefSize(60, 60);

        this.cat1 = new Button();
        this.cat1.setPrefSize(250, 100);
        this.cat2 = new Button();
        this.cat2.setPrefSize(250, 100);
        this.cat3 = new Button();
        this.cat3.setPrefSize(250, 100);
        
        this.secretWordDisplay = new Text();
        this.secretWord = new StringBuilder();
        
        this.letterNum = new Text();

        this.port_prompt = new TextField();
        this.port_prompt.setPrefSize(10, 10);
        this.port_prompt.setPromptText("Enter port number here and then press Connect");

        this.clientChoice = new Button("Connect");
        this.clientChoice.setPrefSize(250, 150);

        this.gameTitle = new Text("Welcome to the Word Game!");
        this.gameTitle.setFont(Font.font("book antiqua", FontWeight.BOLD, FontPosture.REGULAR, 50));

        this.startBtn = new Button("Start");
        this.startBtn.setPrefSize(250, 150);

        this.chooseCategory = new Text("Pick a category to try and guess a word:");
        this.chooseCategory.setFont(Font.font("book antiqua", FontPosture.REGULAR, 25));

        this.guessPrompt = new Text("Pick a letter to see if it's in the secret word:");
        this.guessPrompt.setFont(Font.font("book antiqua", FontPosture.REGULAR, 25));
        
        this.incorrectGuess = new Text("Oops, wrong guess! Try again.");
        this.incorrectGuess.setFont(Font.font("book antiqua", FontPosture.REGULAR, 15));
        
        this.win = new Text("You guessed the secret word, nice job!");
        this.win.setFont(Font.font("book antiqua", FontPosture.REGULAR, 25));
        
        this.loss = new Text("You ran out of guesses, try another word.");
        this.loss.setFont(Font.font("book antiqua", FontWeight.BOLD, FontPosture.REGULAR, 35));
        
        this.stats = new Text(" ");
        this.stats.setFont(Font.font("book antiqua", FontPosture.REGULAR, 25));
        
        this.remainingGuesses = new Text("Guesses remaining: 6");
        this.remainingGuesses.setFont(Font.font("book antiqua", FontPosture.REGULAR, 20));
        
        //incorrectGuess, win, loss, remainingGuesses


        a.setOnAction(e -> letter_handler(a));
        b.setOnAction(e -> letter_handler(b));
        c.setOnAction(e -> letter_handler(c));
        d.setOnAction(e -> letter_handler(d));
        e.setOnAction(event -> letter_handler(e));
        f.setOnAction(e -> letter_handler(f));
        g.setOnAction(e -> letter_handler(g));
        h.setOnAction(e -> letter_handler(h));
        i.setOnAction(e -> letter_handler(i));
        j.setOnAction(e -> letter_handler(j));
        k.setOnAction(e -> letter_handler(k));
        l.setOnAction(e -> letter_handler(l));
        m.setOnAction(e -> letter_handler(m));
        n.setOnAction(e -> letter_handler(n));
        o.setOnAction(e -> letter_handler(o));
        p.setOnAction(e -> letter_handler(p));
        q.setOnAction(e -> letter_handler(q));
        r.setOnAction(e -> letter_handler(r));
        s.setOnAction(e -> letter_handler(s));
        t.setOnAction(e -> letter_handler(t));
        u.setOnAction(e -> letter_handler(u));
        v.setOnAction(e -> letter_handler(v));
        w.setOnAction(e -> letter_handler(w));
        x.setOnAction(e -> letter_handler(x));
        y.setOnAction(e -> letter_handler(y));
        z.setOnAction(e -> letter_handler(z));

        this.startBtn.setOnAction(e -> {primaryStage.setScene(sceneMap.get("categories"));
            this.category1 = clientConnection.category1;
            this.category2 = clientConnection.category2;
            this.category3 = clientConnection.category3;

            this.cat1.setText(category1);
            this.cat2.setText(category2);
            this.cat3.setText(category3);

			primaryStage.show();
        });

        this.pause3 = new PauseTransition(Duration.millis(100));
        this.pause3.setOnFinished(event -> {
            this.secretWord.append("*".repeat(clientConnection.word_length));
            this.secretWordDisplay.setText(String.valueOf(secretWord));
            this.secretWordDisplay.setFont(Font.font("book antiqua", FontWeight.BOLD, FontPosture.REGULAR, 100));

            this.letterNum.setText("This word has " + String.valueOf(clientConnection.word_length) + " letters.");
            this.letterNum.setFont(Font.font("book antiqua", FontPosture.REGULAR, 25));
        });
        
        this.pause4 = new PauseTransition(Duration.seconds(3));
        this.pause4.setOnFinished(event -> {
        	javafx.application.Platform.exit();
        });
        
        this.cat1.setOnAction(e -> {primaryStage.setScene(sceneMap.get("guess"));
            clientConnection.word_length = 0;
            clientConnection.send("category " + cat1.getText());
            this.secretWord.setLength(0);

            //empty while loop used to pause the program to allow time for client to receive length from server
            while(clientConnection.word_length == 0) {}
            
            pause3.play();
            
			primaryStage.show();
        });
        
        this.cat2.setOnAction(e -> {primaryStage.setScene(sceneMap.get("guess"));
            clientConnection.send("category " + cat2.getText());
            this.secretWord.setLength(0);

            //empty while loop used to pause the program to allow time for client to receive length from server
            while(clientConnection.word_length == 0) {}

            pause3.play();
            
			primaryStage.show();
        });
        
        this.cat3.setOnAction(e-> {primaryStage.setScene(sceneMap.get("guess"));
            clientConnection.send("category " + cat3.getText());
            this.secretWord.setLength(0);

            //empty while loop used to pause the program to allow time for client to receive length from server
            while (clientConnection.word_length == 0) {}

            pause3.play();
            
			primaryStage.show();
        });

        this.clientChoice.setOnAction(e-> {primaryStage.setScene(sceneMap.get("start"));
            primaryStage.setTitle("ClientGUI");
            String port = this.port_prompt.getText();

            clientConnection = new Client(data -> {
                Platform.runLater(() -> {
                    listItems2.getItems().add(data.toString());
                });
            }, port);

            clientConnection.start();
        });

        this.clientBtn = new HBox(clientChoice);
        clientBtn.setAlignment(Pos.CENTER);
        this.buttonBox = new VBox(clientBtn, port_prompt);
        startPane = new BorderPane();

		startPane.setPadding(new Insets(200));
		startPane.setCenter(buttonBox);

        startPane.setStyle("-fx-background-color: lightBlue;");

        startScene = new Scene(startPane, 900,600);

        listItems = new ListView<>();
        listItems2 = new ListView<>();

        sceneMap = new HashMap<>();

        sceneMap.put("start",  createStartScene());
        sceneMap.put("categories",  createCategoryScene());
        sceneMap.put("guess",  createGuessScene());

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        this.pause2 = new PauseTransition(Duration.seconds(2));
        pause2.setOnFinished(event -> {
        	primaryStage.setScene(sceneMap.get("categories"));
            primaryStage.show();

            this.remainingGuesses.setText("Guesses remaining: 6");
            this.stats.setText(" ");

            a.setDisable(false);
            b.setDisable(false);
            c.setDisable(false);
            d.setDisable(false);
            e.setDisable(false);
            f.setDisable(false);
            g.setDisable(false);
            h.setDisable(false);
            i.setDisable(false);
            j.setDisable(false);
            k.setDisable(false);
            l.setDisable(false);
            m.setDisable(false);
            n.setDisable(false);
            o.setDisable(false);
            p.setDisable(false);
            q.setDisable(false);
            r.setDisable(false);
            s.setDisable(false);
            t.setDisable(false);
            u.setDisable(false);
            v.setDisable(false);
            w.setDisable(false);
            x.setDisable(false);
            y.setDisable(false);
            z.setDisable(false);
        });

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public void letter_handler(Button button) {
        clientConnection.send(button.getText());
        button.setDisable(true);

        PauseTransition pause = new PauseTransition(Duration.millis(100));
        pause.setOnFinished(event -> {
            if(clientConnection.win){
	            a.setDisable(true);
	            b.setDisable(true);
	            c.setDisable(true);
	            d.setDisable(true);
	            e.setDisable(true);
	            f.setDisable(true);
	            g.setDisable(true);
	            h.setDisable(true);
	            i.setDisable(true);
	            j.setDisable(true);
	            k.setDisable(true);
	            l.setDisable(true);
	            m.setDisable(true);
	            n.setDisable(true);
	            o.setDisable(true);
	            p.setDisable(true);
	            q.setDisable(true);
	            r.setDisable(true);
	            s.setDisable(true);
	            t.setDisable(true);
	            u.setDisable(true);
	            v.setDisable(true);
	            w.setDisable(true);
	            x.setDisable(true);
	            y.setDisable(true);
	            z.setDisable(true);
            	
            	if(clientConnection.won) {
            		stats.setText("You won the game! Congratulations!");
        			stats.setFont(Font.font("book antiqua", FontWeight.BOLD, FontPosture.REGULAR, 50));
        			pause4.play();
            	}
            	else {
            		stats.setText(win.getText());
            		pause2.play();
            	}
            }
            else if (clientConnection.loss){
                a.setDisable(true);
                b.setDisable(true);
                c.setDisable(true);
                d.setDisable(true);
                e.setDisable(true);
                f.setDisable(true);
                g.setDisable(true);
                h.setDisable(true);
                i.setDisable(true);
                j.setDisable(true);
                k.setDisable(true);
                l.setDisable(true);
                m.setDisable(true);
                n.setDisable(true);
                o.setDisable(true);
                p.setDisable(true);
                q.setDisable(true);
                r.setDisable(true);
                s.setDisable(true);
                t.setDisable(true);
                u.setDisable(true);
                v.setDisable(true);
                w.setDisable(true);
                x.setDisable(true);
                y.setDisable(true);
                z.setDisable(true);
                
            	if(clientConnection.lost){
            		stats.setText("You lost the game! Better luck next time...");
            		stats.setFont(Font.font("book antiqua", FontWeight.BOLD, FontPosture.REGULAR, 50));
            		pause4.play();
            	}
            	else{
                    stats.setText(loss.getText());
                    pause2.play();
            	}
            }
            else if(!clientConnection.is_correct){
            	stats.setText(incorrectGuess.getText());
            }
            else{
	            stats.setText(" ");
	            this.secretWord.setCharAt(clientConnection.index, button.getText().charAt(0));
	            this.secretWordDisplay.setText(String.valueOf(secretWord));
	            this.secretWordDisplay.setFont(Font.font("book antiqua", FontWeight.BOLD, FontPosture.REGULAR, 100));
            }

            remainingGuesses.setText("Guesses remaining: " + clientConnection.remaining_guesses);
        });
        pause.play();
    }

    public Scene createStartScene() {
		BorderPane root = new BorderPane();

		root.setCenter(gameTitle);
		root.setBottom(startBtn);
		
		BorderPane.setMargin(startBtn, new Insets(10, 325, 50, 325));

		root.setStyle("-fx-background-color: lightBlue;");

		return new Scene(root, 900, 600);
    }
    
    public Scene createCategoryScene() {
    	BorderPane root = new BorderPane();
    	VBox vb = new VBox(cat1, cat2, cat3);
    	vb.setAlignment(Pos.CENTER);

    	root.setTop(chooseCategory);
    	root.setAlignment(chooseCategory, Pos.CENTER);
    	root.setCenter(vb);

    	root.setStyle("-fx-background-color: lightBlue;");
    	return new Scene(root, 900, 600);
    }
    
    public Scene createGuessScene() {
    	BorderPane root = new BorderPane();
   
        letterBox1 = new HBox(a, b, c, d, e, f, g, h, i, j);
        letterBox1.setStyle("-fx-background-color: lightBlue");
        letterBox1.setAlignment(Pos.CENTER);
        
        letterBox2 = new HBox(k, l, m, n, o, p, q, r, s);
        letterBox2.setStyle("-fx-background-color: lightBlue");
        letterBox2.setAlignment(Pos.CENTER);
        
        letterBox3 = new HBox(s, t, u, v, w, x, y, z);
        letterBox3.setStyle("-fx-background-color: lightBlue");
        letterBox3.setAlignment(Pos.CENTER);
        
        statsBox = new HBox(stats);
        statsBox.setStyle("-fx-background-color: lightBlue");
        statsBox.setAlignment(Pos.CENTER);
        
        VBox vb = new VBox(statsBox, letterBox1, letterBox2, letterBox3);
        VBox vb2 = new VBox (letterNum, guessPrompt, remainingGuesses);

        root.setTop(vb2);
        vb2.setAlignment(Pos.CENTER);
        root.setCenter(secretWordDisplay);
        root.setBottom(vb);

        root.setStyle("-fx-background-color: lightBlue;");
        return new Scene(root, 900, 600);
    }
}




//PSEUDOCODE TO FILL IN_________________________________________________________________________________
//DISABLE CATEGORY BUTTONS WHEN WORD FROM THAT CATEGORY IS GUESSED:
//if(category.guessed == "true"){
//	if(cat1.getText() == category){
//		cat1.setDisable(true);
//	}
//	else if(cat2.getText() == category){
//		cat2.setDisable(true);
//	}
//	else if(cat3.getText() == category){
//		cat3.setDisable(true);
//	}
//}