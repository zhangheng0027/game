package iwe.zh.factorio;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zh on 2020/1/20.
 */
public class HandleModels {

    public static Map<String, List<String>> ri = new HashMap<>(512);
    public static Map<String, List<String>> ir = new HashMap<>(512);

    public static Map<String, Integer> map = new HashMap<>(512);
    public static Set<Integer> conSet = new HashSet<>(512);

    public static void AAA(List<Model> ls, Map<String, Integer> msi) {
        ls.stream().forEach(e -> {
            BBB(e.getIngrediens(), e.getResult());
        });
        DDD();
        for (String str : sets)
            CCC(str, 100);
        List<Node> ln = new ArrayList<>(map.size());
        msi.forEach((k, v) -> {
            ln.add(new Node(k, v, map.get(k)));
        });
        Collections.sort(ln, (e1, e2) -> {
            if (e1.j > e2.j) return 1;
            if (e1.j < e2.j) return -1;
            if (e1.index > e2.index) return 1;
            if (e1.index < e2.index) return -1;
            return 0;
        });
        int i = 0;
        for (Node e : ln)
            msi.put(e.str, i++);
    }

//    private static int lastSize = 512;

    static class Node {
        public String str;
        public int index;
        public int j;
        Node(String str, int index) {
            this.str = str;
            this.index = index;
        }
        Node(String str, int index, int j) {
            this(str, index);
            this.j = j;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "str='" + str + '\'' +
                    ", index=" + index +
                    '}';
        }
    }

    private static void CCC(String str1, int index1) {
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(new Node(str1, index1));

        while (queue.size() > 0) {
            Node node = queue.poll();
            String str = node.str;
            int index = node.index;
            int mIndex = index;
            if (map.containsKey(str)) mIndex = map.get(str);
            if (mIndex >= index) {
                mIndex = index - 1;
            } else {
                continue;
            }
            map.put(str, mIndex);
            if (!ri.containsKey(str))
                continue;
            for (String e : remove(str))
                if (!str.equals(e))
                    queue.add(new Node(e, mIndex));
        }
    }

    public static Set<String> sets = new HashSet<>(256);
    private static void DDD() {
        sets.addAll(ri.keySet());
        sets.removeAll(ir.keySet());
    }

    private static void BBB(List<ModelRI> i, List<ModelRI> r) {
        for (ModelRI mr : r) {
            List lr = getList(ri, mr.name);
            for (ModelRI mi : i) {
                List li = getList(ir, mi.name);
                lr.add(mi.name);
                li.add(mr.name);
            }
        }
    }

    private static List<String> remove(String str) {
        return ri.remove(str);
    }

    private static List<String> getList(Map<String, List<String>> ml, String str) {
        List<String> l = ml.get(str);
        if (l == null) {
            l = new ArrayList<>();
            ml.put(str, l);
        }
        return l;
    }
}
