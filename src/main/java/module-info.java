module pl.roquv.passwordgeneratorjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens pl.roquv.passwordgeneratorjavafx to javafx.fxml;
    exports pl.roquv.passwordgeneratorjavafx;
    exports pl.roquv.passwordgeneratorjavafx.Controllers;
    opens pl.roquv.passwordgeneratorjavafx.Controllers to javafx.fxml;
}