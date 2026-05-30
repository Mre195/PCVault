
import java.io.File;
import java.nio.file.Paths;
import java.sql.Statement;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;

public class Project_1 extends Application // implements EventHandler<Event>
{

    String eee = "moath@gmail.com";
    String ppp = "Moath_1";

    // Products() , sumShow() and Cart()
    public Map<String, Integer> productQuantities = new HashMap<>();// Cart() ..  handleQuantityChange()  ..  createProductItem()
    public Map<String, Label> quantityLabels = new HashMap<>();// save labels so no ERROR's acure
    public Map<String, Integer> cart = new HashMap<>();// updateCart() ..  showSum()

    // Login() ..  Settings()
    String userEmail;
    String userName;

    Scene s1, s2, s3, s4, s5, s6;
    Stage primaryStage;

    // Cart f(x)s in general
    public ListView<String> listView;
    public ObservableList<String> cartItems = FXCollections.observableArrayList();

    // Products()
    ComboBox<String> combo = new ComboBox<>();
    GridPane products = new GridPane();
    GridPane cpuRoot = new GridPane();
    GridPane gpuRoot = new GridPane();
    GridPane ramRoot = new GridPane();
    GridPane ssdRoot = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        s1 = new Scene(Login(primaryStage));
        s2 = new Scene(SignUp(primaryStage));
        s3 = new Scene(Home(primaryStage));
        s4 = new Scene(Products(primaryStage));
        s5 = new Scene(Cart(primaryStage));
        s6 = new Scene(Settings(primaryStage));

        File cssFile = new File("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\light-theme.css");
        String defaultCSS = cssFile.toURI().toString();
        s1.getStylesheets().add(defaultCSS);
        s2.getStylesheets().add(defaultCSS);
        s3.getStylesheets().add(defaultCSS);
        s4.getStylesheets().add(defaultCSS);
        s5.getStylesheets().add(defaultCSS);
        s6.getStylesheets().add(defaultCSS);


