/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_explorer;


import java.io.File;
import java.io.*;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;

/**
 *
 * @author Rifat
 */
public class FileInformation {
    private int FileSize;
    private String ModifyDate;
    private int count;
    private String FileName;
    private ImageView FileIcon;
    private String FilePath;
    private TreeItem<File> TreeNode;
    
    
    public FileInformation(){
        FileName = "";
        ModifyDate = "";
        FileSize = 0;
        FilePath="";
    }

    public FileInformation(ImageView icn,int sz, String mdate,String nm){
        FileSize= sz;
        FileName = nm;
        FileIcon=icn;
        ModifyDate = mdate;
    }
    
    public void setFileSize(int sz) {
        FileSize = sz;
    }
    
    public void setFileName(String nm) {
         FileName= nm;
    }
    
    public void setFileIcon(ImageView icn)  {
        FileIcon = icn;
    }
    
    public void setModifyDate(String mdate) {
        ModifyDate = mdate;
    }
    
    public void setTreeNode(TreeItem<File> nd){
        TreeNode=nd;
    }
    
    public String getFileName() {
        return this.FileName;
    }
    
 

    public ImageView getFileIcon() {
        return FileIcon;
    }
    


    public int getFileSize() {
        return this.FileSize;
    }


    public String getModifyDate() {
        return this.ModifyDate;
    }


    public TreeItem<File> getTreeNode(){
        return this.TreeNode;
    }

}
