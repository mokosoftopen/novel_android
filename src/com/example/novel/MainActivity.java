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
				"������̕���̎��ł���B��l�̉��l���ɂ񂪁A������炵�傤����̉��ŉJ��݂�҂��Ă����B",
                "�L����̉��ɂ́A���̒j�̂ق��ɒN�����Ȃ��B�����A���X�O�h�ɂʂ�̔��͂����A�傫�ȉ~���܂�΂���ɁA�婂��肬�肷����C�Ƃ܂��Ă���B",
                "�鐝��H�������������ɂ���ȏ�́A���̒j�̂ق��ɂ��A�J��݂�����s���}�����߂����❆�G�X�q���݂��ڂ����A������O�l�͂��肻���Ȃ��̂ł���B���ꂪ�A���̒j�̂ق��ɂ͒N�����Ȃ��B",
                "���̂��Ɖ]���ƁA���̓�O�N�A���s�ɂ́A�n�k�Ƃ��ҕ��������Ƃ��Ύ��Ƃ��_�[�Ƃ��]���Ђ킴�킢���Â��ċN�����B�����ŗ����炭���イ�̂��т���͈�ʂ�ł͂Ȃ��B",
                "���L�ɂ��ƁA�����╧���ōӂ��āA���̒O�ɂ�������A����̔��͂��������肵���؂��A�H�΂��ɂݏd�˂āA�d�������̗�����ɔ����Ă����Ɖ]�����ł���B",
                "���������̎n���ł��邩��A������̏C���Ȃǂ́A�����N���̂ĂČڂ�҂��Ȃ������B����Ƃ��̍r��ʂĂ��̂��悢���ɂ��āA�ϒK���肪�����ށB",
                "���l�ʂ��тƂ����ށB�Ƃ��Ƃ����܂��ɂ́A������̂Ȃ����l���A���̖�֎����ė��āA���Ăčs���Ɖ]���K�������o�����B�����ŁA���̖ڂ������Ȃ��Ȃ�ƁA�N�ł��C�������邪���āA���̖�̋ߏ��ւ͑��Ԃ݂����Ȃ����ɂȂ��Ă��܂����̂ł���B"
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
