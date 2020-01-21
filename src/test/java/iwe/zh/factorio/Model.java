/*
 * 文件名：Model.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xhan
 * 修改时间：2020年1月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package iwe.zh.factorio;

import java.util.*;

public class Model {
	
	public String type;
	public String name;
	public boolean enabled;
	public double energy_required;
	public List<ModelRI> ingrediens = new ArrayList<>();
	public List<ModelRI> results = new ArrayList<>();
	public Model expensive;
	public Model nominal;

	public int index = -1;

	// 上一次的输出
	public ModelRI lastModelRI;
	// 关键字
	public static Set<String> gjz = new HashSet<>(Arrays.asList(""));
	// 左引号
	public boolean leftFlag = false;
	
	public final static StringBuffer AS = new StringBuffer("--");
	
	public String token = "";
	public int tokenLeft;
	
	public StringBuffer tempStr = new StringBuffer();
	// 当前所有的左
	public int nLeftCount = 0;
	
	public boolean isNewFalg = true;
	
	public int add(char ch) {
		init();
		if ('{' == ch) {
			nLeftCount++;
			return handString(tempStr.toString().trim());
		}
		if ('}' == ch) {
			nLeftCount--;
			return handString(tempStr.toString().trim());
		}
		if ('(' == ch) {
			nLeftCount++;
			return handString(tempStr.toString().trim());
		}
		if (')' == ch) {
			nLeftCount--;
			return handString(tempStr.toString().trim());
		}
		if ('"' == ch) {
			if (leftFlag) {
				nLeftCount--;
				leftFlag = false;
				return handString(tempStr.toString().trim());
			}
			nLeftCount++;
			leftFlag = true;
			return handString(tempStr.toString().trim());
		}
		if (!leftFlag && tempStr.toString().trim().startsWith("--")) {
			handString(tempStr.substring(0, tempStr.length() - 2).trim());
			tempStr = AS;
			if ('\n' == ch) {
				tempStr = new StringBuffer();
			}
			return 0;
		}
		if (" =,\n".indexOf(ch) > 0) {
			return handString(tempStr.toString().trim());
		}
		
		tempStr.append(ch);
		return 0;
	}
	public boolean isNeedNew() {
		if (nLeftCount >= newModelCount)
			return false;
		return !isNewFalg;
	}

	public void setIndex (Map<String, Integer> map) {
		for (ModelRI ri : getResult()) {
			if (!map.containsKey(ri.name))
				throw new Error("");
			int i = map.get(ri.name);
			if (index < i)
				index = i;
		}

	}
	int newModelCount = 0;
	public int handString(String str) {
		if (str.trim().length() == 0)
			return 0;
		tempStr = new StringBuffer();
		if ("data:extend".equals(str)) {
			return 0;
		}
		if (isNewFalg) {
			isNewFalg = false;
			newModelCount = nLeftCount;
		}
		switch (token) {
		case "expensive":
			if (null == expensive)
				expensive = new Model();
			handModel(expensive, str);
			expensive.init();
			break;
			
		case "normal":
			if (null == nominal)
				nominal = new Model();
			handModel(nominal, str);
			nominal.init();
			break;
			
		case "type":
			type = str;
			token = "";
			break;
		case "name":
			name = str;
			token = "";
			break;
		case "enabled":
			enabled = Boolean.valueOf(str);
			token = "";
			break;
		case "result":
			getLastModelRI(true);
			lastModelRI.put(str);
			token = "";
			break;
		case "result_count":
			getLastModelRI(true);
			lastModelRI.amount = Integer.valueOf(str);
			token = "";
			break;
		case "results":
			getLastModelRI(true);
			lastModelRI.put(str);
			break;
		case "ingredients":
			getLastModelRI(false);
			lastModelRI.put(str);
			break;
		case "energy_required":
			energy_required = Double.valueOf(str);
			token = "";
			break;
		default:
			token = str;
		}
		return 0;
	}
	
	void handModel(Model m, String str) {
		m.nLeftCount = nLeftCount;
		m.tokenLeft = tokenLeft;
		m.handString(str);
	}
	
	public void init() {
		if (tokenLeft > nLeftCount) {
			token = "";
			tokenLeft = nLeftCount;
		}
		if (lastModelRICount > nLeftCount) {
			lastModelRI = null;
			if (("ingredients".equals(token) || "results".equals(token)) && (lastModelRICount > nLeftCount + 1)) {
				token = "";
				lastModelRICount = -1;
				lastModelRI = null;
			}
		} 
		if ("nominal".equals(token) && nominal != null && nominal.newModelCount > nLeftCount) {
			token = "";
		}
		if ("expensive".equals(token) && expensive != null && expensive.newModelCount > nLeftCount) {
			token = "";
		}
		if (null != nominal) {
			nominal.nLeftCount = nLeftCount;
			nominal.init();
		}
		if (null != expensive) {
			expensive.nLeftCount = nLeftCount;
			expensive.init();
		}
	}
	
	public int lastModelRICount = 0;
	boolean lisr = false;
	public ModelRI getLastModelRI(boolean isR) {
		if (null == lastModelRI || lisr != isR) {
			lisr = isR;
			lastModelRICount = nLeftCount;
			lastModelRI = new ModelRI();
			if (isR)
				results.add(lastModelRI);
			else ingrediens.add(lastModelRI);
		}
		return lastModelRI;
	}
	
	@Override
	public String toString() {
		return "{ 材料:" + getIngrediens() + ", 结果:" + getResult() + " }";
	}

	public List<ModelRI> getResult() {
		if (null == results || results.size() == 0)
			return nominal.results;
		return results;
	}

	public List<ModelRI> getIngrediens() {
		if (null == ingrediens || ingrediens.size() == 0)
			return nominal.ingrediens;
		return ingrediens;
	}
	
}
