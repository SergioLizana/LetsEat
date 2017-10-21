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
import ikigaiworks.letseat.databinding.MenuListContentBinding;
import ikigaiworks.letseat.databinding.ProductListContentBinding;
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.model.Producto;
import ikigaiworks.letseat.ui.presenters.menu.CategoryFragmentPresenterImpl;
import ikigaiworks.letseat.ui.presenters.menu.ProductListFragmentPresenterImpl;

/**
 * Created by sergiolizanamontero on 12/10/17.
 */

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    private ArrayList<Producto> data;
    private Context context;
    private ProductListFragmentPresenterImpl presenter;
    private LayoutInflater inflater;
    private ProductListContentBinding binding;

    public MenuListAdapter(ArrayList<Producto> data , Context context, ProductListFragmentPresenterImpl presenter){
        this.data = data;
        this.context = context;
        this.presenter = presenter;
        inflater = LayoutInflater.from(context);
    }
    public MenuListAdapter(ArrayList<Producto> data){
        this.data = data;
    }


    @Override
    public MenuListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_list_content,parent,false);
        return new MenuListAdapter.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(MenuListAdapter.ViewHolder holder, int position) {
        final Producto producto = data.get(position);
        holder.bind(producto,presenter);

    }

    public void addItem(int position , Producto item){
        data.add(position,item);
    }

    public void updateItem(ArrayList<Producto> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public Producto getItem(int adapterPosition){
        return data.get(adapterPosition);
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
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

        public void bind (Producto item, ProductListFragmentPresenterImpl p){
            binding.setVariable(BR.producto, item);
            binding.setVariable(BR.presenter,p);
            binding.executePendingBindings();
        }




    }
}