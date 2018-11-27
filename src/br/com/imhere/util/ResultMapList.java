package br.com.imhere.util;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ResultMapList extends ArrayList<Object> {

    private static final long serialVersionUID = 1L;

    private Object[][] valores;

    private String[] chaves;


    public ResultMapList(String chaves, Collection<Object> valores) {
        setChaves(chaves);
        setValores(valores);
        atualizarMapas();
    }

    public static int[] intersection(int[] a, int[] b) {
        return Arrays.stream(a)
                .distinct()
                .filter(x -> Arrays.stream(b).anyMatch(y -> y == x))
                .toArray();
    }

    protected void setValores(Collection<Object> valores) {
        int size = 0;
        if (valores != null)
            size = valores.size();

        Object[][] array = new Object[size][chaves.length];
        if (size > 0) {
            int i = 0;
            for (Iterator<Object> iter = valores.iterator(); iter.hasNext(); i++) {
                Object[] row = (Object[]) iter.next();
                for (int j = 0; j < row.length; j++)
                    array[i][j] = row[j];
            }
        }
        this.valores = array;
    }

    public String[] getResumeArrayKey(String[] array) {
        List<String> result = Arrays.asList(array);

        if (checkFunctionSql(result)) {///verifica se existe parenteses em posições diferentes, ou seja, foram separados devido a virgula...

            List<String> finalResult = new ArrayList<>();
            AtomicBoolean anterior = new AtomicBoolean(false);
            AtomicInteger p1 = new AtomicInteger(0);
            AtomicInteger p2 = new AtomicInteger(0);

            Arrays.stream(array).forEach(item -> {

                if (!item.contains("(") && !item.contains(")") && !anterior.get()) {
                    finalResult.add(item);
                    anterior.set(item.contains("(") || item.contains(")"));
                } else {
                    p1.set((int) (p1.get() + item.chars().filter(o -> o == '(').count()));
                    p2.set((int) (p2.get() + item.chars().filter(o -> o == ')').count()));

                    if (!anterior.get())
                        finalResult.add(item);
                    else {
                        String tmp = finalResult.get(finalResult.size() - 1);
                        finalResult.remove(finalResult.size() - 1);
                        finalResult.add(tmp + "," + item);
                    }

                    anterior.set(p1.get() != p2.get());
                }
            });
            result = finalResult;
        }

        return result.toArray(new String[result.size()]);
    }

    protected void setChaves(String chaves) {
        String[] array = getResumeArrayKey(chaves.split(","));

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
            array[i] = array[i].substring(array[i].indexOf('.') + 1);
            if (array[i].toLowerCase().contains(" as "))
                array[i] = array[i].substring(array[i].toLowerCase().indexOf(" as ") + 3);
            array[i] = array[i].trim();
        }

        this.chaves = array;
    }

    public boolean checkFunctionSql(List<String> array) {
        int[] parentese1 = IntStream.range(0, array.size())
                .filter(i -> array.get(i).contains("(")).toArray();
        int[] parentese2 = IntStream.range(0, array.size())
                .filter(i -> array.get(i).contains(")")).toArray();

        int[] join12 = intersection(parentese1, parentese2);

        return (join12.length != parentese1.length || join12.length != parentese2.length);
    }

    protected void atualizarMapas() {
        clear();
        if (valores.length < 1) {
            return;
        }

        if (chaves.length != valores[0].length) {
            throw new RuntimeException("A quantidade de keys e de colunas no values deve ser a mesma");
        }

        Map<String, Object> map;
        for (int i = 0; i < valores.length; i++) {
            map = new HashMap<String, Object>() {
                private static final long serialVersionUID = 3455424558917312148L;

                @Override
                public Object get(Object key) {

                    return super.get(key);
                }
            };
            for (int j = 0; j < valores[i].length; j++) {
                map.put(chaves[j], valores[i][j]);
            }
            add(map);
        }
    }

}
