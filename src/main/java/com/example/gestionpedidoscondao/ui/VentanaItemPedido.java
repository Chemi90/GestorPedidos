package com.example.gestionpedidoscondao.ui;

import com.example.gestionpedidoscondao.App;
import com.example.gestionpedidoscondao.Session;
import com.example.gestionpedidoscondao.model.ItemPedido;
import com.example.gestionpedidoscondao.model.Pedido;
import com.example.gestionpedidoscondao.persistence.ItemPedidoDAO;
import com.example.gestionpedidoscondao.persistence.ItemPedidoDAOImp;
import com.example.gestionpedidoscondao.persistence.PedidoDAO;
import com.example.gestionpedidoscondao.persistence.PedidoDAOImp;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VentanaItemPedido extends Application implements Initializable {

    @javafx.fxml.FXML
    private TableView<ItemPedido> tbItemsPedidos;
    @javafx.fxml.FXML
    private TableColumn cCodPedido;
    @javafx.fxml.FXML
    private TableColumn cCantidad;
    @javafx.fxml.FXML
    private Button btnVolver;
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAOImp();
    @javafx.fxml.FXML
    private TableColumn cnomProducto;
    @javafx.fxml.FXML
    private TableColumn cprecioProducto;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    public void loadItemsPedido() {
        System.out.println("Cargando items del pedido: " + Session.getPedido());

        if(itemPedidoDAO == null) {
            System.out.println("Error: itemPedidoDAO no ha sido inicializado");
            return;
        }

        List<ItemPedido> items = itemPedidoDAO.findItemsByPedidoCodigo(Session.getPedido());

        cprecioProducto.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("precio"));
        cnomProducto.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("productoNombre"));
        cCantidad.setCellValueFactory(new PropertyValueFactory<ItemPedido, Integer>("cantidad"));

        tbItemsPedidos.setItems(FXCollections.observableArrayList(items));
    }


    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) throws IOException {
        App.changeScene("ventanaPrincipal.fxml", "Gestor de Pedidos");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItemsPedido();
    }
}
