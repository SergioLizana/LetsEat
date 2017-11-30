package ikigaiworks.letseat.ui.view.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ikigaiworks.letseat.BR;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.databinding.LastOrderListContentBinding;
import ikigaiworks.letseat.databinding.MenuListContentBinding;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.LastOrder;
import ikigaiworks.letseat.ui.presenters.lastorder.LastOrderPresenterImpl;
import ikigaiworks.letseat.ui.presenters.menu.CategoryFragmentPresenterImpl;


/**
 * Created by sergiolizanamontero on 26/9/17.
 */

public class LastOrderAdapter  extends RecyclerView.Adapter<LastOrderAdapter.ViewHolder> {

    private ArrayList<LastOrder> data;
    private Context context;
    private LastOrderPresenterImpl presenter;
    private LayoutInflater inflater;
    private LastOrderListContentBinding binding;

    public LastOrderAdapter(ArrayList<LastOrder> data , Context context, LastOrderPresenterImpl presenter){
        this.data = data;
        this.context = context;
        this.presenter = presenter;
        inflater = LayoutInflater.from(context);
    }
    public LastOrderAdapter(ArrayList<LastOrder> data){
        this.data = data;
    }


    @Override
    public LastOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater,R.layout.last_order_list_content,parent,false);
        return new LastOrderAdapter.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(LastOrderAdapter.ViewHolder holder, int position) {
        final LastOrder order = data.get(position);
        holder.bind(order,presenter);

    }

    public void addItem(int position , LastOrder item){
        data.add(position,item);
    }

    public void updateItem(ArrayList<LastOrder> orders){
        data = orders;
        notifyDataSetChanged();
    }

    public LastOrder getItem(int adapterPosition){
        return data.get(adapterPosition);
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

        public void bind (LastOrder lastOrder, LastOrderPresenterImpl p){
            binding.setVariable(BR.order, lastOrder);
            binding.setVariable(BR.presenter,p);
            binding.executePendingBindings();
        }




    }
}
