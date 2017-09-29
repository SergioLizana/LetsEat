package ikigaiworks.letseat.ui.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ikigaiworks.letseat.BR;
import ikigaiworks.letseat.R;
import ikigaiworks.letseat.databinding.CarruselSlideBinding;
import ikigaiworks.letseat.model.CarruselSlide;
import ikigaiworks.letseat.ui.presenters.Presenter;


/**
 * Created by sergiolizanamontero on 17/8/17.
 */

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.ViewHolder> {

    private ArrayList<CarruselSlide> data;
    private Context context;
    private Presenter presenter;
    private LayoutInflater inflater;
    private CarruselSlideBinding binding;


    public CarruselAdapter(ArrayList<CarruselSlide> data , Context context, Presenter presenter){
        this.data = data;
        this.context = context;
        this.presenter = presenter;
        inflater = LayoutInflater.from(context);
    }
    public CarruselAdapter(ArrayList<CarruselSlide> data){
        this.data = data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Activity context = (Activity) recyclerView.getContext();
        Point windowDimensions = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(windowDimensions);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(inflater,R.layout.carrusel_slide,parent,false);
        return new CarruselAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CarruselSlide category = data.get(position);
        holder.bind(category,presenter);
    }

    public void addItem(int position , CarruselSlide slide){
        data.add(position,slide);
    }

    public void updateCarrusel(ArrayList<CarruselSlide> slideList){
        data = slideList;
        notifyDataSetChanged();
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, int id) {
        view.setImageResource(id);
    }

    public CarruselSlide getSlide(int adapterPosition){
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

        public void bind(final CarruselSlide slide, Presenter presenter) {
            binding.setVariable(BR.slide, slide);
            binding.setVariable(BR.presenter,presenter);
            binding.executePendingBindings();
        }



    }
}


