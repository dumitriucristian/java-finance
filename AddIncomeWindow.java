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
        ChoiceBox <String> incomeType = new ChoiceBox();
     
        //get income types
        try{
            Map  <Integer, String> incomeMap =  Income.getIncomeTypes();
            for( Integer  key  : incomeMap.keySet() ){
                  String incomeName = incomeMap.get(key);
                  int incomeTypeId = key;
                  incomeType.getItems().add(incomeName);
            }
        }catch(SQLException er){   
               System.out.println(er);
        }
        incomeType.getItems().add("Choose Income");  
        incomeType.setValue("Choose Income");
        
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
        
            //check for empty income
            if(suma.getText().isEmpty()){
               
            //}catch(Exception sum){
                AlertBox.display("Alerta","Field sum is empty",300, 200);
            }else{
                try{
                   int sumVal = Integer.parseInt(suma.getText());
                   
                }catch(Exception er){
                    AlertBox.display("Alerta","Value field require only nr's",300, 200);
                }
                
                  //check incomeType to be chosen
            String TypeVal = incomeType.getValue();
            if( TypeVal  ==  "Choose Income" ){
               AlertBox.display("Alerta","Chose an income type",300, 200);
            }
               String explVal = explicatie.getText();
            if(explicatie.getText().isEmpty() ){
                AlertBox.display("Alerta","Small explanation required",300, 200);
            }
            
            try{
                 String dataVal = data.getValue().toString();
                 
            }catch(Exception dat){
                AlertBox.display("Alerta","Please choose a date",300, 200);
            }
            
              int sumVal = Integer.parseInt(suma.getText());    
              String dataVal = data.getValue().toString();
              System.out.println( sumVal +dataVal  + explVal);
        }   
      
         //create a confirmation window with data
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
