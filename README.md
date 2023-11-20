# BlackJack Game: MultiThreading and Sockets

# Descripción del Juego

El *BlackJack Game: MultiThreading and Sockets* es una implementación del clásico juego de cartas Blackjack (también conocido como 21) con funcionalidades avanzadas de multihilo y sockets para permitir la interacción entre varios jugadores.

## Integrantes
- Yesid Alejandro Martinez Guerrero
- Luis Alfonso Rojas Martinez
- David Alejandro Suarez Cardenas

## Reglas

*BASIC RULES*


1. El juego solo debe comenzar cuando se alcance el número total de jugadores para la partida (3 o 4, según el número de miembros en el grupo).
   
2. Una vez que el juego esté en progreso, no se pueden conectar más jugadores.

3. Al finalizar el juego, los jugadores pueden decidir si jugar otra ronda, esperando a que comience de nuevo, y todas las jugadas deben reiniciarse.

4. Después de que el juego concluya, el servidor debe aceptar nuevas conexiones en caso de que uno o más jugadores decidan abandonar la partida.

5. Durante el juego, las cartas deben enviarse como objetos, no como cadenas de texto, valores numéricos u otros tipos.

6. Todos los datos enviados entre el cliente y el servidor deben estar en forma de objetos, no como cadenas de texto, enteros, booleanos, etc.

7. El resultado del juego debe enviarse en forma de archivo; no puede ser una cadena, entero, objeto, etc.

## Ejecución del Juego

Asegúrese de seguir las reglas mencionadas anteriormente antes de ejecutar el juego.

### Requisitos previos
- [Listar aquí cualquier requisito previo necesario para ejecutar el juego]

### Instrucciones de ejecución
### iniciar servidor:
1. ejecute la clase servidor del proyecto BlackJackServer
### iniciar Cliente
1. ejecute la clase controll del proyecto BlackJacClient
2. introdusca un nombre y la direccion de conexion y pulse en el boton designado
3. espere a que los jugadores se unan
