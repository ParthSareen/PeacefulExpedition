package thacks2.scheduler;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class NewTaskActivity extends AppCompatActivity {

    private static final String TAG = "NewTaskActivity";
    public static boolean segStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Task newTask = new Task();

        // user input
        EditText newTaskNameInput = (EditText)findViewById(R.id.newTaskNameInput);
        Spinner newTaskPrioritySpinner = (Spinner)findViewById(R.id.newTaskPrioritySpinner);
        Switch newTaskSittingSwitch = (Switch)findViewById(R.id.newTaskSittingSwitch);
        EditText newTaskDurationTime = (EditText)findViewById(R.id.newTaskDurationTime);
        DatePicker newTaskDeadlineDatePicker = (DatePicker)findViewById(R.id.newTaskDeadlineDatePicker);

        // switch listener
        boolean status = false;
        newTaskSittingSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            segStatus = true;
                        } else {
                            segStatus = false;
                        }
                    }
                }
        );

        // submission button
        Button newTaskSubmitButton = (Button)findViewById(R.id.newTaskSubmitButton);
        newTaskSubmitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // create new task
                        Task newTask = new Task();

                        // set task name
                        newTask.setName(((EditText)findViewById(R.id.newTaskNameInput)).getText().toString());

                        // set task priority
                        newTask.setPriority(((Spinner)findViewById(R.id.newTaskPrioritySpinner)).getSelectedItemPosition() + 1);
                        Log.i(TAG, Integer.toString(newTask.getPriority()));

                        // set task segmentability
                        newTask.setSegmentable(segStatus);

                        // set task duration in minutes
                        Log.i(TAG, ((EditText)findViewById(R.id.newTaskDurationTime)).getText().toString());
                        newTask.setDuration(Integer.parseInt(((EditText)findViewById(R.id.newTaskDurationTime)).getText().toString()));

                        // set task deadline
                        String startDateString = ((EditText)findViewById(R.id.newTaskNameInput)).getText().toString();
                        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                        Date startDate = new Date();
                        try {
                            startDate = df.parse(startDateString);
                            String newDateString = df.format(startDate);
                            System.out.println(newDateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        newTask.setDeadline(startDate);

                        // file IO
                        String FILE_NAME = "tasks.txt";
                        try {
                            // FileOutputStream fileos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
                            Writer output = new BufferedWriter(new FileWriter(FILE_NAME, true));
                            output.append("[name: " + newTask.name + ", duration: " + newTask.duration +
                                "priority: " + newTask.priority + " segmentable: " newTask.segmentable);

                        } catch (FileNotFoundException e) {
                            File file = new File(FILE_NAME);
                            FileOutputStream fileos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
                        }n
                    }
                }
        );
    }
}
