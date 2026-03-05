import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*  Vbox - все элементы идут друг под другом
    Hbox - все элементы друг за другом
*/ 

public class Main extends Application {
    private Logic logic = new Logic();
    
    @Override
    public void start(Stage stage) {
        // Создаём панель с вкладками
        TabPane tabPane = new TabPane();
        
        // Вкладка 1: Шифровка
        Tab encryptTab = new Tab("Шифровка");
        Tab decryptTab = new Tab("Расшифровка");

        encryptTab.setClosable(false); // нельзя закрыть
        decryptTab.setClosable(false);        
        // Содержимое вкладки
        VBox encryptContent = new VBox(10);
        encryptContent.setStyle("-fx-padding: 20; -fx-background-color: #ecf0f1;");
        
        VBox dencryptContent = new VBox(10);
        dencryptContent.setStyle("-fx-padding: 20; -fx-background-color: #ecf0f1;");

        // Заголовок
        Label titleLabel1 = new Label("Щифровка шифром Виженера");
        titleLabel1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        Label titleLabel2 = new Label("Расшифровка шифра Виженера");
        titleLabel2.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        
        // Поле для ввода текста
        TextField inputText1 = new TextField();
        inputText1.setPromptText("Введите текст для шифровки...");
        inputText1.setStyle("-fx-padding: 10; -fx-font-size: 14px;");
        
        TextField inputText2 = new TextField();
        inputText2.setPromptText("Введите текст для расшифровки...");
        inputText2.setStyle("-fx-padding: 10; -fx-font-size: 14px;");
        // Поле для ключа
        TextField keyText1 = new TextField();
        keyText1.setPromptText("Введите ключ...");
        keyText1.setStyle("-fx-padding: 10; -fx-font-size: 14px;");
                
        TextField keyText2 = new TextField();
        keyText2.setPromptText("Введите ключ...");
        keyText2.setStyle("-fx-padding: 10; -fx-font-size: 14px;");
        
        // Поле для результата
        TextField resultText1 = new TextField();
        resultText1.setPromptText("Результат шифровки...");
        resultText1.setEditable(false);
        resultText1.setStyle("-fx-padding: 10; -fx-font-size: 14px; -fx-background-color: #f5f5f5;");

        TextField resultText2 = new TextField();
        resultText2.setPromptText("Результат расшифровки...");
        resultText2.setEditable(false);
        resultText2.setStyle("-fx-padding: 10; -fx-font-size: 14px; -fx-background-color: #f5f5f5;");

        
        // Кнопка шифровки
        Button encryptBtn = new Button("Зашифровать");
        encryptBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");
        
        Button decryptBtn = new Button("Расшифровать");
        decryptBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");
        // Эффект при наведении
        encryptBtn.setOnMouseEntered(e -> 
            encryptBtn.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;"));
        encryptBtn.setOnMouseExited(e -> 
            encryptBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;"));
        
        // Логика шифровки
        encryptBtn.setOnAction(event -> {
            resultText1.setText("");
            String text = inputText1.getText().toLowerCase();
            String key = keyText1.getText().toLowerCase();

            StringBuilder res = logic.Vijner(text, key);
            
            // Анимация при появлении результата
            resultText1.setStyle("-fx-background-color: #e8f5e9; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #27ae60; -fx-border-radius: 5; -fx-background-radius: 5; -fx-effect: dropshadow(gaussian, #27ae60, 10, 0, 0, 0);");
            resultText1.setText(res.toString());
            
            // Возврат к обычному стилю через секунду
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(0.5));
            pause.setOnFinished(e -> resultText1.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #3498db; -fx-border-radius: 5; -fx-background-radius: 5;"));
            pause.play();
        }); //  этот метод вызывает лямбда функцию после нажатия
        
        decryptBtn.setOnAction(event -> {
            resultText2.setText("");
            String text = inputText2.getText().toLowerCase();
            String key = keyText2.getText().toLowerCase();

            StringBuilder res = logic.VijnerDecryption(text, key);
            
            // Анимация при появлении результата
            resultText2.setStyle("-fx-background-color: #e8f5e9; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #27ae60; -fx-border-radius: 5; -fx-background-radius: 5; -fx-effect: dropshadow(gaussian, #27ae60, 10, 0, 0, 0);");
            resultText2.setText(res.toString());
            
            // Возврат к обычному стилю через секунду
            javafx.animation.PauseTransition pause = new javafx.animation.PauseTransition(javafx.util.Duration.seconds(0.5));
            pause.setOnFinished(e -> resultText2.setStyle("-fx-background-color: #f5f5f5; -fx-text-fill: #2c3e50; -fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #3498db; -fx-border-radius: 5; -fx-background-radius: 5;"));
            pause.play();
        });

        // Кнопка очистки
        Button clearBtn1 = new Button("Очистить");
        clearBtn1.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;");
        clearBtn1.setOnAction(e -> {
            inputText1.clear();
            keyText1.clear();
            resultText1.clear();
        });
        
        clearBtn1.setOnMouseEntered(e -> 
            clearBtn1.setStyle("-fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;"));
        clearBtn1.setOnMouseExited(e -> 
            clearBtn1.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;"));

        Button clearBtn2 = new Button("Очистить");
        clearBtn2.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;");
        clearBtn2.setOnAction(e -> {
            inputText2.clear();
            keyText2.clear();
            resultText2.clear();
        });
        
        clearBtn2.setOnMouseEntered(e -> 
            clearBtn2.setStyle("-fx-background-color: #7f8c8d; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;"));
        clearBtn2.setOnMouseExited(e -> 
            clearBtn2.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;"));
        
        // Контейнер для кнопок
        HBox buttonBox1 = new HBox(10, encryptBtn, clearBtn1);
        buttonBox1.setStyle("-fx-alignment: center;");
        

        HBox buttonBox2 = new HBox(10, decryptBtn, clearBtn2);
        buttonBox2.setStyle("-fx-alignment: center;");
        // Собираем всё вместе
        encryptContent.getChildren().addAll(
            titleLabel1, 
            new Label("Исходный текст:"), inputText1,
            new Label("Ключ:"), keyText1,
            buttonBox1,
            new Label("Результат:"), resultText1
        );

        dencryptContent.getChildren().addAll(
            titleLabel2, 
            new Label("Исходный текст:"), inputText2,
            new Label("Ключ:"), keyText2,
            buttonBox2,
            new Label("Результат:"), resultText2
        );
        
        encryptTab.setContent(encryptContent);
        decryptTab.setContent(dencryptContent);
        
        // Добавляем вкладку в панель
        tabPane.getTabs().addAll(encryptTab, decryptTab);
        
        // Создаём сцену
        Scene scene = new Scene(tabPane, 500, 500);
        stage.setTitle("Шифр Виженера");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}



