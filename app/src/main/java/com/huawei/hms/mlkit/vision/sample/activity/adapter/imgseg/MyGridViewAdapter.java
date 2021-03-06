/**
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.huawei.hms.mlkit.vision.sample.activity.adapter.imgseg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huawei.hms.mlkit.vision.sample.R;
import com.huawei.hms.mlkit.vision.sample.activity.entity.Entity;
import com.huawei.hms.mlsdk.common.internal.client.SmartLog;

import java.util.ArrayList;


public class MyGridViewAdapter extends BaseAdapter {
    private final static String TAG = MyGridViewAdapter.class.getName();
    private Context mContext;
    private ArrayList<Entity> mDataList;



    public MyGridViewAdapter(ArrayList<Entity> dataList, Context mContext) {
        super();
        this.mDataList = dataList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return this.mDataList.size();
    }


    @Override
    public Object getItem(int position) {
        return this.mDataList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        try {
            if(convertView == null){
                convertView = LayoutInflater.from(this.mContext).inflate(R.layout.imageseg_gridview, null);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.image);
                holder.selectIcon = convertView.findViewById(R.id.img_select);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            if(this.mDataList.get(position).isSelected()) {
                holder.selectIcon.setBackgroundResource(R.mipmap.seg_selected);
            } else {
                holder.selectIcon.setBackground(null);
            }

            int resourceId = this.mDataList.get(position).getResourceId();
                    Glide.with(this.mContext)
            .load(resourceId)
            .error(R.drawable.icon_logo)
            .into(holder.imageView);
        } catch (Exception e) {
            SmartLog.e("MyGridViewAdapter", e.getMessage());
        }
        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        ImageView selectIcon;
    }
}
