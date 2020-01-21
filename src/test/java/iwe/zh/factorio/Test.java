/*
 * 文件名：Test.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xhan
 * 修改时间：2020年1月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package iwe.zh.factorio;

import org.thymeleaf.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

public class Test {
	
	static List<Model> ms = new ArrayList<>();
	
	public static void main(String[] args) {
		String[] strs = ReadFile.getAllFileString();
		for (String str : strs)
			AAA(str);
//		out.println(ms);
		BBB();
		HandleModels.AAA(ms, map);
		DDD();
	}

	public static void DDD() {
		for (Model m : ms) {
			m.setIndex(map);
		}
		Collections.sort(ms, (e1, e2) -> {
			if (e1.index > e2.index)
				return 1;
			if (e1.index < e2.index)
				return -1;
			return 0;
		});
		for (Model m : ms) {
			EEE(m);
		}
		for (int j = 0; j < tempMap.size(); j++) {
			if (StringUtils.isEmpty(mr.get(j)))
				mr.put(j, "," + tempMap.get(j));
		}
		for (int j = 0; j < mr.size(); j++) {
			out.println("ABC " + j + " / " + mr.get(j));
		}
	}
	public static Map<Integer, String> mr = new Hashtable<>(256);

	public static void EEE(Model m) {
		StringBuffer sb = new StringBuffer(512);
		List<ModelRI> lri = m.getIngrediens();
		Collections.sort(lri, (e1, e2) -> {
			int i1 = map.get(e1.name);
			int i2 = map.get(e2.name);
			if (i1 > i2) return 1;
			if (i1 < i2) return -1;
			return 0;
		});
		int i = 0;
		for (ModelRI ri : lri) {
			appendsd(sb, map.get(ri.name) - i);
			i = map.get(ri.name);
			sb.append(ri.amount);
		}
		for (ModelRI ri : m.getResult()) {
			int a = map.get(ri.name);
			if (mr.containsKey(a))
				continue;
			mr.put(a, ri.amount + "," + ri.name + "," + sb);
		}

	}
	public static void appendsd(StringBuffer sb, int i) {
		while (i-- > 0) sb.append(",");
	}
	public static final AtomicInteger integer = new AtomicInteger(0);
	public static final Map<String, Integer> map = new Hashtable<>(256);

	public static void BBB() {
		Set<String> setI = new HashSet<>();
		Set<String> setR = new HashSet<>();
		for (Model m : ms) {
			for (ModelRI ri : m.getResult())
				setR.add(ri.name);
			for (ModelRI ri : m.getIngrediens())
				setI.add(ri.name);
		}
//		System.out.println(setI);
//		System.out.println(setR);
		setI.removeAll(setR);
//		out.println(setR.size() + setI.size());
		setI.parallelStream().forEach(e -> {
			int i = integer.getAndIncrement();
			map.put(e, i);
			tempMap.put(i, e);
		});
		CCC(ms);
//		out.println(map.size() + "  " + map);
	}
	public static final Map<Integer, String> tempMap = new Hashtable<>(16);
	static {
//		map.put("uranium-238", integer.getAndIncrement());
//		map.put("uranium-235", integer.getAndIncrement());
	}
	public static void CCC(List<Model> ms) {
		if (ms.size() == 0)
			return;
		List<Model> l = new ArrayList<>(32);
		for (Model m : ms) {
			if (contrastAll(map.keySet(), m.getIngrediens())) {
				for (ModelRI ri : m.getResult()) {
					if (map.keySet().contains(ri.name))
						continue;
					map.put(ri.name, integer.getAndIncrement());
				}
			} else l.add(m);
		}
		if (l.size() == ms.size()) {
			out.println("失败 " + l.size() + l.toString());
			return;
		}
		CCC(l);
	}

	public static boolean contrastAll(Set<String> set, List<ModelRI> s2) {
		for (ModelRI s : s2)
			if (!set.contains(s.name))
				return false;
		return true;
	}

	public static void AAA(String str) {
		if (str.startsWith("data:extend"))
			str = str.substring(11);
		Model m = new Model();
		char[] chs = str.toCharArray();
		for (char ch : chs) {
			if (m.isNeedNew()) {
				ms.add(m);
				m = new Model();
			}
			m.add(ch);
		}
	}
	
	public static final String str = " \n" +
			"data:extend(\n" +
			"{\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"poison-capsule\",\n" +
			"    enabled = false,\n" +
			"    energy_required = 8,\n" +
			"    ingredients =\n" +
			"    {\n" +
			"      {\"steel-plate\", 3},\n" +
			"      {\"electronic-circuit\", 3},\n" +
			"      {\"coal\", 10}\n" +
			"    },\n" +
			"    result = \"poison-capsule\"\n" +
			"  },\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"slowdown-capsule\",\n" +
			"    enabled = false,\n" +
			"    energy_required = 8,\n" +
			"    ingredients =\n" +
			"    {\n" +
			"      {\"steel-plate\", 2},\n" +
			"      {\"electronic-circuit\", 2},\n" +
			"      {\"coal\", 5}\n" +
			"    },\n" +
			"    result = \"slowdown-capsule\"\n" +
			"  },\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"cluster-grenade\",\n" +
			"    enabled = false,\n" +
			"    energy_required = 8,\n" +
			"    ingredients =\n" +
			"    {\n" +
			"      {\"grenade\", 7},\n" +
			"      {\"explosives\", 5},\n" +
			"      {\"steel-plate\", 5}\n" +
			"    },\n" +
			"    result = \"cluster-grenade\"\n" +
			"  },\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"defender-capsule\",\n" +
			"    enabled = false,\n" +
			"    energy_required = 8,\n" +
			"    ingredients =\n" +
			"    {\n" +
			"      {\"piercing-rounds-magazine\", 3},\n" +
			"      {\"electronic-circuit\", 3},\n" +
			"      {\"iron-gear-wheel\", 3}\n" +
			"    },\n" +
			"    result = \"defender-capsule\"\n" +
			"  },\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"distractor-capsule\",\n" +
			"    enabled = false,\n" +
			"    energy_required = 15,\n" +
			"    ingredients =\n" +
			"    {\n" +
			"      {\"defender-capsule\", 4},\n" +
			"      {\"advanced-circuit\", 3}\n" +
			"    },\n" +
			"    result = \"distractor-capsule\"\n" +
			"  },\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"destroyer-capsule\",\n" +
			"    enabled = false,\n" +
			"    energy_required = 15,\n" +
			"    ingredients =\n" +
			"    {\n" +
			"      {\"distractor-capsule\", 4},\n" +
			"      {\"speed-module\", 1}\n" +
			"    },\n" +
			"    result = \"destroyer-capsule\"\n" +
			"  },\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"discharge-defense-remote\",\n" +
			"    enabled = false,\n" +
			"    ingredients = {{\"electronic-circuit\", 1}},\n" +
			"    result = \"discharge-defense-remote\"\n" +
			"  },\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"cliff-explosives\",\n" +
			"    enabled = false,\n" +
			"    energy_required = 8,\n" +
			"    ingredients =\n" +
			"    {\n" +
			"      {\"explosives\", 10},\n" +
			"      {\"grenade\", 1},\n" +
			"      {\"empty-barrel\", 1}\n" +
			"    },\n" +
			"    result = \"cliff-explosives\"\n" +
			"  },\n" +
			"  {\n" +
			"    type = \"recipe\",\n" +
			"    name = \"artillery-targeting-remote\",\n" +
			"    enabled = false,\n" +
			"    ingredients =\n" +
			"    {\n" +
			"      {\"processing-unit\", 1},\n" +
			"      {\"radar\", 1}\n" +
			"    },\n" +
			"    result = \"artillery-targeting-remote\"\n" +
			"  }\n" +
			"})\n";
}
