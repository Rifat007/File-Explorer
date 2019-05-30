/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_explorer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Rifat
 */

public class Short implements Shape{
    @Override
    public ImageView sel(Image im)
    {
        ImageView imm=new ImageView();
        imm=new ImageView(im);
        imm.setPreserveRatio(true);
        imm.setFitWidth(30);
        imm.setFitHeight(30);
        return imm;
    }
}

