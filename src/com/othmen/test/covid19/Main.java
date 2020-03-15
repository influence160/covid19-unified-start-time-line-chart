package com.othmen.test.covid19;
	
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
	        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext("com.othmen.test.covid19");
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ApplicationWindow2.fxml"));
			loader.setControllerFactory(ctx::getBean);
			
			Parent root = loader.load();
			Scene scene = new Scene(root,1200,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
