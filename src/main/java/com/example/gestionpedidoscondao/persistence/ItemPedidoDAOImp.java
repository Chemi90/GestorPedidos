package com.example.gestionpedidoscondao.persistence;

import com.example.gestionpedidoscondao.Session;
import com.example.gestionpedidoscondao.model.ItemPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAOImp implements ItemPedidoDAO{
    @Override
    public List<ItemPedido> findItemsByPedidoCodigo(String pedidoId) {
        List<ItemPedido> items = new ArrayList<>();

        // Cambia la consulta para que busque por el 'id' del pedido
        String query = "SELECT \n" +
                "    ip.codPedido,\n" +
                "    ip.cantidad,\n" +
                "    p.nombre,\n" +
                "    (ip.cantidad * p.precio) AS precio_total\n" +
                "FROM \n" +
                "    ItemsPedidos AS ip\n" +
                "JOIN \n" +
                "    Productos AS p ON ip.producto = p.id_productos\n" +
                "WHERE \n" +
                "    ip.codPedido = ?;\n";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, Session.getPedido());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String pedido = rs.getString("CodPedido");
                int cantidad = rs.getInt("cantidad");
                String productoNombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio_total");

                ItemPedido item = new ItemPedido();
                item.setCodPedido(pedido);
                item.setCantidad(cantidad);
                item.setProductoNombre(productoNombre);
                item.setPrecio(precio);

                items.add(item);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
