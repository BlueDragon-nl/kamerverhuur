module main.kamerverhuur {
    requires javafx.controls;
    requires javafx.fxml;


    opens main.kamerverhuur to javafx.fxml;
    exports main.kamerverhuur;
    exports main.kamerverhuur.model;
    opens main.kamerverhuur.model to javafx.fxml;
    exports main.kamerverhuur.Controllers;
    opens main.kamerverhuur.Controllers to javafx.fxml;
    exports main.kamerverhuur.subject;
    opens main.kamerverhuur.subject to javafx.fxml;
}