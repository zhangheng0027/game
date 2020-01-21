/*
 * 文件名：ModelIngredients.java
 * 版权：Copyright by www.amarsoft.com
 * 描述：
 * 修改人：xhan
 * 修改时间：2020年1月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package iwe.zh.factorio;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ModelRI {
	public String name;
	public int amount;
	public String type;
	public int fluidbox_index;
	public double probability;
	public static final Set<String> set = new HashSet<>(Arrays.asList("type", "name", "amount", "fluidbox_index", "probability"));
	public String temp;
	
	int count = 0;
	public void put(String str) {
		count++;
		if (set.contains(str)) {
			temp = str;
			return;
		}
		switch (temp + "") {
		case "type": 
			type = str;
			break;
		case "name":
			name = str;
			break;
		case "amount":
			amount = Integer.valueOf(str);
			break;
		case "fluidbox_index":
			fluidbox_index = Integer.valueOf(str);
			break;
		case "probability":
			probability = Double.valueOf(str);
			break;
		default:
			if ((count & 1) == 0)
				amount = Integer.valueOf(str);
			else {
				name = str;
				amount = 1;
			}
			break;
		}
		temp = "";
	}
	
	@Override
	public String toString() {
		return name == null ? "" : name + " * " + amount;
	}
	
}
