package facturaciondeagualosguaduales;

public class Facturacion {
    String apartamentos[] = new String[6];
    int consumo[] = new int[6];
    Double[] precios = new Double[6];

    public static void main(String[] args) {
        controlAguaApartamento();
    }

    // Pide el numero de apartamento hasta que este entre 1 y 6
    public int agregarApartamentos() {
        int apartamento;
        do {
            System.out.print("Ingrese el numero de apartamento (1 a 6): ");
            apartamento = ValidadorDeTipos.validarEnteros();
            if (apartamento < 1 || apartamento > 6) {
                System.out.println("Apartamento invalido, debe estar entre 1 y 6.");
            }
        } while (apartamento < 1 || apartamento > 6);
        return apartamento;
    }

    public double registrarTarifa(int metro3) {
        double tarifa;
        if (metro3 <= 10) {
            tarifa = 3000;
        } else if (metro3 <= 20) {
            tarifa = 4500;
        } else {
            tarifa = 6000;
        }
        return tarifa;
    }

    public void registrarConsumo() {
        int apartamento = agregarApartamentos();
        int metro3;
        do {
            System.out.print("Registrar consumo de apartamento " + apartamento + ": ");
            metro3 = ValidadorDeTipos.validarEnteros();
            if (metro3 <= 0) {
                System.out.println("El consumo debe ser mayor que 0.");
            }
        } while (metro3 <= 0);

        // -1 es para que se guarde en la posicion real ya que si tengo 6 apartamentos en el index tengo 5 y lo que hace el -1 es llegar a los 5 del index que en realidad son 6 ya que se cuenta desde el cero
        apartamentos[apartamento - 1] = "Apartamento " + apartamento;
        consumo[apartamento - 1] = metro3;
        precios[apartamento - 1] = registrarTarifa(metro3);
        System.out.println("Consumo ingresado");
    }

    public void consultarFactura() {
        int apartamento = agregarApartamentos();

        if (apartamentos[apartamento - 1] == null || precios[apartamento - 1] == null) {
            System.out.println("El apartamento " + apartamento + " no tiene consumo registrado");
        } else {
            double cargoFijo = 12000;
            double tarifa = precios[apartamento - 1];
            double valorConsumo = consumo[apartamento - 1] * tarifa;
            double subtotal = cargoFijo + valorConsumo;
            double recargo = 0;

            if (consumo[apartamento - 1] > 25) {
                recargo = subtotal * 0.10;
            }
            double total = subtotal + recargo;

            System.out.println("\n========Factura========");
            System.out.println(apartamentos[apartamento - 1]);
            System.out.println("Consumo: " + consumo[apartamento - 1] + " m3");
            System.out.println("Tarifa: $ " + tarifa + " por m3");
            System.out.println("Valor del consumo: $ " + valorConsumo);
            System.out.println("Cargo Fijo: $ " + cargoFijo);

            if (consumo[apartamento - 1] > 25) {
                System.out.println("Recargo del 10% : $ " + recargo);
                System.out.println("ALERTA: consumo excesivo");
            }
            System.out.println("Total a pagar: $ " + total);
        }
    }

    public void reporteGeneral() {
        int consumoTotal = 0;
        int aptosConRegistro = 0;
        int aptosSinRegistro = 0;
        int aptosExcesivos = 0;
        int consumoMayor = 0;
        int numAptoMayor = 0;
        double totalARecaudar = 0;
        String lineasDetalle = "";
        int pos = 0;

        while (pos < consumo.length) {
            if (apartamentos[pos] != null && precios[pos] != null) {
                aptosConRegistro++;
                consumoTotal += consumo[pos];

                double valorApartamento = 12000 + consumo[pos] * precios[pos];
                String nivel = "Normal";

                if (consumo[pos] > 25) {
                    valorApartamento = valorApartamento * 1.10;
                    nivel = "Excesivo";
                    aptosExcesivos++;
                }
                totalARecaudar += valorApartamento;

                if (consumo[pos] > consumoMayor) {
                    consumoMayor = consumo[pos];
                    numAptoMayor = pos + 1;
                }

                lineasDetalle += "Apto " + (pos + 1) + " | " + consumo[pos] + " m3 | "
                        + nivel + " | $ " + valorApartamento + "\n";
            } else {
                aptosSinRegistro++;
                lineasDetalle += "Apto " + (pos + 1) + " | Sin registro\n";
            }
            pos++;
        }

        if (aptosConRegistro == 0) {
            System.out.println("Aun no hay consumos registrados");
        } else {
            double consumoPromedio = (double) consumoTotal / aptosConRegistro;

            System.out.println("\n========Reporte general========");
            System.out.println("---- Detalle por apartamento ----");
            System.out.print(lineasDetalle);
            System.out.println("---------- Resumen ----------");
            System.out.println("Consumo total: " + consumoTotal + " m3");
            System.out.println("Consumo promedio: " + consumoPromedio + " m3");
            System.out.println("Mayor consumo: apartamento " + numAptoMayor + " con " + consumoMayor + " m3");
            System.out.println("Sin registro: " + aptosSinRegistro);
            System.out.println("Con consumo excesivo: " + aptosExcesivos);
            System.out.println("Total a recaudar: $ " + totalARecaudar);
        }
    }

    public static void controlAguaApartamento() {
        Facturacion sistema = new Facturacion();
        int option;

        do {
            System.out.println("\n =======LOS GUADUALES - CONTROL DE AGUA=======");
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
                    System.out.println("Adios!");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        } while (option != 4);
    }
}