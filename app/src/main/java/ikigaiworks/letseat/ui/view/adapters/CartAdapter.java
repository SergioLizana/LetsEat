package ikigaiworks.letseat.ui.view.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ikigaiworks.letseat.BR;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.databinding.CartListContentBinding;
import ikigaiworks.letseat.model.ProductToCart;
import ikigaiworks.letseat.ui.view.fragments.cart.FragmentCartList;
import ikigaiworks.letseat.utils.CartUtils;

/**
 * Created by sergiolizanamontero on 12/10/17.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private ArrayList<ProductToCart> data;
    private Context context;
    private LayoutInflater inflater;
    private CartListContentBinding binding;
    private int position;
    private FragmentCartList fragmentCartList;

    public CartAdapter(ArrayList<ProductToCart> data, Context context, FragmentCartList fragmentCartList) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.fragmentCartList = fragmentCartList;
    }

    public CartAdapter(ArrayList<ProductToCart> data) {
        this.data = data;
    }


    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater, R.layout.cart_list_content, parent, false);
        return new CartAdapter.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        this.position = position;
        final ProductToCart producto = data.get(position);
        holder.bind(producto, fragmentCartList);

    }

    public void addItem(int position, ProductToCart item) {
        data.add(position, item);
    }

    public void updateItems(ArrayList<ProductToCart> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        CartUtils.removeFromCartPosition(position);
        data.remove(position);
        notifyItemRemoved(position);
    }

    public ProductToCart getItem(int adapterPosition) {
        return data.get(adapterPosition);
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }

    @BindingAdapter({"android:visibility"})
    public static void setVisibilityToExtra(TextView toExtra, ProductToCart productToCart) {
        toExtra.setVisibility(productToCart.isExtraVisibility());
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

        public void bind(ProductToCart item, FragmentCartList fragmentCartList) {
            binding.setVariable(BR.producto, item);
            binding.setVariable(BR.fragment_cart, fragmentCartList);
            binding.executePendingBindings();
        }


    }
}