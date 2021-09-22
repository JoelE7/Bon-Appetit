package ar.edu.unlam.tallerweb1.repositorioTest;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Producto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioProducto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;


public class RepositorioProductoTest extends SpringTest {

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Test
    @Rollback
    @Transactional
    public void queSePuedanListarTodosLasProductos(){

        List<Producto>productosEsperados = givenUnaListaDeProductos();

        List<Producto>productosObtenidos = whenListoLosProductos();
        
        thenMeTraeLaListaDeProductos(productosEsperados,productosObtenidos);

    }

    @Test
    @Rollback
    @Transactional
    public void queSePuedanListarTodosLosProductosActivos(){

        List<Producto>productosEsperados = givenUnaListaDeProductosActivos();

        List<Producto>productosObtenidos = whenListoLosProductosActivos();

        thenMeTraeLaListaDeProductosActivos(productosEsperados,productosObtenidos);


    }

    private List<Producto> givenUnaListaDeProductosActivos() {
        List<Producto>productosLista = new ArrayList<>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        Producto p4 = new Producto();
        p1.setActivo(true);
        p2.setActivo(true);
        p3.setActivo(false);
        p4.setActivo(false);
        session().save(p1);
        session().save(p2);
        session().save(p3);
        session().save(p4);
        productosLista.add(p1);
        productosLista.add(p2);
        return productosLista;
    }

    private List<Producto> whenListoLosProductosActivos() {
        return repositorioProducto.listarProductosActivos();
    }

    private void thenMeTraeLaListaDeProductosActivos(List<Producto> productosEsperados, List<Producto> productosObtenidos) {
        assertThat(productosEsperados).isEqualTo(productosObtenidos);
        assertThat(productosObtenidos).hasSize(2);
    }

    private List<Producto> givenUnaListaDeProductos() {
        List<Producto>productosLista = new ArrayList<>();
        Producto p1 = new Producto();
        Producto p2 = new Producto();
        Producto p3 = new Producto();
        Producto p4 = new Producto();
        session().save(p1);
        session().save(p2);
        session().save(p3);
        session().save(p4);
        productosLista.add(p1);
        productosLista.add(p2);
        productosLista.add(p3);
        productosLista.add(p4);
        return productosLista;
    }

    private List<Producto> whenListoLosProductos() {
        return repositorioProducto.listarProductos();
    }

    private void thenMeTraeLaListaDeProductos(List<Producto> productosEsperados, List<Producto> productosObtenidos) {
        assertThat(productosEsperados).isEqualTo(productosObtenidos);
        assertThat(productosObtenidos).hasSize(4);

    }


}
