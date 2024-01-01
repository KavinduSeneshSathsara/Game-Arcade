package lk.ijse.GameCafe.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.GameCafe.dto.UserDto;
import lk.ijse.GameCafe.model.UserModel;
import lk.ijse.GameCafe.util.Navigation;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

public class signUpFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private JFXCheckBox txtTerms;
    @FXML
    private JFXButton btnSignUp;

    private static final String EMAIL_USERNAME = "gamearcade44444@gmail.com"; // replace with your email
    private static final String EMAIL_PASSWORD = "Kavindu4545"; // replace with your email password

    @FXML
    public void initialize() {
        // Bind the disable property of the signup button to the not selected property of the checkbox
        btnSignUp.disableProperty().bind(txtTerms.selectedProperty().not());
    }

    @FXML
    public void btnSignupOnAction(ActionEvent actionEvent) throws MessagingException {
        boolean isUserValidated = ValidateUser();

        if (!isUserValidated){
            return;
        }

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String email = txtEmail.getText();

        if (!password.equals(confirmPassword)) {
            new Alert(Alert.AlertType.ERROR, "Passwords do not match.").show();
            return; // Stop execution if passwords don't match
        }

        UserDto dto = new UserDto(username, password, email);
        UserModel userModel = new UserModel();

        try {
            boolean isSaved = userModel.saveUser(dto);
            if (isSaved) {
                if (txtTerms.isSelected()) {
//                     sendConfirmationEmail(email);
                    new Alert(Alert.AlertType.INFORMATION, "A confirmation email has been sent to your address.").show();
                }

                AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login Form");
                stage.centerOnScreen();
            } else {
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

//       private void sendConfirmationEmail(String EMAIL_USERNAME) throws SQLException, MessagingException {
//
//
//           UserModel userModel = new UserModel();
//
//           UserDto userDto = userModel.getEmail(EMAIL_USERNAME);
//           System.out.println(userDto.getEmail());
//
//           EmailController.sendEmail(userDto.getEmail(), "Verification Code for Password Reset",   "");
//
////        // Set up the properties for the email server
////        Properties properties = new Properties();
////        properties.put("mail.smtp.auth", "true");
////        properties.put("mail.smtp.starttls.enable", "true");
////        properties.put("mail.smtp.host", "smtp.gmail.com"); // Change this if using a different email provider
////        properties.put("mail.smtp.port", "587");
////        properties.put("mail.debug", "true");
////        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
////        properties.put("mail.smtp.ssl.ciphersuites", "TLS_AES_128_GCM_SHA256");
////
////        // Get the Session object
////        Session session = Session.getInstance(properties, new Authenticator() {
////            @Override
////            protected PasswordAuthentication getPasswordAuthentication() {
////                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
////            }
////        });
////
////        try {
////            // Create a MimeMessage object
////            Message message = new MimeMessage(session);
////
////            // Set the sender and recipient addresses
////            message.setFrom(new InternetAddress(EMAIL_USERNAME));
////            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
////
////            // Set the email subject and content
////            message.setSubject("Confirmation Email");
////            message.setText("Thank you for signing up with GameCafe. Your account has been created successfully.");
////
////            // Send the email
////            Transport.send(message);
////
////        } catch (MessagingException e) {
////            e.printStackTrace();
////        }
//    }
    private boolean ValidateUser() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();

        // Validate username
        boolean isUsernameValid = username.matches("^[a-zA-Z0-9_]{3,20}$");
        if (!isUsernameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid username. It should be 3-20 characters and can contain letters, numbers, and underscores.").show();
            return false;
        }

        // Validate password
        boolean isPasswordValid = password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        if (!isPasswordValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid password. It should be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one digit.").show();
            return false;
        }

        // Validate email
        boolean isEmailValid = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        if (!isEmailValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid email address.").show();
            return false;
        }

        return true;
    }


    @FXML
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

//    @FXML
//    public void checkBoxONMouseClicked(javafx.scene.input.MouseEvent mouseEvent) {
//    }
}
