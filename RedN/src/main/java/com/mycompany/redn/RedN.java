package com.mycompany.redn;

import java.util.ArrayList;
import java.util.Scanner;
public class RedN {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //crea el arbol inicialmente vacio usando el constructor sin parametros
        ArbolNario arbol = new ArbolNario();

        //controla que las opciones de registro no puedan utilizarse antes de configurar la red
        boolean parametrosDefinidos = false;

        //menu
        while (true) {

            System.out.println("==================================");
            System.out.println("     RedN     ");
            System.out.println("==================================");
            System.out.println("1. definir parametros de la red");
            System.out.println("2. agregar raiz");
            System.out.println("3. agregar participante");
            System.out.println("4. buscar participante");
            System.out.println("5. listar red en preorden");
            System.out.println("6. calcular total de aportes");
            System.out.println("7. calcular aportes por ciudad");
            System.out.println("8. contar participantes");
            System.out.println("9. calcular niveles");
            System.out.println("10. calcular total pendiente");
            System.out.println("11. procesar pagos");
            System.out.println("12. salir");
            System.out.println("==================================");
            System.out.print("seleccione una opcion: ");

            String entradaOpcion = scanner.nextLine();
            int opcion = 0;

            //controla el ciclo que obliga a ingresar una opcion correcta
            boolean opcionValida = false;

            //repite la solicitud mientras la entrada no cumpla las condiciones del menu
            while (!opcionValida) {

                boolean valido = true;

                //revisa cada caracter para impedir letras 
                for (int i = 0; i < entradaOpcion.length(); i++) {
                    if (!Character.isDigit(entradaOpcion.charAt(i))) {
                        valido = false;
                    }
                }

                //solo convierte el texto a numero cuando todos sus caracteres son numeros
                if (valido && !entradaOpcion.isEmpty()) {
                    opcion = Integer.parseInt(entradaOpcion);

                    //limita las opciones a las doce funciones existentes
                    if (opcion >= 1 && opcion <= 12) {
                        opcionValida = true;
                    } else {
                        System.out.println("La opcion debe estar entre 1 y 12");
                        System.out.print("seleccione una opcion: ");
                        entradaOpcion = scanner.nextLine();
                    }

                } else {
                    System.out.println("No se permiten letras en la opcion");
                    System.out.print("seleccione una opcion: ");
                    entradaOpcion = scanner.nextLine();
                }
            }

            System.out.println("==================================");

            //opciones del menu
            switch (opcion) {

                case 1:
                    double aporteMinimo = 0;
                    String entradaAporteMinimo;

                    //repite la entrada hasta obtener un valor valido
                    while (true) {
                        System.out.print("ingrese el aporte minimo: ");
                        entradaAporteMinimo = scanner.nextLine();

                        boolean valido = true;

                        //revisa caracter por caracter para evitar letras en el aporte minimo
                        for (int i = 0; i < entradaAporteMinimo.length(); i++) {
                            if (!Character.isDigit(entradaAporteMinimo.charAt(i))) {
                                valido = false;
                            }
                        }

                        //convierte la entrada a double solo despues de comprobar que es numerica
                        if (valido && !entradaAporteMinimo.isEmpty()) {
                            aporteMinimo = Double.parseDouble(entradaAporteMinimo);

                            //impide establecer un aporte minimo negativo
                            if (aporteMinimo >= 0) {
                                break;
                            } else {
                                System.out.println("El aporte minimo no puede ser negativo");
                            }

                        } else {
                            System.out.println("No se permiten letras en el aporte minimo");
                        }
                    }

                    //guarda el porcentaje que se utilizara para calcular los pagos
                    double porcentajeRetorno = 0;
                    String entradaPorcentaje;

                    //repite la entrada hasta obtener un porcentaje valido
                    while (true) {
                        System.out.print("ingrese el porcentaje de retorno: ");
                        entradaPorcentaje = scanner.nextLine();

                        boolean valido = true;

                        //comprueba que todos los caracteres ingresados sean numeros
                        for (int i = 0; i < entradaPorcentaje.length(); i++) {
                            if (!Character.isDigit(entradaPorcentaje.charAt(i))) {
                                valido = false;
                            }
                        }

                        //convierte el texto a numero solamente cuando la entrada es valida
                        if (valido && !entradaPorcentaje.isEmpty()) {
                            porcentajeRetorno = Double.parseDouble(entradaPorcentaje);

                            //impide utilizar un porcentaje negativo
                            if (porcentajeRetorno >= 0) {
                                break;
                            } else {
                                System.out.println("El porcentaje de retorno no puede ser negativo");
                            }

                        } else {
                            System.out.println("No se permiten letras en el porcentaje de retorno");
                        }
                    }

                    //guarda los parametros configurados dentro del objeto arbol
                    arbol.aporteMinimo = aporteMinimo;
                    arbol.porcentajeRetorno = porcentajeRetorno;

                    //permite utilizar las opciones de registro despues de configurar la red
                    parametrosDefinidos = true;

                    System.out.println("Parametros configurados correctamente");

                    break;

                case 2:

                    //obliga a configurar primero los parametros
                    if (!parametrosDefinidos) {
                        System.out.println("Primero debe definir los parametros de la red");
                        break;
                    }

                    //variable que almacena la identificacion que posteriormente recibe el participante
                    String identificacion = "";

                    //repite la entrada hasta recibir una identificacion formada solamente por numeros
                    while (true) {
                        System.out.print("ingrese la identificacion: ");
                        identificacion = scanner.nextLine();

                        boolean valido = true;

                        //revisa cada caracter para evitar letras en la identificacion
                        for (int i = 0; i < identificacion.length(); i++) {
                            if (!Character.isDigit(identificacion.charAt(i))) {
                                valido = false;
                            }
                        }

                        //permite continuar solamente cuando la identificacion contiene algun valor
                        if (valido && !identificacion.isEmpty()) {
                            break;
                        }

                        System.out.println("No se permiten letras en la identificacion");
                    }

                    //almacena el nombre que sera asignado al participante
                    String nombre = "";

                    //valida el nombre antes de crear el objeto participante
                    while (true) {
                        System.out.print("ingrese el nombre: ");
                        nombre = scanner.nextLine();

                        boolean valido = true;

                        //recorre el nombre para impedir que se registren numeros
                        for (int i = 0; i < nombre.length(); i++) {
                            if (Character.isDigit(nombre.charAt(i))) {
                                valido = false;
                            }
                        }

                        //exige que el nombre tenga contenido y no contenga numeros
                        if (!nombre.isEmpty() && valido) {
                            break;
                        }

                        System.out.println("No se permiten numeros en el nombre");
                    }

                    //almacena la ciudad que sera asociada al participante
                    String ciudad = "";

                    //valida la ciudad antes de continuar con el registro
                    while (true) {
                        System.out.print("ingrese su ciudad: ");
                        ciudad = scanner.nextLine();

                        boolean valido = true;

                        //revisa la ciudad para impedir que se registren numeros
                        for (int i = 0; i < ciudad.length(); i++) {
                            if (Character.isDigit(ciudad.charAt(i))) {
                                valido = false;
                            }
                        }

                        //exige que la ciudad tenga contenido y no contenga numeros
                        if (!ciudad.isEmpty() && valido) {
                            break;
                        }

                        System.out.println("No se permiten numeros en la ciudad");
                    }

                    //almacena el aporte que sera asignado al participante
                    double aporte = 0;
                    String entradaAporte;

                    //valida el aporte antes de crear el participante
                    while (true) {
                        System.out.print("ingrese el aporte: ");
                        entradaAporte = scanner.nextLine();

                        boolean valido = true;

                        //revisa cada caracter para impedir letras en el valor del aporte
                        for (int i = 0; i < entradaAporte.length(); i++) {
                            if (!Character.isDigit(entradaAporte.charAt(i))) {
                                valido = false;
                            }
                        }

                        //convierte el texto a numero despues de comprobar que es valido
                        if (valido && !entradaAporte.isEmpty()) {
                            aporte = Double.parseDouble(entradaAporte);

                            //impide que el aporte registrado sea negativo
                            if (aporte >= 0) {
                                break;
                            } else {
                                System.out.println("El aporte no puede ser negativo");
                            }

                        } else {
                            System.out.println("No se permiten letras en el aporte");
                        }
                    }

                    //compara el aporte ingresado con el minimo configurado en el arbol
                    if (aporte < arbol.aporteMinimo) {
                        System.out.println("el aporte no cumple con el aporte minimo");
                        break;
                    }

                    //crea el objeto participante con los datos validados y sin pago realizado
                    Participante nuevo = new Participante(
                            identificacion,
                            nombre,
                            ciudad,
                            aporte,
                            false,
                            new ArrayList<>()
                    );

                    //envia el participante al metodo que controla la creacion de la raiz
                    arbol.agregarRaiz(nuevo);
                    break;

                case 3:

                    //evita registrar participantes antes de configurar los parametros de la red
                    if (!parametrosDefinidos) {
                        System.out.println("Primero debe definir los parametros de la red");
                        break;
                    }

                    //guarda la identificacion del participante que sera utilizado como padre
                    String identificacionPadre = "";

                    //valida la identificacion del padre antes de realizar la busqueda en el arbol
                    while (true) {
                        System.out.print("ingrese la identificacion del padre: ");
                        identificacionPadre = scanner.nextLine();

                        boolean valido = true;

                        //revisa que la identificacion del padre solo contenga numeros
                        for (int i = 0; i < identificacionPadre.length(); i++) {
                            if (!Character.isDigit(identificacionPadre.charAt(i))) {
                                valido = false;
                            }
                        }

                        //permite continuar solamente con una identificacion valida
                        if (valido && !identificacionPadre.isEmpty()) {
                            break;
                        }

                        System.out.println("No se permiten letras en la identificacion del padre");
                    }

                    //limpia la identificacion anterior para reutilizar la variable
                    identificacion = "";

                    //valida la identificacion del nuevo participante
                    while (true) {
                        System.out.print("ingrese la identificacion: ");
                        identificacion = scanner.nextLine();

                        boolean valido = true;

                        //revisa todos los caracteres de la identificacion
                        for (int i = 0; i < identificacion.length(); i++) {
                            if (!Character.isDigit(identificacion.charAt(i))) {
                                valido = false;
                            }
                        }

                        //permite continuar cuando la identificacion es numerica
                        if (valido && !identificacion.isEmpty()) {
                            break;
                        }

                        System.out.println("No se permiten letras en la identificacion");
                    }

                    //limpia la variable antes de recibir el nuevo nombre
                    nombre = "";

                    //valida el nombre del nuevo participante
                    while (true) {
                        System.out.print("ingrese el nombre: ");
                        nombre = scanner.nextLine();

                        boolean valido = true;

                        //revisa que el nombre no contenga numeros
                        for (int i = 0; i < nombre.length(); i++) {
                            if (Character.isDigit(nombre.charAt(i))) {
                                valido = false;
                            }
                        }

                        //permite continuar cuando el nombre es valido y no esta vacio
                        if (!nombre.isEmpty() && valido) {
                            break;
                        }

                        System.out.println("No se permiten numeros en el nombre");
                    }

                    //limpia la variable antes de recibir la nueva ciudad
                    ciudad = "";

                    //valida la ciudad del nuevo participante
                    while (true) {
                        System.out.print("ingrese la ciudad: ");
                        ciudad = scanner.nextLine();

                        boolean valido = true;

                        //revisa que la ciudad no contenga numeros
                        for (int i = 0; i < ciudad.length(); i++) {
                            if (Character.isDigit(ciudad.charAt(i))) {
                                valido = false;
                            }
                        }

                        //permite continuar cuando la ciudad es valida y no esta vacia
                        if (!ciudad.isEmpty() && valido) {
                            break;
                        }

                        System.out.println("No se permiten numeros en la ciudad");
                    }

                    //reinicia el valor del aporte antes de recibir el nuevo dato
                    aporte = 0;

                    //limpia la entrada anterior del aporte
                    entradaAporte = "";

                    //valida el aporte del nuevo participante
                    while (true) {
                        System.out.print("ingrese el aporte: ");
                        entradaAporte = scanner.nextLine();

                        boolean valido = true;

                        //revisa cada caracter del aporte para detectar entradas no numericas
                        for (int i = 0; i < entradaAporte.length(); i++) {
                            if (!Character.isDigit(entradaAporte.charAt(i))) {
                                valido = false;
                            }
                        }

                        //convierte el texto solamente cuando la entrada es valida
                        if (valido && !entradaAporte.isEmpty()) {
                            aporte = Double.parseDouble(entradaAporte);

                            //evita registrar aportes negativos
                            if (aporte >= 0) {
                                break;
                            } else {
                                System.out.println("El aporte no puede ser negativo");
                            }

                        } else {
                            System.out.println("No se permiten letras en el aporte");
                        }
                    }

                    //comprueba que el aporte cumpla la condicion configurada para la red
                    if (aporte < arbol.aporteMinimo) {
                        System.out.println("el aporte no cumple con el aporte minimo");
                        break;
                    }

                    //crea el participante y prepara su lista de hijos para futuras conexiones
                    nuevo = new Participante(
                            identificacion,
                            nombre,
                            ciudad,
                            aporte,
                            false,
                            new ArrayList<>()
                    );

                    //busca el padre dentro del arbol y conecta el nuevo participante como hijo
                    arbol.agregarPartipante(nuevo, identificacionPadre);
                    break;

                case 4:
                    System.out.print("ingrese la identificacion que desea buscar: ");
                    identificacion = scanner.nextLine();

                    //inicia la busqueda desde la raiz porque el metodo debe recorrer el arbol completo
                    Participante encontrado = arbol.buscarParticipante(
                            arbol.raiz,
                            identificacion
                    );

                    //permite mostrar los datos solamente cuando la busqueda devuelve un participante
                    if (encontrado != null) {
                        System.out.println("==================================");
                        System.out.println("PARTICIPANTE ENCONTRADO: ");
                        System.out.print("");
                        System.out.println("identificacion: " + encontrado.identificacion);
                        System.out.println("nombre: " + encontrado.nombre);
                        System.out.println("ciudad: " + encontrado.ciudad);
                        System.out.println("aporte: " + encontrado.aporte);

                        //el estado se guarda como boolean y se convierte en un mensaje para el usuario
                        if (encontrado.pago) {
                            System.out.println("estado de pago: pagado");
                        } else {
                            System.out.println("estado de pago: pendiente");
                        }

                    } else {
                        //null indica que la identificacion no fue encontrada en ninguna rama
                        System.out.println("el participante no existe");
                    }

                    break;

                case 5:
                    System.out.println("==================================");
                    System.out.println("       RED DE PARTICIPANTES       ");
                    System.out.println("==================================");

                    //el metodo realiza el recorrido comenzando por la raiz y mostrando los niveles
                    arbol.recorridoPreorden();
                    break;

                case 6:

                    //recibe el resultado del recorrido recursivo que suma todos los aportes
                    double totalAportes = arbol.calcularTotalAportes();

                    System.out.println("==================================");
                    System.out.println("        TOTAL DE APORTES          ");
                    System.out.println("==================================");
                    System.out.println("Total aportado: " + totalAportes);
                    break;

                case 7:
                    System.out.print("Ingrese la ciudad: ");
                    String ciudades = scanner.nextLine();

                    //envia la ciudad al metodo que recorre el arbol y filtra los participantes
                    double totalCiudad = arbol.calcularAportesCiudad(ciudades);

                    System.out.println("==================================");
                    System.out.println("       APORTES POR CIUDAD         ");
                    System.out.println("==================================");
                    System.out.println("Ciudad: " + ciudades);
                    System.out.println("Total aportado: " + totalCiudad);
                    
                    break;

                case 8:

                    //obtiene la cantidad mediante un recorrido recursivo de todas las ramas
                    int cantidadParticipantes = arbol.contarParticipantes();

                    System.out.println("==================================");
                    System.out.println("    CANTIDAD DE PARTICIPANTES     ");
                    System.out.println("==================================");
                    System.out.println("Cantidad de participantes: " + cantidadParticipantes);
                    break;

                case 9:

                    //obtiene el nivel maximo encontrado mediante el recorrido recursivo
                    int niveles = arbol.calcularNiveles();

                    System.out.println("==================================");
                    System.out.println("       NIVELES DE LA RED          ");
                    System.out.println("==================================");
                    System.out.println("Cantidad de niveles: " + niveles);
                    break;

                case 10:

                    //obtiene la suma de los aportes pendientes mas el retorno configurado
                    double totalPendiente = arbol.calcularTotalPendiente();

                    System.out.println("==================================");
                    System.out.println("        TOTAL PENDIENTE           ");
                    System.out.println("==================================");
                    System.out.println("Total pendiente: " + totalPendiente);
                    break;

                //permite distribuir un dinero disponible entre los participantes pendientes
                case 11:
                    double dineroDisponible = 0;
                    String entradaDinero;

                    //valida el dinero disponible antes de enviarlo al proceso de pagos
                    while (true) {
                        System.out.print("Ingrese el dinero disponible para pagos: ");
                        entradaDinero = scanner.nextLine();

                        boolean valido = true;

                        //revisa que el dinero ingresado este compuesto solamente por numeros
                        for (int i = 0; i < entradaDinero.length(); i++) {
                            if (!Character.isDigit(entradaDinero.charAt(i))) {
                                valido = false;
                            }
                        }

                        //convierte el dinero a numero solamente cuando la entrada es valida
                        if (valido && !entradaDinero.isEmpty()) {
                            dineroDisponible = Double.parseDouble(entradaDinero);

                            //evita iniciar el proceso con una cantidad negativa
                            if (dineroDisponible >= 0) {
                                break;
                            } else {
                                System.out.println("El dinero disponible no puede ser negativo");
                            }

                        } else {
                            System.out.println("No se permiten letras en el dinero disponible");
                        }
                    }

                    //envia el dinero al arbol y recibe el saldo que no fue utilizado
                    double dineroRestante = arbol.procesarPagos(dineroDisponible);

                    System.out.println("==================================");
                    System.out.println("PROCESO DE PAGOS");
                    System.out.println("==================================");
                    System.out.println("Dinero restante: " + dineroRestante);
                    break;

                case 12:
                    System.out.println("Saliendo del sistema");
                    scanner.close();
                    return;
            }
        }
    }
}//final
