package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activities.R;
import com.example.model.Order;
import com.example.model.Service;
import com.example.services.ServiceManager;
import com.example.services.Session;

import java.time.LocalDate;
import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private Context context;

    public ServiceAdapter() {
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_service_layout, parent, false);

        ServiceViewHolder viewHolder = new ServiceViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        holder.bind(ServiceManager.getInstance().getServices().get(position),context);
    }

    @Override
    public int getItemCount() {
        return ServiceManager.getInstance().getServices().size();
    }

    class ServiceViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView priceTextView;
        private Button button;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.service_img);
            titleTextView = itemView.findViewById(R.id.service_title);
            descriptionTextView = itemView.findViewById(R.id.service_description);
            priceTextView = itemView.findViewById(R.id.service_price);
            button = itemView.findViewById(R.id.service_button);

        }

        public void bind(Service service, Context context){
            imageView.setImageResource(service.getImage());
            titleTextView.setText(service.getTitle());
            descriptionTextView.setText(service.getDescription());
            priceTextView.setText(service.getPrice());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Order order = new Order(LocalDate.now().toString(),service.getTitle(),0f,Order.PROCESSING);
                    Session.getCurrentUser().getOrders().add(order);
                    Toast.makeText(context, context.getString(R.string.order_added), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
