package com.mycompany.redn;

import java.util.ArrayList;
public class ArbolNario {

    //guarda el participante que se encuentra en la parte superior del arbol
    Participante raiz;

    //establece el valor minimo que debe aportar un participante
    double aporteMinimo;

    //establece el porcentaje que se agrega al aporte para calcular el retorno
    double porcentajeRetorno;

    //constructor para crear el arbol sin participantes y con los parametros en cero
    public ArbolNario() {
        raiz = null;
        aporteMinimo = 0;
        porcentajeRetorno = 0;
    }

    //constructor que permite crear el arbol recibiendo directamente los parametros de la red
    public ArbolNario(double aporteMinimo, double porcentajeretorno) {
        raiz = null;
        this.aporteMinimo = aporteMinimo;
        this.porcentajeRetorno = porcentajeretorno;
    }

    //metodo recursivo que busca un participante recorriendo todas las ramas del arbol
    public Participante buscarParticipante(Participante actual, String identificacion) {

        //evita continuar cuando la rama que se esta revisando no tiene participante
        if (actual == null) {
            return null;
        }

        //comprueba primero el participante actual antes de revisar sus hijos
        if (actual.identificacion.equals(identificacion)) {
            return actual;
        }

        //los hijos contienen las siguientes ramas que deben revisarse para encontrar la identificacion
        if (actual.hijos != null) {
            for (Participante hijo : actual.hijos) {

                //vuelve a llamar al mismo metodo para buscar dentro de la rama del hijo
                Participante encontrado = buscarParticipante(hijo, identificacion);

                //si una rama encuentra el participante se detiene la busqueda y se devuelve el resultado
                if (encontrado != null) {
                    return encontrado;
                }
            }
        }
        //indica que la identificacion no aparece en ninguna rama revisada
        return null;

    }

    //metodo que agrega el primer participante y lo establece como raiz
    public void agregarRaiz(Participante nuevo) {

        //impide crear una segunda raiz cuando el arbol ya tiene una
        if (raiz != null) {
            System.out.println("la raiz ya existe");
            return;
        }

        //realiza una busqueda sobre el arbol para comprobar que la identificacion no este repetida
        if (buscarParticipante(raiz, nuevo.identificacion) != null) {
            System.out.println("la identificacion ya existe");
            return;
        }

        //prepara la lista donde posteriormente se podran guardar los hijos de la raiz
        nuevo.hijos = new ArrayList<>();

        //conecta el nuevo participante con la posicion principal del arbol
        raiz = nuevo;
        System.out.println("particpante agregado como raiz");

    }

    //metodo que agrega un nuevo participante debajo de un participante existente
    public void agregarPartipante(Participante nuevo, String identificacionPadre) {

        //un participante hijo no puede registrarse si todavia no existe la raiz
        if (raiz == null) {
            System.out.println("Primero debo agregar la raiz");
            return;
        }

        //busca la identificacion en todo el arbol para evitar participantes repetidos
        if (buscarParticipante(raiz, nuevo.identificacion) != null) {
            System.out.println("la identificacion ya existe ");
            return;
        }

        //obtiene el objeto participante que corresponde a la identificacion del padre
        Participante padre = buscarParticipante(raiz, identificacionPadre);

        //si la busqueda devuelve null significa que el padre no existe en la red
        if (padre == null) {
            System.out.println("el participante padre no existe");
            return;
        }

        //permite agregar hijos aunque la lista del padre todavia no haya sido creada
        if (padre.hijos == null) {
            padre.hijos = new ArrayList<>();
        }

        //cada participante nuevo necesita su propia lista para guardar sus futuros hijos
        nuevo.hijos = new ArrayList<>();

        //agrega el nuevo participante al final de la lista del padre y conserva el orden de registro
        padre.agregarHijo(nuevo);
        System.out.println("Particpante agregado correctamente");

    }

    //metodo que inicia el recorrido preorden usando la raiz como primer participante
    public void recorridoPreorden() {

        //evita intentar recorrer una estructura que todavia no tiene participantes
        if (raiz == null) {
            System.out.println("La red esta vacia");
        } else {
            //envia la raiz al metodo recursivo junto con los valores iniciales para formar el arbol visual
            recorridoPreorden(raiz, "", true);
        }
    }

