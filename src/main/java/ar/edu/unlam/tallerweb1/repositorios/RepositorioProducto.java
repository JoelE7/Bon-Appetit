package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Producto;

import java.util.List;
import java.util.ArrayList;

public interface RepositorioProducto {

    List<Producto> listarProductos();

    List<Producto> listarProductosActivos();
}
