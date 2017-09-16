package ikigaiworks.letseat.ui.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ikigaiworks.letseat.R;
import ikigaiworks.letseat.model.CarruselSlide;
import ikigaiworks.letseat.ui.view.viewholders.RecyclerViewClickListener;

/**
 * Created by sergiolizanamontero on 17/8/17.
 */

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.ViewHolder> {

    private int itemHeight;
    private ArrayList<CarruselSlide> data;
    private OnItemClickListener listener;
    private Context context;


    public interface OnItemClickListener {
        void onItemClick(CarruselSlide item);
    }

    public CarruselAdapter(ArrayList<CarruselSlide> data , OnItemClickListener listener,Context context){
        this.data = data;
        this.listener = listener;
        this.context = context;
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
//        itemHeight = Math.round(windowDimensions.y * 0.6f);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.image_carrusel, parent, false);
        /*ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                itemHeight);
        v.setLayoutParams(params);*/
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(data.get(position).getIcon())
                .into(holder.image);
        holder.bind(data.get(position), listener);
        holder.descripcion.setText(context.getString(data.get(position).getIdText()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView descripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageSlide);
            descripcion = (TextView) itemView.findViewById(R.id.descSlide);
        }

        public void bind(final CarruselSlide slide, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(slide);
                }
            });
        }



    }
}


