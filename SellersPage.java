package application;
	

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.shape.Rectangle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.layout.BorderPane;


public class SellersPage extends Application {
/*	 private String getSelectedGenre(CheckBox SScience, CheckBox SComputer, CheckBox SMath, CheckBox SEnglish, CheckBox SOther) {
	        if (SScience.isSelected()) {
	            return "Natural Science";
	        } else if (SComputer.isSelected()) {
	            return "Computer";
	        } else if (SMath.isSelected()) {
	            return "Math";
	        } else if (SEnglish.isSelected()) {
	            return "English";
	        } else if (SOther.isSelected()) {
	            return "Other";
	        }
	        return "Unknown"; // Default if none selected
	    }
	 
	  private String getSelectedCondition(RadioButton SLikeNew, RadioButton SModerate, RadioButton SHeavily) {
	        if (SLikeNew.isSelected()) {
	            return "Like New";
	        } else if (SModerate.isSelected()) {
	            return "Moderately Used";
	        } else if (SHeavily.isSelected()) {
	            return "Heavily Used";
	        }
	        return "Unknown"; // Default if none selected
	    }
      */

	public void start(Stage primaryStage) {
		try {
			
			
			 Pane root = new Pane();
			 Rectangle rect = new Rectangle(1920, 200); 
             rect.setLayoutX(0);  
             rect.setLayoutY(0); 
             rect.setFill(Color.web("#f4f4d8")); 
             
             String filePath = "C:/Users/ramen/eclipse-workspace/website/src/application/logo.png"; // Adjust this path to your file location
             Image logo = new Image("file:" + filePath);
             
             // Display the image
             ImageView showLogo = new ImageView(logo);
             showLogo.setFitWidth(200);
             showLogo.setFitHeight(150);
             showLogo.setLayoutX(300);
             showLogo.setLayoutY(10);
             
             // Buttons 
             Button[] uploadButtons = new Button[4]; 
             ImageView[] imageViews = new ImageView[4];
             Button[] deleteButtons = new Button[4];
              
			 Button SBuyPage = new Button("Buy");
			SBuyPage.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;"); 
			SBuyPage.setLayoutX(750);
			SBuyPage.setLayoutY(75);
			SBuyPage.setPrefWidth(150);
				
			Button SSellPage = new Button("Sell");
			SSellPage.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;"); 
			SSellPage.setLayoutX(1050);
			SSellPage.setLayoutY(75);
			SSellPage.setPrefWidth(150);
			
			Button SGenerateCart = new Button ("Generate Cart");
			SGenerateCart.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;"); 
			SGenerateCart.setLayoutX(800);
			SGenerateCart.setLayoutY(900);
			SGenerateCart.setPrefWidth(300);
			
			 // Labels
			Label SBookTitle = new Label("Book Title:");
			SBookTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;"); 
			SBookTitle.setLayoutX(50);
			SBookTitle.setLayoutY(210);
			
			Label SAuthor = new Label("Author:");
			SAuthor.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
			SAuthor.setLayoutX(50);
			SAuthor.setLayoutY(310);
			
			Label SOriginalPrice = new Label ("Original Price:");
			SOriginalPrice.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
			SOriginalPrice.setLayoutX(50);
			SOriginalPrice.setLayoutY(410);
			
			Label SQuantity = new Label ("Quantity:");
			SQuantity.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
			SQuantity.setLayoutX(50);
			SQuantity.setLayoutY(500);
			
			Label SGenre = new Label ("Genre:");
			SGenre.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
			SGenre.setLayoutX(50);
			SGenre.setLayoutY(600);
			
			Label SCondition = new Label ("Condition:");
			SCondition.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
			SCondition.setLayoutX(50);
			SCondition.setLayoutY(700);
			
			Label SPictures = new Label ("Pictures:");
			SPictures.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
			SPictures.setLayoutX(50);
			SPictures.setLayoutY(800);
			
			// Text Fields
			TextField SbookTitleField = new TextField();
            SbookTitleField.setLayoutX(50); 
            SbookTitleField.setLayoutY(250);
            SbookTitleField.setPrefSize(750,30);
            SbookTitleField.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;");
            
           TextField SAuthorField = new TextField();
           SAuthorField.setLayoutX(50);
           SAuthorField.setLayoutY(350);
           SAuthorField.setPrefSize(750,30);
           SAuthorField.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;");
           
           TextField SOriginalPriceField = new TextField();
           SOriginalPriceField.setLayoutX(50);
           SOriginalPriceField.setLayoutY(450);
           SOriginalPriceField.setPrefSize(750,30);
           SOriginalPriceField.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;");
           
          SOriginalPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
        	    if (!newValue.matches("\\d*(\\.\\d{0,2})?")) { 
        	    	 SOriginalPriceField.setText(oldValue); 
        	    }
        	});
   
