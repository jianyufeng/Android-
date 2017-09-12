package com.fule.myapplication.group.utilis.test;

/**
 * Created by 简玉锋 on 2016/11/16.
 */

public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);
    int getItemViewType(int position, T t);
}