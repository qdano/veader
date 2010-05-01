package com.android.ray.veader;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class EditBookDialog extends Dialog{

    public EditBookDialog(Context context) {
        super(context);
    }
    
    //@override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.brightness);
        setCancelable(false);
        setCanceledOnTouchOutside(true);
        


        
        SeekBar seek = (SeekBar)findViewById(R.id.brightness_seek);
        int ib = Settings.System.getInt(getContext().getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS,100); 
        
        seek.setProgress(ib);
        seek.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

            //@override
            public void onProgressChanged(SeekBar seekbar, int i, boolean flag) {
                
                WindowManager.LayoutParams lp = getOwnerActivity().getWindow().getAttributes();
                lp.screenBrightness = Math.max(5,i)/255f;
                getOwnerActivity().getWindow().setAttributes(lp);
            }

            //@override
            public void onStartTrackingTouch(SeekBar seekbar) {
              
            }

            //@override
            public void onStopTrackingTouch(SeekBar seekbar) {
                
            }
            
        });
    }
    

    


}
