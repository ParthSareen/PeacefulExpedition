package thacks2.scheduler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.ArrayList;

public class OverviewActivity extends AppCompatActivity {

    ArrayList<Task> app = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        ImageButton taskAddButton = (ImageButton)findViewById(R.id.taskAddButton);
        taskAddButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(OverviewActivity.this, NewTaskActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Button planButton = (Button)findViewById(R.id.planButton);
        planButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ArrayList<Task> mem = new ArrayList<>(NewTaskActivity.tasks); // mem is an alias for the original array
                        for (int z = 0; z < mem.size(); z++)
                            // divide events into minutes
                            if (mem.get(z).getSegmentable()&&mem.get(z).getDuration()>60) {
                                Task targ = new Task(mem.get(z));
                                for (int i = 0;i<targ.getDuration()/60-1;i++) {
                                    Task mod = new Task(targ);
                                    mod.setDuration(60);
                                    app.add(mod);
                                }
                                if (targ.getDuration()%60!=0) {
                                    Task modu = new Task(targ);
                                    modu.setDuration(targ.getDuration() % 60);
                                    app.add(modu);
                                }
                                mem.get(z).setDuration(60);
                            }
                        Collections.sort(app, Task.TaskComparator); // sorted task order
                        Collections.sort(mem, Task.TaskComparator); // original task order

                        for (Task t : mem) { // each task overall
                            System.out.print(t.getName());
                            System.out.print(t.getPriority());
                            System.out.print(t.getDuration());
                            System.out.println(t.getSegmentable());
                        }

                        System.out.println("app---------");

                        // outputs sorted tasks
                        for (Task t : app) {
                            System.out.print(t.getName());
                            System.out.print(t.getPriority());
                            System.out.print(t.getDuration());
                            System.out.println(t.getSegmentable());
                        }

                        ArrayList<Task> agg = new ArrayList<>(mem);
                        agg.addAll(app);
                        Collections.sort(agg,Task.TaskComparator);

                        System.out.println("agg---------");

                        for (Task t : agg) {
                            System.out.print(t.getName());
                            System.out.print(t.getPriority());
                            System.out.print(t.getDuration());
                            System.out.println(t.getSegmentable());
                        }

                        ArrayList<Task> seg = new ArrayList<>();
                        for (Task t : agg)
                            if (t.getSegmentable())
                                seg.add(t);
                        ArrayList<Task> noseg = new ArrayList<>();
                        for (Task t : agg)
                            if (!t.getSegmentable())
                                noseg.add(t);

                        if (noseg.size()==0)  {// no slots, just sort in order
                            Collections.sort(seg, Task.TaskComparator);
                            ArrayList<Task> all = new ArrayList<>();
                            all.addAll(seg);
                            return;
                        }
                        int interval = seg.size()/noseg.size(); // average slot size

                        System.out.println("seg---------");

                        for (Task t : seg) {
                            System.out.print(t.getName());
                            System.out.print(t.getPriority());
                            System.out.print(t.getDuration());
                            System.out.println(t.getSegmentable());
                        }

                        System.out.println("noseg---------");

                        for (Task t : noseg) {
                            System.out.print(t.getName());
                            System.out.print(t.getPriority());
                            System.out.print(t.getDuration());
                            System.out.println(t.getSegmentable());
                        }

                        ArrayList<Task> all = new ArrayList<>();
                        for (int i=0;i<noseg.size();i++) {
                            ArrayList<Task> temp = new ArrayList<>();
                            Task no = noseg.get(i);
                            temp.add(no);
                            for (int j=i*interval;j<interval*(i+1);j++)
                                temp.add(seg.get(j));
                            all.addAll(temp);
                        }
                        for (int j=seg.size()-noseg.size()*interval;j<seg.size();j++)
                            all.add(seg.get(j));

                        System.out.println("all---------");

                        for (Task t : all) {
                            System.out.print(t.getName());
                            System.out.print(t.getPriority());
                            System.out.print(t.getDuration());
                            System.out.println(t.getSegmentable());
                        }

                        System.out.println(all.size());

                        ArrayList<GridLayout> asdf = new ArrayList<>();

                        LinearLayout overviewLinear = (LinearLayout)findViewById(R.id.overviewLinear);
                        for (Task thisTask : all) {
                            Context context = OverviewActivity.this;

                            GridLayout gridLayout = new GridLayout(context);
                            gridLayout.setPadding(20, 20, 20, 20);

                            GridLayout.Spec colSpec = GridLayout.spec(0, GridLayout.BASELINE);
                            GridLayout.Spec titleRowSpec = GridLayout.spec(0);
                            GridLayout.Spec urgencyRowSpec = GridLayout.spec(1);
                            GridLayout.Spec timeRowSpec = GridLayout.spec(2);

                            TextView titleText = new TextView(context);
                            titleText.setTextSize(24);
                            titleText.setText(thisTask.getName());

                            TextView urgencyText = new TextView(context);
                            urgencyText.setText("Urgency: " + thisTask.getPriority());

                            TextView timeText = new TextView(context);
                            timeText.setText("Duration: " + thisTask.getDuration() + " minutes");

                            gridLayout.addView(titleText, new GridLayout.LayoutParams(titleRowSpec , colSpec));
                            gridLayout.addView(urgencyText, new GridLayout.LayoutParams(urgencyRowSpec , colSpec));
                            gridLayout.addView(timeText, new GridLayout.LayoutParams(timeRowSpec , colSpec));
z   
                            gridLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                            asdf.add(gridLayout);
                        }

                        System.out.println(asdf.size());

                        overviewLinear.setOrientation(LinearLayout.VERTICAL);

                        for (GridLayout g : asdf) {

                            // overviewLinear.addView(g);
                            overviewLinear.addView(g, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        }

                        /*int j = 0, counter = 1;
                        for (int i=1;i<seg.size();i++,counter++) // start at i = 1 (assume no slots before the first event)
                            if (counter==interval) {
                                counter =1;
                                seg.add(i + 1,noseg.get(j)); i++;
                                j++;
                            }
                           // seg.add(i + 1, nose)

                        System.out.println("---------");

                        for (Task t : seg) {
                            System.out.println(t.getName());
                            System.out.println(t.getPriority());
                            System.out.println(t.getDuration());
                            System.out.println(t.getSegmentable());
                        }*/
                    }
                }
        );
    }
}
