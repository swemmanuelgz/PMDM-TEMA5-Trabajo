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
 * Adaptador para mostrar la lista de recursos multimedia en un RecyclerView.
 */
public class MultimediaAdapter extends RecyclerView.Adapter<MultimediaAdapter.ViewHolder> {

    // Lista de elementos multimedia a mostrar
    private List<MultimediaItem> items;
    // Listener para manejar el clic en cada ítem
    private OnItemClickListener listener;

    // Interfaz para gestionar los eventos de clic
    public interface OnItemClickListener {
        void onItemClick(MultimediaItem item);
    }

    /**
     * Constructor del adaptador.
     *
     * @param items    Lista de ítems multimedia.
     * @param listener Listener para los clics.
     */
    public MultimediaAdapter(List<MultimediaItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el layout de cada ítem de la lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multimedia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Vincular los datos del ítem a la vista
        MultimediaItem item = items.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * ViewHolder para cada ítem multimedia.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Referencia al TextView que muestra el título
            title = itemView.findViewById(R.id.item_title);
        }

        /**
         * Método para vincular los datos del ítem con la vista.
         *
         * @param item     El recurso multimedia.
         * @param listener Listener para el clic.
         */
        public void bind(final MultimediaItem item, final OnItemClickListener listener) {
            title.setText(item.getTitle());
            // Configurar el clic sobre el ítem para notificar al listener
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}

