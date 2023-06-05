package sg.edu.rp.c346.id22012433.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etElement;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvTask;
    Spinner spinTask;
    ArrayList<String> TaskArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etElement = findViewById(R.id.editTextAdd);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        lvTask = findViewById(R.id.listViewTask);
        spinTask = findViewById(R.id.spinner);
        btnDelete = findViewById(R.id.btnDelete);

        spinTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etElement.setHint(getResources().getString(R.string.task1));
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etElement.setHint(getResources().getString(R.string.task2));
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TaskArray = new ArrayList<String>();

        ArrayAdapter<String> TaskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TaskArray);
        lvTask.setAdapter(TaskAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Taskin = etElement.getText().toString();
                TaskArray.add(Taskin);
                TaskAdapter.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TaskArray.isEmpty() || TaskArray.size()==0) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    int position = Integer.parseInt(etElement.getText().toString());
                    if (position >= 0 && position < TaskArray.size()) {
                        TaskArray.remove(position);
                        TaskAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskArray.clear();
                TaskAdapter.notifyDataSetChanged();
            }
        });
    }
}
