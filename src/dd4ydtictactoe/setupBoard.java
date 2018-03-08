/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dd4ydtictactoe;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 *
 * @author daviddean
 */
public interface setupBoard {
    public void handleButtonAction(ActionEvent event);
    public void menuClickHandler(ActionEvent event);
    //public void highlightWinningLine(Button first, Button second, Button third);
}
