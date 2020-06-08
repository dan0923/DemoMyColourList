package sg.edu.rp.c346.id18015497.demomycolourlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement,etIndex;
    Button btnAdd,btnRemove,btnUpdate;
    ListView lvColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etElement = findViewById(R.id.editTextColor);
        etIndex = findViewById(R.id.editTextIndex);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnRemove = findViewById(R.id.buttonRemoveItem);
        btnUpdate = findViewById(R.id.buttonUpdateItem);
        lvColor = findViewById(R.id.listViewColor);

        final ArrayList<String> alColors = new ArrayList<String>();

        alColors.add("Red");
        alColors.add("Orange");

        final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alColors);
        lvColor.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = etElement.getText().toString();
                int pos = Integer.parseInt(etIndex.getText().toString());
                alColors.add(pos, color);
                adapter.notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = etElement.getText().toString();

                for (int i = 0; i < alColors.size(); i++) {
                    String chosenCol = alColors.get(i);

                    if (chosenCol.equalsIgnoreCase(color)) {
                        alColors.remove(i);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this,String.format("%s removed.", color), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this,String.format("%s does not exist.", color), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = etElement.getText().toString();
                int pos = Integer.parseInt(etIndex.getText().toString());

                if (pos <= alColors.size()) {
                    alColors.set(pos,color);
                    adapter.notifyDataSetChanged();
                }

                else {
                    Toast.makeText(MainActivity.this,String.format("%s does not exist.", pos), Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvColor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedColor = alColors.get(position);
                Toast.makeText(MainActivity.this,selectedColor, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
