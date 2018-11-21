package comdatfrogaming.youtube.giftcardgenerator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.security.SecureRandom;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txt = (TextView) findViewById(R.id.textView);
        formatInstruct = (TextView) findViewById(R.id.formatSelect);
        codeSelect = (RadioGroup) findViewById(R.id.lenGroup);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        //AdView mAdView = (AdView) findViewById(R.id.adView);
       // AdRequest adRequest = new AdRequest.Builder().build();
       // mAdView.loadAd(adRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    SecureRandom rnd = new SecureRandom();

    //String fullString = randomString(5) + "-" + randomString(5) + "-" + randomString(5);

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        //txt.setText(sb); //attempt to push to text, but need to somehow call this in a function from a button
        return sb.toString();
    }


    private Button btnDisplay;
    public TextView txt;
    //public ProgressBar load;
    public TextView formatInstruct;
    private RadioGroup codeSelect;
    private RadioButton lenButton;



     public void generate(View view) {
         String fullCode1 = randomString(5) + "-" + randomString(5) + "-" + randomString(5);
         String fullCode2 = randomString(5) + "-" + randomString(5) + "-" + randomString(5) + "-" + randomString(5) + System.getProperty ("line.separator") + "-" + randomString(5);
         String fullCode3 = randomString(4) + "-" + randomString(6) + "-" + randomString(5);
         String fullCode4 = randomString(4) + "-" + randomString(4) + "-" + randomString(4);
         String fullCode5 = randomString(4) + "-" + randomString(4) + "-" + randomString(4) + "-" + randomString(4) + "-" + randomString(4);
         String errorCode = "Select a format, then press Generate";
         int selectId = codeSelect.getCheckedRadioButtonId();
         String radioSelect = String.valueOf(selectId);
         lenButton = (RadioButton) findViewById(selectId);

         if (selectId == 2131165269){
             txt.setText(fullCode1);
         }
         else if (selectId == 2131165270) {
             txt.setText(fullCode2);
         }
         else if (selectId == 2131165271) {
             txt.setText(fullCode3);
         }
         else if (selectId == 2131165268) {
             txt.setText(fullCode4);
         }
         else if (selectId == 2131165272) {
             txt.setText(fullCode5);
         }
         else if (selectId == -1) {
             txt.setText("Select a Format");
             formatInstruct.setText(errorCode);
         }
         //txt.setText(radioSelect);
        }
    }