        primaryStage.setScene(s1);
        primaryStage.setWidth(1500);
        primaryStage.setHeight(800);
        primaryStage.show();
    }

    public BorderPane Login(Stage x) {

        GridPane loginRoot = new GridPane();
        loginRoot.setPadding(new Insets(30, 40, 30, 40));
        loginRoot.setHgap(40);
        loginRoot.setVgap(20);
        loginRoot.setAlignment(Pos.CENTER);

        Label title = new Label("Log In");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField(eee);
        emailField.setPrefSize(150, 4);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setText(ppp);
        passwordField.setPrefWidth(150);

        Button loginButton = new Button("Log In");
        loginButton.setPrefSize(120, 30);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setPrefSize(120, 30);

        // test fields to blend
        TextField showPass = new TextField();
        showPass.setPrefWidth(150);

        //to change between Password and Text Field
        showPass.textProperty().bindBidirectional(passwordField.textProperty());

        //non visibale
        CheckBox showPassword = new CheckBox("Show Password");
        showPassword.setTextFill(Color.WHITE);
        showPassword.setFont(Font.font("Arial", FontWeight.BOLD, 10));

        showPassword.setOnAction(e -> {
            if (showPassword.isSelected()) {
                loginRoot.getChildren().removeAll(passwordField);
                loginRoot.add(showPass, 1, 2);
            } else {
                loginRoot.getChildren().removeAll(showPass);
                loginRoot.add(passwordField, 1, 2);
            }

        });

        loginRoot.add(title, 0, 0);
        loginRoot.add(emailLabel, 0, 1);
        loginRoot.add(emailField, 1, 1);
        loginRoot.add(passwordLabel, 0, 2);
        loginRoot.add(passwordField, 1, 2);
        loginRoot.add(showPassword, 1, 3);
        loginRoot.add(loginButton, 0, 4);
        loginRoot.add(signUpButton, 1, 4);

        Tooltip emailTooltip = new Tooltip("Please Enter Your Email");
        Tooltip.install(emailField, emailTooltip);
        emailField.setOnMouseEntered(e -> emailTooltip.show(emailField, e.getScreenX(), e.getScreenY() + 10));
        emailField.setOnMouseExited(e -> emailTooltip.hide());

        Tooltip passwordTooltip = new Tooltip("Please Enter Your Password");
        Tooltip.install(passwordField, passwordTooltip);
        passwordField.setOnMouseEntered(e -> passwordTooltip.show(passwordField, e.getScreenX(), e.getScreenY() + 10));
        passwordField.setOnMouseExited(e -> passwordTooltip.hide());

        Tooltip showPassTip = new Tooltip("Please Enter Your Password");
        Tooltip.install(showPass, showPassTip);
        showPass.setOnMouseEntered(e -> showPassTip.show(showPass, e.getScreenX(), e.getScreenY() + 10));
        showPass.setOnMouseExited(e -> showPassTip.hide());

        loginButton.setOnAction(e
                -> // Validation from data base
                {
                    if (emailField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty()) {
                        Alert a = new Alert(Alert.AlertType.ERROR, "Invalid data");
                        a.showAndWait();
                    } else {
                        try {
                            // 1 - Conection
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_JavaFX", "root", "");
                            // 2 - Statment
                            PreparedStatement st = con.prepareStatement("SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?");
                            st.setString(1, emailField.getText());
                            st.setString(2, passwordField.getText());
                            ResultSet result = st.executeQuery();
                            if (!result.next()) {
                                Alert zz = new Alert(Alert.AlertType.ERROR, "The Email/Password is Incorrect");
                                zz.showAndWait();
                            } else {
                                emailField.setText("");
                                passwordField.setText("");
                                userEmail = result.getString("email");
                                userName = result.getString("full name");
                                primaryStage.setScene(s3);
                                primaryStage.setTitle("Home Page");

                                ButtonType heelloo = new ButtonType("Hello! 😊", ButtonBar.ButtonData.YES);

                                Alert wele = new Alert(Alert.AlertType.NONE, "Welcome back 😊", heelloo);
                                wele.setTitle("Welcome Again");
                                wele.setHeaderText("Welcome, " + userName.substring(0, userName.indexOf(" ")));
                                Button heelloo2 = (Button) wele.getDialogPane().lookupButton(heelloo);
                                heelloo2.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

                                wele.showAndWait();

                            }
                        } catch (Exception ex) {
                            Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                            a.showAndWait();
                        }
                    }
                });

        signUpButton.setOnAction(e
                -> {
            primaryStage.setScene(s2);
            primaryStage.setTitle("SignUp Page");
        });

        VBox pic = new VBox(AddPicture("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\14.png", 400));
        pic.setAlignment(Pos.CENTER);

        GridPane containerRoot = new GridPane();
        containerRoot.setAlignment(Pos.CENTER);

        containerRoot.add(pic, 0, 0);
        containerRoot.add(loginRoot, 0, 1);

        BorderPane root = new BorderPane();
        root.setCenter(containerRoot);

        loginRoot.getStyleClass().add("login-root-login");
        title.getStyleClass().add("title-login");
        emailLabel.getStyleClass().add("label-login");
        passwordLabel.getStyleClass().add("label-login");
        loginButton.getStyleClass().add("button-login");
        signUpButton.getStyleClass().add("button-login");
        showPassword.getStyleClass().add("show-password-login");
        containerRoot.getStyleClass().add("container-root-login");
        root.getStyleClass().add("root-login");

        primaryStage.setTitle("Login Page");
        return root;
    }

    public GridPane   SignUp(Stage x) {
        GridPane signUpRoot = new GridPane();
        signUpRoot.setPadding(new Insets(20, 30, 20, 30));
        signUpRoot.setHgap(40);
        signUpRoot.setVgap(20);
        signUpRoot.setAlignment(Pos.CENTER);

        Label title = new Label("Sign Up");

        Label fullNameLabel = new Label("Full Name:");
        TextField fullNameField = new TextField();
        fullNameField.setPrefWidth(200);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);

        Label ConPasswordLabel = new Label("Confirm Password:");
        PasswordField ConPasswordField = new PasswordField();
        ConPasswordField.setPrefWidth(200);

        Label teleNumberLabel = new Label("Phone Number:");
        TextField teleNumberField = new TextField("");
        teleNumberField.setPrefWidth(200);

        Label gender = new Label("Gender: ");
        ToggleGroup grp = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");

        male.setToggleGroup(grp);
        female.setToggleGroup(grp);
        HBox genderBox = new HBox(10, male, female);

        Button returnloginButton = new Button("Log-in Page");
        returnloginButton.setPrefSize(120, 30);

        Button signUpButton = new Button("Register Now");
        signUpButton.setPrefSize(120, 30);

        // test fields to blend
        TextField newPassTextField = new TextField();
        TextField conNewPassTextField = new TextField();
        newPassTextField.setPrefWidth(200);
        conNewPassTextField.setPrefWidth(200);

        //to change between Password and Text Fields
        newPassTextField.textProperty().bindBidirectional(passwordField.textProperty());
        conNewPassTextField.textProperty().bindBidirectional(ConPasswordField.textProperty());

        CheckBox showPassword = new CheckBox("Show Password");

        showPassword.setOnAction(e -> {
            if (showPassword.isSelected()) {
                signUpRoot.getChildren().removeAll(passwordField, ConPasswordField);

                signUpRoot.add(newPassTextField, 1, 3);
                signUpRoot.add(conNewPassTextField, 1, 4);

            } else {
                signUpRoot.getChildren().removeAll(newPassTextField, conNewPassTextField);

                signUpRoot.add(passwordField, 1, 3);
                signUpRoot.add(ConPasswordField, 1, 4);
            }

        });

        signUpButton.setOnAction(e
                -> {

            if (emailField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || ConPasswordField.getText().trim().isEmpty() || fullNameField.getText().trim().isEmpty() || teleNumberField.getText().trim().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Please Fill All Fields");
                a.showAndWait();
            } else if (!fullNameField.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Full Name must contain only letters A-Z, a-z");
                a.showAndWait();
            } else if (!emailField.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Email must be valid and contain '@' and '.'");
                a.showAndWait();
            } else if (!passwordField.getText().equals(ConPasswordField.getText())) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Passwords do not match");
                a.showAndWait();
            } else if (passwordField.getText().length() < 6 || passwordField.getText().length() > 18) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password length must be between 6 and 18 characters");
                a.showAndWait();
            } else if (!passwordField.getText().matches(".*[a-z].*")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
                a.showAndWait();
            } else if (!passwordField.getText().matches(".*[A-Z].*")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
                a.showAndWait();
            } else if (!passwordField.getText().matches(".*\\d.*")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
                a.showAndWait();
            } else if (!passwordField.getText().matches(".*[@\\-_\\$].*")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
                a.showAndWait();
            } else if (teleNumberField.getText().length() != 10 || !teleNumberField.getText().matches("07\\d{8}")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Invalid Phone Number");
                a.showAndWait();
            } else if (!male.isSelected() && !female.isSelected()) {

                Alert a = new Alert(Alert.AlertType.ERROR, "Please select your Gender");
                a.showAndWait();
            } else {
                try {
                    // jdbc:mysql://localhost:3306/Project_JavaFX

                    // 1 - Conection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_JavaFX", "root", "");
                    // 2 - Statment
                    PreparedStatement st = con.prepareStatement("INSERT INTO USERS VALUES(?,?,?,?,?)");

                    // Checking no dupli email OR TeleNum
                    String checkNUMBER = "SELECT TELEPHONE FROM USERS WHERE TELEPHONE = ?";
                    PreparedStatement checkNUMState = con.prepareStatement(checkNUMBER);
                    checkNUMState.setString(1, teleNumberField.getText());
                    ResultSet result1 = checkNUMState.executeQuery();

                    String checkEMAIL = "SELECT EMAIL FROM USERS WHERE EMAIL = ?";
                    PreparedStatement checkEMState = con.prepareStatement(checkEMAIL);
                    checkEMState.setString(1, emailField.getText());
                    ResultSet result2 = checkEMState.executeQuery();

                    if (result1.next() || result2.next()) {
                        Alert zz = new Alert(Alert.AlertType.ERROR, "This Email/Number is already Used");
                        zz.showAndWait();
                    } else {
                        // 3 - Parameter
                        st.setString(1, fullNameField.getText());
                        st.setString(2, emailField.getText());
                        st.setString(3, passwordField.getText());
                        st.setString(4, teleNumberField.getText());
                        if (male.isSelected()) {
                            st.setString(5, "Male");
                        } else if (female.isSelected()) {
                            st.setString(5, "Female");
                        }

                        // 4 - Excute
                        st.execute();
                        Alert good = new Alert(Alert.AlertType.INFORMATION, "Registration is Complete");
                        good.showAndWait();

                        st.close();
                    }

                    result1.close();
                    result2.close();
                    checkEMState.close();
                    checkNUMState.close();
                    con.close();
                } catch (Exception ex) {
                    Alert bad = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    bad.showAndWait();
                }
            }
        });

        signUpRoot.add(title, 0, 0);
        signUpRoot.add(fullNameLabel, 0, 1);
        signUpRoot.add(fullNameField, 1, 1);
        signUpRoot.add(emailLabel, 0, 2);
        signUpRoot.add(emailField, 1, 2);
        signUpRoot.add(passwordLabel, 0, 3);
        signUpRoot.add(passwordField, 1, 3);
        signUpRoot.add(ConPasswordLabel, 0, 4);
        signUpRoot.add(ConPasswordField, 1, 4);
        signUpRoot.add(showPassword, 1, 5);
        signUpRoot.add(teleNumberLabel, 0, 6);
        signUpRoot.add(teleNumberField, 1, 6);
        signUpRoot.add(gender, 0, 7);
        signUpRoot.add(genderBox, 1, 7);
        signUpRoot.add(returnloginButton, 0, 8);
        signUpRoot.add(signUpButton, 1, 8);

        // ToolTip
        Tooltip nameTooltip = new Tooltip("Please Write Your Full Name");
        Tooltip.install(fullNameField, nameTooltip);
        fullNameField.setOnMouseEntered(e -> nameTooltip.show(fullNameField, e.getScreenX(), e.getScreenY() + 10));
        fullNameField.setOnMouseExited(e -> nameTooltip.hide());

        Tooltip emailTooltip = new Tooltip("*****@domain.(com, org, jo, etc...)");
        Tooltip.install(emailField, emailTooltip);
        emailField.setOnMouseEntered(e -> emailTooltip.show(emailField, e.getScreenX(), e.getScreenY() + 10));
        emailField.setOnMouseExited(e -> emailTooltip.hide());

        Tooltip passwordTooltip = new Tooltip(" 6 - 18 , Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
        Tooltip.install(passwordField, passwordTooltip);
        passwordField.setOnMouseEntered(e -> passwordTooltip.show(passwordField, e.getScreenX(), e.getScreenY() + 10));
        passwordField.setOnMouseExited(e -> passwordTooltip.hide());

        Tooltip ConPasswordTooltip = new Tooltip("ReConfirm Your Password");
        Tooltip.install(ConPasswordField, ConPasswordTooltip);
        ConPasswordField.setOnMouseEntered(e -> ConPasswordTooltip.show(ConPasswordField, e.getScreenX(), e.getScreenY() + 10));
        ConPasswordField.setOnMouseExited(e -> ConPasswordTooltip.hide());

        Tooltip passwordSHOWTooltip = new Tooltip(" 6 - 18 , Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
        Tooltip.install(newPassTextField, passwordSHOWTooltip);
        newPassTextField.setOnMouseEntered(e -> passwordSHOWTooltip.show(newPassTextField, e.getScreenX(), e.getScreenY() + 10));
        newPassTextField.setOnMouseExited(e -> passwordSHOWTooltip.hide());

        Tooltip ConPasswordSHOWTooltip = new Tooltip("ReConfirm Your Password");
        Tooltip.install(conNewPassTextField, ConPasswordSHOWTooltip);
        conNewPassTextField.setOnMouseEntered(e -> ConPasswordSHOWTooltip.show(conNewPassTextField, e.getScreenX(), e.getScreenY() + 10));
        conNewPassTextField.setOnMouseExited(e -> ConPasswordSHOWTooltip.hide());

        Tooltip teleTooltip = new Tooltip("Please Write Your Number");
        Tooltip.install(teleNumberField, teleTooltip);
        teleNumberField.setOnMouseEntered(e -> teleTooltip.show(teleNumberField, e.getScreenX(), e.getScreenY() + 10));
        teleNumberField.setOnMouseExited(e -> teleTooltip.hide());

        // setOnAction's 
        returnloginButton.setOnAction(e
                -> {
            primaryStage.setScene(s1);
            primaryStage.setTitle("Login Page");
        });

        GridPane root = new GridPane();
        root.add(signUpRoot, 0, 0);
        root.setAlignment(Pos.CENTER);

        root.getStyleClass().add("root-login");
        returnloginButton.getStyleClass().add("button-signup");
        signUpButton.getStyleClass().add("button-signup");
        showPassword.getStyleClass().add("radio-button-signup");
        signUpRoot.getStyleClass().add("login-root-login");
        title.getStyleClass().add("title-login");
        fullNameLabel.getStyleClass().add("label-login");
        emailLabel.getStyleClass().add("label-login");
        passwordLabel.getStyleClass().add("label-login");
        ConPasswordLabel.getStyleClass().add("label-login");
        teleNumberLabel.getStyleClass().add("label-login");
        gender.getStyleClass().add("label-login");
        male.getStyleClass().add("radio-button-signup");
        female.getStyleClass().add("radio-button-signup");

        return root;
    }

    public BorderPane Home(Stage x) {
        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(20, 20, 10, 20));

        //**********************************//
        GridPane productGrid = new GridPane();
        productGrid.setPadding(new Insets(30, 40, 40, 40));
        productGrid.setHgap(20);
        productGrid.setVgap(40);
        productGrid.setAlignment(Pos.CENTER);

        //**********************************//
        mainRoot.setTop(bar());
        mainRoot.setCenter(productGrid);

        Button b1 = new Button("");
        Button b2 = new Button("");
        Button b3 = new Button("");
        Button b4 = new Button("");

        b1.setPrefSize(450, 450);
        b2.setPrefSize(450, 450);
        b3.setPrefSize(450, 450);
        b4.setPrefSize(450, 450);
        
        String cpu1 = "file:///C:/Users/moath/OneDrive/Documents/NetBeansProjects/Project/src/Java_Project/cpu.png";
        String gpu1 = "file:///C:/Users/moath/OneDrive/Documents/NetBeansProjects/Project/src/Java_Project/gpu.png";
        String ram1 = "file:///C:/Users/moath/OneDrive/Documents/NetBeansProjects/Project/src/Java_Project/ram.png";
        String ssd1 = "file:///C:/Users/moath/OneDrive/Documents/NetBeansProjects/Project/src/Java_Project/ssd.png";
  

        b1.setStyle(
                "-fx-text-fill: #FFFFFF;"
                + "-fx-background-image: url('"+cpu1+"'); "
                + "-fx-background-size: cover; "
                + "-fx-background-repeat: no-repeat; "
                + "-fx-background-position: center center; ");
        b2.setStyle(
                "-fx-text-fill: #FFFFFF;"
                + "-fx-background-image: url('"+gpu1+"'); "
                + "-fx-background-size: cover; "
                + "-fx-background-repeat: no-repeat; "
                + "-fx-background-position: center center; ");
        b3.setStyle(
                "-fx-text-fill: #FFFFFF;"
                + "-fx-background-image: url('"+ram1+"'); "
                + "-fx-background-size: cover; "
                + "-fx-background-repeat: no-repeat; "
                + "-fx-background-position: center center; ");
        b4.setStyle(
                "-fx-text-fill: #FFFFFF;"
                + "-fx-background-image: url('"+ssd1+"'); "
                + "-fx-background-size: cover; "
                + "-fx-background-repeat: no-repeat; "
                + "-fx-background-position: center center; ");

        b1.setAlignment(Pos.CENTER);
        b2.setAlignment(Pos.CENTER);
        b3.setAlignment(Pos.CENTER);
        b4.setAlignment(Pos.CENTER);

        productGrid.addRow(0, b1, b2);
        productGrid.addRow(1, b3, b4);

        b1.setOnMouseEntered(e
                -> {
            b1.setStyle("-fx-text-fill: #FFFFFF;"
                    + "-fx-border-color: #ffffff; "
                    + "-fx-border-width: 2px; "
                    + "-fx-effect: dropshadow(gaussian, #ffffff, 20, 0.7, 0, 0);"
                    + "-fx-background-image: url('"+cpu1+"'); "
                    + "-fx-background-size: cover; "
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center center; ");
        });
        b1.setOnMouseExited(e
                -> {
            b1.setStyle("-fx-text-fill: #FFFFFF;"
                    + "-fx-background-image: url('"+cpu1+"'); "
                    + "-fx-background-size: cover; "
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center center; ");
        });

        b2.setOnMouseEntered(e
                -> {
            b2.setStyle("-fx-text-fill: #FFFFFF;"
                    + "-fx-border-color: #ffffff; "
                    + "-fx-border-width: 2px; "
                    + "-fx-effect: dropshadow(gaussian, #ffffff, 20, 0.7, 0, 0);"
                    + "-fx-background-image: url('"+gpu1+"'); "
                    + "-fx-background-size: cover; "
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center center; ");
        });
        b2.setOnMouseExited(e
                -> {
            b2.setStyle("-fx-text-fill: #FFFFFF;"
                    + "-fx-background-image: url('"+gpu1+"'); "
                    + "-fx-background-size: cover; "
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center center; ");
        });

        b3.setOnMouseEntered(e
                -> {
            b3.setStyle("-fx-text-fill: #FFFFFF;"
                    + "-fx-border-color: #ffffff; "
                    + "-fx-border-width: 2px; "
                    + "-fx-effect: dropshadow(gaussian, #ffffff, 20, 0.7, 0, 0);"
                    + "-fx-background-image: url('"+ram1+"'); "
                    + "-fx-background-size: cover; "
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center center; ");
        });
        b3.setOnMouseExited(e
                -> {
            b3.setStyle("-fx-text-fill: #FFFFFF;"
                    + "-fx-background-image: url('"+ram1+"'); "
                    + "-fx-background-size: cover; "
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center center;");
        });

        b4.setOnMouseEntered(e
                -> {
            b4.setStyle("-fx-text-fill: #FFFFFF;"
                    + "-fx-border-color: #ffffff; "
                    + "-fx-border-width: 2px; "
                    + "-fx-effect: dropshadow(gaussian, #ffffff, 20, 0.7, 0, 0);"
                    + "-fx-background-image: url('"+ssd1+"'); "
                    + "-fx-background-size: cover; "
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center center; ");
        });
        b4.setOnMouseExited(e
                -> {
            b4.setStyle("-fx-text-fill: #FFFFFF;"
                    + "-fx-background-image: url('"+ssd1+"'); "
                    + "-fx-background-size: cover; "
                    + "-fx-background-repeat: no-repeat; "
                    + "-fx-background-position: center center; ");
        });

        b1.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        b2.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        b3.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        b4.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        combo.setPrefSize(150, 30);
        combo.setStyle("-fx-background-color: #FFFFFF;");
        b1.setOnAction(e
                -> {
            combo.setValue("CPU");
            products.getChildren().clear();
            products.addRow(0, combo);
            products.addRow(1, cpuRoot);
            primaryStage.setScene(s4);
            primaryStage.setTitle("Products Page");

        });

        b2.setOnAction(e
                -> {
            combo.setValue("GPU");
            products.getChildren().clear();
            products.addRow(0, combo);
            products.addRow(1, gpuRoot);
            primaryStage.setScene(s4);
            primaryStage.setTitle("Products Page");
        });

        b3.setOnAction(e
                -> {
            combo.setValue("RAM");
            products.getChildren().clear();
            products.addRow(0, combo);
            products.addRow(1, ramRoot);
            primaryStage.setScene(s4);
            primaryStage.setTitle("Products Page");
        });

        b4.setOnAction(e
                -> {
            combo.setValue("SSD");
            products.getChildren().clear();
            products.addRow(0, combo);
            products.addRow(1, ssdRoot);
            primaryStage.setScene(s4);
            primaryStage.setTitle("Products Page");
        });

        mainRoot.getStyleClass().add("outer");
        productGrid.getStyleClass().add("inner");

        return mainRoot;
    }

    public BorderPane Settings(Stage x) {

        GridPane updatePassword = new GridPane();
        updatePassword.setPadding(new Insets(30, 40, 40, 40));
        updatePassword.setHgap(20);
        updatePassword.setVgap(20);
        updatePassword.setAlignment(Pos.CENTER);
        updatePassword.getStyleClass().add("outerSetting");

        Label updateURpassword = new Label("Changing Password:");

        Label newPasswordLabel = new Label("Password:");
        TextField newPasswordField = new PasswordField();
        newPasswordField.setPrefWidth(200);

        Label conNewPasswordLabel = new Label("Confirm Password:");
        TextField conNewPasswordField = new PasswordField();
        conNewPasswordField.setPrefWidth(200);

        // test fields to blend
        TextField newPassTextField = new TextField();
        TextField conNewPassTextField = new TextField();
        newPassTextField.setPrefWidth(200);
        conNewPassTextField.setPrefWidth(200);

        //to change between Password and Text Fields
        newPassTextField.textProperty().bindBidirectional(newPasswordField.textProperty());
        conNewPassTextField.textProperty().bindBidirectional(conNewPasswordField.textProperty());

        //non visibale
        CheckBox showPassword = new CheckBox("Show Password");
        showPassword.setOnAction(e -> {
            if (showPassword.isSelected()) {
                updatePassword.getChildren().removeAll(newPasswordField, conNewPasswordField);

                updatePassword.add(newPassTextField, 1, 1);
                updatePassword.add(conNewPassTextField, 1, 2);

            } else {
                updatePassword.getChildren().removeAll(newPassTextField, conNewPassTextField);

                updatePassword.add(newPasswordField, 1, 1);
                updatePassword.add(conNewPasswordField, 1, 2);
            }

        });
        
        Button numOfUsers = new Button("Number of Users Registered: ");
        numOfUsers.setPrefSize(230, 40);
        numOfUsers.setOnAction(e -> 
        {
                try {
                    // jdbc:mysql://localhost:3306/Project_JavaFX

                    // 1 - Conection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_JavaFX", "root", "");
                    // 2 - Statment
                    PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM USERS");
                    // 3 - Parameters
                    ResultSet result = st.executeQuery();
                    int userCount = 0;
                   if (result.next())
                         userCount = result.getInt("COUNT(*)");

                   numOfUsers.setText("Number of Users Registered: " + userCount);


                } catch (Exception ex) 
                {
                    Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    a.showAndWait();
                }
        });
        
        Button updatePass = new Button("Update Password");
        updatePass.setOnAction(e
                -> {

            if (!newPasswordField.getText().equals(conNewPasswordField.getText())) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Passwords do not match");
                a.showAndWait();
            } else if (newPasswordField.getText().length() < 6 || newPasswordField.getText().length() > 18) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password length must be between 6 and 18 characters");
                a.showAndWait();
            } else if (!newPasswordField.getText().matches(".*[a-z].*")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
                a.showAndWait();
            } else if (!newPasswordField.getText().matches(".*[A-Z].*")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
                a.showAndWait();
            } else if (!newPasswordField.getText().matches(".*\\d.*")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
                a.showAndWait();
            } else if (!newPasswordField.getText().matches(".*[@\\-_\\$].*")) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Password Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
                a.showAndWait();
            } else {
                try {
                    // jdbc:mysql://localhost:3306/Project_JavaFX

                    // 1 - Conection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_JavaFX", "root", "");
                    // 2 - Statment
                    PreparedStatement st = con.prepareStatement("UPDATE USERS SET PASSWORD=? WHERE EMAIL=?");
                    // 3 - Parameters
                    st.setString(1, newPasswordField.getText());
                    st.setString(2, userEmail);
                    // 4 - Execute
                    st.execute();

                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Your Password has been Updated");
                    a.showAndWait();

                } catch (Exception ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    a.showAndWait();
                }
            }
        });

        Button deleteAccount = new Button("Delete Account");
        deleteAccount.setOnAction(e
                -> {
            try {
                ButtonType delete = new ButtonType("Delete", ButtonBar.ButtonData.YES);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.NO);

                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "This action cannot be undone", cancel, delete);
                confirmAlert.setTitle("Delete Account");
                confirmAlert.setHeaderText("Are you sure you want to delete your account?");

                Button deleteButton = (Button) confirmAlert.getDialogPane().lookupButton(delete);
                deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                confirmAlert.setContentText("This action cannot be undone");
                Optional<ButtonType> result = confirmAlert.showAndWait();

                if (result.get() == delete) {
                    // 1 - Conection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_JavaFX", "root", "");
                    // 2 - Statment
                    PreparedStatement st = con.prepareStatement("DELETE FROM USERS WHERE EMAIL=?");
                    // 3 - Parameters
                    st.setString(1, userEmail);
                    // 4 - Execute
                    st.execute();

                    primaryStage.setScene(s1);
                    primaryStage.setTitle("Login Page");
                } else {
                    confirmAlert.close();
                }
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                a.showAndWait();
            }
        });

        Button logout = new Button("Log Out");
        logout.setOnAction(e
                -> {

            ButtonType logOut = new ButtonType("Log Out", ButtonBar.ButtonData.YES);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.NO);

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "You can Log-in any time", cancel, logOut);
            confirmAlert.setTitle("Loging Out of the Application");
            confirmAlert.setHeaderText("Are you sure you want to Log Out?");

            Button logOutButton = (Button) confirmAlert.getDialogPane().lookupButton(logOut);
            logOutButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");

            confirmAlert.setContentText("You can Log-in any time");
            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.get() == logOut) {
                primaryStage.setScene(s1);
                primaryStage.setTitle("Login Page");
            } else {
                confirmAlert.close();
            }

        });

        updatePassword.add(updateURpassword, 0, 0);
        updatePassword.add(newPasswordLabel, 0, 1);
        updatePassword.add(newPasswordField, 1, 1);
        updatePassword.add(conNewPasswordLabel, 0, 2);
        updatePassword.add(conNewPasswordField, 1, 2);
        updatePassword.add(showPassword, 1, 3);
        updatePassword.add(updatePass, 0, 4);
        updatePassword.add(deleteAccount, 0, 6);
        updatePassword.add(logout, 0, 7);
        updatePassword.add(numOfUsers, 1, 7);

        
        updateURpassword.getStyleClass().add("title-login");
        newPasswordLabel.getStyleClass().add("label-login");
        conNewPasswordLabel.getStyleClass().add("label-login");

        numOfUsers.getStyleClass().add("button-setting12");
        showPassword.getStyleClass().add("radio-button-signup");
        updatePass.getStyleClass().add("button-setting12");
        deleteAccount.getStyleClass().add("button-red");
        logout.getStyleClass().add("button-red");

        updatePass.setPrefSize(140, 35);
        deleteAccount.setPrefSize(120, 35);
        logout.setPrefSize(120, 35);

        GridPane accounInformation = new GridPane();
        accounInformation.setPadding(new Insets(30, 40, 40, 40));
        accounInformation.setHgap(20);
        accounInformation.setVgap(20);
        accounInformation.setAlignment(Pos.CENTER);
        accounInformation.getStyleClass().add("outerSetting");
        Label accountInfo = new Label("Account Information:");

        Label fullNameLabel = new Label("Full Name:");
        TextField fullNameField = new TextField();
        fullNameField.setPrefWidth(200);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        passwordField.setPrefWidth(200);

        Label teleNumberLabel = new Label("Phone Number:");
        TextField teleNumberField = new TextField("");
        teleNumberField.setPrefWidth(200);

        Label gender = new Label("Gender: ");
        TextField genderField = new TextField();
        genderField.setPrefWidth(200);

        ToggleButton show_hide = new ToggleButton("Show / Hide");
        show_hide.setPrefSize(120, 35);
        show_hide.setOnAction(e
                -> {
            if (show_hide.isSelected()) {
                try {
                    // jdbc:mysql://localhost:3306/Project_JavaFX

                    // 1 - Conection
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_JavaFX", "root", "");
                    // 2 - Statment
                    PreparedStatement st = con.prepareStatement("SELECT * FROM USERS WHERE EMAIL=?");
                    // 3 - Parameters
                    st.setString(1, userEmail);
                    ResultSet result = st.executeQuery();

                    String name = null;
                    String password = null;
                    String number = null;
                    String sex = null;

                    while (result.next()) {
                        name = result.getString("full name");
                        password = result.getString("password");
                        number = result.getString("telephone");
                        sex = result.getString("gender");
                    }

                    fullNameField.setText(name);
                    emailField.setText(userEmail);
                    passwordField.setText(password);
                    teleNumberField.setText(number);
                    genderField.setText(sex);

                } catch (Exception ex) {
                    Alert zz = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    zz.showAndWait();
                }
            } else {
                fullNameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                teleNumberField.setText("");
                genderField.setText("");
            }
        });

        accounInformation.add(accountInfo, 0, 0);
        accounInformation.add(fullNameLabel, 0, 1);
        accounInformation.add(fullNameField, 1, 1);
        accounInformation.add(emailLabel, 0, 2);
        accounInformation.add(emailField, 1, 2);
        accounInformation.add(passwordLabel, 0, 3);
        accounInformation.add(passwordField, 1, 3);
        accounInformation.add(teleNumberLabel, 0, 4);
        accounInformation.add(teleNumberField, 1, 4);
        accounInformation.add(gender, 0, 5);
        accounInformation.add(genderField, 1, 5);
        accounInformation.add(show_hide, 0, 6);

                
                
        accountInfo.getStyleClass().add("title-login");
        fullNameLabel.getStyleClass().add("label-login");
        emailLabel.getStyleClass().add("label-login");
        passwordLabel.getStyleClass().add("label-login");
        teleNumberLabel.getStyleClass().add("label-login");
        gender.getStyleClass().add("label-login");
        show_hide.getStyleClass().add("button-setting15");

        GridPane Main = new GridPane();
        Main.setPadding(new Insets(30, 40, 40, 40));
        Main.setHgap(20);
        Main.setVgap(20);
        Main.getStyleClass().add("inner");

        Main.add(accounInformation, 0, 0);
        Main.add(updatePassword, 1, 0);

        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(20, 20, 10, 20));
        mainRoot.getStyleClass().add("outer");
        mainRoot.setTop(bar());
        mainRoot.setCenter(Main);

        conNewPassTextField.textProperty().bindBidirectional(conNewPasswordField.textProperty());

        Tooltip q = new Tooltip(" 6 - 18 , Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
        Tooltip.install(passwordField, q);
        newPassTextField.setOnMouseEntered(e -> q.show(newPassTextField, e.getScreenX(), e.getScreenY() + 10));
        newPassTextField.setOnMouseExited(e -> q.hide());

        Tooltip y = new Tooltip(" 6 - 18 , Should Contain: A-Z, a-z, 0-9, ('@', '-', '_', '$')");
        Tooltip.install(newPasswordField, y);
        newPasswordField.setOnMouseEntered(e -> y.show(newPasswordField, e.getScreenX(), e.getScreenY() + 10));
        newPasswordField.setOnMouseExited(e -> y.hide());

        Tooltip z = new Tooltip("ReConfirm Your Password");
        Tooltip.install(newPassTextField, z);
        conNewPassTextField.setOnMouseEntered(e -> z.show(conNewPassTextField, e.getScreenX(), e.getScreenY() + 10));
        conNewPassTextField.setOnMouseExited(e -> z.hide());

        Tooltip v = new Tooltip("ReConfirm Your Password");
        Tooltip.install(conNewPasswordField, v);
        conNewPasswordField.setOnMouseEntered(e -> v.show(conNewPasswordField, e.getScreenX(), e.getScreenY() + 10));
        conNewPasswordField.setOnMouseExited(e -> v.hide());

        return mainRoot;
    }

    public BorderPane Products(Stage x) 
    {
        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(20, 20, 10, 20));

        //**********************************//
        products.setPadding(new Insets(30, 40, 40, 40));
        products.setHgap(20);
        products.setVgap(40);

        //**********************************//
        mainRoot.setTop(bar());
        mainRoot.setCenter(products);

        // cpu
        cpuRoot.setPadding(new Insets(20, 20, 10, 20));

        
        cpuRoot.addRow(1, createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\Rayzen9.png", "AMD Rayzen 9", 450),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\Rayzen7.png", "AMD Rayzen 7", 300),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\Rayzen5.png", "AMD Rayzen 5", 200));

        cpuRoot.addRow(2, createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\i7.jpg", "Intel iCore 7", 320),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\i5.jpg", "Intel iCore 5", 180),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\i3.jpg", "Intel iCore 3", 110));

        // gpu
        gpuRoot.setPadding(new Insets(20, 20, 10, 20));

        gpuRoot.addRow(1, createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\RTX5070.png", "RTX 5070", 600),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\RTX5060.png", "RTX 5060", 400));

        gpuRoot.addRow(2, createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\RTX9070.png", "RX 9070", 580),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\RTX9070.png", "RX 9060", 350));

        // ram
        ramRoot.setPadding(new Insets(20, 20, 10, 20));

        ramRoot.addRow(1, createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\32.png", "Samsung 32 Ram", 120),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\16.png", "Samsung 16 Ram", 70),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\8.png", "Samsung 8  Ram", 40));

        // ssd
        ssdRoot.setPadding(new Insets(20, 20, 10, 20));

        ssdRoot.addRow(1, createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\4TB.png", "Crucial 4TB", 350),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\2TB.png", "Crucial 2TB", 180),
                createProductItem("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\1TB.png", "Crucial 1TB", 90));

        combo.getItems().addAll("CPU", "GPU", "RAM", "SSD");
        combo.setValue("CPU");
        combo.setPrefSize(150, 30);
        products.addRow(0, combo);
        products.addRow(1, cpuRoot);

        combo.setOnAction(e
                -> {
            products.getChildren().clear();
            products.addRow(0, combo);

            switch (combo.getValue()) {
                case "CPU" ->
                    products.addRow(1, cpuRoot);
                case "GPU" ->
                    products.addRow(1, gpuRoot);
                case "RAM" ->
                    products.addRow(1, ramRoot);
                case "SSD" ->
                    products.addRow(1, ssdRoot);
                default -> {
                }
            }
        });

        mainRoot.getStyleClass().add("outer");
        products.getStyleClass().add("inner");
        gpuRoot.getStyleClass().add("innercombo");
        cpuRoot.getStyleClass().add("innercombo");
        ramRoot.getStyleClass().add("innercombo");
        ssdRoot.getStyleClass().add("innercombo");

        return mainRoot;
    }

    public BorderPane Cart(Stage x) {
        listView = new ListView<>(cartItems);
        listView.setPrefSize(500, 500);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        GridPane cartRoot = new GridPane();
        cartRoot.setPadding(new Insets(30, 40, 40, 40));
        cartRoot.setHgap(20);
        cartRoot.setVgap(40);
        cartRoot.setAlignment(Pos.CENTER);
        cartRoot.getStyleClass().add("inner");

        Button cost = new Button("Total Cost = " + showSum());
        cost.setPrefSize(130, 30);
        cost.setOnAction(e -> {
            cost.setText("Total Cost = " + showSum());
        });

        Button buy = new Button("Buy");
        buy.setPrefSize(130, 30);
        buy.setOnAction(e
                -> {
            ButtonType Buy = new ButtonType("Buy", ButtonBar.ButtonData.YES);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.NO);

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "This action cannot be undone", cancel, Buy);
            confirmAlert.setTitle("Confirm Purchase");
            confirmAlert.setHeaderText("Are you sure you want to complete this purchase?");
            Optional<ButtonType> result = confirmAlert.showAndWait();

            if (result.get() == Buy) {
                if (!listView.getItems().isEmpty()) {
                    updateInventory();
                    listView.getItems().clear();
                    cartItems.clear();
                    productQuantities.clear();
                    cart.clear();
                } else if (listView.getItems().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Shop Now -__-");
                    alert.setHeaderText("Shopping Cart Information");
                    alert.setContentText("Your shopping cart is Empty");
                    alert.showAndWait();
                }
            }

        });

        Button reAdd = new Button("re-Add");
        reAdd.setPrefSize(130, 30);
        reAdd.setOnAction(e
                -> // handleQuantityChange -- > numberOfItems ,,, updateCart --> proName
                {
                    ObservableList<String> selected = listView.getSelectionModel().getSelectedItems();
                    List<String> copy = new ArrayList<>(selected);

                    for (String item : copy) {
                        String name = item.split("x")[0].trim();
                        if ((productQuantities.getOrDefault(name, 0)) > 0) {
                            handleQuantityChange(name, 1);
                        }
                    }
                });
        Button remove = new Button("Remove");
        remove.setPrefSize(130, 30);
        remove.setOnAction(e
                -> // handleQuantityChange -- > numberOfItems ,,, updateCart --> proName
                {
                    ObservableList<String> selected = listView.getSelectionModel().getSelectedItems();
                    List<String> copy = new ArrayList<>(selected);

                    for (String item : copy) {
                        String name = item.split("x")[0].trim();
                        if ((productQuantities.getOrDefault(name, 0)) > 0) {
                            handleQuantityChange(name, -1);
                        }
                    }
                });

        VBox buttoooons = new VBox(10, reAdd, remove, cost, buy);
        reAdd.getStyleClass().add("button-login");
        remove.getStyleClass().add("button-login");
        cost.getStyleClass().add("total-cost");
        buy.getStyleClass().add("button-login");

        cartRoot.add(listView, 0, 0);
        cartRoot.add(buttoooons, 1, 0);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20, 20, 10, 20));
        root.getStyleClass().add("outer");
        root.setTop(bar());
        root.setCenter(cartRoot);

        return root;
    }

    public GridPane bar() 
    {
        HBox topRoot = new HBox();
        topRoot.setPadding(new Insets(15));
        topRoot.setSpacing(40);
        topRoot.setAlignment(Pos.CENTER);

        Button home = new Button("Home");
        Button settings = new Button("Settings");
        Button productes = new Button("Products");
        Button cart1 = new Button("Cart");
        ToggleButton darkLight = new ToggleButton("Dark Mode");
        darkLight.setOnAction(e -> {
            File cssFile = darkLight.isSelected()
                    ? new File("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\dark-theme.css")
                    : new File("C:\\Users\\moath\\OneDrive\\Documents\\NetBeansProjects\\Project\\src\\Java_Project\\light-theme.css");

            String cssPath = cssFile.toURI().toString();

            Scene[] scenes = {s1, s2, s3, s4, s5, s6};

            for (Scene scene : scenes) {
                if (scene != null) {
                    scene.getStylesheets().clear();
                    scene.getStylesheets().add(cssPath);
                }
            }

            darkLight.setText(darkLight.isSelected() ? "Light Mode" : "Dark Mode");
        });

        home.setPrefSize(170, 35);
        settings.setPrefSize(170, 35);
        productes.setPrefSize(170, 35);
        cart1.setPrefSize(170, 35);
        darkLight.setPrefSize(170, 35);

        topRoot.getChildren().addAll(home, productes, cart1, settings, darkLight);

        home.setOnAction(e
                -> {
            primaryStage.setScene(s3);
            primaryStage.setTitle("Home Page");
        });

        productes.setOnAction(e
                -> {
            primaryStage.setScene(s4);
            primaryStage.setTitle("Products Page");
        });

        cart1.setOnAction(e
                -> {
            primaryStage.setScene(s5);
            primaryStage.setTitle("Cart Page");
        });

        settings.setOnAction(e
                -> {
            primaryStage.setScene(s6);
            primaryStage.setTitle("Settings Page");
        });

        GridPane ScenesBar = new GridPane();
        ScenesBar.setAlignment(Pos.CENTER);
        ScenesBar.add(topRoot, 0, 0);

        home.getStyleClass().add("buttonBar");
        productes.getStyleClass().add("buttonBar");
        settings.getStyleClass().add("buttonBar");
        cart1.getStyleClass().add("buttonBar");
        darkLight.getStyleClass().add("buttonBar");
        
        ScenesBar.getStyleClass().add("ScenesBar");
        topRoot.getStyleClass().add  ("toprootbar");

        return ScenesBar;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public VBox createProductItem(String imagePath, String productName, int price) 
    {
        Label nameLabel = new Label(productName);
        Label pricing = new Label(price + "$");
        pricing.setTextFill(Color.DARKGREEN);
        Label priceLabel = new Label("Price: ");

        int quantity = productQuantities.getOrDefault(productName, 0);
        productQuantities.put(productName, quantity);

        Label quantityLabel = new Label("" + quantity);
        quantityLabels.put(productName, quantityLabel);

        Button add = new Button("+");
        Button sub = new Button("-");

        // actions
        add.setOnAction(e
                -> {
            handleQuantityChange(productName, 1);
        });
        sub.setOnAction(e
                -> {
            handleQuantityChange(productName, -1);
        });

        add.setPrefSize(25, 25);
        sub.setPrefSize(25, 25);
        add.getStyleClass().add("button-setting15");
        sub.getStyleClass().add("button-setting15");

        HBox quantityBox = new HBox(5, sub, quantityLabel, add);
        HBox pricingBox = new HBox(5, priceLabel, pricing);

        VBox productCard = new VBox(5);
        productCard.getChildren().addAll(AddPicture(imagePath, 100), nameLabel, pricingBox, quantityBox);
        productCard.setPadding(new Insets(5));
        productCard.setSpacing(10);
        productCard.setPrefSize(200, 170);
        productCard.getStyleClass().add("productCard");

        return productCard;
    }

    public void handleQuantityChange(String productName, int change) {
        int currentQty = productQuantities.get(productName);
        int newQty = currentQty + change;
        if (newQty >= 0) {
            productQuantities.put(productName, newQty);
            quantityLabels.get(productName).setText(String.valueOf(newQty));
        }
        updateCart(productName, newQty);
    }

    public void updateCart(String productName, int newQuantity) {
        cartItems.clear();

        if (newQuantity > 0) {
            cart.put(productName, newQuantity);
        } else {
            cart.remove(productName);
        }

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            cartItems.add(entry.getKey() + "  x" + entry.getValue());
        }
    }

    public void updateInventory() {
        for (String item : cartItems) {
            String[] parts = item.split("x");
            if (parts.length < 2) {
                continue;
            }

            String productName = parts[0];
            int quantity = Integer.parseInt(parts[1].trim());

            if (quantity > 0) {
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_JavaFX", "root", "");
                    PreparedStatement st = conn.prepareStatement("UPDATE items SET quantity = quantity - ? WHERE name = ?");
                    st.setInt(1, quantity);
                    st.setString(2, productName.replace(" ", ""));
                    st.executeUpdate();
                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update inventory: " + ex.getMessage());
                    alert.showAndWait();
                }
            }
        }
    }

    public int showSum() {
        int SumOfList = 0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_JavaFX", "root", "");

            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                String productName = entry.getKey();
                int quantity = entry.getValue();

                PreparedStatement priceST = conn.prepareStatement("Select PRICE FROM ITEMS WHERE name = ?");

                priceST.setString(1, productName.replace(" ", ""));
                ResultSet priceCheck = priceST.executeQuery();
                if (priceCheck.next()) {
                    int sumSum = priceCheck.getInt("PRICE") * quantity;
                    SumOfList += sumSum;
                }
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error" + ex.getMessage());
            alert.showAndWait();
        }
        return SumOfList;
    }

    public FlowPane AddPicture(String filepath, int width) {
        Image img = null;
        try {
            FileInputStream fis = new FileInputStream(filepath);
            img = new Image(fis);
        } catch (Exception e) {
            System.out.println("File not found or error reading the image.");
            return new FlowPane(); // Return empty pane on error
        }

        ImageView imgView = new ImageView(img);
        imgView.setPreserveRatio(true);
        imgView.setFitWidth(width);

        FlowPane pic = new FlowPane(imgView);
        pic.setAlignment(Pos.CENTER);
        pic.setPadding(new Insets(7));
        return pic;
    }

}
