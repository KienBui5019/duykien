package com.example.buiduykien_2122110362;

import android.os.Bundle;
import android.os.Build;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private ArrayList<Product> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lấy giỏ hàng an toàn từ Intent
        cartList = getSerializableArrayListExtraSafe("cart");

        adapter = new CartAdapter(cartList);
        recyclerView.setAdapter(adapter);


    }

    @SuppressWarnings("unchecked")
    private ArrayList<Product> getSerializableArrayListExtraSafe(String key) {
        ArrayList<Product> cartList = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            cartList = (ArrayList<Product>) getIntent().getSerializableExtra(key, ArrayList.class);
        } else {
            Object obj = getIntent().getSerializableExtra(key);
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> rawList = (ArrayList<?>) obj;
                if (!rawList.isEmpty() && rawList.get(0) instanceof Product) {
                    cartList = (ArrayList<Product>) rawList;
                } else if (rawList.isEmpty()) {
                    cartList = new ArrayList<>();  // danh sách rỗng nhưng hợp lệ
                }
            }
        }
        if (cartList == null) {
            cartList = new ArrayList<>();  // tránh null gây crash khi setAdapter
        }
        return cartList;
    }



}




