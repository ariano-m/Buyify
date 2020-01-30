# Buyify

Web de compra en la que se podrán realizar acciones propias de una tienda online tales como visualizar los productos, registrarse como usuario, añadir al carrito de la compra, realizar pedidos, ver el historial de pedidos, generar facturas, etc.

Constará de una parte pública que permitirá a todos los usuarios navegar por la tienda, iniciar sesión, registrarse y añadir productos al carrito de compra.

Respecto a la parte privada los usuarios podrán comprar, ver los pedidos realizados, generar las facturas, cerrar sesión y añadir productos a su lista de deseos.

Distinguimos tres tipos de usuarios:

* Invitado: podrá acceder a únicamente a las funcionalidades de la parte pública.
* Cliente: podrá acceder tanto a la parte pública como a la parte privada.
* Administrador: cuenta con privilegios para realizar tareas tales como añadir nuevos productos.

## Entidades principales
* Producto
* Categoría de producto (es un campo de producto)
* Pedido
* Usuario
* Tipo de usuario
* Oferta
* Reseña

## Aplicación
* Auth token: proporciona acceso al servicio interno
* Devolver lista de productos (paginated)
* Devolver lista de usuarios registrados
* Devolver lista de pedidos
* Insertar nuevo producto

## Funcionalidades servicio interno
* Enviar mails
* Reescalar imágenes en determinadas acciones (añadir producto)

Nombre | Correo | Usuario
------ | ------ | -------
Adrián Riaño Martínez | a.riano.2016 at alumnos.urjc.es | c0mputer64
David Dominguez Martín | d.dominguez.2017 at alumnos.urjc.es | elmagno1996
Jorge Portal Carrasquilla | j.portal.2016 at alumnos.urjc.es | jprtal
