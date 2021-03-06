package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ProductoNoEncontrado;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioProductoImpl implements ServicioProducto {


    RepositorioProducto repositorioProducto;

    @Autowired
    public ServicioProductoImpl(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }


    @Override
    public List<Producto> listarProductos() {

        if (this.repositorioProducto.listarProductos().size() > 0) {
            return this.repositorioProducto.listarProductos();
        } else {
            throw new ListaNoEncontrada();
        }
    }

    @Override
    public List<Producto> listarProductosActivos() throws ListaNoEncontrada {

        if (this.repositorioProducto.listarProductosActivos().size() < 1) {
            throw new ListaNoEncontrada("Ups! No se encontró ninguna categoría ni productos por mostrar.");
        }
        return this.repositorioProducto.listarProductosActivos();
    }

    @Override
    public Producto buscarProductoPorNombre(String nombreProducto) {

        if (this.repositorioProducto.buscarProductoPorNombre(nombreProducto) == null) {
            throw new ProductoNoEncontrado();
        }
        return this.repositorioProducto.buscarProductoPorNombre(nombreProducto);
    }

    @Override
    public Producto buscarProductoPorId(Long idProducto) {

        if (this.repositorioProducto.buscarProductoPorId(idProducto) == null) {
            throw new ProductoNoEncontrado();
        }

        return this.repositorioProducto.buscarProductoPorId(idProducto);
    }

    @Override
    public Long darMeGusta(Long idProducto) {
        Producto productoEncontrado = buscarProductoPorId(idProducto);
        Integer cantidad = productoEncontrado.getCantidadMeGusta();
        productoEncontrado.setCantidadMeGusta(++cantidad);
        return this.repositorioProducto.actualizarProducto(productoEncontrado);
    }

    @Override
    public List<Producto> listarDestacados() {
        List<Producto> listaProductosDestacados = this.repositorioProducto.buscarProductosConMasDe(3);
        if(listaProductosDestacados.size()>3){
            for (int i = 0; i < listaProductosDestacados.size(); i++) {
                listaProductosDestacados.remove(listaProductosDestacados.get((int) (Math.random() * listaProductosDestacados.size())));
                if(listaProductosDestacados.size()==3)
                    break;
            }
        }
        if (listaProductosDestacados.size() < 3) {

            List<Producto> listaProductosTotales = this.repositorioProducto.listarProductos();
            for (int i = 0; i < listaProductosTotales.size(); i++) {
                listaProductosDestacados.add(listaProductosTotales.get((int) (Math.random() * listaProductosTotales.size())));
                if (listaProductosDestacados.size() == 3) {
                    break;
                }
            }
        }
        return listaProductosDestacados;
    }
}

