import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author gp
 * @create 2020/1/14 16:03
 */
//利用贪心算法解决集合覆盖问题
public class Greedy {
    public static void main(String[] args) {
        //创建电台,放入hashmap中,每个hashmap的key就是电台的频率,value就是对应的可以覆盖的地区
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        HashSet<String> set1 = new HashSet<>();
        set1.add("北京");
        set1.add("上海");
        set1.add("天津");

        HashSet<String> set2 = new HashSet<>();
        set2.add("北京");
        set2.add("广州");
        set2.add("深圳");

        HashSet<String> set3 = new HashSet<>();
        set3.add("成都");
        set3.add("上海");
        set3.add("杭州");

        HashSet<String> set4 = new HashSet<>();
        set4.add("上海");
        set4.add("天津");

        HashSet<String> set5 = new HashSet<>();
        set5.add("杭州");
        set5.add("大连");

        hashMap.put("k1", set1);
        hashMap.put("k2", set2);
        hashMap.put("k3", set3);
        hashMap.put("k4", set4);
        hashMap.put("k5", set5);

        HashSet<String> areas = new HashSet<>();//存放所有的地区
        areas.add("北京");
        areas.add("上海");
        areas.add("大连");
        areas.add("广州");
        areas.add("天津");
        areas.add("杭州");
        areas.add("成都");
        areas.add("深圳");


        //选择的广播集合
        ArrayList<String> selects = new ArrayList<>();

        //临时set集合
        HashSet<String> tmp1 = new HashSet<>();
        HashSet<String> tmp2 = new HashSet<>();


        //保存最大的未覆盖地区的hashmap的key
        String maxKey;
        while (areas.size() > 0) {
            maxKey = null;
            for (String key : hashMap.keySet()) {
                tmp1.clear();
                tmp2.clear();
                tmp1.addAll(hashMap.get(key));
                tmp1.retainAll(areas);//求交集,并保存到temp中
                int size = 0;
                if (maxKey != null) {
                    tmp2.addAll(hashMap.get(maxKey));
                    tmp2.retainAll(areas);
                    size = tmp2.size();
                }
                //如果当前这个集合包含的未覆盖地区的数量,比maxKey指向的集合包含的未覆盖地区还要多
                if (tmp1.size() > 0 && (maxKey == null || tmp1.size() > size)) {
                    //每次都选择最优的
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                selects.add(maxKey);
                areas.removeAll(hashMap.get(maxKey));
            }
        }

        System.out.println("得到的结果为:" + selects);
        System.out.println(hashMap);

    }
}
