package clases;

import java.util.Calendar;
import javax.swing.JOptionPane;

public class Cola {
//Nodo sirve como punto de referencia para otro nodo
    private Nodo inicioCola;
    private Nodo finalCola;
    private int precioTotal = 0;
    private int contador = 0;
    public final int AFORO_MAX = 10; // Aforo permitido, el valor se puede cambiar
    public int aforo = 0;
    String cola;
    //crea una instancia de calendario y obtiene la fecha de esa instancia, por defecto es hoy
    Calendar now = Calendar.getInstance();
    private final int DIAS[][] = {{5, 6, 7, 8, 9},
    {0, 1, 2, 3, -1},
    {4, 5, 6, 7, -1},
    {8, 9, 0, 1, -1},
    {2, 3, 4, 5, -1},
    {6, 7, 8, 9, -1},
    {0, 1, 2, 3, 4}};

    // Método constructor
    public Cola() {
        inicioCola = null;
        finalCola = null;
    }

    // Método para saber si la cola está vacía
    public boolean colaVacia() {
        if (inicioCola == null) {
            return true;
        } else {
            return false;
        }
    }

    // Método para insertar datos a la cola
    public void insertar(int informacion) {

        Nodo nuevoNodo = new Nodo();
        nuevoNodo.informacion = informacion;
        nuevoNodo.siguiente = null;

        if (colaVacia()) {
            inicioCola = nuevoNodo;
            finalCola = nuevoNodo;
        } else {
            finalCola.siguiente = nuevoNodo;
            finalCola = nuevoNodo;
        }
        aforo++;
        contador++;
    }

    // Método para eliminar un elemento de la cola
    public int eliminar() {
        if (!colaVacia()) {
            int informacion = inicioCola.informacion;

            if (inicioCola == finalCola) {
                inicioCola = null;
                finalCola = null;
            } else {
                inicioCola = inicioCola.siguiente;
            }
            aforo--;
            return informacion;

        } else {
            return Integer.MAX_VALUE;
        }
    }

    // Método para verificar si el número de cédula ya está registrado
    public boolean verificarCedula(int cedula) {
        Nodo recorrido = inicioCola;
        
        while (recorrido != null) {
            
            if(cedula == recorrido.informacion){
                JOptionPane.showMessageDialog(null, "Este cliente ya ha ingresado al supermercado");
                return false;
            }
            cola += recorrido.informacion + " ";
            recorrido = recorrido.siguiente;
        }
        return true;
    }

    // Método para mostrar el contenido de la cola
    public void mostrarContenido() {
        Nodo recorrido = inicioCola;
        String colaInvertida = "";
        //int ctr = 0;

        while (recorrido != null) {
            cola += recorrido.informacion + " ";
            recorrido = recorrido.siguiente;
        }

        String cadena[] = cola.split(" ");

        for (int i = cadena.length - 1; i >= 0; i--) {
            colaInvertida += " " + cadena[i];
           // ctr++;
        }

        JOptionPane.showMessageDialog(null, "Actualmente hay " + contador + " personas en el supermercado"
                + "\nAforo permitido: " + AFORO_MAX);
        cola = "";
    }

    // Array con los dias de la semana
    String[] strDays = new String[]{
        "DOMINGO",
        "LUNES",
        "MARTES",
        "MIERCOLES",
        "JUEVES",
        "VIERNES",
        "SABADO"};

    // Obtener día actual
    public String getDia() {
        return strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
    }

    // Método para calcular si aplica el pico y cédula
    public boolean picoCedula(int cedula) {
        int ultimoDigito = cedula % 10;

        for (int i = 0; i < strDays.length; i++) {
            if (strDays[i].equals(strDays[now.get(Calendar.DAY_OF_WEEK) - 1])) {

                for (int j = 0; j < DIAS[0].length; j++) {
                    if (ultimoDigito == DIAS[i][j]) {
                        return true;
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Lo sentimos, su número de cédula no termina en " + digitosPicoCedula());
        return false;
    }

    // Método para calcular qué dígitos son aplicables al pico y cédula
    public String digitosPicoCedula() {
        String digitos = "";

        for (int i = 0; i < strDays.length; i++) {
            if (strDays[i].equals(strDays[now.get(Calendar.DAY_OF_WEEK) - 1])) {

                for (int j = 0; j < DIAS[0].length; j++) {

                    if (DIAS[i][j] > -1) {
                        digitos += DIAS[i][j] + " ";
                    } else {
                        break;
                    }
                }
            }
        }
        return digitos;
    }

    // Método para guardar el dinero pagado por los clientes
    public void pagarProductos() {
        int precio = Integer.parseInt(JOptionPane.showInputDialog(null, "Cliente identificado con CC N° " + inicioCola.informacion
                + "\npor favor ingrese el precio a pagar por sus productos"));
        JOptionPane.showMessageDialog(null, "Gracias por su compra, vuelva pronto");
        precioTotal += precio;

    }

    // Método para contar el acumulado de dinero y clientes
    public void verEstadisticas() {
        JOptionPane.showMessageDialog(null, "Total dinero recaudado: $" + precioTotal
                + "\nTotal clientes: " + contador);
    }
}