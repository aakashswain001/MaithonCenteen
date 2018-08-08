package xyz.sleepygamers.maithoncenteen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

import xyz.sleepygamers.maithoncenteen.interfaces.AddorRemoveCallbacks;
import xyz.sleepygamers.maithoncenteen.models.foodmenu;

public class FoodmenuAdapter extends RecyclerView.Adapter<FoodmenuAdapter.ViewHolder> {
    Context mCtx;
    private List<foodmenu> foodemnuList;

    public FoodmenuAdapter(Context mCtx, List<foodmenu> foodemnuList) {
        this.mCtx = mCtx;
        this.foodemnuList = foodemnuList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.foodmenu_row, parent, false);
        return new FoodmenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final foodmenu foodmenu = foodemnuList.get(position);

        holder.name.setText(foodmenu.getName());
        holder.price.setText(foodmenu.getPrice());
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = holder.elegantNumberButton.getNumber();
                int number = Integer.parseInt(num);
                int k = foodemnuList.get(position).getCount();
                foodemnuList.get(position).setCount(number);
                if (number-k > 0) {
                    ((AddorRemoveCallbacks) mCtx).onAddProduct( number-k);
                } else if (number-k < 0)
                    ((AddorRemoveCallbacks) mCtx).onRemoveProduct(k - number);

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodemnuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, price;
        private Button addToCart;
        private ElegantNumberButton elegantNumberButton;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.breakfast_name);
            price = itemView.findViewById(R.id.breakfast_price);
            elegantNumberButton = itemView.findViewById(R.id.qty_button);
            addToCart = itemView.findViewById(R.id.add_to_cart);

        }

    }
}
