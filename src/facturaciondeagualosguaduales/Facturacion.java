package facturaciondeagualosguaduales;

public class Facturacion {

    // Consumo en m3 de cada apartamento (0 = sin consumo registrado)
    int consumo[] = new int[6];

    public static void main(String[] args) {
        controlAguaApartamento();
    }

    // Pide el numero de apartamento hasta que este entre 1 y 6
    public int pedirApartamento() {
        int apto;
        do {
            System.out.print("Ingrese el numero de apartamento (1 a 6): ");
            apto = ValidadorDeTipos.validarEnteros();
            if (apto < 1 || apto > 6) {
                System.out.println("Apartamento invalido, debe estar entre 1 y 6.");
            }
        } while (apto < 1 || apto > 6);
        return apto;
    }

    // Devuelve el valor por m3 segun el rango de consumo
    public double obtenerTarifa(int m3) {
        double tarifa;
        if (m3 <= 10) {
            tarifa = 3000;
        } else if (m3 <= 20) {
            tarifa = 4500;
        } else {
            tarifa = 6000;
        }
        return tarifa;
    }

    // Opcion 1
    public void registrarConsumo() {
        int apto = pedirApartamento();
        int m3;
        do {
            System.out.print("Ingrese el consumo en m3 del apartamento " + apto + ": ");
            m3 = ValidadorDeTipos.validarEnteros();
            if (m3 <= 0) {
                System.out.println("El consumo debe ser mayor que 0.");
            }
        } while (m3 <= 0);

        consumo[apto - 1] = m3; // si ya tenia consumo, lo reemplaza
        System.out.println("Consumo registrado correctamente.");
    }

    // Opcion 2
    public void consultarFactura() {
        int apto = pedirApartamento();
        int m3 = consumo[apto - 1];

        if (m3 == 0) {
            System.out.println("El apartamento " + apto + " no tiene consumo registrado");
        } else {
            double cargoFijo = 12000;
            double tarifa = obtenerTarifa(m3);
            double valorConsumo = m3 * tarifa;
            double subtotal = cargoFijo + valorConsumo;
            double recargo = 0;

            if (m3 > 25) {
                recargo = subtotal * 0.10;
            }
            double total = subtotal + recargo;

            System.out.println("\n======== Factura apartamento " + apto + " ========");
            System.out.println("Consumo: " + m3 + " m3");
            System.out.println("Tarifa aplicada: $ " + tarifa + " por m3");
            System.out.println("Valor del consumo: $ " + valorConsumo);
            System.out.println("Cargo fijo: $ " + cargoFijo);
            if (m3 > 25) {
                System.out.println("Recargo (10%): $ " + recargo);
                System.out.println("ALERTA: consumo excesivo");
            }
            System.out.println("Total a pagar: $ " + total);
        }
    }

    // Opcion 3
    public void reporteGeneral() {
        int total = 0;
        int registrados = 0;
        int sinRegistro = 0;
        int excesivos = 0;
        int mayor = 0;
        int aptoMayor = 0;
        int i = 0;

        while (i < consumo.length) {
            if (consumo[i] > 0) {
                registrados++;
                total += consumo[i];
                if (consumo[i] > mayor) {
                    mayor = consumo[i];
                    aptoMayor = i + 1;
                }
                if (consumo[i] > 25) {
                    excesivos++;
                }
            } else {
                sinRegistro++;
            }
            i++;
        }

        if (registrados == 0) {
            System.out.println("Aun no hay consumos registrados");
        } else {
            double promedio = (double) total / registrados;
            System.out.println("\n======== Reporte general ========");
            System.out.println("Consumo total del conjunto: " + total + " m3");
            System.out.println("Consumo promedio: " + promedio + " m3");
            System.out.println("Mayor consumo: apartamento " + aptoMayor + " con " + mayor + " m3");
            System.out.println("Apartamentos sin registro: " + sinRegistro);
            System.out.println("Apartamentos con consumo excesivo: " + excesivos);
        }
    }

    public static void controlAguaApartamento() {
        Facturacion sistema = new Facturacion();
        int option;

        do {
            System.out.println("\n===== LOS GUADUALES - CONTROL DE AGUA =====");
            System.out.println("1. Registrar consumo de un apartamento");
            System.out.println("2. Consultar factura de un apartamento");
            System.out.println("3. Ver reporte general del conjunto");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");
            option = ValidadorDeTipos.validarEnteros();

            switch (option) {
                case 1:
                    sistema.registrarConsumo();
                    break;

                case 2:
                    sistema.consultarFactura();
                    break;

                case 3:
                    sistema.reporteGeneral();
                    break;

                case 4:
                    System.out.println("Hasta pronto!!");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        } while (option != 4);
    }
}