/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.sql.SQLException;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author web
 */
public class AddIncomeWindow {
    
   static void display(){
       //create stage
      Stage secondWindow = new Stage();
      secondWindow.initModality(Modality.APPLICATION_MODAL);
      secondWindow.setTitle("Add income form");
      secondWindow.setMinWidth(450);
       secondWindow.setMinHeight(280);
      
      ///create a label
      Label labelItype = new Label("Income Type:");
      Label labelSuma = new Label("Value:  ");
      Label labelExplicatie =  new Label("Explanation: ");
      Label labelData = new Label("Income date: ");
      Label labelRecursiva = new Label("Recursiv: ");
      
       //create choiceBox
      ChoiceBox incomeType = new ChoiceBox();
        //get income types
        
          try{
             Map  <Integer, String> incomeMap =  Income.getIncomeTypes();
                 for( Integer  key  : incomeMap.keySet() ){
                    String incomeName = incomeMap.get(key);
                    int incomeTypeId = key;
                    System.out.println( "id: "+ incomeTypeId +" incomeName: " +incomeName);
                        incomeType.getItems().add(incomeName);
                 }
             }catch(SQLException er){   
                 System.out.println(er);
             }
        incomeType.setValue("salaries");
       
      TextField suma = new TextField();
      TextField explicatie =  new TextField();
      DatePicker data = new DatePicker();
      CheckBox recursiv = new CheckBox();
      
    //create layout
      GridPane layout = new GridPane();
      
      
       //create button
     Button addIncomeButton =  new Button("Add Income");
 
     //on click income buttone
     addIncomeButton.setOnAction(e ->{
         //check for null
         
         if ( suma.getText().isEmpty() ){
             AlertBox.display("Alerta","Campul Suma este gol",300, 200);
        
             System.out.println("ALERTA!");
         }else{
                //validation for integer
                try{
                   Integer.parseInt(suma.getText());
               }catch(Exception er){
                   AlertBox.display("Alerta","Acest camp primeste doar cifre",300, 200);
               }
         
         }
         
     
         System.out.println("clicka clicka: ");
         //check for empty fields
         
     });
     
     layout.setConstraints(labelItype,1,1);
     layout.setConstraints(labelSuma,1,2);
     layout.setConstraints(labelExplicatie,1,3);
     layout.setConstraints(labelData,1,4);
     layout.setConstraints(labelRecursiva,1,5);
     
     layout.setConstraints(incomeType,2,1);
     layout.setConstraints(suma,2,2);
     layout.setConstraints(explicatie,2,3);
     layout.setConstraints(data,2,4);
     layout.setConstraints(recursiv,2,5);
     layout.setConstraints(addIncomeButton, 4,2);
    
     layout.setPadding(new Insets(10));
     layout.setVgap(10);
     layout.setHgap(10);
     
     // layout.setConstraints(testLabel ,1,1);
     layout.getChildren().addAll(labelItype, labelSuma, labelExplicatie,labelData, labelRecursiva);
     layout.getChildren().addAll(incomeType,suma, explicatie, data, recursiv,addIncomeButton);
      
     //add layout to the scene
      Scene scene = new Scene(layout, 100, 100);
      //seteaza window
      secondWindow.setScene(scene);
      //show the scene
       secondWindow.showAndWait();
   }
    
}
