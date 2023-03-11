package joey.goodknight.light;



import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.admob.android.ads.AdView;

public class lColor extends Activity {
    /** Called when the activity is first created. */
    //Create UI Elements
	Spinner units;
	TextView Color;
	EditText input;
	TextView colorbox;
	
	private int unitVal;
	
	
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        input = (EditText) findViewById(R.id.numInput);
        Color = (TextView) findViewById(R.id.colorbox);
        units = (Spinner) findViewById(R.id.light_units);
        colorbox = (TextView) findViewById(R.id.colorbox);
        
        input.setText("478");
        
        AdView adView = (AdView)findViewById(R.id.ad);
        adView.requestFreshAd();
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        units.setAdapter(adapter);
        units.setOnItemSelectedListener(new MyOnItemSelectedListener());
    
        input.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                  // Perform action on key press
                	updateColorBox();
                  return true;
                }
                return false;
            }
        });
        
        final Button button = (Button) findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	updateColorBox();
            }
        });
    }
	
	
	
	
	
	//Spinner Choice nested class
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	    	String mid = parent.getItemAtPosition(pos).toString();
	    	if (mid.equalsIgnoreCase("nm--nanometers")) {
	    		setUnitVal(1);
	    	} else if (mid.equalsIgnoreCase("Hz--Hertz (1/s)")) {
	    		setUnitVal(2);
	    	} else if (mid.equalsIgnoreCase("eV--electron-volts")) {
	    		setUnitVal(3);
	    	} else if (mid.equalsIgnoreCase("J--Joules")) {
	    		setUnitVal(4);
	    	} else if (mid.equalsIgnoreCase("1/cm--wavenumbers")) {
	    		setUnitVal(5);
	    	} else setUnitVal(1);
	    	updateColorBox();
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	public void updateColorBox() {
		try {
			float x = Float.parseFloat(input.getText().toString());
			light test = new light(x, getUnitVal());
			Color.setBackgroundColor(android.graphics.Color.rgb(test.redValue(), test.greenValue(), test.blueValue()));
			colorbox.setText(test.spectrumSection() + "\n" +
					test.getParameter(1) + " nm\n" +
					test.getParameter(2) + " Hz\n" +
					test.getParameter(3) + " eV\n" +
					test.getParameter(4) + " J\n" +
					test.getParameter(5) + " 1/cm");
			
			
		} catch (NumberFormatException crap) {
			new AlertDialog.Builder(lColor.this)
		      .setMessage("Please enter a valid, positive number.")
		      .setPositiveButton("OK", null)
		      .show();
		}
		
	}
	public void setUnitVal(int i) {
		unitVal = i;
	}
	public int getUnitVal() {
		return unitVal;
	}
}