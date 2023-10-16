package com.example.myapplication;
//59-71 проверить
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/fakehomer/jsonforms2/main/") // Замените на свой адрес GitHub Pages
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyApi myApi = retrofit.create(MyApi.class);

        Call<List<Item>> call = myApi.getData();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    List<Item> itemList = response.body();

                    adapter = new ItemAdapter(itemList);
                    recyclerView.setAdapter(adapter);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


                    Spinner descriptionSpinner = findViewById(R.id.descriptionSpinner);
                    Button filterButton = findViewById(R.id.filterButton);

                    Button resetFilterButton = findViewById(R.id.resetButton);


                    resetFilterButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            descriptionSpinner.setSelection(0);
                            adapter.setItems(itemList);
                        }
                    });


                    ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                            MainActivity.this, R.array.description_options, android.R.layout.simple_spinner_item);
                    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                    descriptionSpinner.setAdapter(spinnerAdapter);

                    filterButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String selectedDescription = descriptionSpinner.getSelectedItem().toString();
                            List<Item> filteredList = new ArrayList<>();
                            for (Item item : itemList) {
                                if (selectedDescription.equals(item.getDescription())) {
                                    filteredList.add(item);
                                }
                            }
                            adapter.setItems(filteredList);
                        }
                    });
                } else {

                }

            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                // Обработка ошибки
            }
        });
    }

}
