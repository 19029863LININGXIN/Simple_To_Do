package sg.edu.rp.c346.id19029863.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    EditText et;
    Button add, clear, delete;
    Spinner spn;
    ListView LVTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spn = findViewById(R.id.spinner);
        et = findViewById(R.id.editText);
        add = findViewById(R.id.buttonAdd);
        delete = findViewById(R.id.buttonDelete);
        clear = findViewById(R.id.buttonClear);
        LVTask = findViewById(R.id.listViewTask);

        ArrayList<String> task = new ArrayList<String>();

        ArrayAdapter atask = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, task);

        LVTask.setAdapter(atask);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        et.setHint(R.string.newtask );
                        add.setEnabled(true);
                        delete.setEnabled(false);
                        break;
                    case 1:
                        et.setHint(R.string.removetask );
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                String newtask = et.getText().toString();
                task.add(newtask);
                atask.notifyDataSetChanged();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(et.getText().toString());

                if (task.isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.listRemove , Toast.LENGTH_SHORT).show();
                }else if (pos >task.size() -1 || pos < 0 ){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }else{
                    task.remove(pos);
                    atask.notifyDataSetChanged();
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                task.clear();
                atask.notifyDataSetChanged();
            }
        });

    }
}