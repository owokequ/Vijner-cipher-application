import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.function.UnaryOperator;

public class Main extends Application {
    
    // Валидация: только русские буквы, пробелы и перевод строки в нижний регистр
    private UnaryOperator<TextFormatter.Change> russianTextFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("[а-яё\\s\\n]*")) {
            return change;
        }
        return null;
    };
    
    // Валидация: только русские буквы для ключа (без пробелов)
    private UnaryOperator<TextFormatter.Change> russianKeyFilter = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("[а-яё]*")) {
            return change;
        }
        return null;
    };
    
    @Override
    public void start(Stage stage) {
        // Общий стиль для всего приложения
        String globalStyle = "-fx-font-family: 'Segoe UI', 'Noto Sans', 'System';";
        
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: #2c3e50; -fx-tab-min-width: 100px; -fx-tab-max-width: 120px;");
        showWelcomeDialog(stage);
        // ========== Вкладка 1: Шифровка ==========
        Tab encryptTab = new Tab("🔐 Шифровка");
        encryptTab.setClosable(false);
        
        VBox encryptContent = new VBox(15);
        encryptContent.setStyle("-fx-padding: 25; -fx-background-color: linear-gradient(to bottom, #ecf0f1, #d5dbdb); -fx-border-radius: 10;");
        encryptContent.setEffect(new DropShadow(10, Color.GRAY));
        
        Label titleLabel1 = new Label("🔐 Шифровка шифром Виженера");
        titleLabel1.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        titleLabel1.setStyle("-fx-text-fill: #2c3e50; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 2, 2);");
        
        // Поле ввода текста с валидацией
        TextField inputText1 = new TextField();
        inputText1.setPromptText("Введите текст для шифровки (только русские буквы)...");
        inputText1.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bdc3c7;");
        inputText1.setTextFormatter(new TextFormatter<>(russianTextFilter));
        
        // Поле для ключа с валидацией
        TextField keyText1 = new TextField();
        keyText1.setPromptText("Введите ключ (только русские буквы, без пробелов)...");
        keyText1.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bdc3c7;");
        keyText1.setTextFormatter(new TextFormatter<>(russianKeyFilter));
        
        // Результат
        TextField resultText1 = new TextField();
        resultText1.setPromptText("Результат шифровки...");
        resultText1.setEditable(false);
        resultText1.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #fef9e6; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #f39c12;");
        
        // Кнопки
        Button encryptBtn = new Button("🔒 Зашифровать");
        encryptBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3498db, #2980b9); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;");
        encryptBtn.setEffect(new DropShadow(5, Color.rgb(0,0,0,0.3)));
        
        encryptBtn.setOnMouseEntered(e -> encryptBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3cb0fd, #3498db); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;"));
        encryptBtn.setOnMouseExited(e -> encryptBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3498db, #2980b9); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;"));
        
        encryptBtn.setOnAction(event -> {
            String text = inputText1.getText().toLowerCase();
            String key = keyText1.getText().toLowerCase();
            
            if (text.isEmpty()) {
                showError(resultText1, "❌ Введите текст для шифровки!");
                return;
            }
            if (key.isEmpty()) {
                showError(resultText1, "❌ Введите ключ!");
                return;
            }
            
            StringBuilder res = Logic.Vijner(text, key);
            showSuccess(resultText1, res.toString());
        });
        
        Button clearBtn1 = new Button("🗑️ Очистить");
        clearBtn1.setStyle("-fx-background-color: linear-gradient(to bottom, #95a5a6, #7f8c8d); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;");
        clearBtn1.setOnAction(e -> {
            inputText1.clear();
            keyText1.clear();
            resultText1.clear();
            resultText1.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #fef9e6; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #f39c12;");
        });
        
        HBox buttonBox1 = new HBox(20, encryptBtn, clearBtn1);
        buttonBox1.setStyle("-fx-alignment: center;");
        
        encryptContent.getChildren().addAll(
            titleLabel1, 
            new Label("📝 Исходный текст:"), inputText1,
            new Label("🔑 Ключ:"), keyText1,
            buttonBox1,
            new Label("✨ Результат:"), resultText1
        );
        encryptTab.setContent(encryptContent);
        
        // ========== Вкладка 2: Расшифровка ==========
        Tab decryptTab = new Tab("🔓 Расшифровка");
        decryptTab.setClosable(false);
        
        VBox decryptContent = new VBox(15);
        decryptContent.setStyle("-fx-padding: 25; -fx-background-color: linear-gradient(to bottom, #ecf0f1, #d5dbdb); -fx-border-radius: 10;");
        decryptContent.setEffect(new DropShadow(10, Color.GRAY));
        
        Label titleLabel2 = new Label("🔓 Расшифровка шифра Виженера");
        titleLabel2.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        titleLabel2.setStyle("-fx-text-fill: #2c3e50; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 2, 2);");
        
        TextField inputText2 = new TextField();
        inputText2.setPromptText("Введите зашифрованный текст...");
        inputText2.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bdc3c7;");
        inputText2.setTextFormatter(new TextFormatter<>(russianTextFilter));
        
        TextField keyText2 = new TextField();
        keyText2.setPromptText("Введите ключ...");
        keyText2.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bdc3c7;");
        keyText2.setTextFormatter(new TextFormatter<>(russianKeyFilter));
        
        TextField resultText2 = new TextField();
        resultText2.setPromptText("Результат расшифровки...");
        resultText2.setEditable(false);
        resultText2.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #e8f5e9; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #27ae60;");
        
        Button decryptBtn = new Button("🔓 Расшифровать");
        decryptBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #27ae60, #229954); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;");
        decryptBtn.setEffect(new DropShadow(5, Color.rgb(0,0,0,0.3)));
        
        decryptBtn.setOnMouseEntered(e -> decryptBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #2ecc71, #27ae60); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;"));
        decryptBtn.setOnMouseExited(e -> decryptBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #27ae60, #229954); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;"));
        
        decryptBtn.setOnAction(event -> {
            String text = inputText2.getText().toLowerCase();
            String key = keyText2.getText().toLowerCase();
            
            if (text.isEmpty() || key.isEmpty()) {
                showError(resultText2, "❌ Введите текст и ключ!");
                return;
            }
            
            StringBuilder res = Logic.dencrypt(text, key);
            showSuccess(resultText2, res.toString());
        });
        
        Button clearBtn2 = new Button("🗑️ Очистить");
        clearBtn2.setStyle("-fx-background-color: linear-gradient(to bottom, #95a5a6, #7f8c8d); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;");
        clearBtn2.setOnAction(e -> {
            inputText2.clear();
            keyText2.clear();
            resultText2.clear();
            resultText2.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #e8f5e9; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #27ae60;");
        });
        
        HBox buttonBox2 = new HBox(20, decryptBtn, clearBtn2);
        buttonBox2.setStyle("-fx-alignment: center;");
        
        decryptContent.getChildren().addAll(
            titleLabel2, 
            new Label("📝 Зашифрованный текст:"), inputText2,
            new Label("🔑 Ключ:"), keyText2,
            buttonBox2,
            new Label("✨ Результат:"), resultText2
        );
        decryptTab.setContent(decryptContent);
        
        // ========== Вкладка 3: Дешифровка (взлом) ==========
        Tab deshifrTab = new Tab("💀 Взлом");
        deshifrTab.setClosable(false);
        
        VBox deshifrContent = new VBox(15);
        deshifrContent.setStyle("-fx-padding: 25; -fx-background-color: linear-gradient(to bottom, #ecf0f1, #d5dbdb); -fx-border-radius: 10;");
        deshifrContent.setEffect(new DropShadow(10, Color.GRAY));
        
        Label titleLabel3 = new Label("💀 Дешифровка (взлом шифра)");
        titleLabel3.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));
        titleLabel3.setStyle("-fx-text-fill: #c0392b; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 2, 2);");
        
        TextField inputText3 = new TextField();
        inputText3.setPromptText("Введите зашифрованный текст для взлома...");
        inputText3.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #bdc3c7;");
        
        TextField resultKeyText = new TextField();
        resultKeyText.setPromptText("Подобранный ключ...");
        resultKeyText.setEditable(false);
        resultKeyText.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #fff3e0; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #e67e22;");
        
        TextField resultDecryptText = new TextField();
        resultDecryptText.setPromptText("Расшифрованный текст...");
        resultDecryptText.setEditable(false);
        resultDecryptText.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #e8f5e9; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #27ae60;");
        
        Button hackBtn = new Button("💀 Взломать");
        hackBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #e67e22, #d35400); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;");
        hackBtn.setEffect(new DropShadow(5, Color.rgb(0,0,0,0.3)));
        
        hackBtn.setOnMouseEntered(e -> hackBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #f39c12, #e67e22); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;"));
        hackBtn.setOnMouseExited(e -> hackBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #e67e22, #d35400); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;"));
        
        hackBtn.setOnAction(event -> {
            String encrypted = inputText3.getText().toLowerCase();
            
            if (encrypted.isEmpty()) {
                showError(resultKeyText, "❌ Введите текст для взлома!");
                return;
            }
            
            try {
                resultKeyText.setText("🔍 Анализируем...");
                resultDecryptText.setText("🔍 Подбираем ключ...");
                
                // Анализируем и подбираем ключ
                String guessedKey = Logic.deshifrator(encrypted, Logic.sdvig(encrypted));
                String decrypted = Logic.dencrypt(encrypted, guessedKey).toString();
                
                showSuccess(resultKeyText, guessedKey);
                showSuccess(resultDecryptText, decrypted);
            } catch (Exception ex) {
                showError(resultKeyText, "❌ Не удалось взломать текст!");
                resultDecryptText.setText("");
            }
        });
        
        Button clearBtn3 = new Button("🗑️ Очистить");
        clearBtn3.setStyle("-fx-background-color: linear-gradient(to bottom, #95a5a6, #7f8c8d); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 12 25; -fx-background-radius: 25; -fx-cursor: hand;");
        clearBtn3.setOnAction(e -> {
            inputText3.clear();
            resultKeyText.clear();
            resultDecryptText.clear();
            resultKeyText.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #fff3e0; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #e67e22;");
            resultDecryptText.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #e8f5e9; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #27ae60;");
        });
        
        HBox buttonBox3 = new HBox(20, hackBtn, clearBtn3);
        buttonBox3.setStyle("-fx-alignment: center;");
        
        deshifrContent.getChildren().addAll(
            titleLabel3, 
            new Label("📝 Зашифрованный текст:"), inputText3,
            buttonBox3,
            new Label("🔑 Найденный ключ:"), resultKeyText,
            new Label("✨ Расшифрованный текст:"), resultDecryptText
        );
        deshifrTab.setContent(deshifrContent);
        
        // Добавляем все вкладки
        tabPane.getTabs().addAll(encryptTab, decryptTab, deshifrTab);
        
        Scene scene = new Scene(tabPane, 650, 600);
        scene.getStylesheets().add("data:text/css," 
            + ".tab-pane .tab-header-area { -fx-padding: 10 0 0 0; }"
            + ".tab { -fx-background-color: #34495e; -fx-padding: 8 20; }"
            + ".tab:selected { -fx-background-color: #3498db; }"
            + ".tab .tab-label { -fx-text-fill: white; -fx-font-weight: bold; }"
        );
        
        stage.setTitle("🔐 Шифр Виженера — Криптографический инструмент");
        stage.setScene(scene);
        stage.show();
    }
    
    private void showError(TextField field, String message) {
        field.setText(message);
        field.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #ffe6e6; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #e74c3c; -fx-text-fill: #c0392b;");
        
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            if (field.getText().equals(message)) {
                field.clear();
                field.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #fef9e6; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #f39c12;");
            }
        });
        pause.play();
    }
    
    private void showSuccess(TextField field, String message) {
        field.setText(message);
        field.setStyle("-fx-padding: 12; -fx-font-size: 14px; -fx-background-color: #e8f5e9; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #27ae60; -fx-text-fill: #2c3e50;");
    }

    private void showWelcomeDialog(Stage parentStage) {
    // Создаём новое окно
    Stage dialog = new Stage();
    dialog.initOwner(parentStage);
    dialog.setTitle("📜 Добро пожаловать в Шифр Виженера");
    dialog.setResizable(false);
    
    // Основной контейнер
    VBox content = new VBox(15);
    content.setStyle("-fx-padding: 25; -fx-background-color: linear-gradient(to bottom, #2c3e50, #34495e); -fx-border-radius: 10;");
    content.setPrefWidth(500);
    
    // Заголовок
    Label titleLabel = new Label("🔐 Шифр Виженера");
    titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #f1c40f;");
    titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
    
    // Подзаголовок
    Label subtitleLabel = new Label("Инструмент для шифрования, расшифровки и криптоанализа");
    subtitleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #bdc3c7;");
    
    // Разделитель
    Label separator = new Label("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    separator.setStyle("-fx-text-fill: #f1c40f;");
    
    // Правила использования
    VBox rulesBox = new VBox(8);
    rulesBox.setStyle("-fx-padding: 15; -fx-background-color: rgba(0,0,0,0.3); -fx-background-radius: 8;");
    
    Label rulesTitle = new Label("📋 Правила использования:");
    rulesTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #f1c40f;");
    
    Label rule1 = new Label("🔤 1. Вводить можно только русские буквы (а-я, ё) и пробелы");
    rule1.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 13px;");
    
    Label rule2 = new Label("🔑 2. Ключ должен состоять только из русских букв (без пробелов)");
    rule2.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 13px;");
    
    Label rule3 = new Label("📏 3. Для взлома текст должен быть достаточно длинным (от 50 символов)");
    rule3.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 13px;");
    
    rulesBox.getChildren().addAll(rulesTitle, rule1, rule2, rule3);
    
    // Информация о версии
    VBox infoBox = new VBox(5);
    infoBox.setStyle("-fx-padding: 10; -fx-alignment: center;");
    
    Label authorLabel = new Label("Разработано для ТиМП Артемом Вершининым");
    authorLabel.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 11px;");
    
    infoBox.getChildren().addAll(authorLabel);
    
    // Кнопка "Понятно"
    Button okButton = new Button("✅ Понятно, начать работу");
    okButton.setStyle("-fx-background-color: linear-gradient(to bottom, #27ae60, #229954); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 25; -fx-cursor: hand;");
    okButton.setEffect(new DropShadow(5, Color.rgb(0,0,0,0.3)));
    
    okButton.setOnMouseEntered(e -> okButton.setStyle("-fx-background-color: linear-gradient(to bottom, #2ecc71, #27ae60); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 25; -fx-cursor: hand;"));
    okButton.setOnMouseExited(e -> okButton.setStyle("-fx-background-color: linear-gradient(to bottom, #27ae60, #229954); -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 25; -fx-cursor: hand;"));
    
    okButton.setOnAction(e -> dialog.close());
    
    // Кнопка "Не показывать больше" (опционально)
    CheckBox dontShowAgain = new CheckBox("Не показывать при следующем запуске");
    dontShowAgain.setStyle("-fx-text-fill: #bdc3c7; -fx-font-size: 11px;");
    
    // Собираем всё
    content.getChildren().addAll(
        titleLabel, 
        subtitleLabel, 
        separator,
        rulesBox,
        okButton,
        dontShowAgain,
        infoBox
    );
    
    Scene dialogScene = new Scene(content);
    dialog.setScene(dialogScene);
    
    // Загружаем сохранённую настройку
    java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userNodeForPackage(Main.class);
    boolean dontShow = prefs.getBoolean("dontShowWelcome", false);
    
    if (!dontShow) {
        dialog.showAndWait();
        
        // Сохраняем настройку
        dontShowAgain.setOnAction(e -> {
            prefs.putBoolean("dontShowWelcome", dontShowAgain.isSelected());
        });
    }
}   

    public static void main(String[] args) {
        launch();
    }
}