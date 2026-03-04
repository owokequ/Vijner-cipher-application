import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*  Vbox - все элементы идут друг под другом
    Hbox - все элементы друг за другом


*/ 

public class TestFX extends Application {
    @Override
    public void start(Stage stage) {

        Label label = new Label("Выберите 1 из вариантов"); // создает надпись
        TextField textEncrypt = new TextField();
        textEncrypt.setPromptText("Тут появится текст шифровки/расшифровки");
        // Стили для поля результата
        textEncrypt.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #3498db; -fx-border-radius: 5; -fx-background-radius: 5;");

        TestFX main = new TestFX();

        label.setStyle("-fx-font-family: 'Noto-sans'; -fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 2, 2);");
        
        Button encryption = new Button("Шифровка"); // создает кнопку 
        Button decryption = new Button("Расшифровка"); // создает кнопку 
        Button decipherment = new Button("Дешифровка"); // создает кнопку 
        
        // Стили для кнопок
        String buttonStyle = "-fx-background-color: linear-gradient(to bottom, #3498db, #2980b9); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 1, 1);";
        String buttonHoverStyle = "-fx-background-color: linear-gradient(to bottom, #3cb0fd, #3498db); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 2, 2);";
        
        encryption.setStyle(buttonStyle);
        decryption.setStyle(buttonStyle);
        decipherment.setStyle(buttonStyle);
        
        // Эффекты при наведении
        encryption.setOnMouseEntered(e -> encryption.setStyle(buttonHoverStyle));
        encryption.setOnMouseExited(e -> encryption.setStyle(buttonStyle));
        decryption.setOnMouseEntered(e -> decryption.setStyle(buttonHoverStyle));
        decryption.setOnMouseExited(e -> decryption.setStyle(buttonStyle));
        decipherment.setOnMouseEntered(e -> decipherment.setStyle(buttonHoverStyle));
        decipherment.setOnMouseExited(e -> decipherment.setStyle(buttonStyle));

        TextField textForEncr = new TextField();
        textForEncr.setPromptText("Введите текст для шифровки/расшифровки/дешифровки...");
        // Стили для поля ввода текста
        textForEncr.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-background-radius: 5; -fx-prompt-text-fill: #95a5a6;");
        // Эффект при фокусе
        textForEncr.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                textForEncr.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #3498db; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-prompt-text-fill: #95a5a6;");
            } else {
                textForEncr.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-background-radius: 5; -fx-prompt-text-fill: #95a5a6;");
            }
        });

        TextField textKey = new TextField();
        textKey.setPromptText("Введите ключ...");
        // Стили для поля ввода ключа
        textKey.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-background-radius: 5; -fx-prompt-text-fill: #95a5a6;");
        // Эффект при фокусе
        textKey.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                textKey.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #3498db; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-prompt-text-fill: #95a5a6;");
            } else {
                textKey.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #bdc3c7; -fx-border-radius: 5; -fx-background-radius: 5; -fx-prompt-text-fill: #95a5a6;");
            }
        });

        encryption.setOnAction(event -> {
            textEncrypt.setText("");
            String text = textForEncr.getText();
            String key = textKey.getText();
            System.out.println(text);
            System.out.println(key);

            StringBuilder res = main.Vijner(text, key);
            
            // Анимация при появлении результата
            textEncrypt.setStyle("-fx-background-color: #e8f5e9; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #27ae60; -fx-border-radius: 5; -fx-background-radius: 5; -fx-effect: dropshadow(gaussian, #27ae60, 10, 0, 0, 0);");
            textEncrypt.setText(res.toString());
            
            // Возврат к обычному стилю через секунду
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(0.5));
            pause.setOnFinished(e -> textEncrypt.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #3498db; -fx-border-radius: 5; -fx-background-radius: 5;"));
            pause.play();
        }); //  этот метод вызывает лямбда функцию после нажатия

        decryption.setOnAction(event -> {
            textEncrypt.setText("");
            String text = textForEncr.getText();
            String key = textKey.getText();
            System.out.println(text);
            System.out.println(key);

            StringBuilder res = main.VijnerDecryption(text, key);
            
            // Анимация при появлении результата
            textEncrypt.setStyle("-fx-background-color: #e8f5e9; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #27ae60; -fx-border-radius: 5; -fx-background-radius: 5; -fx-effect: dropshadow(gaussian, #27ae60, 10, 0, 0, 0);");
            textEncrypt.setText(res.toString());
            
            // Возврат к обычному стилю через секунду
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(0.5));
            pause.setOnFinished(e -> textEncrypt.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #3498db; -fx-border-radius: 5; -fx-background-radius: 5;"));
            pause.play();
        });

        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(encryption, decryption, decipherment);
        // Стили для контейнера с кнопками
        hbox.setStyle("-fx-alignment: center; -fx-padding: 10;");

        VBox vbox = new VBox(10); // создаем VBox и задаем по 10 пикселей между элементами
        vbox.getChildren().addAll(label, hbox, textForEncr, textKey, textEncrypt);
        // Стили для главного контейнера
        vbox.setStyle("-fx-padding: 20; -fx-background-color: linear-gradient(to bottom, #ecf0f1, #bdc3c7); -fx-border-color: #34495e; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10;");

        //Создаем сцену и показываем
        Scene scene = new Scene(vbox, 500, 400); // увеличил размер, чтобы все влезло красиво
        stage.setTitle("Крипто-шифровальщик"); // добавил заголовок окна
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public StringBuilder Vijner(String encrypt, String key) {
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя ";
        StringBuilder keyAll = new StringBuilder();

        StringBuilder megaRes = new StringBuilder();
        for(int i = 0; i < encrypt.length(); i++){
            char keyChar = key.charAt(i % key.length());
            keyAll.append(keyChar);
        }

        for(int i = 0; i < encrypt.length(); i++){
            int index_encr = -1;
            int index_key = -1;
            for(int j = 0; j < alphabet.length(); j++){
                if(encrypt.charAt(i) == alphabet.charAt(j)){
                    index_encr = j;
                }
                if(keyAll.charAt(i) == alphabet.charAt(j)){
                    index_key = j;
                }
            }
            if(index_encr != -1 && index_key != -1){
                int resIndex = index_encr + index_key;


                if(resIndex >= alphabet.length()){
                    resIndex = resIndex - alphabet.length();

                }
                char char_word = alphabet.charAt(resIndex);
                megaRes.append(char_word);
            }
        }

        return megaRes;
    }

    public StringBuilder VijnerDecryption(String encrypt, String key) {
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя ";
        StringBuilder keyAll = new StringBuilder();

        StringBuilder megaRes = new StringBuilder();
        for(int i = 0; i < encrypt.length(); i++){
            char keyChar = key.charAt(i % key.length());
            keyAll.append(keyChar);
        }

        for(int i = 0; i < encrypt.length(); i++){
            int index_encr = -1;
            int index_key = -1;
            for(int j = 0; j < alphabet.length(); j++){
                if(encrypt.charAt(i) == alphabet.charAt(j)){
                    index_encr = j;
                }
                if(keyAll.charAt(i) == alphabet.charAt(j)){
                    index_key = j;
                }
            }
            if(index_encr != -1 && index_key != -1){
                int resIndex = index_encr - index_key;


                if(resIndex < 0){
                    resIndex = resIndex + alphabet.length();

                }
                char char_word = alphabet.charAt(resIndex);
                megaRes.append(char_word);
            }
        }

        return megaRes;
    }
}