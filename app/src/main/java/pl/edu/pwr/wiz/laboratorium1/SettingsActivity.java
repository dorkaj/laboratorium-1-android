package pl.edu.pwr.wiz.laboratorium1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import yuku.ambilwarna.AmbilWarnaDialog;

public class SettingsActivity extends AppCompatActivity {
    public static final int CHANGE_SETTINGS = 0;
    private int textColor, backgroundColor;
    private final String TEXT_COLOR = "textColor";
    private  final  String BACKGROUND_COLOR = "backgroundColor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TypedValue typedValue = new TypedValue();

        // @TODO Pobierz aktualne kolory */

        Intent data = getIntent();
        textColor = data.getIntExtra(TEXT_COLOR, Color.BLACK);
        backgroundColor = data.getIntExtra(BACKGROUND_COLOR,Color.WHITE);

    }

    public void onSave(View view) {
        Intent result = new Intent();

        // @TODO Przekaż dane powrotne z nowymi kolorami

        result.putExtra(TEXT_COLOR, textColor);
        result.putExtra(BACKGROUND_COLOR, backgroundColor);
        result.putExtra("txt", "Ustawienia zapisane");
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    public void onTextColor(View view) {
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, textColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                // color is the color selected by the user.
                textColor = color;
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
            }
        });

        dialog.show();
    }

    public void onBackgroundColor(View view) {
        // @TODO Wstaw okno dialogowe do wyboru koloru tla analogicznie do tego uzytego przy wyborze koloru tekstu

        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, backgroundColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                backgroundColor = color;
            }
        });
        dialog.show();
    }
}
