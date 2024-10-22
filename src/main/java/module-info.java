module com.example.cse360_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.group43.cse360_project to javafx.fxml;
    exports com.group43.cse360_project;
}