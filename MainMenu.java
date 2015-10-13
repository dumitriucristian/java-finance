/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budget;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author web
 */
public class MainMenu {
    
    //create menus
    static public HBox hboxMenu = new HBox(0);
    static public Menu income = new Menu("Income");
    static public Menu expense = new Menu("Expense");
    static public Menu graphs =  new Menu("Graphs");
    static public Menu balance = new Menu("Balance");
    static public Menu utils = new Menu("Utils");
    static public Menu projections = new Menu("Projections");
    static public Menu budget = new Menu("Budget");
    static public Menu users  = new Menu ("Users");
    
    //create submenu
    static public MenuItem addIncome = new MenuItem("Add Income..");
    static public MenuItem listIncomes = new MenuItem("List All Incomes");
     static public MenuItem listIncomeTypes = new MenuItem("List Income Types");
    static public MenuItem addExpenses = new MenuItem("Add Expense...");
    static public MenuItem listAllExpenses = new MenuItem("List All Expenses");
    
    static public MenuItem incomeGraph = new MenuItem("Income Graph");
    static public MenuItem expenseGraph = new MenuItem("Expense Graph");
    static public MenuItem balanceGraph = new MenuItem("Balance Graph");
    static public MenuItem currentBalance = new MenuItem("Current Ballance");
    static public MenuItem balanceAt = new MenuItem("Balance At");
    static public MenuItem recurrentPayments = new MenuItem("Recurrent Payments");
    static public MenuItem recurrentIncomes = new MenuItem("Recurrent Incomes");
    
    static public MenuItem createProjection = new MenuItem("Create Projections");
    static public MenuItem compareProjection = new MenuItem("Compare Projections");
    static public MenuItem createBudgets = new MenuItem("Create Budgets");
    static public MenuItem compareBudgets = new MenuItem("Compare Budgets");
    static public MenuItem switchUser = new MenuItem("Switch User");
    static public MenuItem listAllUsers = new MenuItem("List All Users");
    static public MenuItem logIn = new MenuItem("logIn");
    static public MenuItem logOff = new MenuItem("logOff");
     
    public MainMenu(){

     
        //add submenu      
       income.getItems().addAll(addIncome, listIncomes, listIncomeTypes);
       expense.getItems().addAll(addExpenses, listAllExpenses);
       graphs.getItems().addAll(incomeGraph, expenseGraph, balanceGraph);
       balance.getItems().addAll(currentBalance, balanceAt);
       utils.getItems().addAll(recurrentPayments , recurrentIncomes);
       projections.getItems().addAll(createProjection, compareProjection);
       budget.getItems().addAll(createBudgets, compareBudgets);
       users.getItems().addAll(switchUser, listAllUsers, logIn, logOff);
         
        
         //menuLeft
        MenuBar mainMenu = new MenuBar();
        mainMenu.autosize();
        mainMenu.getMenus().addAll(income, expense, graphs, balance, budget, projections, utils, users);
       
        

        
         //hbox
        hboxMenu.getChildren().addAll(mainMenu);
        
        
            addIncome.setOnAction(e -> {
                //create a scene
           
                AddIncomeWindow.display();
               // window.setScene(scene);
               // window.show();
                //create a gridPane
                
        });        
        
        listIncomes.setOnAction( e -> {
            Income incomev = new Income();
            try{
              ResultSet rs =   incomev.getUserIncomes(8);
              while(rs.next()){
                  
                  String income_name = rs.getString(3);
                  System.out.println("Income name:" + income_name);
                  HBox allIncomeBox = new HBox(10);
              }
                 
            }catch(SQLException ev){
                System.out.println("eroare ba" +ev);
            }
            System.out.println("Daca merge");
        });
        
       listIncomeTypes.setOnAction( e ->{

            try{
                Map  <Integer, String> incomeMap =  Income.getIncomeTypes();
                    for( Integer  key  : incomeMap.keySet() ){
                       String incomeName = incomeMap.get(key);
                       int incomeTypeId = key;
                       System.out.println( "id: "+ incomeTypeId +" incomeName: " +incomeName);
                    }
               }catch(SQLException er){   
                    System.out.println(er);
               };
            
        });
    }
    
}
