package clases;

import javax.swing.JOptionPane;

public class ClasePrincipal {

    public static void main(String[] args) {

        int opcion = 0;
        int cedula = 0;
        Cola cola = new Cola();

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Menú principal\n\n"
                        + "1. Ingresar al supermercado\n"
                        + "2. Consultar pico y cédula\n"
                        + "3. Ver estado del supermercado\n"
                        + "4. Pagar productos\n"
                        + "5. Ver estadísticas\n"
                        + "6. Salir\n\n"));

                switch (opcion) {
                    case 1:
                        if (cola.aforo < cola.AFORO_MAX) {
                            cedula = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    "Por favor ingrese su número de cédula"));

                            if (cola.picoCedula(cedula)) {

                                if (cola.verificarCedula(cedula)) {
                                    cola.insertar(cedula);
                                    JOptionPane.showMessageDialog(null, "Bienvenido al supermercado");
                                }
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Lo sentimos, no pueden ingresar más personas");
                        }
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null, "Hoy día " + cola.getDia() + " cédulas terminadas en " + cola.digitosPicoCedula());
                        break;

                    case 3:
                        if (cola.colaVacia()) {
                            JOptionPane.showMessageDialog(null, "El establecimiento está vacío");
                        } else {
                            cola.mostrarContenido();
                        }
                        break;

                    case 4:
                        if (cola.colaVacia()) {
                            JOptionPane.showMessageDialog(null, "El establecimiento está vacío");
                        } else {
                            cola.pagarProductos();
                            cola.eliminar();
                        }
                        break;

                    case 5:
                        cola.verEstadisticas();
                        break;

                    case 6:
                        opcion = 6;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "opcion no disponible");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingresar un valor válido");
            }

        } while (opcion != 6);
    }
}