    //metodo recursivo que recorre cada participante y construye la representacion visual del arbol
    public void recorridoPreorden(Participante actual, String espacio, boolean ultimo) {

        //detiene una llamada recursiva cuando no existe un participante en esa posicion
        if (actual == null) {
            return;
        }

        //la raiz no necesita espacios porque es el primer elemento del arbol
        if (espacio.equals("")) {
            System.out.print("|-- ");
            //indica que el participante pertenece a una rama que ya tiene indentacion
        } else if (ultimo) {
            System.out.print(espacio + "|-- ");
            //mantiene la misma estructura visual para los participantes anteriores al ultimo hijo
        } else {
            System.out.print(espacio + "|-- ");
        }

        //muestra los datos almacenados en el participante actual y su estado de pago
        System.out.println(
                "[" + actual.identificacion + "] "
                + actual.nombre + " | "
                + actual.ciudad + " | Aporte: $"
                + actual.aporte + " | "
                + (actual.pago ? "PAGADO" : "PENDIENTE")
        );

        //solo se recorren los hijos cuando existe una lista asociada al participante
        if (actual.hijos != null) {

            // recorre los hijos en el mismo orden en que fueron registrados
            for (int i = 0; i < actual.hijos.size(); i++) {

                //permite saber si el hijo actual es el ultimo para construir correctamente la rama visual
                boolean esUltimo = i == actual.hijos.size() - 1;

                //guarda la indentacion que recibira el siguiente nivel del arbol
                String nuevoEspacio;

                //los hijos directos de la raiz comienzan con cuatro espacios
                if (espacio.equals("")) {
                    nuevoEspacio = "    ";
                    //si no quedan hermanos se agregan espacios para continuar la rama
                } else if (ultimo) {
                    nuevoEspacio = espacio + "    ";
                    //si quedan hermanos se conserva una linea vertical para mostrar que la rama continua
                } else {
                    nuevoEspacio = espacio + "|   ";
                }

                //procesa el hijo y le entrega la indentacion correspondiente a su nivel
                recorridoPreorden(
                        actual.hijos.get(i),
                        nuevoEspacio,
                        esUltimo
                );
            }
        }
    }

    //metodo que inicia el calculo del dinero aportado por toda la red
    public double calcularTotalAportes() {

        //si no existe raiz no existen participantes que puedan aportar dinero
        if (raiz == null) {
            return 0;
        }

        //comienza la suma desde la raiz y continua por todas las ramas
        return calcularTotalAportes(raiz);
    }

    //metodo recursivo que suma el aporte de cada participante y sus descendientes
    public double calcularTotalAportes(Participante actual) {

        //evita sumar datos cuando la rama no contiene participante
        if (actual == null) {
            return 0;
        }

        //comienza el acumulado con el aporte del participante que se esta procesando
        double total = actual.aporte;

        //revisa las ramas que dependen del participante actual
        if (actual.hijos != null) {

            //cada llamada devuelve la suma completa de una rama y la agrega al total
            for (Participante hijo : actual.hijos) {
                total = total + calcularTotalAportes(hijo);
            }
        }

        //devuelve el aporte del participante junto con todos los aportes de sus descendientes
        return total;
    }

    //metodo que inicia el calculo de aportes buscando una ciudad especifica
    public double calcularAportesCiudad(String ciudad) {

        //evita recorrer el arbol cuando no existe ningun participante
        if (raiz == null) {
            return 0;
        }

        //inicia el filtro por ciudad desde la raiz
        return calcularAportesCiudad(raiz, ciudad);
    }

    //metodo recursivo que suma solamente los aportes de los participantes de una ciudad
    public double calcularAportesCiudad(Participante actual, String ciudad) {

        //detiene la rama cuando no existe un participante
        if (actual == null) {
            return 0;
        }

        //comienza en cero porque el participante puede no pertenecer a la ciudad buscada
        double total = 0;

        //permite comparar ciudades sin importar si fueron escritas en mayusculas o minusculas
        if (actual.ciudad.equalsIgnoreCase(ciudad)) {
            total = actual.aporte;
        }

        //continua la busqueda por todas las ramas para encontrar otros participantes de la misma ciudad
        if (actual.hijos != null) {
            for (Participante hijo : actual.hijos) {

                //suma solamente los aportes que correspondan a la ciudad buscada
                total = total + calcularAportesCiudad(hijo, ciudad);
            }
        }

        //devuelve el total encontrado dentro de esta rama
        return total;
    }

    //metodo que inicia el conteo de todos los participantes de la red
    public int contarParticipantes() {

        //una red sin raiz no contiene participantes
        if (raiz == null) {
            return 0;
        }

        //comienza el conteo desde la raiz
        return contarParticipantes(raiz);
    }

