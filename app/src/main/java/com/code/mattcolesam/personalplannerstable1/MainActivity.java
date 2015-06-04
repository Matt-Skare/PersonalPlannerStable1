package com.code.mattcolesam.personalplannerstable1;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    TextView currentTimeValue, totalTimeValue;
    int time, totalTime;
    SeekBar bar;
    EditText taskInputValue;
    Button myButton;
    String x;
    List<codeTemTask> codeTemTasksList = new ArrayList<codeTemTask>();

    public class codeTemTask {
        String taskName;
        String taskTime;
    }
    CodeTempAdapter taskListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        currentTimeValue = (TextView) findViewById(R.id.textView);
        totalTimeValue = (TextView) findViewById(R.id.textView2);
        bar = (SeekBar) findViewById(R.id.seekBar);
        taskInputValue = (EditText) findViewById(R.id.editText);
        myButton = (Button) findViewById(R.id.button);

        bar.setOnSeekBarChangeListener(this);
        myButton.setOnClickListener(mButtonListener);


        taskListAdapter = new CodeTempAdapter();

        ListView codeTempLists = (ListView)findViewById(R.id.listView1);
        codeTempLists.setAdapter(taskListAdapter);

        codeTempLists.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

                codeTemTask task = taskListAdapter.getCodeTempTask(arg2);

                //Toast.makeText(MainActivity.this, task.taskName, Toast.LENGTH_LONG).show();

            }
        });
    }

    View.OnClickListener mButtonListener = new View.OnClickListener() {

        public void onClick(View v) {
            x = taskInputValue.getText().toString();
            onAddTask(time);

            codeTemTask task = new codeTemTask();
            task.taskName = x;
            task.taskTime = "" + time;
            codeTemTasksList.add(task);

            getDataForListView();
        }
    };

    public void onAddTask(int taskTime) {
        totalTime += taskTime;
        totalTimeValue.setText("Total time: " + totalTime);
    }

    public class CodeTempAdapter extends BaseAdapter {

        List<codeTemTask> codeTemTaskList = getDataForListView();
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return codeTemTaskList.size();
        }

        @Override
        public codeTemTask getItem(int arg0) {
            // TODO Auto-generated method stub
            return codeTemTaskList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {

            if(arg1==null)
            {
                LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                arg1 = inflater.inflate(R.layout.listitem, arg2,false);
            }

            TextView taskName = (TextView)arg1.findViewById(R.id.textView1);
            TextView taskDesc = (TextView)arg1.findViewById(R.id.textView2);

            codeTemTask task = codeTemTaskList.get(arg0);

            taskName.setText(task.taskName);
            taskDesc.setText(task.taskTime);

            return arg1;
        }

        public codeTemTask getCodeTempTask(int position)
        {
            return codeTemTaskList.get(position);
        }

    }

    public List<codeTemTask> getDataForListView()
    {



        return codeTemTasksList;

    }

    // for seek bar
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // mTextView1.setText("");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // mTextView1.setText("");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        currentTimeValue.setText("" + progress);
        time = progress;
    }
}
