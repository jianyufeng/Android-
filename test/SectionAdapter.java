package com.fule.myapplication.group.utilis.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/16.
 */

public abstract class SectionAdapter extends MultiItemCommonAdapter {
    private SectionSupport mSectionSupport;
    private static final int TYPE_SECTION = 0;
    private LinkedHashMap<String,Integer> mSections;
    private MultiItemTypeSupport headerItemTypeSupport = new MultiItemTypeSupport() {
        @Override
        public int getLayoutId(int itemType) {
            if (itemType == TYPE_SECTION)
                return mSectionSupport.sectionHeaderLayoutId();
            else
                return mLayoutId;
        }

        @Override
        public int getItemViewType(int position, Object o) {
            return mSections.values().contains(position) ?
                    TYPE_SECTION :
                    1;
        }
    };

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, null);
    }

    final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            findSections();
        }

    };

    public SectionAdapter(Context context, int layoutId, List datas, SectionSupport sectionSupport) {
        super(context, datas, null);
        mLayoutId = layoutId;
        mMultiItemTypeSupport = headerItemTypeSupport;
        mSectionSupport = sectionSupport;
        mSections = new LinkedHashMap<>();
        findSections();
        registerAdapterDataObserver(observer);
    }

//    @Override
//    protected boolean isEnabled(int viewType) {
//        if (viewType == TYPE_SECTION)
//            return false;
//        return super.isEnabled(viewType);
//    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(observer);
    }

    public void findSections() {
        int n = mDatas.size();
        int nSections = 0;
        mSections.clear();

        for (int i = 0; i < n; i++) {
            String sectionName = mSectionSupport.getTitle(mDatas.get(i));

            if (!mSections.containsKey(sectionName)) {
                mSections.put(sectionName, i + nSections);
                nSections++;
            }
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + mSections.size();
    }

    public int getIndexForPosition(int position) {
        int nSections = 0;
        Set<Map.Entry<String, Integer>> entrySet  = mSections.entrySet();
        for (Map.Entry entry : entrySet) {
            if ((Integer)entry.getValue() < position) {
                nSections++;
            }
        }
        return position - nSections;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        position = getIndexForPosition(position);
        if (holder.getItemViewType() == TYPE_SECTION)
        {
            holder.setText(mSectionSupport.sectionTitleTextViewId(), mSectionSupport.getTitle(mDatas.get(position)));
            return;
        }
        super.onBindViewHolder(holder, position);
    }
}