    //metodo recursivo que cuenta el participante actual y todos los que dependen de el
    public int contarParticipantes(Participante actual) {

        //una rama vacia no agrega participantes al conteo
        if (actual == null) {
            return 0;
        }

        //comienza en uno porque el participante actual tambien debe contarse
        int cantidad = 1;

        //continua el conteo por cada hijo encontrado
        if (actual.hijos != null) {
            for (Participante hijo : actual.hijos) {

                //agrega al conteo todos los participantes encontrados debajo del hijo
                cantidad = cantidad + contarParticipantes(hijo);
            }
        }

        //devuelve la cantidad acumulada de participantes de la rama
        return cantidad;
    }

    //metodo que inicia el calculo de niveles de toda la red
    public int calcularNiveles() {

        //una red vacia no tiene niveles
        if (raiz == null) {
            return 0;
        }

        //comienza el calculo tomando la raiz como primer nivel
        return calcularNiveles(raiz);
    }

    //metodo recursivo que busca la rama con mayor profundidad
    public int calcularNiveles(Participante actual) {

        //una posicion vacia no aporta niveles al calculo
        if (actual == null) {
            return 0;
        }

        //guarda el nivel mas alto encontrado entre los hijos
        int nivelMayor = 0;

        //revisa todas las ramas que salen del participante actual
        if (actual.hijos != null) {
            for (Participante hijo : actual.hijos) {

                //calcula cuantos niveles tiene la rama que comienza en el hijo
                int nivelHijo = calcularNiveles(hijo);

                //conserva solamente la mayor profundidad encontrada
                if (nivelHijo > nivelMayor) {
                    nivelMayor = nivelHijo;
                }
            }
        }

        //suma el nivel del participante actual al nivel mas profundo de sus hijos
        return nivelMayor + 1;
    }

    //metodo que inicia el calculo de todo el dinero que esta pendiente de pago
    public double calcularTotalPendiente() {

        //una red vacia no tiene dinero pendiente
        if (raiz == null) {
            return 0;
        }

        //comienza el calculo desde la raiz
        return calcularTotalPendiente(raiz);
    }

    //metodo recursivo que calcula el valor pendiente de cada participante
    public double calcularTotalPendiente(Participante actual) {

        //detiene la rama cuando no existe un participante
        if (actual == null) {
            return 0;
        }

        //comienza el acumulado en cero para el participante actual
        double total = 0;

        //solo se calcula dinero pendiente para participantes que todavia no han recibido el pago
        if (!actual.pago) {

            //calcula el aporte mas el porcentaje de retorno establecido para la red
            total = actual.aporte + (actual.aporte * porcentajeRetorno / 100);
        }

        //continua buscando pagos pendientes en todos los hijos
        if (actual.hijos != null) {
            for (Participante hijo : actual.hijos) {

                //agrega al total el dinero pendiente encontrado en cada rama
                total = total + calcularTotalPendiente(hijo);
            }
        }

        //devuelve el total pendiente de esta rama
        return total;
    }

    //metodo que inicia el procesamiento de pagos desde la raiz
    public double procesarPagos(double dineroDisponible) {

        //envia el dinero disponible al recorrido recursivo para procesar los pagos en preorden
        return procesarPagos(raiz, dineroDisponible);
    }

    //metodo recursivo que procesa los pagos siguiendo el orden del arbol
    public double procesarPagos(Participante actual, double dineroDisponible) {

        //devuelve el dinero sin cambios cuando no existe un participante
        if (actual == null) {
            return dineroDisponible;
        }

        //calcula cuanto necesita el participante incluyendo el porcentaje de retorno
        double valorPago = actual.aporte + (actual.aporte * porcentajeRetorno / 100);

        //evita pagar dos veces y tambien evita realizar pagos parciales
        if (!actual.pago && dineroDisponible >= valorPago) {

            //cambia el estado para indicar que el participante ya recibio su pago
            actual.pago = true;

            //descuenta del dinero disponible el valor completo del pago
            dineroDisponible = dineroDisponible - valorPago;

            //informa el participante al que se le realizo el pago
            System.out.println("Pago realizado a " + actual.nombre + " por " + valorPago);
        }

        //continua el procesamiento con los hijos del participante actual
        if (actual.hijos != null) {

            //recorre los hijos en el orden en que fueron registrados para mantener el preorden
            for (Participante hijo : actual.hijos) {

                //envia a cada hijo el dinero que quedo despues de los pagos anteriores
                dineroDisponible = procesarPagos(hijo, dineroDisponible);
            }
        }

        //devuelve el dinero que no fue utilizado durante el recorrido
        return dineroDisponible;
    }

}
