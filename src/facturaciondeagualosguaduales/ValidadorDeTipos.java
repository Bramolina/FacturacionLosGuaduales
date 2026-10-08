package facturaciondeagualosguaduales;
import java.util.Scanner;

public class ValidadorDeTipos {
    static Scanner sc= new Scanner(System.in);

    public static int validarEnteros(){
        int value=sc.nextInt();
        sc.nextLine();
        return value;
    }

    public static String validarString(){
        String value=sc.nextLine();
        return value;
    }

    public static Double validarDouble(){
        Double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }
}
