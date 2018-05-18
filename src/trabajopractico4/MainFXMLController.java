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
import javafx.event.EventType;
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
public class MainFXMLController implements Initializable
{

    @FXML
    private TextField txtCantExperimentos;
    @FXML
    private TextField txtProbabilidad;
    @FXML
    private Button btnSimular;
    @FXML
    private TableView<Row> tblMontecarlo;

    private Instancia instanciaActual;
    @FXML
    private TextField txtDesde;
    @FXML
    private TextField txtHasta;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tblMontecarlo.setEditable(true);

        TableColumn nroExperimento = new TableColumn("EXP");
        TableColumn rndRecuerdaMensaje = new TableColumn("RND Recuerda");
        TableColumn recuerdaMensaje = new TableColumn("¿Recuerda el mensaje?");
        TableColumn rndComprarProducto = new TableColumn("RND probabilidad comprar");
        TableColumn comprarProducto = new TableColumn("¿Comprara producto?");
        TableColumn acumuladorCompras = new TableColumn("Sumatoria Compras");

        nroExperimento.setCellValueFactory(new PropertyValueFactory<>("nroExperimento"));
        rndRecuerdaMensaje.setCellValueFactory(new PropertyValueFactory<>("rndRecuerdaMensaje"));
        recuerdaMensaje.setCellValueFactory(new PropertyValueFactory<>("recuerdaMensaje"));
        rndComprarProducto.setCellValueFactory(new PropertyValueFactory<>("rndComprarProducto"));
        comprarProducto.setCellValueFactory(new PropertyValueFactory<>("comprarProducto"));
        acumuladorCompras.setCellValueFactory(new PropertyValueFactory<>("sumatoriaCompras"));

        tblMontecarlo.getColumns().addAll(nroExperimento, rndRecuerdaMensaje, recuerdaMensaje, rndComprarProducto, comprarProducto, acumuladorCompras);

        tblMontecarlo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void handleBtnSimular(ActionEvent event)
    {
        int desde = Integer.parseInt(txtDesde.getText());
        int hasta = Integer.parseInt(txtHasta.getText());
        int cantidad = Integer.parseInt(txtCantExperimentos.getText());
        int acumuladorCompras = 0;

        if (desde == 0)
        {
            desde = 1;
        }

        instanciaActual = new Instancia();

        for (int i = 0; i < cantidad; i++)
        {
            int experimento = i + 1;

            if (experimento >= desde && experimento <= hasta)
            {

                instanciaActual.setExperimento(experimento);

                double rndRecuerda = Math.random();
                instanciaActual.setRndRecuerdaMensaje(rndRecuerda);
                instanciaActual.setRecuerdaMensaje(respuestaRecuerda(rndRecuerda));

                double rndCompra = Math.random();
                instanciaActual.setRndCompraProducto(rndCompra);

                String resp = respuestaCompraSI(rndCompra);
                instanciaActual.setComprarProducto(resp);

                if ("Definitivamente si".equals(resp))
                {
                    acumuladorCompras++;
                    instanciaActual.setSumatoriaCompras(acumuladorCompras);

                }

                String s_exp = String.valueOf(instanciaActual.getExperimento());
                String s_rndRec = String.valueOf(instanciaActual.getRndRecuerdaMensaje());
                String s_rndComp = String.valueOf(instanciaActual.getRndCompraProducto());
                String s_sum = String.valueOf(instanciaActual.getSumatoriaCompras());
                Row r = new Row(s_exp, s_rndRec, instanciaActual.getRecuerdaMensaje(), s_rndComp, instanciaActual.getComprarProducto(), s_sum);
                tblMontecarlo.getItems().add(r);

            } else
            {
                double rndRecuerda = Math.random();
                String res = respuestaRecuerda(rndRecuerda);

                double rndCompra = Math.random();
                if ("SI".equals(res))
                {
                    if ("Definitivamente si".equals(respuestaCompraSI(rndCompra)))
                    {
                        acumuladorCompras++;
                    }
                } else
                {
                    if ("Definitivamente si".equals(respuestaCompraNO(rndCompra)))
                    {
                        acumuladorCompras++;
                    }
                }
            }
        }
        double probabilidadCompra = (double)instanciaActual.getSumatoriaCompras()/(double)cantidad;
        txtProbabilidad.setText(String.valueOf(probabilidadCompra));

    }

    private String respuestaRecuerda(double rnd_anuncio)
    {
        if (rnd_anuncio < 0.4)
        {
            return "SI";
        }
        return "NO";
    }

    private String respuestaCompraSI(double rnd_recuerda)
    {
        if (rnd_recuerda < 0.6)
        {
            if (rnd_recuerda < 0.3)

            {
                return "Definitivamente no";
            }
            return "Dudoso";
        }

        return "Definitivamente si";
    }

    private String respuestaCompraNO(double rnd_recuerda)
    {
        if (rnd_recuerda < 0.9)
        {
            if (rnd_recuerda < 0.5)

            {
                return "Definitivamente no";
            }
            return "Dudoso";
        }
        return "Definitivamente si";
    }

    public static class Row
    {

        private final SimpleStringProperty nroExperimento;
        private final SimpleStringProperty rndRecuerdaMensaje;
        private final SimpleStringProperty recuerdaMensaje;
        private final SimpleStringProperty rndComprarProducto;
        private final SimpleStringProperty comprarProducto;
        private final SimpleStringProperty sumatoriaCompras;

        private Row(String nroExperimento, String rndRecuerda, String recuerda, String rndComprar, String comprar, String sumComprar)
        {
            this.nroExperimento = new SimpleStringProperty(nroExperimento);
            this.rndRecuerdaMensaje = new SimpleStringProperty(rndRecuerda);
            this.rndComprarProducto = new SimpleStringProperty(rndComprar);
            this.recuerdaMensaje = new SimpleStringProperty(recuerda);
            this.comprarProducto = new SimpleStringProperty(comprar);
            this.sumatoriaCompras = new SimpleStringProperty(sumComprar);
        }

        public void setNroExperimento(String asd)
        {
            nroExperimento.set(asd);
        }

        public void setRndRecuerda(String asd)
        {
            rndRecuerdaMensaje.set(asd);
        }

        public void setRndComprarProducto(String asd)
        {
            rndComprarProducto.set(asd);
        }

        public void setRecuerdaMensaje(String asd)
        {
            recuerdaMensaje.set(asd);
        }

        public void setComprarProducto(String asd)
        {
            comprarProducto.set(asd);
        }

        public void setsumatoriaCompras(String asd)
        {
            sumatoriaCompras.set(asd);
        }

        public String getNroExperimento()
        {
            return nroExperimento.get();
        }

        public String getRndRecuerdaMensaje()
        {
            return rndRecuerdaMensaje.get();
        }

        public String getRecuerdaMensaje()
        {
            return recuerdaMensaje.get();
        }

        public String getRndComprarProducto()
        {
            return rndComprarProducto.get();
        }

        public String getComprarProducto()
        {
            return comprarProducto.get();
        }

        public String getSumatoriaCompras()
        {
            return sumatoriaCompras.get();
        }

    }

}