          //Spinner for quantity
           Spinner<Integer> SBookQuantity = new Spinner<>();
           SBookQuantity.setLayoutX(160);
           SBookQuantity.setLayoutY(510);
           SBookQuantity.setPrefWidth(80);
           SBookQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
           
           // Check Boxes for genre
           CheckBox SScience = new CheckBox("Natural Science");
			SScience.setLayoutX(140);
			SScience.setLayoutY(607);
			SScience.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");
			
			
			CheckBox SComputer = new CheckBox("Computer");
			SComputer.setLayoutX(300);
			SComputer.setLayoutY(607);
			SComputer.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");
			
			CheckBox SMath = new CheckBox("Math");
			SMath.setLayoutX(420);
			SMath.setLayoutY(607);
			SMath.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");
			
			CheckBox SEnglish = new CheckBox("English");
			SEnglish.setLayoutX(505);
			SEnglish.setLayoutY(607);
			SEnglish.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");
			
			CheckBox SOther = new CheckBox("Other");
			SOther.setLayoutX(600);
			SOther.setLayoutY(607);
			SOther.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");
			
			// Radio Buttons for conditions
			ToggleGroup SConditionChoice = new ToggleGroup();
            RadioButton SLikeNew = new RadioButton("Like New");
            SLikeNew.setToggleGroup(SConditionChoice);
            SLikeNew.setLayoutX(180);
            SLikeNew.setLayoutY(708);
            SLikeNew.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");
			
			RadioButton SModerate = new RadioButton("Moderately Used");
			SModerate.setToggleGroup(SConditionChoice);
			SModerate.setLayoutX(290);
			SModerate.setLayoutY(708);
			SModerate.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");

			RadioButton SHeavily = new RadioButton("Heavily Used");
			SHeavily.setToggleGroup(SConditionChoice);
			SHeavily.setLayoutX(460);
			SHeavily.setLayoutY(708);
			SHeavily.setStyle("-fx-font-size: 16px;-fx-font-weight: bold;");
			
			
			//check out box
			Rectangle infoBox = new Rectangle(1100, 200, 300, 400); 
            infoBox.setFill(Color.LIGHTGRAY);
            infoBox.setStroke(Color.BLACK);
            infoBox.setVisible(false); // Initially hidden

            // Label to display generated information
            Label infoLabel = new Label(); 
            infoLabel.setLayoutX(1120);
            infoLabel.setLayoutY(220);
            infoLabel.setStyle("-fx-font-size: 18px;");
            infoLabel.setWrapText(true);
            infoLabel.setPrefWidth(300);
            infoLabel.setVisible(false); // Hidden

            // Generate button action
            SGenerateCart.setOnAction(event -> { 
                String bookTitle = SbookTitleField.getText();
                String author = SAuthorField.getText();
                String price = SOriginalPriceField.getText();
                int quantity = SBookQuantity.getValue();  
                boolean atLeastOneImageUploaded = false;		//checks if the boxes are filled and an image is selected
                for (ImageView imageView : imageViews) {
                    if (imageView.getImage() != null) {
                        atLeastOneImageUploaded = true;
                        break; 
                    }
                }
                
                if (!bookTitle.isEmpty() && !author.isEmpty() && !price.isEmpty()  && (SScience.isSelected() || SComputer.isSelected() || SMath.isSelected() || SEnglish.isSelected() || SOther.isSelected()) && SConditionChoice.getSelectedToggle() != null && atLeastOneImageUploaded == true) {
                	 double originalPrice = Double.parseDouble(price);
                     double Condition = 0.0;
                     if (SLikeNew.isSelected()) {
                    	 Condition = 0.8;					// 20% off original price if new
                     }else if (SModerate.isSelected()) {
                    	 Condition = 0.7;					// 30% off original price if moderate
                     }else if (SHeavily.isSelected()) {
                    	 Condition = 0.6;					// 40% off original price if heavily
                     }
                     double salePrice = originalPrice * Condition;
                     double adminFee = salePrice * 0.2;
                     double userGet = salePrice - adminFee;
                     double Total = userGet * quantity;
                     	
                	infoLabel.setText(
                    		
                    	"		     CART" + "\n" +	
                    	"------------------------------------" + "\n" +
                    	"Original Price:		$" + price +  " X " + quantity + "\n" +
                        "Sale Price:		$"+ String.format("%.2f", salePrice)  +  " X " + quantity + "\n" +
                        "Admin's Fee:		$" + String.format("%.2f", adminFee)  +  " X " + quantity + "\n" +
                        "------------------------------------"+ "\n" +
                        "You get:			$"+ String.format("%.2f", userGet) +  " X " + quantity + "\n" +
                        "Total:			$"  + String.format("%.2f", Total)  + "\n"          
                    );
                	// Generate the list Book button
                    Button ListBook = new Button ("List My Book"); 
                    ListBook.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;"); 
                    ListBook.setLayoutX(1126);
                    ListBook.setLayoutY(545);
                    ListBook.setPrefWidth(250);  
                    root.getChildren().add(ListBook); 
                    infoBox.setVisible(true);
                    infoLabel.setVisible(true);
                } else {			// Error if fields aren't filled out
                    infoLabel.setText("Please fill out all fields before generating.");
                    infoBox.setVisible(true);
                    infoLabel.setVisible(true);
                }
            });
		    
