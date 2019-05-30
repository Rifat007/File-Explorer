/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_explorer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//FACTORY CLASS

/**
 *
 * @author Rifat
 */
//public interface Shape
//{
//    ImageView sel(Image im);
//     
//}
//
//class short implements Shape
//{
//    @Override
//}
public class ImageShape{
    public Shape get_image(int point){
        //ImageView IMAGE=new ImageView();
        switch(point){
        case 0:
            return new Short();
        case 1:
            return new Medium();
        case 2:
            return new Large();
        }
        return null;
    }
       
}