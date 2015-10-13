/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author web
 */
public class Budget extends Application {
     
    Stage window;   //set stage variable
    BorderPane layout; //set layout as Border Pane
    
    
    @Override
    public void start(Stage primaryStage) {
       
        window = primaryStage;
        //set primary staeg to full screen 
        //primaryStage.setMaximized(true);
         
        window.setTitle("Main Stage");
        
       
        //alert message
        
        Alert intro = new Alert(AlertType.INFORMATION);
        intro.setTitle("Welcome");
        intro.setHeaderText(null);
        intro.setContentText("This is a demo content with a demo user. "
                +  "\nIf you wish to use your own user please create one and switch user."
                + "\n Thank you ");
        intro.setHeight(400);
        intro.setResizable(true);
         
        //create main menu
        MainMenu mainMenu = new MainMenu();
        
        //ceate label
        Label userNameLabel = new Label("User Name: ");
        Label registrationLabel = new Label("Registration");
        
        //get user data
         User user=new User();
        int userId = user.getUserId();
        String userName = user.getFirstName();
        
        //get user registrationdate and format
        Date registrationDate = user.getRegistrationDate();
        System.out.println(registrationDate);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formatedRegistrationDate = df.format(registrationDate);
        
        //create the content for content
        Text contentText = new Text(userName);
        Text registration  = new Text(formatedRegistrationDate);
         contentText.setFill(Color.BLACK);
        
        //display current date and time 
        

         
           
        //create a button to open an alert
        Button alertButton = new Button("Apasa");
        alertButton.setOnAction(e -> {
            System.out.println("5s2");
        });
        // nr of days till the end of the year
        
         //create content layot as grid
        GridPane content = new GridPane();
         content.setConstraints(userNameLabel,0,1);
        content.setConstraints(contentText,1,1);
       
        content.setConstraints(registrationLabel,0,2);
        content.setConstraints(registration, 1, 2);
        
        content.getChildren().addAll(contentText, userNameLabel, registration , registrationLabel, alertButton);
      
        
                
                
      
        //create footer text
       Text footerText = new Text("Aici ar trebuii sa fie un text centrat");
       footerText.setFill(Color.WHITE);
       footerText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
       
       //create footer box
       HBox footerBox = new HBox(0);
       footerBox.setStyle("-fx-background-color: #336699");
       footerBox.setAlignment(Pos.CENTER);
       footerBox.setPadding(new Insets(10) );
       footerBox.getChildren().add(footerText);
      
       
       
       //create layout
        layout = new BorderPane();
        layout.setTop( MainMenu.hboxMenu );
     
        layout.setLeft(content);
        layout.setBottom(footerBox);
        
        //set scene
        Scene scene = new Scene(layout, 900, 600);
        window.setScene(scene);
        window.show();
       // intro.showAndWait();
        
    }

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
