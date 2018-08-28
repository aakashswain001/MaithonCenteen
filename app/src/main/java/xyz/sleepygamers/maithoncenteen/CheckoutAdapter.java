package xyz.sleepygamers.maithoncenteen;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xyz.sleepygamers.maithoncenteen.models.foodmenu;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    Context mCtx;
    private List<foodmenu> foodemnuList;

    public CheckoutAdapter(Context mCtx, List<foodmenu> foodemnuList) {
        this.mCtx = mCtx;
        this.foodemnuList = foodemnuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.checkout_row, parent, false);
        return new CheckoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final foodmenu foodmenu = foodemnuList.get(position);

        holder.menuname.setText(foodmenu.getName());
        holder.price.setText(Integer.toString(Integer.parseInt(foodmenu.getPrice()) * foodmenu.getCount()));
        holder.quantity.setText(Integer.toString(foodmenu.getCount()));
        if (foodmenu.getType().equals("veg")) {
            holder.veg.setVisibility(View.VISIBLE);
        } else {
            holder.nonveg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return foodemnuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView menuname, price, quantity;
        private ImageView veg, nonveg;

        public ViewHolder(View itemView) {
            super(itemView);
            menuname = itemView.findViewById(R.id.tv_menuname);
            price = itemView.findViewById(R.id.tv_price);
            quantity = itemView.findViewById(R.id.tv_quantity);
            veg = itemView.findViewById(R.id.veg);
            nonveg = itemView.findViewById(R.id.nonveg);
        }

    }
}
