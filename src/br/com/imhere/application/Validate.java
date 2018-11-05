package br.com.imhere.application;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {


    public static boolean isBigger(Object objectBigger, Object objectSmaller) {
        return isBigger(objectBigger, objectSmaller, false);
    }

    public static boolean isEqual(Object objectA, Object objectB) {
        if (isNull(objectA) && isNull(objectB)) {
            return true;
        } else if ((isNull(objectA) && !isNull(objectB)) || (!isNull(objectA) && isNull(objectB))) {
            return false;
        } else if (Validate.isDate(objectA)) {
            return ((Date) objectA).getTime() == ((Date) objectB).getTime();
        } else if (objectA instanceof BigDecimal) {
            return new BigDecimal((String) objectA).compareTo(new BigDecimal((String) objectB)) == 0;
        } else if (objectA instanceof String || objectA instanceof Character) {
            return objectA.toString().equalsIgnoreCase(objectB.toString());
        } else if (objectA instanceof Integer) {
            return Integer.parseInt(objectA.toString()) == Integer.parseInt(objectB.toString());
        } else if (objectA instanceof Float) {
            return Float.parseFloat(objectA.toString()) == Float.parseFloat(objectB.toString());
        } else if (objectA instanceof Double) {
            return Double.parseDouble(objectA.toString()) == Double.parseDouble(objectB.toString());
        }
        return objectA == objectB;
    }

    public static boolean isBigger(Object objectBigger, Object objectSmaller, boolean acceptEqual) {
        if (Validate.isDate(objectSmaller)) {
            return Validate.isBigger((Date) objectBigger, (Date) objectSmaller, acceptEqual);
        } else if (objectBigger instanceof BigDecimal) {
            return Validate.isBigger((BigDecimal) objectBigger, (BigDecimal) objectSmaller, acceptEqual);
        } else if (objectBigger instanceof Integer) {
            return Validate.isBigger((Integer) objectBigger, (Integer) objectSmaller, acceptEqual);
        } else if (objectBigger instanceof Float) {
            return Validate.isBigger((Float) objectBigger, (Float) objectSmaller, acceptEqual);
        } else if (objectBigger instanceof Double) {
            return Validate.isBigger((Double) objectBigger, (Double) objectSmaller, acceptEqual);
        }
        return false;
    }

    public static Boolean isBigger(BigDecimal objectBigger, BigDecimal objectSmaller, boolean acceptEqual) {
        return !acceptEqual ? objectBigger.compareTo(objectSmaller) == 1 : objectBigger.compareTo(objectSmaller) == 1 || objectSmaller.compareTo(objectBigger) == 0;
    }

    public static Boolean isBigger(Float objectBigger, Float objectSmaller, boolean acceptEqual) {
        return !acceptEqual ? objectBigger > objectSmaller : objectBigger >= objectSmaller;
    }

    public static Boolean isBigger(Double objectBigger, Double objectSmaller, boolean acceptEqual) {
        return !acceptEqual ? objectBigger > objectSmaller : objectBigger >= objectSmaller;
    }

    public static Boolean isBigger(Integer objectBigger, Integer objectSmaller, boolean acceptEqual) {
        return !acceptEqual ? objectBigger > objectSmaller : objectBigger >= objectSmaller;
    }

    public static Boolean isBigger(Date objectBigger, Date objectSmaller, boolean acceptEqual) {
        return !acceptEqual ? objectSmaller.before(objectBigger) : objectSmaller.before(objectBigger) || objectSmaller.getTime() == objectBigger.getTime();
    }

    public static Boolean isBigger(BigDecimal objectBigger, BigDecimal objectSmaller) {
        return isBigger(objectBigger, objectSmaller, false);
    }

    public static Boolean isBigger(Float objectBigger, Float objectSmaller) {
        return isBigger(objectBigger, objectSmaller, false);
    }

    public static Boolean isBigger(Double objectBigger, Double objectSmaller) {
        return isBigger(objectBigger, objectSmaller, false);
    }

    public static Boolean isBigger(Integer objectBigger, Integer objectSmaller) {
        return isBigger(objectBigger, objectSmaller, false);
    }

    public static Boolean isBigger(Date objectBigger, Date objectSmaller) {
        return isBigger(objectBigger, objectSmaller, false);
    }

    public static Boolean between(String string, Integer min, Integer max) {
        if (string == null)
            return false;
        return (string.length() >= min && string.length() <= max);
    }

    public static Boolean max(String string, Integer max) {
        return between(string, 0, max);
    }

    public static Boolean min(String string, Integer min) {
        if (string == null)
            return false;
        return string.length() >= min;
    }

    public static Boolean between(Integer number, Integer min, Integer max) {
        if (number == null)
            return false;
        return (number >= min && number <= max);
    }

    public static Boolean max(Integer number, Integer max) {
        return between(number, 0, max);
    }

    public static Boolean min(Integer number, Integer min) {
        if (number == null)
            return false;
        return number > min;
    }

    public static Boolean isEmpty(Object object) {
        if (isNull(object))
            return true;
        String string = object.toString();
        return string.trim().equals("");
    }

    public static <T> Boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }

    public static Boolean isNull(Object object) {
        return object == null;
    }

    public static Boolean isNullOrEmpty(Object object) {
        return object == null || object.toString().isEmpty();
    }

    public static Boolean isDate(String data, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        String dataString = data;
        try {
            return (Date) sdf.parse(dataString) != null;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Boolean bigDecimalZero(BigDecimal object) {
        if (object == null)
            return false;
        return object.compareTo(BigDecimal.ZERO) == 0;
    }

    public static Boolean bigDecimalZeroSmaller(BigDecimal object) {
        if (object == null)
            return false;
        return object.compareTo(BigDecimal.ZERO) < 0;
    }

    public static Boolean bigDecimalZeroMore(BigDecimal object) {
        if (object == null)
            return false;
        return object.compareTo(BigDecimal.ZERO) > 0;
    }

    public static Boolean bigDecimalZeroDifferent(BigDecimal object) {
        if (object == null)
            return false;
        return object.compareTo(BigDecimal.ZERO) != 0;
    }

    public static Boolean isNumeric(Object object) {
        if (object == null)
            return false;
        String s = object.toString();
        return s.matches("^[0-9]*$");
    }

    public static Boolean isString(Object object) {
        if (object == null)
            return false;
        String s = object.toString();
        return s.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü 1234567890.,'\\+!@#&\\*/()-?]*$") || s.length() > 80;
    }

    public static Boolean isMoney(Object object) {
        if (object == null)
            return false;
        String s = object.toString();
        return s.matches("^R\\$.*");
    }

    public static Boolean isInteger(Object object) {
        if (object == null)
            return false;
        String s = object.toString();
        return s.matches("^R\\$.*");
    }

    public static Boolean equalsNumber(String number, Integer equal) {
        if (number == null)
            return false;
        try {
            Integer i = Integer.parseInt(number);
            return i == equal;
        } catch (Exception e) {
            return false;
        }
    }

    public static Boolean equalsNumber(Integer number, Integer equal) {
        if (number == null)
            return false;
        return number == equal;
    }

    public static Boolean isPercent(Object object) {
        if (object == null)
            return false;
        String s = object.toString();
        return s.matches(".*%$");
    }

    public static Boolean isDate(Object object) {
        if (object == null)
            return false;
        String s = object.toString();
        return s.matches("\\d{2}/\\d{2}/\\d{4}") || object.getClass().getTypeName().contains("Date");
    }

    public static boolean isValidContaBancaria(String conta) {
        if (isNull(conta))
            return false;
        return conta.matches("^[0-9]+-[0-9|x|X]$");
    }

    public static Boolean isNotEmpty(Object object) {
        if (isNull(object))
            return false;
        String string = object.toString();
        return !string.trim().equals("");
    }

    public static Boolean isMail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches())
                isEmailIdValid = true;
        }
        return isEmailIdValid;
    }

    public static Boolean isTelefone(String numeroTelefone) {
        return numeroTelefone.matches("\\(\\d{2}\\)\\d{4,5}-\\d{4}");
    }

    public static Boolean isUrl(String url) {
        if (url == null)
            return false;
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public static boolean isNumberType(Field field) {
        return isNumberType(field.getType());
    }

    public static boolean isNumberType(Class clazz) {
        return (clazz == BigDecimal.class || clazz == int.class || clazz == byte.class ||
                clazz == short.class || clazz == Integer.class || clazz == float.class ||
                clazz == double.class);
    }
}
