use db;

INSERT INTO Categoria(nombreCategoria) values
                                           ('Bebidas'),
                                           ('Comida vegana'),
                                           ('Comida vegetariana'),
                                           ('Hamburguesas'),
                                           ('Papas fritas'),
                                           ('Pizzas');

INSERT INTO Producto(PRODUCTO_TIPO,ID_PRODUCTO,nombre, precio,ID_CATEGORIA,nombreImagen,cantidadMeGusta,descripcion,tiempoDeCoccion) VALUES
                                                                                                                                         ('Bebida',1,'Cerveza Stella Artois',150.0,1,"cerveza.webp",3,"Cerveza Stella Artois Lata de 473 ml.",NULL),
                                                                                                                                         ('Bebida',2,'Agua', 80.0,1,"agua.webp",1,"Agua Mineral sin Gas Villavicencio Botella de 500 ml.",NULL),
                                                                                                                                         ('Bebida',3,'Coca cola',250.0,1,"cocacola.webp",2,"Coca cola Sabor original Botella de 1.5 lts.",NULL),
                                                                                                                                         ('Comida',5,'Pizza de Jamón',450.0,6,"pizza.webp",3,"La pizza de jamón con mozzarella huevo y aceitunas.No dejes de probarla!!",'30.00'),
                                                                                                                                         ('Comida',6,'Pizza de pepperoni',450.0,6,"pizza2.webp",3,"La auténtica pizza Pepperoni con tomate, queso, pepperoni y orégano sobre una fina masa y horneada en el horno de leña.",'20.00' ),
                                                                                                                                         ('Comida',7,'Fugazzeta Rellena',450.0,6,"pizza3.webp",4,"No hay una pizza más argentina que la fugazzeta rellena disfruta de esta delicia que incluye jamón cocido natural, doble mozzarella, cebolla blanca, provolone y aceite de oliva.",'20.00' ),
                                                                                                                                         ('Comida',8,'Pizza de Muzzarella',450.0,6,"pizza4.webp",3,"La pizza de mozzarella es la más clásica entre todas las recetas de pizzas. Es la que nos gusta a la mayoría. Es una receta compuesta por una masa baja y crocante con una cubierta de salsa de tomate, mozzarella, aceitunas y orégano.",'20.00' ),
                                                                                                                                         ('Comida',9,'Pizza Vegana',650.0,2,"pizza-vegana-tomate.webp",1,"",'30.00'),
                                                                                                                                         ('Comida',10,'Panqueque de Espinaca',450.0,2,"vegano-panqueque.webp",1,"",'30.00'),
                                                                                                                                         ('Comida',11,'Pizza Tropical',450.0,2,"pizza-tropical.webp",1,"",'30.00'),
                                                                                                                                         ('Comida',12,'Wrap vegetariano',350.0,3,"wrap-vegetariano.webp",3,"",'30.00'),
                                                                                                                                         ('Comida',13,'Ensalada mediterranea',350.0,3,"ensalada-mediterranea.webp",1,"",'20.00'),
                                                                                                                                         ('Comida',14,'Hamburguesa jamón y queso',550.0,4,"hambur-jyq.webp",2,"Hamburguesa doble con jamón y queso con pan de semillas. La mejor combinación de sabores!",'30.00'),
                                                                                                                                         ('Comida',15,'Hamburguesa con Huevo',550.0,4,"hambur-huevo.webp",4,"Hamburguesa doble con jamón, huevo, lechuga, tomate, queso cheddar y pan con semillas. La mejor combinación de sabores!",'30.00' ),
                                                                                                                                         ('Comida',16,'Hamburguesa con Cheddar',650.0,4,"hambur-cheddar.webp",2,"Nuestra carne de siempre con loncha de queso cheddar, salsa barbacoa, cheddar en crema, y panceta. La mejor combinación de sabores.",'20.00'),
                                                                                                                                         ('Comida',17,'Papas Fritas',350.0,5,"papas-fritas.webp",4,"Porción de papas grandes.No dejes de probarlas!!",'20.00' ),
                                                                                                                                         ('Comida',18,'Papas con Huevo',450.0,5,"papas-huevo.webp",2,"",'20.00' ),
                                                                                                                                         ('Comida',19,'Papas al Verdeo',550.0,5,"papas-verdeo.webp",1,"",'20.00' );


INSERT INTO Reserva(nombre,celular,mesas,fecha,hora) values
                                                         ('Joel', '1522557899',1, '2021-12-30','22:00'),
                                                         ('Rodrigo','1522557899',25, '2021-12-30', '22:00'),
                                                         ('Sebastian','1566466616',4,'2021/12/30',"22:00");
