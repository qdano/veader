package org.ray.veader;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TateMoji extends Activity {
        class MySurfaceView extends SurfaceView implements
SurfaceHolder.Callback {
                public MySurfaceView(Context context) {
                        super(context);
                        getHolder().addCallback(this);
                }

                void doDraw() {
                        Canvas canvas = getHolder().lockCanvas();
                        canvas.save(); // ★ここで状態を保存
                        canvas.rotate(90.0f);
                        Paint paint = new Paint();
                        canvas.drawColor(Color.WHITE);
                        paint.setColor(Color.BLUE);
                        paint.setAntiAlias(true);
                        paint.setTextSize(24);
                      
                        canvas.drawText("中文", paint.getTextSize(), 0,
paint);
                        canvas.restore(); // ★ここで状態を復元
                        getHolder().unlockCanvasAndPost(canvas);
                }

                public void surfaceChanged(SurfaceHolder holder, int format, int
width, int height) {
                        Log.d("TEST", "surfaceChanged");
                }

                public void surfaceCreated(SurfaceHolder holder) {
                        Log.d("TEST", "surfaceCreated");
                        doDraw();
                }

                public void surfaceDestroyed(SurfaceHolder holder) {
                        Log.d("TEST", "surfaceDestroyed");
                }

        }

        protected void onCreate(final Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(new MySurfaceView(this));
        }

} 