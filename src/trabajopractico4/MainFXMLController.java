/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajopractico4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Bruno
 */
public class MainFXMLController implements Initializable {
    
    @FXML
    private TextField txtCantExperimentos;
    @FXML
    private TextField txtProbabilidad;
    @FXML
    private Button btnSimular;
    @FXML
    private TableView<Row> tblMontecarlo;

    private Instancia instanciaActual;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblMontecarlo.setEditable(true);

        TableColumn nroExperimento = new TableColumn("EXP");
        TableColumn rndRecuerdaMensaje = new TableColumn("RND Recuerda");
        TableColumn recuerdaMensaje = new TableColumn("¿Recuerda el mensaje?");
        TableColumn rndComprarProducto = new TableColumn("RND probabilidad comprar");
        TableColumn comprarProducto = new TableColumn("¿Comprara producto?");
        TableColumn probDefinitivamenteSi = new TableColumn("Probabilidad respuesta Def Si");

        nroExperimento.setCellValueFactory(new PropertyValueFactory<>("nroExperimento"));
        rndRecuerdaMensaje.setCellValueFactory(new PropertyValueFactory<>("rndRecuerdaMensaje"));
        recuerdaMensaje.setCellValueFactory(new PropertyValueFactory<>("recuerdaMensaje"));
        rndComprarProducto.setCellValueFactory(new PropertyValueFactory<>("rndComprarProducto"));
        comprarProducto.setCellValueFactory(new PropertyValueFactory<>("comprarProducto"));
        probDefinitivamenteSi.setCellValueFactory(new PropertyValueFactory<>("probDefinitivamenteSi"));

        tblMontecarlo.getColumns().addAll(nroExperimento, rndRecuerdaMensaje, recuerdaMensaje, rndComprarProducto, comprarProducto, probDefinitivamenteSi);

        tblMontecarlo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }    

    @FXML
    private void handleBtnSimular(ActionEvent event) {
    }
    
    public static class Row
    {

        private final SimpleStringProperty nroExperimento;
        private final SimpleStringProperty rndRecuerdaMensaje; 
        private final SimpleStringProperty recuerdaMensaje;
        private final SimpleStringProperty rndComprarProducto;
        private final SimpleStringProperty comprarProducto;
        private final SimpleStringProperty probDefinitivamenteSi;

        private Row(String desde,String hasta, String frecuencia_obtenida, String frecuencia_esperada, String col3, String col4)
        {
            this.nroExperimento = new SimpleStringProperty(desde);
            this.rndRecuerdaMensaje = new SimpleStringProperty(hasta);
            this.rndComprarProducto = new SimpleStringProperty(frecuencia_esperada);
            this.recuerdaMensaje = new SimpleStringProperty(frecuencia_obtenida);
            this.comprarProducto =new SimpleStringProperty(col3);
            this.probDefinitivamenteSi =new SimpleStringProperty(col4);
        }

        public void setDesde(String asd)
        {
            nroExperimento.set(asd);
        }
        
        public void sethasta(String asd)
        {
            rndRecuerdaMensaje.set(asd);
        }

        public void setFrec_esp(String asd)
        {
            rndComprarProducto.set(asd);
        }

        public void setFrec_obt(String asd)
        {
            recuerdaMensaje.set(asd);
        }

        public void setCol3(String asd)
        {
            comprarProducto.set(asd);
        }
        
        public void setCol4(String asd)
        {
            probDefinitivamenteSi.set(asd);
        }

        public String getDesde()
        {
            return nroExperimento.get();
        }
        public String getHasta()
        {
            return rndRecuerdaMensaje.get();
        }

        public String getFrecuencia_obtenida()
        {
            return recuerdaMensaje.get();
        }

        public String getFrecuencia_esperada()
        {
            return rndComprarProducto.get();
        }
        
        public String getCol3()
        {
            return comprarProducto.get();
        }
        public String getCol4()
        {
            return probDefinitivamenteSi.get();
        }

    }
    
}
