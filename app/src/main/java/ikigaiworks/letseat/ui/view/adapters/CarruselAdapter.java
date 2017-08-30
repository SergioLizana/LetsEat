package ikigaiworks.letseat.ui.view.adapters;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import ikigaiworks.letseat.R;

/**
 * Created by sergiolizanamontero on 17/8/17.
 */

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.ViewHolder> {

    private int itemHeight;
    private List<Integer> data;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Integer item);
    }

    public CarruselAdapter(List<Integer> data , OnItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }
    public CarruselAdapter(List<Integer> data){
        this.data = data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Activity context = (Activity) recyclerView.getContext();
        Point windowDimensions = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(windowDimensions);
        itemHeight = Math.round(windowDimensions.y * 0.6f);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.image_carrusel, parent, false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                itemHeight);
        v.setLayoutParams(params);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(data.get(position))
                .into(holder.image);
        holder.bind(position, listener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private View overlay;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            overlay = itemView.findViewById(R.id.overlay);
        }

        public void bind(final int position, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(position);
                }
            });
        }


        public void setOverlayColor(@ColorInt int color) {
            overlay.setBackgroundColor(color);
        }
    }
}


