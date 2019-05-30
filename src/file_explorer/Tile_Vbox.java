/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_explorer;

import static file_explorer.UpdateModify.boxes;
import java.io.File;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rifat
 */
//Adapter_ Vbox
class Tile_Vbox{
    VBox box;
    Tile_Vbox(VBox box,ImageView image,Label l){
        this.box=box;
//        File_Explorer.previous_file=f;
//        File_Explorer.previous_tree_item=temp;
//         File_Explorer.getFile.clear();
//         boxes=new VBox[100];
//         File[] gg;
//        obserb.clear();
//        File_Explorer.tile.getChildren().clear();
//        temp.getChildren().clear();
        this.box.getChildren().addAll(image,l);
    }
    
}