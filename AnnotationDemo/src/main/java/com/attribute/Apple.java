package com.attribute;

import com.attribute.FruitColor.Color;

public class Apple {
    @FruitName(value = "Apple")
    private String appleName;

    @FruitColor(fruitColor = Color.RED)
    private String appleColor;
}
