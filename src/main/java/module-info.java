module pl.roquv.passwordgeneratorjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens pl.roquv.passwordgeneratorjavafx to javafx.fxml;
    exports pl.roquv.passwordgeneratorjavafx;
}