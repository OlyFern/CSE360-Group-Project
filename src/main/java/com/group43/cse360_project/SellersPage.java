package com.group43.cse360_project;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static com.group43.cse360_project.Header.createHeader;


public class SellersPage {
    private Stage stage;
    public String imageName;
    public SellersPage(Stage stage){
        this.stage = stage;
    }

    public Scene getScene(User user) {
        {


            try {
                Pane root = new Pane();
                BorderPane nroot = new BorderPane();
                Button[] uploadButtons = new Button[4];
                ImageView[] imageViews = new ImageView[4];
                Button[] deleteButtons = new Button[4];
                Button SGenerateCart = new Button ("Generate Cart");
                SGenerateCart.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                SGenerateCart.setPrefWidth(300);

                Label SBookTitle = new Label("Book Title:");
                SBookTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

                Label SAuthor = new Label("Author:");
                SAuthor.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                SAuthor.setLayoutX(50);
                SAuthor.setLayoutY(310);

                Label SOriginalPrice = new Label ("Original Price:");
                SOriginalPrice.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                SOriginalPrice.setLayoutX(50);
                SOriginalPrice.setLayoutY(410);

                Label SQuantity = new Label ("Quantity:");
                SQuantity.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                SQuantity.setLayoutX(50);
                SQuantity.setLayoutY(500);

                Label SGenre = new Label ("Genre:");
                SGenre.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                SGenre.setLayoutX(50);
                SGenre.setLayoutY(600);

                Label SCondition = new Label ("Condition:");
                SCondition.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                SCondition.setLayoutX(50);
                SCondition.setLayoutY(700);

                Label SPictures = new Label ("Pictures:");
                SPictures.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                SPictures.setLayoutX(50);
                SPictures.setLayoutY(800);

                TextField SbookTitleField = new TextField();
                SbookTitleField.setLayoutX(50);
                SbookTitleField.setLayoutY(250);
                //SbookTitleField.setPrefSize(750,30);
                SbookTitleField.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;");

                TextField SAuthorField = new TextField();
                SAuthorField.setLayoutX(50);
                SAuthorField.setLayoutY(350);
                //SAuthorField.setPrefSize(750,30);
                SAuthorField.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;");

                TextField SOriginalPriceField = new TextField();
                SOriginalPriceField.setLayoutX(50);
                SOriginalPriceField.setLayoutY(450);
                // SOriginalPriceField.setPrefSize(750,30);
                SOriginalPriceField.setStyle("-fx-font-size: 18px;-fx-font-weight: bold;");

                SOriginalPriceField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                        SOriginalPriceField.setText(oldValue);
                    }
                });


