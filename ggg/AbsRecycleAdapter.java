package com.fule.myapplication.group.utilis;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 简玉锋 on 2016/7/6.
 * 自定义Recycle的万能适配器
 */
public abstract class AbsRecycleAdapter<T> extends RecyclerView.Adapter<AbsRecycleViewHolder> {

    private AbsRecycleClick.ItemClickListener mItemClickListener;
    private AbsRecycleClick.ItemLongClickListener mLongClickListener;

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(AbsRecycleClick.ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * 设置Item长按监听
     *
     * @param listener
     */
    public void setOnItemLongClickListener(AbsRecycleClick.ItemLongClickListener listener) {
        this.mLongClickListener = listener;
    }

    /**
     * 设置Item长按监听
     *
     * @param parent  RecycleView
     * @param viewType 布局类型
     */
    @Override
    public AbsRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AbsRecycleViewHolder holder = new AbsRecycleViewHolder(LayoutInflater.from(context).inflate(LayoutID, parent, false), mItemClickListener, mLongClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(AbsRecycleViewHolder holder, int position) {
        holder.itemView.setTag(position);
        blindViewHolder(holder, datas.get(position));
    }

    public abstract void blindViewHolder(AbsRecycleViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public T getItem(int positoion) {
        return datas.get(positoion);
    }

    public Context context;
    private List<T> datas;
    private int LayoutID;

    public AbsRecycleAdapter(Context context, List<T> datas, int LayoutID) {
        this.context = context;
        this.datas = datas;
        this.LayoutID = LayoutID;

    }

    public void addData(T data) {
        int position = datas.size();
        datas.add(position, data);
        notifyItemInserted(position);
    }

    public void refreshData(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void remove(int position) {

        datas.remove(position);
        notifyItemRemoved(position);
        if (position != datas.size()) {
            notifyItemRangeChanged(position, datas.size() - position);
        }
    }

    public void remove(T t) {
        int i = datas.indexOf(t);
        datas.remove(t);
        if (i == -1) return;
        notifyItemRemoved(i);
        if (i != datas.size()) {
            notifyItemRangeChanged(i, datas.size() - i);
        }
    }
//
//    public void clearData() {
//        this.datas.clear();
//        notifyDataSetChanged();
//    }


//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(LayoutID, parent, false);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        showItemContenData(viewHolder, datas.get(position), parent, position);
//        return convertView;
//    }

//    public abstract void showItemContenData(ViewHolder viewHolder, T data, ViewGroup parent, int position);
//
//    public class ViewHolder {
//        private SparseArray<View> viewCaches = new SparseArray<View>();//已经查找到的UI控件的缓存
//        public View itemmView;
//
//        private ViewHolder(View itemView) {
//            this.itemmView = itemView;
//        }
//
//        public View getLayoutView() {
//            return itemmView;
//        }
//
//        public GridView getItemGridView(int id) {
//            GridView gv = (GridView) getChildView(id);
//
//            return gv;
//        }
//
//        private View getChildView(int itemChildViewId) {
//            View view = viewCaches.get(itemChildViewId);
//
//            if (view == null) {
//                view = itemmView.findViewById(itemChildViewId);
//                if (view != null) {
//                    viewCaches.put(itemChildViewId, view);
//                }
//            }
//            return view;
//        }
//
//        public void setTextViewContent(int itemChildViewId, String content) {
//            TextView childView = (TextView) getChildView(itemChildViewId);
//            if (childView != null) {
//                childView.setText(content);
//            }
//        }
//
//        public LinearLayout getLinearLayout(int itemChildViewId) {
//            LinearLayout childView = (LinearLayout) getChildView(itemChildViewId);
//            return childView;
//        }
//
//        public ImageView getImageView(int itemChildViewId) {
//            ImageView childView = (ImageView) getChildView(itemChildViewId);
//            return childView;
//        }
//
//        public RelativeLayout getRelativeLayout(int itemChildViewId) {
//            RelativeLayout childView = (RelativeLayout) getChildView(itemChildViewId);
//            return childView;
//        }
//    }
}
