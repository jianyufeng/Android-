package com.fule.myapplication.group.utilis;

import android.view.View;

/**
 * Created by 简玉锋 on 2016/11/16.
 *  由于RecycleView没有对应的点击事件
 *  为RecycleView添加点击事件
 */

public interface AbsRecycleClick {
    //为RecycleView添加点击事件
    interface ItemClickListener {
        void onItemClick(View view, int postion);
    }
    //为RecycleView添加长按事件
    interface ItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
