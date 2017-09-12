package com.fule.myapplication.group.utilis.test;

/**
 * Created by Administrator on 2016/11/16.
 */

public interface  SectionSupport<T> {
    public int sectionHeaderLayoutId();

    public int sectionTitleTextViewId();

    public String getTitle(T t);
}
