package com.ssquare.doit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ssquare.doit.Adapter.ToDoAdapter;
import com.ssquare.doit.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView taskRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;

    private List<ToDoModel> taskList;

    // BottomSheetDialog
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        taskList = new ArrayList<>();

        fab = findViewById(R.id.floatingActingButton);
        taskRecyclerView = findViewById(R.id.taskRecyclerView);

        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(this);
        taskRecyclerView.setAdapter(tasksAdapter);


        ToDoModel task = new ToDoModel();
        task.setTask("This is a test task");
        task.setStatus(0);
        task.setId(1);

        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);
        taskList.add(task);

        tasksAdapter.setTask(taskList);


        //bottomSheetDialog.setContentView(R.layout.add_task_layout);
        //bottomSheetDialog.setCanceledOnTouchOutside(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog = new BottomSheetDialog(MainActivity.this, R.style.BottomSheetStyle);
                View bottomSheetView = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_task_layout,
                        (LinearLayout)findViewById(R.id.sheetLayoutId));

                TextInputEditText newTask;
                Button saveBtn;

                newTask = (TextInputEditText) bottomSheetView.findViewById(R.id.newTaskEditText);
                saveBtn = (Button) bottomSheetView.findViewById(R.id.saveBtn);

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String strTask = newTask.getText().toString();

                        ToDoModel task = new ToDoModel();
                        task.setTask(strTask);

                        task.setStatus(0);
                        task.setId(1);

                        taskList.add(task);
                        tasksAdapter.notifyDataSetChanged();

                        Toast.makeText(MainActivity.this, "New Task Added", Toast.LENGTH_LONG).show();
                        bottomSheetDialog.dismiss();

                    }
                });


                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();


                Toast.makeText(getApplicationContext(),"Hello Kitty",Toast.LENGTH_SHORT).show();

            }
        });

//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}