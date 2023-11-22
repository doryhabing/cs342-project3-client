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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
impot java.lang.StringBuilder;

public class ClientGUI extends Application{
    TextField port_prompt;
    Text gameTitle, chooseCategory, guessPrompt;
    Button clientChoice, startBtn, a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, cat1, cat2, cat3;
    HashMap<String, Scene> sceneMap;
    HBox letterBox1, letterBox2, letterBox3;
    VBox buttonBox;
    Scene startScene;
    BorderPane startPane;
    Client clientConnection;
    StringBuilder secretWord;
    ListView<String> listItems, listItems2;

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
        
        this.cat1 = new Button("category 1");
        this.cat1.setPrefSize(250, 100);
        this.cat2 = new Button("category 2");
        this.cat2.setPrefSize(250, 100);
        this.cat3 = new Button("category 3");
        this.cat3.setPrefSize(250, 100);
        
        this.port_prompt = new TextField();
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
        
        this.startBtn.setOnAction(e-> {primaryStage.setScene(sceneMap.get("categories"));
			primaryStage.show();
        });
        
        this.cat1.setOnAction(e-> {primaryStage.setScene(sceneMap.get("guess"));
        	clientConnection.send(cat1.getText());
			primaryStage.show();
        });
        
        this.cat2.setOnAction(e-> {primaryStage.setScene(sceneMap.get("guess"));
        	clientConnection.send(cat2.getText());
			primaryStage.show();
        });
        
        this.cat3.setOnAction(e-> {primaryStage.setScene(sceneMap.get("guess"));
        	clientConnection.send(cat3.getText());
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

        this.buttonBox = new VBox(port_prompt, clientChoice);
        port_prompt.setAlignment(Pos.CENTER);
        startPane = new BorderPane();
        startPane.setCenter(buttonBox);
        startPane.setStyle("-fx-background-color: lightBlue;");

        startScene = new Scene(startPane, 900,600);

        listItems = new ListView<>();
        listItems2 = new ListView<>();

        sceneMap = new HashMap<>();

        sceneMap = new HashMap<String, Scene>();

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

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public void letter_handler(Button button) {
        clientConnection.send(button.getText());
        button.setDisable(true);
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
        
        VBox vb = new VBox(letterBox1, letterBox2, letterBox3);
        
        root.setTop(guessPrompt);
        root.setAlignment(guessPrompt, Pos.CENTER);
        root.setBottom(vb);

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

        root.setStyle("-fx-background-color: lightBlue;");
        return new Scene(root, 900, 600);
    }
}
