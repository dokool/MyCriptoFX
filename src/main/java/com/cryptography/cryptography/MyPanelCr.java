package com.cryptography.cryptography;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MyPanelCr {
    public BorderPane border;

    // Иницилизация поле border в конструкторе класса (MainPanelCrypto)
    public MyPanelCr() {
        border = new BorderPane();
        border.setLeft(addVBox());
    }


    private VBox addVBox() {
        // Тут создаются кодом UI элементы
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(10);

        // Просто устанавливает значения для пользователя
        Text text = new Text("Введите текст");
        Text text1 = new Text("Хеш вашего текста. В таком виде, например, пароль может хранить в б.д.");
        Text text2 = new Text("Код для атентификации Вашего текста. Позволяет проверить его подлинность (нез");
        Text text3 = new Text("Секретный ключ. Ваш зашифрованный/расшифрованный текст");
        Text text4 = new Text("Открытый/закрытый ключ. Ваш зашифрованный/расшифрованный текст");
        Text text5 = new Text("Электронная подпись, сигнатура подписи и её аутентификация");

        // Устанавливает можно или нельзя вписывать данные в TextArea
        TextArea textInput = getTextArea(true);
        TextArea textOutput1 = getTextArea(false);
        TextArea textOutput2 = getTextArea(false);
        TextArea textOutput3 = getTextArea(false);
        TextArea textOutput4 = getTextArea(false);
        TextArea textOutput5 = getTextArea(false);
        Button btn = new Button("Get Cripto");
        btn.setPrefSize(150, 20);

        vbox.getChildren().addAll(text, textInput,
                text1, textOutput1,
                text2, textOutput2,
                text3, textOutput3,
                text4, textOutput4,
                text5, textOutput5, btn);

        // Создается событие нажалие клика кнопки
        btn.setOnAction((ActionEvent Event) -> {
            // Проверка на пустоту ввода
            if (!textInput.getText().isEmpty()) {
                // Обработчик ошибок
                try {
                    // Просто вызывает методы шифрования (5 методов) и текс передается в аргумент этих методов для шифрования
                    textOutput1.setText(MyCrypto.getHashString(textInput.getText()));
                    textOutput2.setText(MyCrypto.getMacString(textInput.getText()));
                    textOutput3.setText(MyCrypto.getCipherString(textInput.getText()));
                    textOutput4.setText(MyCrypto.getPairCipherString(textInput.getText()));
                    textOutput5.setText(MyCrypto.getElectSigmatString(textInput.getText()));
                } catch (Exception ex) { //Если ошибка поймана
                    // Вывод ее в консоль
                    System.err.println(MyCrypto.class.getName());
                }
            }
        });
        // Возвращает все элементы UI
        return vbox;
    }

    // Получает текс с UI элемента и параментр isEditable метода делает так чтобы нельзя или можно было изменять данные в TextArea
    private TextArea getTextArea(boolean isEditable) {
        TextArea textOutput = new TextArea();
        textOutput.setPrefColumnCount(50);
        textOutput.setPrefRowCount(2);
        textOutput.setEditable(isEditable);
        return textOutput;
    }
}


