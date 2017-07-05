package com.kunion.autotest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ListViewActivity extends AppCompatActivity{

    private ListView lt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        List<Map<String, String>> listems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String, String> listem = new HashMap<>();
            listem.put("STR", String.format(Locale.getDefault(), "item %d", i));
            listem.put("LEN", String.valueOf(i));
            listems.add(listem);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, listems,
                R.layout.adapter_simple, new String[] { "STR", "LEN"},
                new int[] {R.id.simple_item,R.id.simple_len});

        lt1=(ListView)findViewById(R.id.list);
        lt1.setAdapter(adapter);

    }
}
