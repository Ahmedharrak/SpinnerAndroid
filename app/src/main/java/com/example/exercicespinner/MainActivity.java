package com.example.exercicespinner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    TextView textView;
    ArrayList<String> arrayList;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);
        arrayList = new ArrayList<>();
        arrayList.add("China");
        arrayList.add("India");
        arrayList.add("USA");
        arrayList.add("Indonesia");
        arrayList.add("Pakistan");
        arrayList.add("Brazil");
        arrayList.add("Nigeria");
        arrayList.add("Bangladesh");
        arrayList.add("Russia");
        arrayList.add("Mexico");


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.dialog_searchable_spinner);

                dialog.getWindow().setLayout(650,800);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        textView.setText(adapter.getItem(position));

                        dialog.dismiss();

                    }
                });

            }
        });

        initList();
        Spinner spinnerCountries = findViewById(R.id.spinner_countries);
        mAdapter = new CountryAdapter(this, mCountryList);
        spinnerCountries.setAdapter(mAdapter);
        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getCountryName();
                Toast.makeText(MainActivity.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void initList() {
        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryItem("China", R.drawable.china));
        mCountryList.add(new CountryItem("India", R.drawable.india));
        mCountryList.add(new CountryItem("USA", R.drawable.usa));
        mCountryList.add(new CountryItem("Indonesia", R.drawable.indonesia));
        mCountryList.add(new CountryItem("Pakistan", R.drawable.pakistan));
        mCountryList.add(new CountryItem("Brazil", R.drawable.brazil));
        mCountryList.add(new CountryItem("Nigeria", R.drawable.nigeria));
        mCountryList.add(new CountryItem("Bangladesh", R.drawable.bangladesh));
        mCountryList.add(new CountryItem("Russia", R.drawable.russia));
        mCountryList.add(new CountryItem("Mexico", R.drawable.mexico));

    }
}