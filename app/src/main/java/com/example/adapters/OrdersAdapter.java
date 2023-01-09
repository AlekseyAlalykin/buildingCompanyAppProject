package com.example.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activities.R;
import com.example.model.Order;
import com.example.services.Session;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
    private Context context;

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_order_layout, parent, false);

        OrdersAdapter.OrderViewHolder viewHolder = new OrdersAdapter.OrderViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        List<Order> orders = Session.getCurrentUser().getOrders();
        holder.bind(orders.get(orders.size() - position - 1), context);
    }

    @Override
    public int getItemCount() {
        return Session.getCurrentUser().getOrders().size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Order order;
        RatingBar ratingBar;
        TextView orderTitleTextView;
        TextView orderDateTextView;
        TextView currentStatusTextView;
        Button orderButton;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            ratingBar = itemView.findViewById(R.id.rating);
            orderTitleTextView = itemView.findViewById(R.id.order_title);
            orderDateTextView = itemView.findViewById(R.id.order_date);
            currentStatusTextView = itemView.findViewById(R.id.current_status);
            orderButton = itemView.findViewById(R.id.order_button);
        }

        public void bind(Order order, Context context){
            this.order = order;
            orderButton.setText(context.getString(R.string.orders_cancel_button_text));
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    order.setRating(ratingBar.getRating());
                }
            });

            if (order.getCurrentState().equals(Order.FINISHED))
                ratingBar.setVisibility(View.VISIBLE);
            else
                ratingBar.setVisibility(View.GONE);

            ratingBar.setRating(order.getRating());

            orderTitleTextView.setText(order.getTitle());
            orderDateTextView.setText(order.getDate());
            currentStatusTextView.setText(getStringForCurrentState(order.getCurrentState(),context));
            currentStatusTextView.setTextColor(getColorForCurrentState(order.getCurrentState(),context));
            if (!order.getCurrentState().equals(Order.PROCESSING))
                orderButton.setVisibility(View.GONE);
            else
                orderButton.setVisibility(View.VISIBLE);

            orderButton.setOnClickListener(this::onClick);
        }

        public String getStringForCurrentState(Integer state, Context context){
            switch (order.getCurrentState()){
                case Order.PROCESSING:
                    return context.getString(R.string.orders_state_processing);

                case Order.IN_PROGRESS:
                    return context.getString(R.string.orders_state_in_progress);

                case Order.FINISHED:
                    return context.getString(R.string.orders_state_done);

                case Order.CANCELED:
                    return context.getString(R.string.orders_state_canceled);

            }

            return "";
        }

        public int getColorForCurrentState(Integer state, Context context){
            switch (order.getCurrentState()){
                case Order.PROCESSING:
                    return context.getColor(R.color.order_processing);

                case Order.IN_PROGRESS:
                    return context.getColor(R.color.order_in_progress);

                case Order.FINISHED:
                    return context.getColor(R.color.order_finished);

                case Order.CANCELED:
                    return context.getColor(R.color.order_canceled);

            }

            return 0;
        }

        @Override
        public void onClick(View view) {
            order.setCurrentState(Order.CANCELED);
            orderButton.setText(context.getString(R.string.orders_restore_button_text));
            orderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    order.setCurrentState(Order.PROCESSING);
                    orderButton.setText(context.getString(R.string.orders_cancel_button_text));
                    orderButton.setOnClickListener(OrderViewHolder.this);
                    currentStatusTextView.setText(getStringForCurrentState(order.getCurrentState(),context));
                    currentStatusTextView.setTextColor(getColorForCurrentState(order.getCurrentState(),context));
                }
            });
            //orderButton.setVisibility(View.GONE);
            currentStatusTextView.setText(getStringForCurrentState(order.getCurrentState(),context));
            currentStatusTextView.setTextColor(getColorForCurrentState(order.getCurrentState(),context));
        }
    }


}
