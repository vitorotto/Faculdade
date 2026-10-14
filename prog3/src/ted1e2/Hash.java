package ted1e2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static final int SALT = 26;

    public static String genHash(String texto) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(texto.getBytes());
        StringBuilder resultado = new StringBuilder();
        for (byte b : hash) {
            resultado.append(String.format("%02x", b));
        }
        return resultado.toString();
    }

    public static boolean validateHash(String texto, String hashRecebido) throws NoSuchAlgorithmException {
        String hashCalculado = genHash(texto);
        return hashCalculado.equals(hashRecebido);
    }
}
