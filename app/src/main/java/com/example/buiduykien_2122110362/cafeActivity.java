package com.example.buiduykien_2122110362;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class cafeActivity extends AppCompatActivity {

    private ListView listViewApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cafe);

        // Ánh xạ ListView
        listViewApi = findViewById(R.id.Api);

        // Nút quay lại
        Button quaylai1 = findViewById(R.id.quaylai1);
        quaylai1.setOnClickListener(v -> {
            Intent intentBack1 = new Intent(cafeActivity.this, HomeActivity2.class);
            intentBack1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intentBack1);
            finish();
        });

        // Ẩn ActionBar nếu có
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Nút kết nối API
        Button ketnoiapi = findViewById(R.id.ketnoiapi);
        ketnoiapi.setOnClickListener(v -> {
            ApiService apiService = ApiClient.getClient().create(ApiService.class);
            apiService.getProduct().enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        JsonElement body = response.body();
                        if (body.isJsonArray()) {
                            JsonArray jsonArray = body.getAsJsonArray();
                            List<String> dataList = new ArrayList<>();
                            try {
                                for (JsonElement element : jsonArray) {
                                    if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
                                        dataList.add(element.getAsString());
                                    } else if (element.isJsonObject() && element.getAsJsonObject().has("name")) {
                                        dataList.add(element.getAsJsonObject().get("name").getAsString());
                                    } else {
                                        dataList.add(element.toString());
                                    }
                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                                        cafeActivity.this,
                                        android.R.layout.simple_list_item_1,
                                        dataList
                                );
                                listViewApi.setAdapter(adapter);
                                Toast.makeText(cafeActivity.this, "Kết nối API thành công!", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(cafeActivity.this, "Lỗi xử lý dữ liệu API!", Toast.LENGTH_SHORT).show();
                                Log.e("API Parsing Error", e.toString());
                            }
                        } else {
                            Toast.makeText(cafeActivity.this, "Dữ liệu API không phải mảng JSON!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(cafeActivity.this, "Lỗi phản hồi từ server!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    String message = (t.getMessage() != null) ? t.getMessage() : "Lỗi kết nối không xác định!";
                    Log.e("API Error", message);
                    Toast.makeText(cafeActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Xử lý padding hệ thống (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.muangay), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
