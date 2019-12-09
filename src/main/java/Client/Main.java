package Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


    public class Main extends Application {
//        Controller c;

        @Override
        public void start(Stage primaryStage) throws Exception{
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream("sample.fxml"));
            primaryStage.setTitle("Chat");
//            c = loader.getController();
            primaryStage.setScene(new Scene(root, 350, 375));
            primaryStage.getIcons().add(new Image("Lesson_6/Chat/client/resourses/icon.png"));
            primaryStage.show();

//            primaryStage.setOnCloseRequest(event -> {
//                c.Dispose();
//                Platform.exit();
//                System.exit(0);
//            });
//        }


//        public static void main(String[] args) {
//            launch(args);
//        }
    }

}
