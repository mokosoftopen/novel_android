package com.example.novel;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	List<String> arrTekisuto;
	int tekisuto_idx;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		arrTekisuto = Arrays.asList(
				"ある日の暮方の事である。一人の下人げにんが、羅生門らしょうもんの下で雨やみを待っていた。",
                "広い門の下には、この男のほかに誰もいない。ただ、所々丹塗にぬりの剥はげた、大きな円柱まるばしらに、蟋蟀きりぎりすが一匹とまっている。",
                "朱雀大路すざくおおじにある以上は、この男のほかにも、雨やみをする市女笠いちめがさや揉烏帽子もみえぼしが、もう二三人はありそうなものである。それが、この男のほかには誰もいない。",
                "何故かと云うと、この二三年、京都には、地震とか辻風つじかぜとか火事とか饑饉とか云う災わざわいがつづいて起った。そこで洛中らくちゅうのさびれ方は一通りではない。",
                "旧記によると、仏像や仏具を打砕いて、その丹にがついたり、金銀の箔はくがついたりした木を、路ばたにつみ重ねて、薪たきぎの料しろに売っていたと云う事である。",
                "洛中がその始末であるから、羅生門の修理などは、元より誰も捨てて顧る者がなかった。するとその荒れ果てたのをよい事にして、狐狸こりが棲すむ。",
                "盗人ぬすびとが棲む。とうとうしまいには、引取り手のない死人を、この門へ持って来て、棄てて行くと云う習慣さえ出来た。そこで、日の目が見えなくなると、誰でも気味を悪るがって、この門の近所へは足ぶみをしない事になってしまったのである。"
				);
		tekisuto_idx = 0;
		
		TextView tv1 = (TextView)findViewById(R.id.textView1);
		tv1.setText("");
		
		BitmapGet task = new BitmapGet();
		task.execute();
		
	}
	
	class BitmapGet extends AsyncTask<Void, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(Void... params) {
			// TODO Auto-generated method stub
			URL url;
			InputStream input;
			Bitmap myBitmap = null;
			try {
				url = new URL("http://white-knight.blog.so-net.ne.jp/blog/_images/blog/_f26/white-knight/6348107.jpg");
				input = url.openStream();
				myBitmap = BitmapFactory.decodeStream(input); 
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return myBitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap bmp ){
			ImageView iv1 = (ImageView)findViewById(R.id.imageView1);
			iv1.setImageBitmap(bmp);
		}
		
	}
	
	void setGenko()
	{
		TextView tv1 = (TextView)findViewById(R.id.textView1);
		tv1.setText(arrTekisuto.get(tekisuto_idx));
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	 
		if ( event.getAction() == MotionEvent.ACTION_UP ) {
			tekisuto_idx++;
			if ( tekisuto_idx >= arrTekisuto.size() ) return true;
			setGenko();
		}
		
	    return true;
	}
	
	public void onStart()
	{
		super.onStart();
		setGenko();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
