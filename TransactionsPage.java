package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;



public class TransactionsPage extends Application {
	
	 
	public void start(Stage primaryStage) {
		
			 Pane root = new Pane();
			
			 	// Labels
			 	Label Title = new Label("Admin Services");
			 	Title.setStyle("-fx-font-size: 48px;"); 
			 	Title.setLayoutX(800);
				Title.setLayoutY(75);
				
				Label TrasactionTitle = new Label ("Transactions");
				TrasactionTitle.setStyle("-fx-font-size: 48px;"); 
				TrasactionTitle.setLayoutX(250);
				TrasactionTitle.setLayoutY(275);
				// Buttons
				Button ATransactions = new Button("Transactions");
				ATransactions.setStyle("-fx-font-size: 24px;"); 
				ATransactions.setLayoutX(200);
				ATransactions.setLayoutY(200);
				ATransactions.setPrefWidth(300);
				
				Button AProfiles = new Button("Profiles");
				AProfiles.setStyle("-fx-font-size: 24px;"); 
				AProfiles.setLayoutX(600);
				AProfiles.setLayoutY(200);
				AProfiles.setPrefWidth(300);
				
				Button Statistics = new Button("Statistics");
				Statistics.setStyle("-fx-font-size: 24px;"); 
				Statistics.setLayoutX(1000);
				Statistics.setLayoutY(200);
				Statistics.setPrefWidth(300);
				
				Button ASysConfig = new Button("System Configurations");
				ASysConfig.setStyle("-fx-font-size: 24px;"); 
				ASysConfig.setLayoutX(1400);
				ASysConfig.setLayoutY(200);
				ASysConfig.setPrefWidth(300);
				
				 // Create a Rectangle
				Rectangle rectangle = new Rectangle(1400, 500); 
	            rectangle.setLayoutX(300);
	            rectangle.setLayoutY(400);      
	            rectangle.setFill(Color.WHITE); 
	            rectangle.setStroke(Color.BLACK);
	            
	            String filePath = "C:\\Users\\ramen\\eclipse-workspace\\website\\src\\application\\test.txt"; 
	            ArrayList<information> transactions = readTransactionsFromFile(filePath);
	            int yPosition = 300; 
	            for (information info : transactions) {
	                Rectangle rect = new Rectangle(1400, 140); 
	                rect.setLayoutX(300);  
	                rect.setLayoutY(yPosition); 
	                rect.setFill(Color.LIGHTBLUE); 

	               
	                System.out.println("Transaction: " + info.getTransactionNumber() +
	                        " | Price: $" + info.getPrice() + " | Status: " + info.getStatus());

	                root.getChildren().add(rect); 
	                yPosition += 120; 
	            }
			
			 
		        root.getChildren().add(Title);
		        root.getChildren().add(ATransactions);
		        root.getChildren().add(AProfiles);
		        root.getChildren().add(Statistics);
		        root.getChildren().add(ASysConfig);
		        root.getChildren().add(TrasactionTitle);
		        root.getChildren().add(rectangle);
		 


			 	Scene scene = new Scene(root, 1920, 1000); 
		      
		        primaryStage.setTitle("Admin Services");
		        primaryStage.setScene(scene);
		  
				primaryStage.show();
				}
	
	private ArrayList<information> readTransactionsFromFile(String filePath) {
        ArrayList<information> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
               
                String[] parts = line.split(",");

                for (String part : parts) {
                    
                    String[] transactionDetails = part.split("-");

                    
                    int transactionNumber = Integer.parseInt(transactionDetails[0]);
                    String image = transactionDetails[1];
                    String title = transactionDetails[2];
                    String purchasedBy = transactionDetails[3];
                    String purchasedFrom = transactionDetails[4];
                    double price = Double.parseDouble(transactionDetails[5]);
                    String status = transactionDetails[6];

                  
                    information info = new information(transactionNumber, image, title, purchasedBy, purchasedFrom, price, status);
                    transactions.add(info);  
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }

		
public static void main(String[] args) {
	launch(args);
}
}

