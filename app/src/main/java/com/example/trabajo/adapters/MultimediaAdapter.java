package com.example.trabajo.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajo.MultimediaItem;
import com.example.trabajo.R;

import java.util.List;

/**
 * Adaptador para el RecyclerView que muestra la lista de recursos multimedia.
 */
public class MultimediaAdapter extends RecyclerView.Adapter<MultimediaAdapter.ViewHolder> {

    private List<MultimediaItem> items;
    private OnItemClickListener listener;

    /**
     * Interfaz para manejar los clics en cada ítem.
     */
    public interface OnItemClickListener {
        void onItemClick(MultimediaItem item);
    }

    public MultimediaAdapter(List<MultimediaItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el layout de cada ítem
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multimedia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MultimediaItem item = items.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * ViewHolder para cada elemento de la lista.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
        }

        /**
         * Vincula los datos del ítem con la vista y configura el clic.
         */
        public void bind(final MultimediaItem item, final OnItemClickListener listener) {
            title.setText(item.getTitle());
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}

