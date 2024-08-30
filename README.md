# Taller RabbitMQ - Diplomado en Arquitectura de Software - Laura Sofía Guío Camargo
El presente taller se realizó en Java, construyendo un ejemplo de "Topic Exchange" Usando como guía la documentación presentada en el sitio web de RabbitMQ[^1].
## Requisitos
1. Java Versión 22.0.2
2. RabbitMQ debe estar en ejecución en local. 
## Descripción  
Se realizó una simulación de un "Newsletter" en el que el usuario (Consumer) realiza su suscripción de acuerdo a sus temas de interés. Cuando se envían los newsletter (Producer) recibe tanto el topic como un mensaje de cada uno de los temas a los que se encuentra suscrito.

> **Nota:** Para cambiar los temas a los que el usuario está suscrito se puede configurar la línea 21 de la clase _"Consumer"_ teniendo en cuenta la sintaxis de las claves de enrutamiento. Al igual se pueden añadir más temas en la línea 9 de la clase _"Producer"_

## Ejecución
Este programa ejecuta desde el archivo Main.java donde:
1. Inicia el Hilo de suscripción (Clase _"Consumer"_ - Método _"suscribeNewsletter"_)
2. Se suspende 5 segundos el hilo. 
3. Inicia el Hilo de publicación (Clase _"Producer"_ - Método _"publicMessage"_) 

[^1]: https://www.rabbitmq.com/tutorials/tutorial-five-java#topic-exchange
