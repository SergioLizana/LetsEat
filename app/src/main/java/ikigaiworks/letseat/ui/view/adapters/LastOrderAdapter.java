package ikigaiworks.letseat.ui.view.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import ikigaiworks.letseat.BR;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.databinding.LastOrderListContentBinding;
import ikigaiworks.letseat.model.FavOrder;
import ikigaiworks.letseat.model.LastOrder;
import ikigaiworks.letseat.ui.presenters.lastorder.LastOrderPresenterImpl;


/**
 * Created by sergiolizanamontero on 26/9/17.
 */

public class LastOrderAdapter  extends RecyclerView.Adapter<LastOrderAdapter.ViewHolder> {

    private LinkedHashMap<String,FavOrder> data;
    private Context context;
    private String[] mKeys;
    private LastOrderPresenterImpl presenter;
    private LayoutInflater inflater;
    private LastOrderListContentBinding binding;

    public LastOrderAdapter(LinkedHashMap<String,FavOrder> data , Context context, LastOrderPresenterImpl presenter){
        this.data = data;
        this.context = context;
        this.presenter = presenter;
        inflater = LayoutInflater.from(context);
        mKeys = data.keySet().toArray(new String[data.size()]);
    }
    public LastOrderAdapter(LinkedHashMap<String,FavOrder> data){
        this.data = data;
    }


    @Override
    public LastOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater,R.layout.last_order_list_content,parent,false);
        return new LastOrderAdapter.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(LastOrderAdapter.ViewHolder holder, int position) {
        final FavOrder order = data.get(mKeys[position]);
        holder.bind(order,presenter);

    }

    public void addItem(int position , LinkedHashMap<String,FavOrder> item){
        data.putAll(item);
    }

    public void updateItem(LinkedHashMap<String,FavOrder> items){
        data = items;
        notifyDataSetChanged();
    }

    public FavOrder getItem(int adapterPosition){
        return data.get(mKeys[adapterPosition]);
    }

    public String getName(int adapterPosition){
       return mKeys[adapterPosition];
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind (FavOrder favOrder, LastOrderPresenterImpl p){
            binding.setVariable(BR.fav, favOrder);
            binding.setVariable(BR.presenter,p);
            binding.executePendingBindings();
        }




    }
}
