package xyz.sleepygamers.maithoncenteen.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xyz.sleepygamers.maithoncenteen.R;
import xyz.sleepygamers.maithoncenteen.models.Order;

public class Order_historyAdapter extends RecyclerView.Adapter<Order_historyAdapter.OrderHistoryHolder> {

    private Context mCtx;
    private List<Order> OrderHistoryList;

    public Order_historyAdapter(Context mCtx, List<Order> OrderHistoryList) {
        this.mCtx = mCtx;
        this.OrderHistoryList = OrderHistoryList;
    }

    @NonNull
    @Override
    public Order_historyAdapter.OrderHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.order_history, null);
        return new OrderHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Order_historyAdapter.OrderHistoryHolder holder, int position) {
        Order order = OrderHistoryList.get(position);

        holder.order_details.setText(order.getOrder_details());
        holder.order_date.setText(order.getOrder_date());
        holder.price.setText(String.valueOf(order.getPrice()));
        holder.order_type.setText(order.getOrder_type());
        holder.delivery_type.setText(order.getDelivery_type());
        holder.status.setText(order.getStatus());

    }

    @Override
    public int getItemCount() {
        return OrderHistoryList.size();
    }
    class OrderHistoryHolder extends RecyclerView.ViewHolder {

        TextView order_details, order_date,price, order_type,delivery_type,status;

        public OrderHistoryHolder(View itemView) {
            super(itemView);

            order_details = itemView.findViewById(R.id.order_details);
            order_date = itemView.findViewById(R.id.order_date);
            price = itemView.findViewById(R.id.price);
            order_type = itemView.findViewById(R.id.order_type);
            delivery_type = itemView.findViewById(R.id.delivery_type);
            status = itemView.findViewById(R.id.order_status);

        }
    }
}
