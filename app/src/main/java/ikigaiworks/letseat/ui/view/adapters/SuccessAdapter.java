package ikigaiworks.letseat.ui.view.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ikigaiworks.letseat.BR;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.databinding.LinearContentPaySuccessBinding;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.utils.CartUtils;

/**
 * Created by sergiolizanamontero on 28/11/17.
 */

public class SuccessAdapter extends RecyclerView.Adapter<SuccessAdapter.ViewHolder> {
    private ArrayList<ProductToCart> data;
    private Context context;
    private LayoutInflater inflater;
    private LinearContentPaySuccessBinding binding;
    private int position;

    public SuccessAdapter(ArrayList<ProductToCart> data , Context context){
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public SuccessAdapter(ArrayList<ProductToCart> data){
        this.data = data;
    }


    @Override
    public SuccessAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.linear_content_pay_success,parent,false);
        return new SuccessAdapter.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(SuccessAdapter.ViewHolder holder, int position) {
        this.position = position;
        final ProductToCart producto = data.get(position);
        holder.bind(producto);
    }

    public void addItem(int position , ProductToCart item){
        data.add(position,item);
    }

    public void updateItems(ArrayList<ProductToCart> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        CartUtils.removeFromCartPosition(position);
        data.remove(position);
        notifyItemRemoved(position);
    }

    public ProductToCart getItem(int adapterPosition){
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

        public void bind (ProductToCart item){
            binding.setVariable(BR.producto, item);
            binding.executePendingBindings();
        }




    }
}
