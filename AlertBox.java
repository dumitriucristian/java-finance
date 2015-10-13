/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 *
 * @author web
 */
public class AlertBox {
    
    public static void display(String title, String content, int width, int height){
        
         //create stage
        Alert emptyField = new Alert(AlertType.INFORMATION);
        emptyField.setTitle(title);
        emptyField.setHeaderText(null);
        emptyField.setContentText(content);
        emptyField.setHeight(height);
        emptyField.setResizable(true);
        
        //show the scene
       emptyField.showAndWait();
    }
    
}
