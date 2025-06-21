package com.example.buiduykien_2122110362;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity2 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home2);



        // Nhận dữ liệu Intent (nếu cần)
        Intent intent = getIntent();
        String id = intent.getStringExtra("phone");

        // Ẩn ActionBar nếu có
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Nút Đăng Xuất
        Button dangxuat = findViewById(R.id.dangxuat);
        dangxuat.setOnClickListener(v -> {
            Intent intentLogout = new Intent(HomeActivity2.this, MainActivity.class);
            intentLogout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentLogout);
            finish();
        });

        // Nút Quay Lại
        Button quaylai = findViewById(R.id.quaylai);
        quaylai.setOnClickListener(v -> {
            Intent intentBack = new Intent(HomeActivity2.this, MainActivity.class);
            intentBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentBack);
            finish();
        });

        // Nút API
        Button cafe = findViewById(R.id.cafe);
        cafe.setOnClickListener(v -> {
            Intent intentCafe = new Intent(HomeActivity2.this, cafeActivity.class);
            startActivity(intentCafe);
        });
        // Nút cafe ở đa
        Button Cafe = findViewById(R.id.Cafe);
        Cafe.setOnClickListener(v -> {
            Intent intentCafe = new Intent(HomeActivity2.this, ProductListActivity.class);
            startActivity(intentCafe);
        });



        // Xử lý padding theo hệ thống (systemBars)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.muangay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
