package application;
	

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.shape.Rectangle;

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


public class SellersPage extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			 Pane root = new Pane();
			 Rectangle rect = new Rectangle(1920, 200); 
             rect.setLayoutX(0);  
             rect.setLayoutY(0); 
             rect.setFill(Color.web("#f4f4d8")); 
			 
			 
			 // Buttons 
			 
			 Button SBuyPage = new Button("Buy");
			SBuyPage.setStyle("-fx-font-size: 24px;"); 
			SBuyPage.setLayoutX(750);
			SBuyPage.setLayoutY(75);
			SBuyPage.setPrefWidth(150);
				
			Button SSellPage = new Button("Sell");
			SSellPage.setStyle("-fx-font-size: 24px;"); 
			SSellPage.setLayoutX(1050);
			SSellPage.setLayoutY(75);
			SSellPage.setPrefWidth(150);
			
			Button SGenerateCart = new Button ("Generate Cart");
			SGenerateCart.setStyle("-fx-font-size: 24px;"); 
			SGenerateCart.setLayoutX(800);
			SGenerateCart.setLayoutY(900);
			SGenerateCart.setPrefWidth(300);
			
			 // Labels
			Label SBookTitle = new Label("Book Title:");
			SBookTitle.setStyle("-fx-font-size: 24px;"); 
			SBookTitle.setLayoutX(50);
			SBookTitle.setLayoutY(200);
			
			Label SAuthor = new Label("Author:");
			SAuthor.setStyle("-fx-font-size: 24px;");
			SAuthor.setLayoutX(50);
			SAuthor.setLayoutY(300);
			
			Label SOriginalPrice = new Label ("Original Price:");
			SOriginalPrice.setStyle("-fx-font-size: 24px;");
			SOriginalPrice.setLayoutX(50);
			SOriginalPrice.setLayoutY(400);
			
			Label SQuantity = new Label ("Quantity:");
			SQuantity.setStyle("-fx-font-size: 24px;");
			SQuantity.setLayoutX(50);
			SQuantity.setLayoutY(500);
			
			Label SGenre = new Label ("Genre:");
			SGenre.setStyle("-fx-font-size: 24px;");
			SGenre.setLayoutX(50);
			SGenre.setLayoutY(600);
			
			Label SCondition = new Label ("Condition:");
			SCondition.setStyle("-fx-font-size: 24px;");
			SCondition.setLayoutX(50);
			SCondition.setLayoutY(700);
			
			Label SPictures = new Label ("Pictures:");
			SPictures.setStyle("-fx-font-size: 24px;");
			SPictures.setLayoutX(50);
			SPictures.setLayoutY(800);
			
			
			
			// Text Fields
			TextField SbookTitleField = new TextField();
            SbookTitleField.setLayoutX(50); 
            SbookTitleField.setLayoutY(250);
            SbookTitleField.setPrefWidth(250);
            
           TextField SAuthorField = new TextField();
           SAuthorField.setLayoutX(50);
           SAuthorField.setLayoutY(350);
           SAuthorField.setPrefWidth(250);
           
           TextField SOriginalPriceField = new TextField();
           SOriginalPriceField.setLayoutX(50);
           SOriginalPriceField.setLayoutY(450);
           SOriginalPriceField.setPrefWidth(250);
           
          SOriginalPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
        	    if (!newValue.matches("\\d*(\\.\\d{0,2})?")) { 
        	    	 SOriginalPriceField.setText(oldValue); 
        	    }
        	});
           
           Spinner<Integer> SBookQuantity = new Spinner<>();
           SBookQuantity.setLayoutX(150);
           SBookQuantity.setLayoutY(510);
           SBookQuantity.setPrefWidth(50);
           SBookQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));
           
           // Check Boxes
           CheckBox SScience = new CheckBox("Natural Science");
			SScience.setLayoutX(150);
			SScience.setLayoutY(611);
			
			CheckBox SComputer = new CheckBox("Computer");
			SComputer.setLayoutX(300);
			SComputer.setLayoutY(611);
			
			CheckBox SMath = new CheckBox("Math");
			SMath.setLayoutX(450);
			SMath.setLayoutY(611);
			
			CheckBox SEnglish = new CheckBox("English");
			SEnglish.setLayoutX(600);
			SEnglish.setLayoutY(611);
			
			CheckBox SOther = new CheckBox("Other");
			SOther.setLayoutX(750);
			SOther.setLayoutY(611);
			// Radio Buttons
			ToggleGroup SConditionChoice = new ToggleGroup();
            RadioButton SLikeNew = new RadioButton("Like New");
            SLikeNew.setToggleGroup(SConditionChoice);
            SLikeNew.setLayoutX(200);
			SLikeNew.setLayoutY(711);
			
			RadioButton SModerate = new RadioButton("Moderately Used");
			SModerate.setToggleGroup(SConditionChoice);
			SModerate.setLayoutX(350);
			SModerate.setLayoutY(711);
			
			RadioButton SHeavily = new RadioButton("Heavily Used");
			SHeavily.setToggleGroup(SConditionChoice);
			SHeavily.setLayoutX(500);
			SHeavily.setLayoutY(711);
			
			Rectangle infoBox = new Rectangle(1100, 200, 300, 400); // Added rectangle for info display
            infoBox.setFill(Color.LIGHTGRAY);
            infoBox.setStroke(Color.BLACK);
            infoBox.setVisible(false); // Initially hidden

            // Label to display generated information
            Label infoLabel = new Label(); // Added label for info display
            infoLabel.setLayoutX(1120);
            infoLabel.setLayoutY(220);
            infoLabel.setStyle("-fx-font-size: 18px;");
            infoLabel.setWrapText(true);
            infoLabel.setPrefWidth(300);
            infoLabel.setVisible(false); // Initially hidden

            // Generate button action
            SGenerateCart.setOnAction(event -> { // Added event handler for Generate Cart
                String bookTitle = SbookTitleField.getText();
                String author = SAuthorField.getText();
                String price = SOriginalPriceField.getText();
                int quantity = SBookQuantity.getValue();
                int total ;

                if (!bookTitle.isEmpty() && !author.isEmpty() && !price.isEmpty()  && (SScience.isSelected() || SComputer.isSelected() || SMath.isSelected() || SEnglish.isSelected() || SOther.isSelected()) && SConditionChoice.getSelectedToggle() != null) {
                	 double originalPrice = Double.parseDouble(price);
                     double Condition = 0.0;
                     if (SLikeNew.isSelected()) {
                    	 Condition = 0.8;
                     }else if (SModerate.isSelected()) {
                    	 Condition = 0.7;
                     }else if (SHeavily.isSelected()) {
                    	 Condition = 0.6;
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
                    
                    Button ListBook = new Button ("List My Book"); // Button made when  the cart is generated
                    ListBook.setStyle("-fx-font-size: 24px;"); 
                    ListBook.setLayoutX(1150);
                    ListBook.setLayoutY(545);
                    ListBook.setPrefWidth(200);
                    
                    root.getChildren().add(ListBook); 
                    
                    infoBox.setVisible(true);
                    infoLabel.setVisible(true);
                } else {
                    infoLabel.setText("Please fill out all fields before generating.");
                    infoBox.setVisible(true);
                    infoLabel.setVisible(true);
                }
            });
            
            String filePath = "C:/Users/ramen/eclipse-workspace/website/src/application/logo.png"; // Adjust this path to your file location
            Image logo = new Image("file:" + filePath);

            // Display the image
            ImageView imageView = new ImageView(logo);
            imageView.setFitWidth(200);
            imageView.setFitHeight(150);
            imageView.setLayoutX(300);
            imageView.setLayoutY(10);
            
            
            root.getChildren().add(rect);
            root.getChildren().add(imageView);   
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
	        root.getChildren().add(infoBox); // Add rectangle to the pane
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
