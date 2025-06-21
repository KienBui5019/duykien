package com.example.buiduykien_2122110362;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList = new ArrayList<>();
    private List<Product> originalList = new ArrayList<>();
    private List<Product> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Thêm sản phẩm mẫu
        productList.add(new Product("1", "Cafe", 40000, "https://example.com/cafe1.jpg"));
        productList.add(new Product("2", "Trà Sữa Chân Châu", 35000, "https://example.com/trasua.jpg"));
        productList.add(new Product("3", "Cà phê muối", 35000, "https://example.com/caphemuoi.jpg"));
        productList.add(new Product("4", "Trà đào xả ớt", 35000, "https://example.com/tradao.jpg"));
        productList.add(new Product("5", "Trà đá", 35000, "https://example.com/trada.jpg"));
        productList.add(new Product("6", "Matcha latte", 35000, "https://example.com/matcha.jpg"));

        originalList.addAll(productList);

        adapter = new ProductAdapter(productList, new ProductAdapter.OnItemClickListener() {
            @Override
            public void onEdit(int position) {
                Toast.makeText(ProductListActivity.this, "Mua sản phẩm: " + productList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onAddToCart(int position) {
                Product product = productList.get(position);
                cartList.add(product);
                Toast.makeText(ProductListActivity.this, product.getName() + " đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onViewDetail(int position) {
                Product product = productList.get(position);
                Toast.makeText(ProductListActivity.this, "Xem: " + product.getName(), Toast.LENGTH_SHORT).show();

                // Nếu có activity chi tiết thì mở tại đây
                // Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                // intent.putExtra("product", product);
                // startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);

        Button xemgiohang = findViewById(R.id.xemgiohang);
        xemgiohang.setOnClickListener(v -> {
            Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
            intent.putExtra("cart", new ArrayList<>(cartList));
            startActivity(intent);
        });

        SearchView timkiem = findViewById(R.id.timkiem);
        timkiem.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void filterList(String text) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : originalList) {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(product);
            }
        }
        adapter.updateList(filteredList);
    }
}
