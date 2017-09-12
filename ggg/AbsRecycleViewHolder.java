package com.fule.myapplication.group.utilis;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;


/**
 * Created by 简玉锋 on 2016/11/5.
 */

public class AbsRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

    /*用于存储各类控件的容器*/
    private SparseArray<View> mViews;

    private AbsRecycleClick.ItemClickListener mListener;
    private AbsRecycleClick.ItemLongClickListener mLongClickListener;
    public AbsRecycleViewHolder(View itemView, AbsRecycleClick.ItemClickListener listener, AbsRecycleClick.ItemLongClickListener longClickListener) {
        super(itemView);
        mViews = new SparseArray<View>();
        itemView.setTag(this);
        this.mListener = listener;
        this.mLongClickListener = longClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
        if (mLongClickListener != null) {
            mLongClickListener.onItemLongClick(view, (Integer) view.getTag());
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(view,(Integer) view.getTag());
        }
    }



    //通过ViewID在容器中获取控件，若在容器中没有该控件就通过ID获取，然后存储到容器中
    private  <T extends View> T getView(int ViewID){
        View view = mViews.get(ViewID);
        if(view == null){
            view = itemView.findViewById(ViewID);
            if (view != null) {
                mViews.put(ViewID , view);
            }
        }
        return (T) view;
    }


    /**
     * 辅助方法，减少用户为控件设置值的代码
     */
    //返回值设置为BaseViewHolder类型是为了实现链式编程
    public AbsRecycleViewHolder setText2TextView(int ViewID , String text){
        ((TextView)getView(ViewID)).setText(text);
        return this;
    }
//    public void setImageViewContent(int itemChildViewId, String url) {
//
//        ImageView childView = (ImageView) getView(itemChildViewId);
//        if (childView != null) {
//            if (!TextUtils.isEmpty(url) && url.startsWith("http")) {
//                ImageLoader.getInstance().displayImage(url, childView, DisplayImageOptionsUtil.getImagesOption());
//            } else {
//                if (FileUtils.checkFile(url)) {
//                    ImageLoader.getInstance().displayImage(ImageDownloader.Scheme.FILE.wrap(url), childView, DisplayImageOptionsUtil.getImagesOption());
//                }else {
//                    childView.setImageResource(R.drawable.icon_touxiang_persion_gray);
//                }
//            }
//        }
//    }
}
