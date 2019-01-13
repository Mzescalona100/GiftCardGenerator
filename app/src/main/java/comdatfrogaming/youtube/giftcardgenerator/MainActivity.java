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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private static final int RB1_ID = 1000;//first radio button id
    private static final int RB2_ID = 1001;//first radio button id
    private static final int RB3_ID = 1002;//first radio button id
    private static final int RB4_ID = 1003;//first radio button id
    private static final int RB5_ID = 1004;//first radio button id
    int timesPressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        timesPressed = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txt = (TextView) findViewById(R.id.textView);
        formatInstruct = (TextView) findViewById(R.id.formatSelect);

        codeSelect = (RadioGroup) findViewById(R.id.lenGroup);
        RadioButton radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        RadioButton radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        RadioButton radioButton4 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton radioButton5 = (RadioButton) findViewById(R.id.radioButton4);

        radioButton1.setId(RB1_ID);
        radioButton2.setId(RB2_ID);
        radioButton3.setId(RB3_ID);
        radioButton4.setId(RB4_ID);
        radioButton5.setId(RB5_ID);

        MobileAds.initialize(this, "ca-app-pub-9282720184335688~5696695852");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9282720184335688/9523482132");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                //mInterstitialAd.show();
            }

            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });


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

    String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
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
        timesPressed += 1;
        if (timesPressed > 15) {
            timesPressed = 0;
            mInterstitialAd.show();
        }
            String fullCode1 = randomString(5) + "-" + randomString(5) + "-" + randomString(5);
            String fullCode2 = randomString(5) + "-" + randomString(5) + "-" + randomString(5) + "-" + randomString(5) + System.getProperty("line.separator") + "-" + randomString(5);
            String fullCode3 = randomString(4) + "-" + randomString(6) + "-" + randomString(5);
            String fullCode4 = randomString(4) + "-" + randomString(4) + "-" + randomString(4);
            String fullCode5 = randomString(4) + "-" + randomString(4) + "-" + randomString(4) + "-" + randomString(4) + "-" + randomString(4);
            String errorCode = "Select a format, then press Generate";
            int selectId = codeSelect.getCheckedRadioButtonId();
            String radioSelect = String.valueOf(selectId);
            lenButton = (RadioButton) findViewById(selectId);

            if (selectId == RB1_ID) {
                txt.setText(fullCode1);
            } else if (selectId == RB2_ID) {
                txt.setText(fullCode2);
            } else if (selectId == RB3_ID) {
                txt.setText(fullCode3);
            } else if (selectId == RB4_ID) {
                txt.setText(fullCode4);
            } else if (selectId == RB5_ID) {
                txt.setText(fullCode5);
            } else if (selectId == -1) {
                txt.setText("Select a Format");
                formatInstruct.setText(errorCode);
            }
            //txt.setText(radioSelect);
        }
    }
