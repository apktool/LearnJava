package com.springel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionDemo {
    private Map<String, String> nameMap;
    private List<String> teleList;

    public CollectionDemo() {
        nameMap = new HashMap<String, String>();
        nameMap.put("001", "Tom");
        nameMap.put("002", "Mike");
        nameMap.put("003", "Alice");

        teleList = new ArrayList<String>();
        teleList.add("13600000000");
        teleList.add("13688880000");
        teleList.add("13688889999");
    }

    public Map<String, String> getNameMap() {
        return nameMap;
    }

    public void setNameMap(Map<String, String> nameMap) {
        this.nameMap = nameMap;
    }

    public List<String> getTeleList() {
        return teleList;
    }

    public void setTeleList(List<String> teleList) {
        this.teleList = teleList;
    }
}
