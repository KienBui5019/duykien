package com.example.buiduykien_2122110362;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        EditText nhapsdt = findViewById(R.id.nhapsdt);
        EditText nhapmatkhau = findViewById(R.id.nhapmatkhau);
        Button dangnhap1 = findViewById(R.id.dangnhap1);
        Button dangky= findViewById(R.id.dangky); // Nút đăng ký

        // Xử lý đăng nhập
        dangnhap1.setOnClickListener(v -> {
            String inputPhone = nhapsdt.getText().toString().trim();
            String inputPass = nhapmatkhau.getText().toString().trim();

            if (inputPhone.isEmpty() || inputPass.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy dữ liệu từ SharedPreferences
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            String savedPhone = prefs.getString("phone", "");
            String savedPass = prefs.getString("password", "");

            if (inputPhone.equals(savedPhone) && inputPass.equals(savedPass)) {
                // Đăng nhập thành công
                Intent intent = new Intent(getApplicationContext(), HomeActivity2.class);
                intent.putExtra("phone", inputPhone);
                startActivity(intent);
                finish(); // Đóng MainActivity
            } else {
                Toast.makeText(getApplicationContext(), "Sai số điện thoại hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý chuyển sang trang đăng ký
        dangky.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, dangkyActivity.class);
            startActivity(intent);
        });

        // Ẩn ActionBar nếu có
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Xử lý lề hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.muangay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
