package com.kunion.autotest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/7/5.
 */

public class ListViewActivity2 extends AppCompatActivity {

    private ListView lt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        List<SearchItem> listems = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            SearchItem item = new SearchItem();
            item.setKeyword(String.format(Locale.getDefault(), "item %d", i));
            listems.add(item);
        }

        MyAdapter adapter = new MyAdapter(this, listems);

        lt1=(ListView)findViewById(R.id.list);
        lt1.setAdapter(adapter);

    }

    public class ViewHolder {
        TextView mText;
    }

    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private List<SearchItem> mItems;
        public MyAdapter(Context context, List<SearchItem> items) {
            this.mInflater = LayoutInflater.from(context);
            mItems = items;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mItems.size();
        }

        @Override
        public SearchItem getItem(int arg0) {
            // TODO Auto-generated method stub
            return mItems.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 显示优化（只要之前显示过的就可以不再再次从布局文件读取，直接从缓存中读取——ViewHolder的作用）
            // 其实是setTag和getTag中Tag的作用
            ViewHolder holder = null;
            if (convertView == null) {// 如果是第一次显示该页面(要记得保存到viewholder中供下次直接从缓存中调用)

                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.adapter_simple2, null);
                // 以下为保存这一屏的内容，供下次回到这一屏的时候直接refresh，而不用重读布局文件
                holder.mText = (TextView) convertView.findViewById(R.id.simple2_text);
                convertView.setTag(holder);

            } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏

                holder = (ViewHolder) convertView.getTag();
            }

            SearchItem item = mItems.get(position);
            holder.mText.setText(item.getKeyword());

            return convertView;
        }
    }
}