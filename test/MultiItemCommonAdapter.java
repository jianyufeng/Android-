package com.fule.myapplication.group.utilis.test;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */

public abstract class MultiItemCommonAdapter extends CommonAdapter {
    protected MultiItemTypeSupport mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List datas,
                                  MultiItemTypeSupport multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.get(mContext, parent, layoutId);
        return holder;
    }
}
