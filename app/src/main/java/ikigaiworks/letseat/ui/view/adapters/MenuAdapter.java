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
import ikigaiworks.letseat.model.Category;
import ikigaiworks.letseat.ui.presenters.menu.MenuFragmentPresenterImpl;


/**
 * Created by sergiolizanamontero on 26/9/17.
 */

public class MenuAdapter  extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private ArrayList<Category> data;
    private Context context;
    private MenuFragmentPresenterImpl presenter;
    private LayoutInflater inflater;
    private MenuListContentBinding binding;

    public MenuAdapter(ArrayList<Category> data , Context context, MenuFragmentPresenterImpl presenter){
        this.data = data;
        this.context = context;
        this.presenter = presenter;
        inflater = LayoutInflater.from(context);
    }
    public MenuAdapter(ArrayList<Category> data){
        this.data = data;
    }


    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater,R.layout.menu_list_content,parent,false);
        return new MenuAdapter.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        final Category category = data.get(position);
        holder.bind(category,presenter);

    }

    public void addItem(int position , Category item){
        data.add(position,item);
    }

    public void updateItem(ArrayList<Category> categories){
        data = categories;
        notifyDataSetChanged();
    }

    public Category getItem(int adapterPosition){
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

        public void bind (Category category, MenuFragmentPresenterImpl p){
            binding.setVariable(BR.category, category);
            binding.setVariable(BR.presenter,p);
            binding.executePendingBindings();
        }




    }
}
