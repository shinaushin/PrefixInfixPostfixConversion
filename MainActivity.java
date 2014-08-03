package com.AustinJShin.conversion;

import com.AustinJShin.conversion.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static String extraMessage = "";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();

		TabSpec spec1=tabHost.newTabSpec("Welcome!");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Welcome!");
		
		TabSpec spec2=tabHost.newTabSpec("Convert!");
		spec2.setIndicator("Convert!");
		spec2.setContent(R.id.tab2);

		TabSpec spec3=tabHost.newTabSpec("Additional Practice");
		spec3.setIndicator("Additional Practice");
		spec3.setContent(R.id.tab3);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	String end = "";
    	if (message.trim().length() > 0)
    	{
    		Convert obj = new Convert();
    		end = obj.convert(message);
    	} else { end = "Please enter in an equation."; }
    	TextView text = (TextView) findViewById(R.id.textView);
    	text.setText(end);
    	editText.setText("");
    }
    
    public void onClick2(View view)
    {
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	String end = "";
    	if (message.trim().length() > 0)
    	{
    		Convert obj = new Convert();
    		end = obj.calculate(message);
    	} else { end = "Please enter in an equation."; }
    	TextView text = (TextView) findViewById(R.id.textView);
    	text.setText(end);
    	editText.setText("");
    }
    
    public void onClick3 (View view)
    {
    	TextView text = (TextView) findViewById(R.id.awesome);
    	text.setText("((1*2)+(2+3))-((5-6)+(7/8))");
    }
    
    public void onClick4 (View view)
    {
    	TextView text = (TextView) findViewById(R.id.awesome);
    	text.setText("(X+Y)*(A-B)");
    }
    
    public void onClick5 (View view)
    {
    	TextView text = (TextView) findViewById(R.id.awesome);
    	text.setText("(1+(2/(3*(4+(5-6)))))+(9-8)");
    }
    
    public void onClick6 (View view)
    {
    	TextView text = (TextView) findViewById(R.id.awesome);
    	text.setText("(A+B)/(C-(A/B))");
    }
}
