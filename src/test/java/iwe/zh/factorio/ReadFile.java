package iwe.zh.factorio;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by zh on 2020/1/19.
 */
public class ReadFile {

    public static String readFile(String filePath) {
        StringBuffer sb = new StringBuffer(2048);
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine()).append('\n');
            }
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public static final Set<String> FILEPATH = new HashSet<>(Arrays.asList(
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\ammo.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\capsule.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\equipment.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\fluid-recipe.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\inserter.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\module.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\recipe.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\demo-furnace-recipe.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\demo-recipe.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\demo-turret.lua",
            "C:\\streamGame\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe\\turret.lua"
    ));

    public static String[] getAllFileString() {
        String[] strs = new String[FILEPATH.size()];
        int i = 0;
        for (String s : FILEPATH)
            strs[i++] = readFile(s);
        return strs;
    }

}
