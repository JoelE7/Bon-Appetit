package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioProductoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class ServicioProductoTest {

    private RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
    private ServicioProducto servicioProducto = new ServicioProductoImpl(repositorioProducto);

    List<Producto>listaDeProducto = new ArrayList<Producto>();

    @Test
    public void queSeListenTodosLosProductos() throws ListaNoEncontrada {

        givenUnaListaDeProductos();

        whenListoLosProductos();

        thenMeTraeLaListaDeProductos();

    }

    @Test(expected = ListaNoEncontrada.class)
    public void queCuandoLaListaEsteVaciaLanzeUnaListaNoEncontradaException(){

        givenUnaListaVacia();

        whenListoLosProductos();
        
    }
    
    @Test
    public void queSePuedanListarLosProductosActivos(){

        givenQueExisteUnaListaDeProductosActivos();

        whenListoLosProductosActivos();

        thenMeTraeLaListaDeProductosActivos();

    }

    @Test(expected = ListaNoEncontrada.class)
    public void queCuandoListeProductosActivosYLaListaSeaNulaLanzaUnaListaNoEncontradaExcepcion(){

        givenUnaListaDeProductosActivosVacia();

        whenListoLosProductosActivos();

    }

    private void givenUnaListaDeProductosActivosVacia() {
        List<Producto>productos = new ArrayList<>();
        when(repositorioProducto.listarProductosActivos()).thenReturn(productos);
    }

    private void givenQueExisteUnaListaDeProductosActivos() {
        List<Producto>productos = new ArrayList<Producto>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        p1.setActivo(true);
        p2.setActivo(true);
        p3.setActivo(true);
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        when(repositorioProducto.listarProductosActivos()).thenReturn(productos);
    }

    private void whenListoLosProductosActivos() {
        listaDeProducto = servicioProducto.listarProductosActivos();
    }

    private void thenMeTraeLaListaDeProductosActivos() {
        assertThat(listaDeProducto).isNotNull();
        assertThat(listaDeProducto).hasSize(3);
    }

    private void givenUnaListaVacia() {
        List<Producto>listaVacia = new ArrayList<>();
        when(repositorioProducto.listarProductos()).thenReturn(listaVacia);
    }

    private void givenUnaListaDeProductos() {
        List<Producto> productos = new ArrayList<Producto>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        Producto p4 = new Producto();
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        when(repositorioProducto.listarProductos()).thenReturn(productos);

    }

    private void whenListoLosProductos()  {
        listaDeProducto = servicioProducto.listarProductos();
    }

    private void thenMeTraeLaListaDeProductos() {
        assertThat(listaDeProducto).isNotNull();
        assertThat(listaDeProducto).hasSize(4);
    }


}
