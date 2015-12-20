package com.community.tsinghua;

import android.content.Context;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by LG on 2015-12-03.
 */
public class PushReceiver extends PushMessageReceiver {
    public static final String TAG = PushReceiver.class.getSimpleName();
    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {
        String responseString = "onBind errorCode=" + i + "appid=" + s + "userId=" + s1 + "channelId" + s2 + "requesId" + s3;
        Log.d(TAG, responseString);

    }

    @Override
    public void onUnbind(Context context, int i, String s) {
        String responseString = "onUnbind errorCode=" + i + "requestId=" + s;
        Log.d(TAG, responseString);

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

        String responseString = "onSetTags errorCode=" + i + "SuccessTags=" + list + "failTags=" + list1 +"requesId" + s;
        Log.d(TAG, responseString);

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
        String responseString = "onDelTags errorCode=" + i + "SuccessTags=" + list + "failTags=" + list1 +"requesId" + s;
        Log.d(TAG, responseString);

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

        String responseString = "onListTags errorCode=" + i + "tags=" + list;
        Log.d(TAG, responseString);

    }

    @Override
    public void onMessage(Context context, String s, String s1) {
        String messageString = "透传消息 message=" + s + "customContentString=" + s1;
        Log.d(TAG, messageString);

        if(s1!=null & s1!="")
        {
            JSONObject customJson = null;
            try{
                customJson = new JSONObject(s1);
                String myvalue = null;
                if(!customJson.isNull("mykey")){
                    myvalue = customJson.getString("mykey");
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        String notifyString = "通知点击 title=" + s + "description=" + s1 + "customContent=" + s2;
        Log.d(TAG, notifyString);

        if(s1!=null & s1!="")
        {
            JSONObject customJson = null;
            try{
                customJson = new JSONObject(s1);
                String myvalue = null;
                if(!customJson.isNull("mykey")){
                    myvalue = customJson.getString("mykey");
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
        String notifyString = "通知到达 title=" + s + "description=" + s1 + "customContent=" + s2;
        Log.d(TAG, notifyString);

        if(s1!=null & s1!="")
        {
            JSONObject customJson = null;
            try{
                customJson = new JSONObject(s1);
                String myvalue = null;
                if(!customJson.isNull("mykey")){
                    myvalue = customJson.getString("mykey");
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }
}