            //--------------------------------------------------------Taking care of Uploading Pictures---------------------------------------------------------
           
            
           
            boolean[] atLeastOneImageUploaded = { false }; 

            // Loop to create buttons and image views
            for (int i = 0; i < 4; i++) { 
                int index = i; 
                uploadButtons[i] = new Button("📷📸"); 
                uploadButtons[i].setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
                uploadButtons[i].setLayoutX(50 + (i * 200)); 
                uploadButtons[i].setLayoutY(850); 
                uploadButtons[i].setPrefWidth(100);

                imageViews[i] = new ImageView(); 
                imageViews[i].setFitWidth(150);
                imageViews[i].setFitHeight(150);
                imageViews[i].setLayoutX(50 + (i * 200)); 
                imageViews[i].setLayoutY(850); 
                
                deleteButtons[i] = new Button("X"); 
                deleteButtons[i].setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: red;");
                deleteButtons[i].setPrefSize(20, 20);
                deleteButtons[i].setLayoutX(175 + (i * 200)); 
                deleteButtons[i].setLayoutY(850);
                deleteButtons[i].setVisible(false); 
                
                // action to upload an images
                uploadButtons[i].setOnAction(event -> {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

                    File file = fileChooser.showOpenDialog(primaryStage); 
                    if (file != null) {
                        Image image = new Image("file:" + file.getAbsolutePath());
                        imageViews[index].setImage(image); 
                        deleteButtons[index].setVisible(true);
                        
                        atLeastOneImageUploaded[0] = true;
                    }
                });
    
                // action to delete an image
                deleteButtons[i].setOnAction(event -> { 
                    imageViews[index].setImage(null); 
                    deleteButtons[index].setVisible(false); 
                    atLeastOneImageUploaded[0] = false;
                });
                // Add button and ImageView to the root
                root.getChildren().add(uploadButtons[i]); 
                root.getChildren().add(imageViews[i]); 
                root.getChildren().add(deleteButtons[i]);
                          
            }
      
            root.getChildren().add(rect);
            root.getChildren().add(showLogo);   
	        root.getChildren().add(SBookTitle);
	        root.getChildren().add(SbookTitleField);
	        root.getChildren().add(SAuthor);
	        root.getChildren().add(SAuthorField);
	        root.getChildren().add(SOriginalPrice);
	        root.getChildren().add(SOriginalPriceField);
	        root.getChildren().add(SQuantity);
	        root.getChildren().add(SBookQuantity);
	        root.getChildren().add(SGenre);
	        root.getChildren().add(SScience);
	        root.getChildren().add(SComputer);
	        root.getChildren().add(SMath);
	        root.getChildren().add(SEnglish);
	        root.getChildren().add(SOther);
	        root.getChildren().add(SCondition);
	        root.getChildren().add(SLikeNew);
	        root.getChildren().add(SModerate);
	        root.getChildren().add(SHeavily);
	        root.getChildren().add(SPictures);
	        root.getChildren().add(SBuyPage);
	        root.getChildren().add(SSellPage);
	        root.getChildren().add(SGenerateCart);
	        root.getChildren().add(infoBox); 
	        root.getChildren().add(infoLabel); 
	        
	     
	        Scene scene = new Scene(root, 1920, 1000); 
	        
	        // Set up the Stage (window)
	        primaryStage.setTitle("Seller's Page");
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