                Spinner<Integer> SBookQuantity = new Spinner<>();
                SBookQuantity.setLayoutX(160);
                SBookQuantity.setLayoutY(510);
                SBookQuantity.setPrefWidth(80);
                SBookQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1));

                CheckBox SScience = new CheckBox("Natural Science");
                SScience.setLayoutX(140);
                SScience.setLayoutY(607);
                SScience.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;");


                CheckBox SComputer = new CheckBox("Computer");
                SComputer.setLayoutX(300);
                SComputer.setLayoutY(607);
                SComputer.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;");

                CheckBox SMath = new CheckBox("Math");
                SMath.setLayoutX(420);
                SMath.setLayoutY(607);
                SMath.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;");

                CheckBox SEnglish = new CheckBox("English");
                SEnglish.setLayoutX(505);
                SEnglish.setLayoutY(607);
                SEnglish.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;");

                CheckBox SOther = new CheckBox("Other");
                SOther.setLayoutX(600);
                SOther.setLayoutY(607);
                SOther.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;");

                ToggleGroup SConditionChoice = new ToggleGroup();
                RadioButton SLikeNew = new RadioButton("Like New");
                SLikeNew.setToggleGroup(SConditionChoice);
                SLikeNew.setLayoutX(180);
                SLikeNew.setLayoutY(708);
                SLikeNew.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;");

                RadioButton SModerate = new RadioButton("Moderately Used");
                SModerate.setToggleGroup(SConditionChoice);
                SModerate.setLayoutX(290);
                SModerate.setLayoutY(708);
                SModerate.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;");

                RadioButton SHeavily = new RadioButton("Heavily Used");
                SHeavily.setToggleGroup(SConditionChoice);
                SHeavily.setLayoutX(460);
                SHeavily.setLayoutY(708);
                SHeavily.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;");


                Rectangle infoBox = new Rectangle(1100, 200, 300, 400);
                infoBox.setFill(Color.LIGHTGRAY);
                infoBox.setStroke(Color.BLACK);
                infoBox.setVisible(false);

                Label infoLabel = new Label();
                infoLabel.setLayoutX(1120);
                infoLabel.setLayoutY(220);
                infoLabel.setStyle("-fx-font-size: 18px;");
                infoLabel.setWrapText(true);
                infoLabel.setPrefWidth(300);
                infoLabel.setVisible(false);

                Button ListBook = new Button ("List My Book");
                ListBook.setStyle("-fx-font-size: 20px;-fx-font-weight: bold;");
                ListBook.setVisible(false);

                VBox list = new VBox(new StackPane(infoBox, infoLabel), ListBook, SGenerateCart);

                SGenerateCart.setOnAction(event -> {
                    String bookTitle = SbookTitleField.getText();
                    String author = SAuthorField.getText();
                    String price = SOriginalPriceField.getText();
                    int quantity = SBookQuantity.getValue();
                    boolean atLeastOneImageUploaded = false;
                    for (ImageView imageView : imageViews) {
                        if (imageView.getImage() != null) {
                            atLeastOneImageUploaded = true;
                            break;
                        }
                    }

                    if (!bookTitle.isEmpty() && !author.isEmpty() && !price.isEmpty()  && (SScience.isSelected() || SComputer.isSelected() || SMath.isSelected() || SEnglish.isSelected() || SOther.isSelected()) && SConditionChoice.getSelectedToggle() != null && atLeastOneImageUploaded == true) {
                        double originalPrice = Double.parseDouble(price);
                        double Condition = 0.0;
                        BookGenre genreListing;
                        BookCondition bookCondition;
                        if (SScience.isSelected()) {
                            genreListing = BookGenre.NATURAL_SCIENCE;
                        }else if (SComputer.isSelected()){
                            genreListing = BookGenre.COMPUTERS;
                        } else if (SMath.isSelected()) {
                            genreListing = BookGenre.MATH;
                        } else if (SEnglish.isSelected()) {
                            genreListing = BookGenre.ENGLISH_LANGUAGE;
                        } else {
                            genreListing = BookGenre.OTHER;
                        }
                        if (SLikeNew.isSelected()) {
                            Condition = 0.8;
                            bookCondition = BookCondition.NEW;
                        }else if (SModerate.isSelected()) {
                            Condition = 0.7;
                            bookCondition = BookCondition.USED;
                        }else if (SHeavily.isSelected()) {
                            Condition = 0.6;
                            bookCondition = BookCondition.HEAVY;
                        } else {
                            bookCondition = null;
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
                        ListBook.setVisible(true);
                        ListBook.setOnMouseClicked(e -> {
                            try {
                                String uniqueID = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
                                BookDB.addNewBook(imageName, bookTitle, author, genreListing, bookCondition, "temp", (float) salePrice, quantity);
                                System.out.println(imageName);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        });
                        infoBox.setVisible(true);
                        infoLabel.setVisible(true);
                    } else {
                        infoLabel.setText("Please fill out all fields before generating.");
                        infoBox.setVisible(true);
                        infoLabel.setVisible(true);
                    }
                });


                boolean[] atLeastOneImageUploaded = { false };

                HBox imageBox = new HBox();
                for (int i = 0; i < 4; i++) {
                    int index = i;
                    uploadButtons[i] = new Button("📷📸");
                    uploadButtons[i].setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
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
                    deleteButtons[i].setVisible(false);
                    System.out.println(imageName);


                    uploadButtons[i].setOnAction(event -> {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

                        File file = fileChooser.showOpenDialog(new Stage());
                        if (file != null) {
                            Image image = new Image("file:" + file.getAbsolutePath());
                            imageViews[index].setImage(image);
                            deleteButtons[index].setVisible(true);

                            atLeastOneImageUploaded[0] = true;
                            try {
                                File resourcesDir = new File("src/main/resources/com/group43/cse360_project/pcitures");
                                if (!resourcesDir.exists()) {
                                    resourcesDir.mkdir(); // Create the resources folder if it doesn't exist
                                }

                                // Generate a unique name for the copied file to avoid overwriting
                                String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getName();


                                // Destination file path in the resources directory
                                File destinationFile = new File(resourcesDir, uniqueFileName);
                                this.imageName = "src\\main\\resources\\com\\group43\\cse360_project\\pcitures\\" + uniqueFileName;
                                // Copy the file from its original location to the resources folder
                                java.nio.file.Files.copy(file.toPath(), destinationFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                                // Log or set a path variable if needed for later access
                                System.out.println("Image copied to resources folder: " + destinationFile.getAbsolutePath());
                                System.out.println(imageName);
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.err.println("Failed to copy the image to the resources folder.");
                            }
                        }
                    });

                    deleteButtons[i].setOnAction(event -> {
                        imageViews[index].setImage(null);
                        deleteButtons[index].setVisible(false);
                        atLeastOneImageUploaded[0] = false;
                    });
                    StackPane stackPane = new StackPane(uploadButtons[i], imageViews[i]);
                    StackPane.setAlignment(deleteButtons[i], Pos.TOP_RIGHT);
                    stackPane.getChildren().add(deleteButtons[i]);
                    imageBox.getChildren().add(stackPane);

                }

                HBox genreBox = new HBox(SGenre, SScience, SComputer, SMath, SEnglish, SOther);
                genreBox.setSpacing(10);
                HBox conditionBox = new HBox(SCondition, SLikeNew, SModerate, SHeavily);
                conditionBox.setSpacing(10);
                VBox textFields = new VBox(SBookTitle, SbookTitleField,
                        SAuthor, SAuthorField,
                        SOriginalPrice, SOriginalPriceField,
                        SQuantity, SBookQuantity,
                        genreBox, conditionBox, imageBox);
                textFields.setSpacing(10);
                textFields.setPadding(new Insets(10));
                HBox header = createHeader(stage, user);
                nroot.setTop(header);
                nroot.setCenter(textFields);
                list.setPadding(new Insets(10));
                nroot.setRight(list);
                return new Scene(nroot, 1200, 675);

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
