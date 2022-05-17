module main.kamerverhuur {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.kamerverhuur to javafx.fxml;
    exports main.kamerverhuur;
    exports main.kamerverhuur.model;
    opens main.kamerverhuur.model to javafx.fxml;
}