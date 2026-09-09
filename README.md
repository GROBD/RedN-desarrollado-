# RedN(desarrollado)
Trabajo Universitario por Gabriel David Obando, Javier Aldair Lopez Botinza, Jesus Alejandro Ortega Chamorro.

# Instrucciones de funcionamiento

Al iniciar la aplicacion se muestra un menu con las diferentes opciones disponibles. Primero se deben definir los parametros de la red como el aporte minimo y el porcentaje de retorno. Luego se puede registrar el participante raiz y agregar nuevos participantes indicando la identificacion de su padre.

La aplicacion permite buscar participantes por su identificacion, visualizar la red mediante un recorrido preorden, calcular el total de aportes, consultar los aportes de una ciudad, contar participantes, calcular los niveles de la red, consultar el dinero pendiente y procesar los pagos siguiendo el orden del arbol.

# descripcion de la estructura N-aria

El proyecto utiliza un arbol N-ario para representar una red de participantes. Cada participante es un nodo y puede tener cero o varios hijos. El primer participante registrado es la raiz y los demas participantes se agregan como hijos de un participante existente. Los hijos se almacenan en una lista para conservar el orden de registro.   

# Clases utilizadas      
Participante.java: representa cada nodo del arbol y almacena los datos del participante como identificacion, nombre, ciudad, aporte, estado de pago y sus hijos.

ArbolNario.java: administra la estructura del arbol N-ario. Contiene la raiz y permite agregar participantes, buscar nodos, realizar recorridos y ejecutar operaciones como calcular aportes, contar participantes, calcular niveles y procesar pagos.

RedN.java: contiene el metodo principal y el menu de la aplicacion. Se encarga de interactuar con el usuario, solicitar los datos y ejecutar las diferentes opciones del sistema.
