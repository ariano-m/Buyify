# Buyify

Web de compra en la que se podrán realizar acciones propias de una tienda online tales como visualizar los productos, registrarse como usuario, realizar pedidos, ver el historial de pedidos, generar facturas, etc.

Constará de una parte pública que permitirá a todos los usuarios navegar por la tienda, iniciar sesión, registrarse y añadir productos al carrito de compra.

Respecto a la parte privada los usuarios podrán comprar, ver los pedidos realizados, generar las facturas y cerrar sesión.

Distinguimos tres tipos de usuarios:

* Invitado: podrá acceder a únicamente a las funcionalidades de la parte pública.
* Cliente: podrá acceder tanto a la parte pública como a la parte privada.
* Administrador: cuenta con privilegios para realizar tareas tales como añadir nuevos productos.

## Entidades principales
* Producto
* Pedido
* Usuario
* Oferta
* Reseña

## Aplicación
* Devolver lista de productos (paginated)
* Devolver lista de usuarios registrados
* Devolver lista de pedidos
* Insertar nuevo producto

## Funcionalidades servicio interno
* Enviar mails
* Reescalar imágenes en determinadas acciones (añadir producto)

## Modelo E/R
<img src="/docs/modeloER.png" alt="drawing" width="750"/>
 	
## Diagrama de clases
<img src="/docs/diagramaClases.png" alt="drawing" width="1000"/>

## Diagrama de clases y templates
<img src="/docs/DiagramaClasesTemplates.png" alt="drawing" width="1000"/>

## Páginas principales
<img src="/docs/screens/login.png" alt="drawing" width="400"/>
Login: página de inicio de sesión para los usuarios

<img src="/docs/screens/productos.png" alt="drawing" width="500"/>
Productos: página principal de la actividad de la web

<img src="/docs/screens/productos-id.png" alt="drawing" width="500"/>
Vista producto: página donde se visualizan los detalles de un producto

<img src="/docs/screens/profile.png" alt="drawing" width="350"/>
Perfil: página de perfil de usuario

## Diagrama de flujo
<img src="/docs/FlujoNavegacion.svg" alt="drawing" width="1000"/>

Nombre | Correo | Usuario
------ | ------ | -------
Adrián Riaño Martínez | a.riano.2016 at alumnos.urjc.es | c0mputer64
David Dominguez Martín | d.dominguez.2017 at alumnos.urjc.es | elmagno1996
Jorge Portal Carrasquilla | j.portal.2016 at alumnos.urjc.es | jprtal
