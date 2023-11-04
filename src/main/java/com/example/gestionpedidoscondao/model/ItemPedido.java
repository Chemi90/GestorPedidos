package com.example.gestionpedidoscondao.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemPedido {
    private int id;
    private String codPedido;
    private int cantidad;
    private String productoNombre;
    private Double precio;
}