package com.example.buiduykien_2122110362;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dangkyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky); // Layout của đăng ký

        EditText dksdt = findViewById(R.id.dksdt);
        EditText dkmk = findViewById(R.id.dkmk);
        Button nutdangky = findViewById(R.id.nutdangky);

        nutdangky.setOnClickListener(v -> {
            String phone = dksdt.getText().toString().trim();
            String password = dkmk.getText().toString().trim();

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu thông tin vào SharedPreferences
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("phone", phone);
            editor.putString("password", password);
            editor.apply();

            Toast.makeText(getApplicationContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển về MainActivity (đăng nhập)
            Intent intent = new Intent(dangkyActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Đóng RegisterActivity

            // Ẩn ActionBar nếu có
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
        });
    }
}
