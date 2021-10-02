package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ProductoNoEncontrado;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioProductoImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class ServicioProductoTest {

    private RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
    private ServicioProducto servicioProducto = new ServicioProductoImpl(repositorioProducto);
    private String nombreProducto = "Pizza";
    private Producto producto;
    private Long idProducto = 1L;
    private Long idObtenido;

    List<Producto>listaDeProducto = new ArrayList<Producto>();

    @Test
    public void queSeListenLosProductos() throws ListaNoEncontrada {

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

    @Test
    public void queSePuedaBuscarUnProductoPorNombre(){

        givenUnaListaDeProductos();

        whenBuscoUnProductoPorSuNombre();

        thenMeDevuelveElProductoBuscado();


    }

    @Test(expected = ProductoNoEncontrado.class)
    public void queCuandoBuscoUnProductoPorNombreYNoLoEncuentraLanzeUnProductoNoEncontradoException(){

        givenUnProductoInexistente();

        whenBuscoUnProductoPorSuNombre();

    }

    @Test
    public void queSePuedaBuscarUnProductoPorId(){

        givenQueExisteUnProducto();

        whenBuscoUnProductoPorId();

        thenMeDevuelveElProducto();

    }

    @Test(expected = ProductoNoEncontrado.class)
    public void queCuandoBuscoUnProductoPorIdYNoExisteMeLanzeUnProductoNoEncontradoException(){

        givenQueNoExisteUnProducto();

        whenBuscoUnProductoPorId();

    }

    @Test
    public void queSePuedaDarMeGustaAUnProducto(){

        givenQueExisteUnProducto();

        whenDoyMeGustaAUnProducto();

        thenMeDevuelveElIdDeEseProducto();

    }

    private void whenDoyMeGustaAUnProducto() {
        idObtenido = servicioProducto.darMeGusta(idProducto);
    }

    private void thenMeDevuelveElIdDeEseProducto() {
        assertThat(idObtenido).isNotNull();
        assertThat(idObtenido).isEqualTo(1L);
    }

    private void givenQueNoExisteUnProducto() {
        when(repositorioProducto.buscarProductoPorId(1L)).thenThrow(ProductoNoEncontrado.class);
    }

    private void givenQueExisteUnProducto() {
        Producto p1 = new Producto();
        p1.setCantidadMeGusta(0);
        when(repositorioProducto.buscarProductoPorId(idProducto)).thenReturn(p1);
        when(repositorioProducto.actualizarProducto(anyObject())).thenReturn(1L);
    }

    private void whenBuscoUnProductoPorId() {
        producto = servicioProducto.buscarProductoPorId(idProducto);
    }

    private void thenMeDevuelveElProducto() {
        assertThat(producto).isNotNull();
    }

    private void givenUnProductoInexistente() {
        when(repositorioProducto.buscarProductoPorNombre(nombreProducto)).thenReturn(null);
    }

    private void whenBuscoUnProductoPorSuNombre() {
         producto = servicioProducto.buscarProductoPorNombre(nombreProducto);
    }

    private void thenMeDevuelveElProductoBuscado() {
        assertThat(producto).isNotNull();

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
        p1.setNombre(nombreProducto);
        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        when(repositorioProducto.listarProductos()).thenReturn(productos);
        when(repositorioProducto.buscarProductoPorNombre(nombreProducto)).thenReturn(p1);

    }

    private void whenListoLosProductos() throws ListaNoEncontrada {
        listaDeProducto = servicioProducto.listarProductos();
    }

    private void thenMeTraeLaListaDeProductos() {
        assertThat(listaDeProducto).isNotNull();
        assertThat(listaDeProducto).hasSize(4);
    }


}
