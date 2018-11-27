package br.com.imhere.application;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
    public static void addInfoMessage(String message) {
        addMessage(message, FacesMessage.SEVERITY_INFO);
    }

    public static void addWarnMessage(String message) {
        addMessage(message, FacesMessage.SEVERITY_WARN);
    }

    public static void addErroMessage(String message) {
        addMessage(message, FacesMessage.SEVERITY_ERROR);
    }

    private static void addMessage(String message, Severity type) {
        FacesContext.getCurrentInstance().addMessage(" ",
                new FacesMessage(type, message, message));
    }


    /**
     * @param value - Valor do Enum para realizar a conversão
     * @param clazz - Classe do Enum para realizar todo o processo de reflexão
     * @return
     */
    public static <T> T parseEnum(Object value, final Class<T> clazz) {
        if (value == null)
            return null;
        try {
            Method values = clazz.getMethod("values");
            for (Object item : (Object[]) values.invoke(clazz)) {
                if (item.getClass().getDeclaredMethod("getValor").invoke(item) == value ||
                        item.getClass().getDeclaredMethod("getValor").invoke(item).equals(value)) {
                    return (T) item;
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
        return null;
    }

    public static void redirect(String redirect) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String value) {
        try {
            // Classe utilizada para gerar a criptografia em hash
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(value.getBytes(StandardCharsets.UTF_8));

            // convertendo um array bite em hexadecimal
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(String.format("%02X", 0xFF & b));
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "Erro ao encriptar";
    }

    public static String encryptPassword(String value) {
        return Util.encrypt(Util.encrypt(value.substring(value.length() - 6)));
    }


}
