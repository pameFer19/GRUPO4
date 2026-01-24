package com.example.electronicazytron.model.entities


//clase principal del objeto producto
class Producto(
    var codigo: String,
    var descripcion: String,
    var fecha_fab: String,
    var costo: Number,
    var disponibilidad: Int
)