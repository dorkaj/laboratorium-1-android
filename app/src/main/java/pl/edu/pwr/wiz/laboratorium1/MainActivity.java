package pl.edu.pwr.wiz.laboratorium1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    private final String TEXT_COLOR = "textColor";
    private static final int CHANGE_SETTINGS = 0;
    private static final String TAG = "MainActivity";
    TextView welcome;
    CoordinatorLayout coordinatorLayout;
    int backgroundColor, textColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);

        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

// @TODO wyświetlać Snackbar
        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "more info", Snackbar.LENGTH_LONG).show();

            }
        });

        // @TODO wyswietlac i ukrywac obrazek

        final ImageView image1 = (ImageView) this.findViewById(R.id.image1);
        Button button1 = (Button) this.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.i(TAG, "Click on button1");
                if (image1.getVisibility() == View.VISIBLE) {
                    image1.setVisibility(View.INVISIBLE);
                }else{
                    image1.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
             /* Pobierz dane o aktualnych kolorach do nowej aktywnosci */
             welcome = (TextView) findViewById(R.id.welcome);
            int textColor = welcome.getCurrentTextColor();

            ColorDrawable cd = (ColorDrawable) coordinatorLayout.getBackground();

            if(cd != null) {
                backgroundColor = cd.getColor();
            } else {
                backgroundColor = WHITE;
            }

            // @TODO otworz aktywnosc z ustawieniami i przeslij do niej aktualne kolory - hint uzyj funkcji startActivityForResult


            Intent data = new Intent ("pl.edu.pwr.wiz.laboratorium1.SettingsActivity");
            data.putExtra(TEXT_COLOR, textColor);
            data.putExtra("backgrounColor", backgroundColor);
            startActivityForResult(data, CHANGE_SETTINGS);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == SettingsActivity.CHANGE_SETTINGS) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {
                int textColor = data.getIntExtra(TEXT_COLOR, Color.BLACK);
                int backgraundColor = data.getIntExtra("backgroundColor", Color.WHITE);
                // @TODO Pobierz dane powrotne z Intentu 'data'

                // @TODO Zmien kolor tekstu w TextView o id welcome

                welcome.setTextColor(textColor);
                coordinatorLayout.setBackgroundColor(backgraundColor);

                // Wyswietlamy info, ze dane zostaly zapisane
                String txt = (String) data.getStringExtra("txt");
                Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
