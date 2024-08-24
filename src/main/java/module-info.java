module pl.roquv.passwordgeneratorjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires java.desktop;


    opens pl.roquv.passwordgeneratorjavafx to javafx.fxml;
    exports pl.roquv.passwordgeneratorjavafx;
    exports pl.roquv.passwordgeneratorjavafx.Controllers;
    opens pl.roquv.passwordgeneratorjavafx.Controllers to javafx.fxml;
    exports pl.roquv.passwordgeneratorjavafx.Generator;
    opens pl.roquv.passwordgeneratorjavafx.Generator to javafx.fxml;
}