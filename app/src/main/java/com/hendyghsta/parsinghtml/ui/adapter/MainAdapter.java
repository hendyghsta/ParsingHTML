package com.hendyghsta.parsinghtml.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendyghsta.nonton.App;
import com.hendyghsta.nonton.R;
import com.hendyghsta.nonton.ui.activity.main.MainActivity;
import com.hendyghsta.nonton.utils.Interstitial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;


public class MainAdapter<T> extends RecyclerView.Adapter<MainAdapter<T>.ViewHolder> {

    private static final String TAG = MainAdapter.class.getSimpleName();
    private List<T> items = Collections.emptyList();
    private SparseBooleanArray selectedItems;
    private OnItemClickListener<T> onItemClickListener;
    private Context context;

    protected MainAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.populate(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateList(List<T> list) {
        this.items = list;
        notifyDataSetChanged();
    }

    public void loadMoreList(List<T> list){
        int prevSize = getItemCount();
        this.items.addAll(list);
        notifyItemRangeInserted(prevSize, list.size());
    }

    public void removeList() {
        int size = getItemCount();
        items.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void toggleSelection(int pos) {
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
        }
        else {
            selectedItems.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T item, boolean isLongClick);
    }

    public void setOnItemClickListener(final OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void populate(T item) {
        }

        @Override
        public void onClick(View v) {
            handleClick(v, false);
        }

        @Override
        public boolean onLongClick(View v) {
            return handleClick(v, true);
        }

        private boolean handleClick(View v, boolean isLongClick) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, items.get(getAdapterPosition()), isLongClick);
                int clickCount = ((App) context.getApplicationContext()).getClickcount() + 1;
                ((App)context.getApplicationContext()).setClickcount(clickCount);
                if (clickCount % 10 != 5)
                    return true;
                if (context instanceof MainActivity)
                    new Interstitial(context);
                return true;
            }
            return false;
        }
    }
}
