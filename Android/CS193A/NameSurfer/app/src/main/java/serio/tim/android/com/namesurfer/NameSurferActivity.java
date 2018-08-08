package serio.tim.android.com.namesurfer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.data.SimpleDatabase;

public class NameSurferActivity extends SimpleActivity {

    // range of years to show in the graph
    private static final int START_YEAR = 1880;
    private static final int END_YEAR = 2010;

    // range of ranks to show; MIN_RANK is 1; 0 means "not ranked"
    private static final int MAX_RANK = 1000;

    /* Runs when activity is created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_surfer);
        handleEnterKeyPress(findEditText(R.id.name));

        SimpleDatabase.with(this)
                .executeSqlFile("babynames");

        GraphView graph = $(R.id.graph);
        graph.setTitle("baby name popularity");
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinX(START_YEAR);
        graph.getViewport().setMaxX(END_YEAR);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(MAX_RANK);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
    }

    /* Called when the user presses Enter on the text box. Runs a query. */
    @Override
    public void onEnterKeyPress(View editText) {
        doQuery();
    }

    /* Called when the user clicks the Search button. Runs a query. */
    public void searchClick(View view) {
        doQuery();
    }

    /*
     * This method performs a query on the babynames database
     * for the given name/sex and prints the results.
     */
    private void doQuery() {
        String name = $ET(R.id.name).getText().toString();
        String sex = $SW(R.id.sex).isChecked() ? "F" : "M";

        // do the query on the database
        SQLiteDatabase db = openOrCreateDatabase(
                "babynames", MODE_PRIVATE, null);
        Cursor cr = db.rawQuery(
                "SELECT year, rank FROM ranks WHERE name = '" + name
                        + "' AND sex = '" + sex + "' ORDER BY year", null);
        if (cr.moveToFirst()) {
            // store the ranks as lines between points of a series
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();

            do {
                // read next rank and add to series
                int year = cr.getInt(cr.getColumnIndex("year"));   // 1880
                int rank = cr.getInt(cr.getColumnIndex("rank"));   // 133
                series.appendData(new DataPoint(year, MAX_RANK - rank), false, 1000);
                log("year=" + year + ", rank=" + rank);
            } while (cr.moveToNext());
            cr.close();

            // add series to screen
            GraphView graph = $(R.id.graph);
            graph.addSeries(series);
        }
    }
}
