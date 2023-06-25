/*package br.seguradora.test;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Mermaid extends Application {
    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Carregar página HTML que contém o código Mermaid e o script Mermaid.js
        webEngine.loadContent(
            "<html>\n" +
            "<head>\n" +
            "    <script src=\"https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js\"></script>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"mermaid\">\n" +
            "        graph LR\n" +
            "        A-->B\n" +
            "        B-->C\n" +
            "        C-->D\n" +
            "        D-->E\n" +
            "    </div>\n" +
            "    <script>\n" +
            "        mermaid.initialize({ startOnLoad: true });\n" +
            "        mermaid.init(undefined, document.getElementsByClassName('mermaid'));\n" +
            "    </script>\n" +
            "</body>\n" +
            "</html>"
        );

        // Esperar até que o carregamento da página seja concluído
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                // Obter o objeto JavaScript global
                JSObject window = (JSObject) webEngine.executeScript("window");
                // Adicionar uma interface Java/JavaScript para comunicação
                window.setMember("javaApp", this);
                // Executar um comando JavaScript para notificar a interface Java/JavaScript
                webEngine.executeScript("javaApp.onPageLoaded()");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(webView);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método chamado pelo JavaScript para notificar a interface Java/JavaScript
    public void onPageLoaded() {
        System.out.println("Página carregada!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/