package utils;

public class Entradas {
    public static int leInt(String mensagem) {
        return Integer.parseInt(IO.readln(mensagem));
    }

    public static double leDouble(String mensagem) {
        return Double.parseDouble(IO.readln(mensagem));
    }

    public static float leFloat(String mensagem) {
        return Float.parseFloat(IO.readln(mensagem));
    }

    public static String leString(String mensagem) {
        return IO.readln(mensagem);
    }
}
