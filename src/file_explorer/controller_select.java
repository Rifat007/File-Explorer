/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_explorer;

import static file_explorer.File_Explorer.PathText;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rifat
 */

//Vbox Controller
class controller_select 
{
    VBox vbox;
    controller_select(VBox vbox){
        this.vbox=vbox;
        run_action_listener();
        
    }
    public void run_action_listener() 
    {
        vbox.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() 
        {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) 
            {
                TreeItem<File> g=new TreeItem<>();
                File fl;
                Label lbl=new Label("");
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
                {
                    lbl=(Label)(vbox.getChildren().get(1));
                    String str=lbl.getText();
                    File_Explorer.PathText.setText(str);
                    System.out.println(lbl.getText());

                    fl = File_Explorer.getFile.get(str);

                    if(fl.isDirectory()){

                         TreeItem<File> fff=new TreeItem<>(fl);
                         fff.setExpanded(true);
                         UpdateModify. update_items(fl,g);
                     }
                     else if(fl.isFile())
                     {
                         Desktop dd=Desktop.getDesktop();
                         try {
                             dd.open(fl);
                         } catch (IOException ex) {
                             Logger.getLogger(controller_select.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                }
            }

        });
    }
}   