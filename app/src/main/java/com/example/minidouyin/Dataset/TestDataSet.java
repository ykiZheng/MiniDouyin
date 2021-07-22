package com.example.minidouyin.Dataset;

import java.util.ArrayList;
import java.util.List;

public class TestDataSet {
    public static List<TestData> getData() {
        List<TestData> result = new ArrayList();
        result.add(new TestData("让人忘记原唱的歌手"));
        result.add(new TestData("林丹退役"));
        result.add(new TestData("你在教我做事？"));
        result.add(new TestData("投身乡村教育的燃灯者"));
        result.add(new TestData("暑期嘉年华"));
        result.add(new TestData("2020年三伏天有40天"));
        result.add(new TestData("会跟游客合照的老虎"));
        result.add(new TestData("苏州暴雨"));
        result.add(new TestData("6月全国菜价上涨"));
        result.add(new TestData("猫的第六感有多强"));
        result.add(new TestData("IU真好看"));
        return result;
    }
}
