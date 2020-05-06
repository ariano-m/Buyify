# Buyify
Enlace al [vídeo](https://youtu.be/hyzn7XxpExk).

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
* Devolver lista de productos
* Devolver lista de usuarios registrados
* Devolver lista de pedidos
* Insertar nuevo producto

## Funcionalidades [servicio interno](https://github.com/c0mputer64/Buyify-rest)
* Enviar mails
* Generación de pdf (facturas)

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

## Despliegue VM

1. Compilación

```
$ mvn package
```

Para omitir los tests:  
```
$ mvn clean package -DskipTests
```

2. Distribución
```
$ scp buyify-0.0.1-SNAPSHOT.jar <user>@<host>:
$ scp buyify-rest-0.0.1-SNAPSHOT.jar <user>@<host>:
$ scp buyify.service <user>@<host>:
$ scp buyify-rest.service <user>@<host>:
```

3. Despliegue
```
$ sudo apt install openjdk-8-jre-headless
$ sudo apt install mysql-server

$ sudo mysql_secure_installation
$ mysqld --initialize (En caso de que no cree el directorio)
$ sudo mysql
$ mysql> create database db_buyify;
$ mysql> CREATE USER 'admin'@'localhost' IDENTIFIED BY 'mascachapas';
$ mysql> GRANT ALL ON db_buyify.* to 'admin'@'localhost';

$ sudo cp buyify.service /etc/systemd/system/
$ sudo cp buyify-rest.service /etc/systemd/system/

$ sudo systemctl enable --now buyify.service
$ sudo systemctl enable --now buyify-rest.service
```

## Fase 4
En esta fase se ha procedido a la dockerización de todos los elementos que conforman la aplicación:
- Tres instacias de la base de datos. Una master y dos slaves.
- Una instacia redis para la caché y sesión distribuida.
- Dos instancia de Haproxy para el balance de carga. Una para el servicio interno y otra para la app web.
- Dos instacias de la app web.
- Dos instacias del servicio interno.

Todos estos elementos están definidos en el fichero [docker-compose.yml](https://github.com/c0mputer64/Buyify/blob/master/docker-compose.yml) y los archivos de configuración se pueden encontrar [aquí](https://github.com/c0mputer64/Buyify/tree/master/docs).



Nombre | Correo | Usuario
------ | ------ | -------
Adrián Riaño Martínez | a.riano.2016 at alumnos.urjc.es | c0mputer64
David Dominguez Martín | d.dominguez.2017 at alumnos.urjc.es | elmagno1996
Jorge Portal Carrasquilla | j.portal.2016 at alumnos.urjc.es | jprtal
