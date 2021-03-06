package org.ray.veader;

import org.ray.veader.R;
import org.ray.veader.util.SDCardUtil;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.ImageView;


public class NoSDCardActivity extends Activity {
    private ImageView mIcon;
    private BroadcastReceiver mRecevier;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.sd_confirm_activity);
        
        mIcon = (ImageView) findViewById(R.id.icon);

        mRecevier = new BroadcastReceiver(){
            //@override
            public void onReceive(Context content, Intent intent) {
                // check sd card
                if (SDCardUtil.hasExternalStorage()) {
                    finish();
                }
                
            }
            
        };
        
        IntentFilter intentFilter = new IntentFilter(
                Intent.ACTION_MEDIA_MOUNTED);
        intentFilter.addDataScheme("file");
       
        registerReceiver(mRecevier, new IntentFilter(intentFilter));
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRecevier);
        
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
    		Intent intent = new Intent(Intent.ACTION_MAIN);
    		intent.addCategory(Intent.CATEGORY_HOME);
    		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(intent);
    		finish();
    		return true;
    	}

        return false;
    }
    
}
