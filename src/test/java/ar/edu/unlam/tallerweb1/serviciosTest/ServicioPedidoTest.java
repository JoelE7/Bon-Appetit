package ar.edu.unlam.tallerweb1.serviciosTest;

import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPedido;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedidoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class ServicioPedidoTest {

    private RepositorioPedido repositorioPedido = mock(RepositorioPedido.class);
    private RepositorioProducto repositorioProducto = mock(RepositorioProducto.class);
    private ServicioPedido servicioPedido = new ServicioPedidoImpl(repositorioPedido, repositorioProducto);
    Pedido pedido;
    private Long idProducto = 2L;
    private Long idPedido = 1L;

    @Test
    public void queSePuedaGenerarUnPedido() {

        givenUnPedidoNuevo();

        Long idPedido = whenQuieroGenerarElPedido();

        thenObtengoElIdDelPedidoGenerado(idPedido);

    }

    @Test
    public void queSePuedaAgregarDosVecesUnaComidaAUnPedidoYQueSeCalculeElTotalYElTiempoDeCoccion() {
        givenQueExisteUnPedidoCon(20.0, 200.0);
        whenAgregoUnaComidaCon(40.0, 750.0);
        thenMeDevuelveUnPedidoCon(60.0, 950.0);
    }

    private void givenQueExisteUnPedidoCon(double tiempoCoccion, double costo) {
        Pedido pedido = new Pedido();
        Comida producto = new Comida();
        producto.setPrecio(750.0);
        producto.setTiempoDeCoccion(40.0);
        ItemPedido itemPedido = new ItemPedido();
        pedido.setTiempoPreparacion(tiempoCoccion);
        pedido.setTotal(costo);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(producto);
        when(repositorioPedido.obtenerItemPedido(anyLong(), anyLong())).thenReturn(itemPedido);
    }

    private void whenAgregoUnaComidaCon(double tiempoCoccion, double costo) {
        pedido = servicioPedido.agregarProductoAlPedido(idProducto, idPedido);
    }

    private void thenMeDevuelveUnPedidoCon(double tiempoCoccion, double costo) {
        assertThat(pedido.getTotal()).isEqualTo(costo);
        assertThat(pedido.getTiempoPreparacion()).isEqualTo(tiempoCoccion);
    }

    @Test
    public void queSePuedaAgregarUnaBebidaAUnPedidoYQueSeCalculeElTotal() {

        givenQueExisteUnPedidoSinBebidas();

        whenAgregoUnaBebida();

        thenMeDevuelveElPedido();

    }

    @Test
    public void queSePuedaEliminarUnaComidaDeUnPedido() {

        givenQueExisteUnPedidoConProductosDentro();

        whenQuieroEliminarUnProducto();

        thenMeEliminaElProducto();

    }

    private void givenQueExisteUnPedidoConProductosDentro() {
        Comida comida = new Comida();
        Bebida bebida = new Bebida();
        ItemPedido itemPedido = new ItemPedido();
        comida.setPrecio(200.0);
        comida.setTiempoDeCoccion(100.0);
        comida.setNombre("Pizza");
        bebida.setPrecio(300.0);
        bebida.setNombre("Agua");
        Pedido pedido = new Pedido();
        pedido.setTotal(500.0);
        pedido.setTiempoPreparacion(100.0);

        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(comida);
        when(repositorioPedido.obtenerItemPedido(anyLong(), anyLong())).thenReturn(itemPedido);

    }

    private void whenQuieroEliminarUnProducto() {
        pedido = servicioPedido.eliminarComidaDeUnPedido(idProducto, idPedido);
    }

    private void thenMeEliminaElProducto() {
        assertThat(pedido).isNotNull();
        assertThat(pedido.getTotal()).isEqualTo(300.0);
        assertThat(pedido.getTiempoPreparacion()).isEqualTo(0.0);
    }


    private void givenQueExisteUnPedidoSinBebidas() {
        Pedido pedido = new Pedido();
        Bebida producto = new Bebida();
        ItemPedido itemPedido = new ItemPedido();
        producto.setPrecio(50.0);
        when(repositorioPedido.obtenerPedido(anyLong())).thenReturn(pedido);
        when(repositorioProducto.buscarProductoPorId(anyLong())).thenReturn(producto);
        when(repositorioPedido.obtenerItemPedido(anyLong(), anyLong())).thenReturn(itemPedido);

    }

    private void whenAgregoUnaBebida() {
        pedido = servicioPedido.agregarProductoAlPedido(idProducto, idPedido);
    }

    private void thenMeDevuelveElPedido() {
        assertThat(pedido).isNotNull();
        assertThat(pedido.getTotal()).isEqualTo(50.0);
        assertThat(pedido.getTiempoPreparacion()).isEqualTo(0.0);
    }

    private void givenUnPedidoNuevo() {
        when(repositorioPedido.generarPedido(anyObject())).thenReturn(1L);
    }

    private Long whenQuieroGenerarElPedido() {
        return servicioPedido.generarPedido();
    }

    private void thenObtengoElIdDelPedidoGenerado(Long idPedido) {
        assertThat(idPedido).isEqualTo(1L);
    }

}
