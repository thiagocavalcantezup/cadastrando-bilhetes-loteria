package br.com.zup.handora.cadastrandobilhetesloteria.bilhete;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TelefoneUtils {

    public static String anonymize(String telefone) {
        return telefone.replaceAll(".*([0-9]{4})", "****$1");
    }

    public static byte[] hash(String telefone) {
        try {
            return MessageDigest.getInstance("SHA3-256")
                                .digest(telefone.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erro ao gerar hash de um telefone: " + telefone);
        }
    }

}
