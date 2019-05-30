/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_explorer;
import java.util.Comparator;

import static file_explorer.File_Explorer.PathText;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;


//SINGLETON



/**
 *
 * @author Rifat
 */
class UpdateModify{
    public static  ObservableList<FileInformation> obserb= FXCollections.observableArrayList();
    static VBox[] boxes;
    private UpdateModify(){
        
    }
    public  static void update_items(File f,TreeItem<File> temp)
    {
        File_Explorer.previous_file=f;
        
        File[] gg;
        File_Explorer.previous_tree_item=temp;
        File_Explorer.getFile.clear();
        boxes=new VBox[100];
       
        obserb.clear();
        File_Explorer.tile.getChildren().clear();
        temp.getChildren().clear();
        if(f.isDirectory()||temp==File_Explorer.rootItem)
        {
            String vv=new String("");
            vv=f.getPath();
            File_Explorer.PathText.setText(vv);
            try{
                if(temp==File_Explorer.rootItem)
                {
                    gg=File.listRoots();
                }
                else
                {
                    gg=f.listFiles();
                }
                File_Explorer.total_files=0;
                for(File p:gg)
                {
                    if(temp==File_Explorer.rootItem){
                        String vx=new String("");
                        vx=p.getPath();
                        File_Explorer.getFile.put(vx,p);

                    }
                    else{
                        String bc=new String("");
                        bc=p.getName();
                        File_Explorer.getFile.put(bc,p);

                    }
                    //File_Explorer.total_files=0;
                    ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(p);
                    BufferedImage ic = (BufferedImage) icon.getImage();
                    Image image = SwingFXUtils.toFXImage(ic, null);
                    ImageView iv = new ImageView (image);
                    Node Icon=iv;
                    TreeItem<File> po=new TreeItem<File>(p,Icon);
                    Shape sss;
                    ImageShape imgshp= new ImageShape();
                    sss = imgshp.get_image(File_Explorer.image_size_indicator);
                    ImageView ff=sss.sel(image);
                    temp.getChildren().add(po);
                    FileInformation pro;
                    if(temp==File_Explorer.rootItem){
                        int k=(int)p.length();
                        String vdc=new String("");
                        vdc=p.getPath();
                        pro=new FileInformation(ff,k,File_Explorer.sdf.format(p.lastModified()),vdc);
                    }
                    else{
                        int ww=(int)p.length();
                        String vdc1=new String("");
                        vdc1=p.getName();

                        pro=new FileInformation(ff,ww,File_Explorer.sdf.format(p.lastModified()),vdc1);
                    }
                    pro.setTreeNode(po);
                    obserb.add(pro);
                    
                    
                    
                    
                    VBox boxs=new VBox(8);
                    Label newlab;
                    if(temp==File_Explorer.rootItem){
                        String dc=new String("");
                        dc=p.getPath();
                        newlab=new Label(dc);
                    }
                    else{
                        String dc1=new String("");
                        dc1=p.getName();
                        newlab=new Label(dc1);
                    }
                    ImageShape imgsp=new ImageShape();
                    Shape vxx=imgsp.get_image(File_Explorer.image_size_indicator);
                    ImageView ggg=vxx.sel(image);
                  // boxs.getChildren().addAll(ggg,newlab);
                    Tile_Vbox vertical_box=new Tile_Vbox(boxs,ggg,newlab);
                    File_Explorer.tile.getChildren().add(vertical_box.box);
                    boxes[File_Explorer.total_files]=boxs;
                    controller_select o=new controller_select(boxs);
                    //System.out.println(File_Explorer.total_files);
                    File_Explorer.total_files++;
                }
                Comparator<FileInformation> ff=new Comparator<FileInformation>(){
                    public int compare(FileInformation fl1,FileInformation fl2)
                    {
                        int sz1=fl1.getFileSize();
                        int sz2=fl2.getFileSize();
                        return sz1-sz2;
                    }
                    
                };
                obserb.sort(ff);
                //System.out.println(File_Explorer.total_files);
            }catch(NullPointerException e){
                
                }
        }
    
    }
    
}
