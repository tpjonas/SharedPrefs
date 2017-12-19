package cimdata.android.dez2017.sharedprefs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

public class ColorActivity extends AppCompatActivity {

    public static final String PREF_INT_COLOR = "pref.int.color";
    ViewGroup layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_color);

        layout = findViewById(R.id.ly_accolor_root);

        // Das Extra extrahieren
        Intent intent = getIntent();

        String color = "#" + intent.getStringExtra(MainActivity.EXTRA_STRING_COLOR);

        // Defaultfarbe, die angezeigt wird, wenn das parsen schiefgeht
        // und wenn keine andere Farbe angezeigt wird
        int colorVal = Color.CYAN;

        try {

            // Hier verwandeln wir den ColorString in einen ColorWert.
            colorVal = Color.parseColor(color);

            // Die erfolgreich geparste
            saveParsedColor(colorVal);

        } catch (Exception e) {

            // Wenn es einen Fehler beim Parsen gab, eine Meldung ausgeben
            Toast.makeText(this, "Unbekannte Farbe!", Toast.LENGTH_LONG).show();

            // und den Farbwert aus der SharedPref auslesen, mit Fallback zur Default-Farbe
            colorVal = readColorPref(colorVal);


        }

        // Den Extrawert einfügen
        layout.setBackgroundColor(colorVal);

    }

    private int readColorPref(int fallbackVal) {

        // Zuerst holen wir uns eine Instanz der Shared Prefs
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE); // Innerhalb einer Activity cann man den Context-Aufruf weglassen

        // Dann versuchen wir den Wert mit Hilfe des Schlüssels auszulesen.
        // Für den Fall, dass das nicht klappt übergen wir einen Fallback-Wert.
        int color = prefs.getInt(PREF_INT_COLOR, fallbackVal);

        // Zum Schluss geben wir f0f0f0gelesene oder den Fallback Wert zurück.
        return color;

    }

    private void saveParsedColor(int color) {

        // Zuerst holen wir uns eine Instanz der shared preferences
        //
        // Zwei Möglichkeiteh
        // 1. Eine eigene Prefs-Datei anlegen (get SharedPreferences(name, mode))
        // 2. Die default Prefs-Datei verwenden (getPreferences(mode))
        //
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);

        // Danach holen wir uns den Editor aus den SharedPrefs, in den wir unsere Werte schreiben.
        // Diesen benötigen wir nur zum schreiben nicht zum lesen.
        SharedPreferences.Editor editor = prefs.edit();

        // Nun können wir die Werte als Key-Value Paare schreiben.
        editor.putInt(PREF_INT_COLOR, color);

        // Zum Schluss müssen wir die geschriebenen Daten noch committen.
        // Dann sind sie erst in den prefs gespeichert
        editor.commit();

        /*
         * MethodChaining
         *
         * Als "Kurzschreibweise" können wir das Schreiben der Prefs auch hintereinander hängen.
         */
        /*
        getPreferences(MODE_PRIVATE)
                .edit()
                .putInt(PREF_INT_COLOR, color)
                .commit();
        */

    }
}
