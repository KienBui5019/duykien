package com.example.buiduykien_2122110362;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    public interface OnItemClickListener {
        void onEdit(int position);

        void onAddToCart(int position);
        void onViewDetail(int position); // Thêm hàm xử lý nút "Xem"
    }

    private List<Product> products;
    private OnItemClickListener listener;

    public ProductAdapter(List<Product> products, OnItemClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(v);
    }

    public void updateList(List<Product> newList) {
        this.products = newList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(String.format("%.0f đ", product.getPrice()));

        Glide.with(holder.imgProduct.getContext())
                .load(product.getImageUrl())
                .placeholder(R.drawable.img)  // Ảnh mặc định
                .into(holder.imgProduct);

        // Gắn sự kiện cho các nút
        holder.xem.setOnClickListener(v -> listener.onViewDetail(position));
        holder.themgiohang.setOnClickListener(v -> listener.onAddToCart(position));
        holder.muangay1.setOnClickListener(v -> listener.onEdit(position));  // hoặc thực hiện mua ngay nếu bạn xử lý logic
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvName, tvPrice;
        Button xem, muangay1;
        ImageButton themgiohang;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            xem = itemView.findViewById(R.id.xem);
            muangay1 = itemView.findViewById(R.id.muangay1);
            themgiohang = itemView.findViewById(R.id.themgiohang);
        }
    }
}
