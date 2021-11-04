package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.Excepciones.ListaCategoriaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.ListaNoEncontrada;
import ar.edu.unlam.tallerweb1.Excepciones.PedidoInexistente;
import ar.edu.unlam.tallerweb1.modelo.Categoria;
import ar.edu.unlam.tallerweb1.modelo.Pedido;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.servicios.ServicioCategoria;
import ar.edu.unlam.tallerweb1.servicios.ServicioPedido;
import ar.edu.unlam.tallerweb1.servicios.ServicioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorPedido {

    private ServicioPedido servicioPedido;
    private ServicioProducto servicioProducto;
    private ServicioCategoria servicioCategoria;

    @Autowired
    public ControladorPedido(ServicioPedido servicioPedido, ServicioProducto servicioProducto, ServicioCategoria servicioCategoria) {
        this.servicioPedido = servicioPedido;
        this.servicioProducto = servicioProducto;
        this.servicioCategoria = servicioCategoria;
    }

    @RequestMapping("carrito")
    public ModelAndView carrito(@RequestParam Long idPedido) {
        ModelMap model = new ModelMap();

        try {
            Pedido pedido = this.servicioPedido.obtenerPedido(idPedido);
            List<Producto> listaProductos = this.servicioProducto.listarProductos();
            List<Categoria> listaCategorias = this.servicioCategoria.listarCategorias();
            List<Producto> destacados = this.servicioProducto.listarDestacados();
            model.put("listaProductos", listaProductos);
            model.put("listaCategorias", listaCategorias);
            model.put("destacados", destacados);
            model.put("pedido",pedido);
        } catch (ListaNoEncontrada e) {
             model.put("msgError", "No hay productos");
        } catch (ListaCategoriaNoEncontrada f) {
             model.put("categoriasNoEncontradas", "No se encontro ninguna categoria por mostrar");
        }
        return new ModelAndView("productos", model);
    }

    @RequestMapping(path = "agregarPedido")
    public ModelAndView agregarProductoAlPedido(@RequestParam Long idProducto, @RequestParam Long idPedido) {

        Pedido pedido = this.servicioPedido.agregarComidaAlPedido(idProducto, idPedido);
        return new ModelAndView("redirect:carrito?idPedido=" + idPedido);
    }

    @RequestMapping("confirmar-pedido")
    public ModelAndView procesarPedido(@RequestParam Long idPedido) {
        ModelMap model = new ModelMap();
        try {
            Pedido pedido = this.servicioPedido.obtenerPedido(idPedido);
            model.put("pedido", pedido);
        } catch (PedidoInexistente e) {
            model.addAttribute("pedidoError", "Este pedido no existe");
        }
        return new ModelAndView("formularioPedido", model);
    }

    @RequestMapping("eliminar-producto")
    public ModelAndView eliminarProducto(@RequestParam Long idProducto, @RequestParam Long idPedido){


        Pedido pedido = servicioPedido.eliminarComidaDeUnPedido(idProducto,idPedido);

        if(pedido.getListaProductos().size()>0){
            return new ModelAndView("redirect:carrito?idPedido=" + idPedido);
        }

        return new ModelAndView("redirect:listarProductos");


    }

}
