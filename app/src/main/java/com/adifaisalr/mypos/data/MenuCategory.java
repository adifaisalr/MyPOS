package com.adifaisalr.mypos.data;

public sealed interface MenuCategory permits Food, Beverage {
    String getName();
}

