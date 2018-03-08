/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dd4ydtictactoe;

//import java.net.URL;
//import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author daviddean
 */
public class TicTacToeController implements setupBoard, Initializable {
    
    @FXML Button btn1 = new Button();
    @FXML Button btn2 = new Button();
    @FXML Button btn3 = new Button();
    @FXML Button btn4 = new Button();
    @FXML Button btn5 = new Button();
    @FXML Button btn6 = new Button();
    @FXML Button btn7 = new Button();
    @FXML Button btn8 = new Button();
    @FXML Button btn9 = new Button();
    
    String[] content = new String[9];
    BufferedReader read;
    FileWriter write;
    
    @FXML GridPane gameBoard;
    
    private boolean isFirstPlayer = true;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setters();
    }
    
    public void setters(){       
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");      
    } 
    
    public void fillArray(){
        content[0] = btn1.getText();
        content[1] = btn2.getText();
        content[2] = btn3.getText();
        content[3] = btn4.getText();
        content[4] = btn5.getText();
        content[5] = btn6.getText();
        content[6] = btn7.getText();
        content[7] = btn8.getText();
        content[8] = btn9.getText();
    }
    
    @Override
    public void handleButtonAction(ActionEvent event) {
        
        Button btnClicked = (Button)event.getTarget();
        String btnLabel = btnClicked.getText();
        
        if("".equals(btnLabel) && isFirstPlayer){
            btnClicked.setText("X");
            isFirstPlayer = false;
        } else if("".equals(btnLabel) && !isFirstPlayer){
            btnClicked.setText("O");
            isFirstPlayer = true;
        }
        
        //check for winner
        findThreeInARow();
        
    }
    
    private boolean findThreeInARow(){
        
        //need to check all 3 rows/ 3 columns
        //row1
//        if (""!=btn1.getText() && btn1.getText() == btn2.getText() && btn2.getText() == btn3.getText()){
//		   highlightWinningLine(btn1,btn2,btn3);
//		   return true;
//	   }
        
        if(!btn1.getText().equals("") && btn1.getText().equals(btn2.getText()) && btn2.getText().equals(btn3.getText())){
            highlightWinningLine(btn1, btn2, btn3);
            return true;
        }
        
        //row 2
        if(!btn4.getText().equals("") && btn4.getText().equals(btn5.getText()) && btn5.getText().equals(btn6.getText())){
            highlightWinningLine(btn4, btn5, btn6);
            return true;
        }
        
        //row 3
        if(!btn7.getText().equals("") && btn7.getText().equals(btn8.getText()) && btn8.getText().equals(btn9.getText())){
            highlightWinningLine(btn7, btn8, btn9);
            return true;
        }
        
        //col 1
        if(!btn4.getText().equals("") && btn1.getText().equals(btn4.getText()) && btn4.getText().equals(btn7.getText())){
            highlightWinningLine(btn1, btn4, btn7);
            return true;
        }
        
        //col 2
        if(!btn2.getText().equals("") && btn2.getText().equals(btn5.getText()) && btn5.getText().equals(btn8.getText())){
            highlightWinningLine(btn2, btn5, btn8);
            return true;
        }
        
        //col 3
        if(!btn3.getText().equals("") && btn3.getText().equals(btn6.getText()) && btn6.getText().equals(btn9.getText())){
            highlightWinningLine(btn3, btn6, btn9);
            return true;
        }
        
        //diag left -> right
        if(!btn1.getText().equals("") && btn1.getText().equals(btn5.getText()) && btn5.getText().equals(btn9.getText())){
            highlightWinningLine(btn1, btn5, btn9);
            return true;
        }
        
        //diag left -> right
        if(!btn3.getText().equals("") && btn3.getText().equals(btn5.getText()) && btn5.getText().equals(btn7.getText())){
            highlightWinningLine(btn3, btn5, btn7);
            return true;
        }
        
        //else
        return false;
        
    }
    
    //@Override
    private void highlightWinningLine(Button first, Button second, Button third){
        first.getStyleClass().add("winning-button");
        second.getStyleClass().add("winning-button");
        third.getStyleClass().add("winning-button");
        
        Alert alert = new Alert(AlertType.INFORMATION);
        
        if(first.getText().equals("X")){
            alert.setTitle("Gameover");
            alert.setHeaderText("Congrats Player 1!");
            alert.setContentText("To play a new game, simply hit menu -> Play");
            alert.showAndWait();
        } else {
            alert.setTitle("Gameover");
            alert.setHeaderText("Congrats Player 2!");
            alert.setContentText("To play a new game, simply hit menu -> Play");
            alert.showAndWait();
        }
        
    }
    
    @Override
    public void menuClickHandler(ActionEvent event){
        
        MenuItem clickedMenu = (MenuItem)event.getTarget();
        String menuLabel = clickedMenu.getText();
        
        switch(menuLabel){
            case "Play":
                ObservableList<Node> buttons = gameBoard.getChildren();
            
                buttons.forEach(button -> {
                    ((Button) button).setText("");
                    button.getStyleClass().remove("winning-button");
                });
                break;
            case "About":
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("About Page");
                alert.setHeaderText("Having some trouble with tictactoe? No worries");
                alert.setContentText("Just find a friend to play with. 1st player will be X's and 2nd O's");

                alert.showAndWait();
                break;
            case "Quit":{
                System.exit(0);
                break;
            }
            default:
                System.exit(0);
        }
        
    }  
    
    @FXML
    private void handleLoadGame(ActionEvent event){
        
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) gameBoard.getScene().getWindow();
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files","*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        
        if(file != null){

            try{                     
                read = new BufferedReader(new FileReader("/Users/daviddean/Desktop/CS3330/Dd4ydTicTacToe/src/dd4ydtictactoe/buttonLog.txt"));
                String line = "";
                for(int i = 0; i < 9; i++){
                    if((line = read.readLine()) != null){
                        content[i] = line;
                    } else{
                        content[i] = "";
                    }
                }  
            } catch(FileNotFoundException e){
               e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            } finally{
                if(read != null){
                    try{
                       read.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
            
        btn1.setText(content[0]);
        btn2.setText(content[1]);
        btn3.setText(content[2]);
        btn4.setText(content[3]);
        btn5.setText(content[4]);
        btn6.setText(content[5]);
        btn7.setText(content[6]);
        btn8.setText(content[7]);
        btn9.setText(content[8]);
   
    }
    
    @FXML
    private void handleSaveGame(ActionEvent event){
        
        fillArray();
        
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) gameBoard.getScene().getWindow();
        
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files","*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        
        if(file != null){
            try{
                write = new FileWriter("/Users/daviddean/Desktop/CS3330/Dd4ydTicTacToe/src/dd4ydtictactoe/buttonLog.txt");
                for(int i = 0; i < 9; i++){
                    write.write(content[i] + "\n");
                }
            } catch(IOException e){
                e.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            } finally{
                if(write != null){
                    try{
                        write.close();
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        
    }
    
}


