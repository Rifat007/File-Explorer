
package file_explorer;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class File_Explorer extends Application 
{
    static File rootFile;
    static String hostName="";
    static File previous_file;
    static TreeItem<File> previous_tree_item;
    static int image_size_indicator=0;
    ScrollPane scroll=new ScrollPane();
    static HashMap<String, File> getFile = new HashMap<String, File>();
    int index;
    static TilePane tile=new TilePane();
    static int total_files=0;
   
    static TextField PathText=new TextField();
    static  TreeItem<File> rootItem;
    static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    TreeItem<File> selectednode=null;
    int indicator=0;
     
    TableView<FileInformation> table;
    
     
    @Override
    public void start(Stage primaryStage) 
    {
        scroll.setStyle("-fx-background: rgb(255,255,255);");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setFitToWidth(true);
        scroll.setPrefSize(800, 480);
        UpdateModify.boxes=new VBox[100];
        
        tile.setHgap(20);
                 //horizontal gaph
        tile.setVgap(20);
                  //vertical gaph
        tile.setTileAlignment(Pos.CENTER);
        PathText.setPrefWidth(800);
        
         try{
            hostName=InetAddress.getLocalHost().getHostName();
         }catch(UnknownHostException e){
        }
        rootFile=new File("This PC");
        rootItem=new TreeItem<File>(rootFile);
        rootItem.setExpanded(true);
        TreeView <File> tree=new TreeView<File>(rootItem);
        tree.setPrefHeight(480);
        tree.selectionModelProperty();
      
        TableColumn<FileInformation, ImageView> icColumn = new TableColumn<>("Icon");
        icColumn.setMinWidth(200);
        //width of icon column
        icColumn.setCellValueFactory(new PropertyValueFactory<>("FileIcon"));
          
        TableColumn<FileInformation, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(250);
        //width of Name column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("FileName"));
       
        TableColumn<FileInformation,Double> sizeColumn = new TableColumn<>("DataSize");
        sizeColumn.setMinWidth(100);
        //width of size column
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("FileSize"));
        
        TableColumn<FileInformation, String> modifieddateColumn = new TableColumn<>("LastModified");
        modifieddateColumn.setMinWidth(250);
        // width of modify column
        modifieddateColumn.setCellValueFactory(new PropertyValueFactory<>("ModifyDate"));
        table = new TableView<>();
        table.setPrefHeight(480);
        //hjeight of  the table view
        table.setItems(UpdateModify.obserb);
        table.getColumns().addAll(icColumn,nameColumn, sizeColumn, modifieddateColumn);
        table.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event)
            {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) 
                try{
                PathText.clear();
                PathText.setText(table.getSelectionModel().getSelectedItem().getTreeNode().getValue().getPath());
                File fx=table.getSelectionModel().getSelectedItem().getTreeNode().getValue();
                if(fx.isDirectory())
                {
                 UpdateModify. update_items(fx,table.getSelectionModel().getSelectedItem().getTreeNode());
                }
                else if(fx.isFile())
                {
                    Desktop dd=Desktop.getDesktop();
                    try {
                        dd.open(fx);
                    } catch (IOException ex) {
                        Logger.getLogger(controller_select.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                }catch(Exception e){}     
            }    
        });
        
       tree.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends TreeItem<File>> observable,TreeItem<File> oldValue,TreeItem<File> newValue) -> {
           
        try{
          PathText.clear();

          PathText.setText(newValue.getValue().getPath());
          System.out.println(newValue.getValue().getPath());
          UpdateModify. update_items(newValue.getValue(),newValue);


        }catch(Exception e){
        }      
       }); 
        
         
        Button small_view_button=new Button("SMALL ICON");
        small_view_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                image_size_indicator=0;
                UpdateModify. update_items(previous_file,previous_tree_item);

            }   
        }
        );
        
        
//        Button src=new Button("GO");
//        src.setOnAction(new EventHandler<ActionEvent>() {
//            @Override 
//            public void handle(ActionEvent e) {
//                String strr=PathText.getText();
//                File ffl = File_Explorer.getFile.get(strr);
//                TreeItem<File> fff=new TreeItem<>();
//                 //fff.setExpanded(true);
//                UpdateModify.update_items(ffl,fff);
//
//            }
//        });
        
        
        Button medium_view_button=new Button("MEDIUM ICON");
        medium_view_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                image_size_indicator=1;
                UpdateModify. update_items(previous_file,previous_tree_item);
     
            }   
        }
        );
        
        Button large_view_button=new Button("LARGE ICON");
        large_view_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                image_size_indicator=2;
                UpdateModify. update_items(previous_file,previous_tree_item);
     
            }   
        }
        );
        HBox root = new HBox();
        VBox fin=new VBox();
        HBox twoview= new HBox();
        String curr="Current Direction";
        VBox root2=new VBox();
        HBox root3=new HBox();
        Label myLabel = new Label("   CURRENT DIRECTION     :      ");
        Label myLbl1=new Label("                                                                                                                                                                                                                                                     ");
        VBox rt= new VBox();
        HBox button_box=new HBox();
        button_box.getChildren().addAll(myLbl1,small_view_button,medium_view_button,large_view_button);
        Button detailed_button=new Button("TABLE");
        detailed_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                indicator=1;
                table.setVisible(true);
                scroll.setVisible(false);
                image_size_indicator=0;
                UpdateModify. update_items(previous_file,previous_tree_item);
                button_box.setVisible(false);

            }
        });
        
        Button list_button=new Button("TILES");
        list_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent e) {
                indicator=0;
                table.setVisible(false);
                scroll.setVisible(true);
                button_box.setVisible(true);
     
            }
        }
        );
        twoview.setPrefWidth(1000);
        twoview.getChildren().addAll(detailed_button,list_button);
        root3.setPrefWidth(1000);
        root3.setPrefHeight(60);
        //root3.setAccessibleText(curr);
        root3.getChildren().addAll(myLabel,PathText);
        root2.setPrefWidth(300);
        root2.getChildren().addAll(tree);
        Group g=new Group();
        scroll.setContent(tile);
        scroll.setPannable(true); 
        g.getChildren().addAll(scroll,table);
        root.getChildren().addAll(root2,g);
        fin.getChildren().addAll(twoview,root3,root,button_box);
        table.setVisible(false);
        Scene scene = new Scene(fin, 1100, 600);
        primaryStage.setTitle("RIFAT007");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
}