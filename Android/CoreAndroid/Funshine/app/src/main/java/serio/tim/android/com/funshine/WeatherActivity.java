package serio.tim.android.com.funshine;

import android.*;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import serio.tim.android.com.funshine.Model.DailyWeatherReport;

public class WeatherActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    final String URL_BASE = "http://api.openweathermap.org/data/2.5/forecast";
    final String URL_COORD = "/?lat=";//9.9687&lon=76.299";
    final String URL_UNITS = "&units=imperial";
    final String URL_API_KEY = "&APPID=7a7116e2dcf391e9756a0437afef72f2";

    private GoogleApiClient mGoogleApiClient;
    private final int PERMISSION_LOCATION = 111;
    private ArrayList<DailyWeatherReport> weatherReportList = new ArrayList<>();

    private ImageView weatherIcon;
    private ImageView weatherIconMini;
    private TextView weatherDate;
    private TextView currentTemp;
    private TextView lowTemp;
    private TextView cityCountry;
    private TextView weatherDescription;

    WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIcon = (ImageView)findViewById(R.id.weatherIcon);
        weatherIconMini = (ImageView)findViewById(R.id.weatherIconMini);
        weatherDate = (TextView)findViewById(R.id.weatherDate);
        currentTemp = (TextView)findViewById(R.id.currentTemp);
        lowTemp = (TextView)findViewById(R.id.lowTemp);
        cityCountry = (TextView)findViewById(R.id.cityCountry);
        weatherDescription = (TextView)findViewById(R.id.weatherDescription);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.content_weather_reports);
        mAdapter = new WeatherAdapter(weatherReportList);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).enableAutoManage(this, this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        downloadWeatherData(null);
    }

    public void downloadWeatherData(Location location) {
        final String fullCoords = URL_COORD + location.getLatitude() + "&lon=" + location.getLongitude();
        final String url = URL_BASE + fullCoords + URL_UNITS + URL_API_KEY;
        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject city = response.getJSONObject("city");
                    String cityName = city.getString("name");
                    String country = city.getString("country");

                    JSONArray list = response.getJSONArray("list");

                    for(int x = 0; x < 5; x++) {
                        JSONObject obj = list.getJSONObject(x);
                        JSONObject main = obj.getJSONObject("main");
                        Double currentTemp = main.getDouble("temp");
                        Double maxTemp = main.getDouble("temp_max");
                        Double minTemp = main.getDouble("temp_min");

                        JSONArray weatherArr = obj.getJSONArray("weather");
                        JSONObject weather = weatherArr.getJSONObject(0);
                        String weatherType = weather.getString("main");

                        String rawDate = obj.getString("dt_txt");

                        DailyWeatherReport report = new DailyWeatherReport(cityName, country, currentTemp.intValue(), maxTemp.intValue(), minTemp.intValue(), weatherType, rawDate);
                        Log.v("JSON", "Printing from class: " + report.getWeather());
                        weatherReportList.add(report);
                    }
                } catch (JSONException e) {
                    Log.v("JSON", "EXC: " + e.getLocalizedMessage());
                }
                updateUI();
                mAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("FUN", error.getLocalizedMessage());
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }

    /*private ImageView weatherIcon;
    private ImageView weatherIconMini;
    private TextView weatherDate;
    private TextView currentTemp;
    private TextView lowTemp;
    private TextView cityCountry;
    private TextView weatherDescription;*/

    public void updateUI() {
        if(weatherReportList.size() > 0) {
            DailyWeatherReport report = weatherReportList.get(0);

            switch (report.getWeather()) {
                case DailyWeatherReport.WEATHER_TYPE_CLOUDS:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                    break;
                case DailyWeatherReport.WEATHER_TYPE_RAIN:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.rainy));
                    break;
                case DailyWeatherReport.WEATHER_TYPE_SNOW:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                    break;
                default:
                    weatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
                    weatherIconMini.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
            }
            weatherDate.setText("Today, May 1");
            currentTemp.setText(Integer.toString(report.getCurrentTemp()));
            lowTemp.setText(Integer.toString(report.getMinTemp()));
            cityCountry.setText(report.getCityName() + ", " + report.getCountry());
            weatherDescription.setText(report.getWeather());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        downloadWeatherData(location);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);
        } else {
            startLocationServices();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public void startLocationServices() {
        try {
            LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);
        } catch (SecurityException exception) {

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case PERMISSION_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationServices();
                } else {
                    //Show a dialog saying you cant find the users location
                    Toast.makeText(this, "I can't find your location - you're denied permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public class WeatherAdapter extends RecyclerView.Adapter<WeatherReportViewHolder> {
        private ArrayList<DailyWeatherReport> mDailyWeatherReports;

        public WeatherAdapter(ArrayList<DailyWeatherReport> mDailyWeatherReports) {
            this.mDailyWeatherReports = mDailyWeatherReports;
        }

        @Override
        public void onBindViewHolder(WeatherReportViewHolder holder, int position) {
            DailyWeatherReport report = mDailyWeatherReports.get(position);
            holder.updateUI(report);
        }

        @Override
        public int getItemCount() {
            return mDailyWeatherReports.size();
        }

        @Override
        public WeatherReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weather, parent, false);
            return new WeatherReportViewHolder(card);
        }
    }

    public class WeatherReportViewHolder extends RecyclerView.ViewHolder {
        private ImageView lweatherIcon;
        private TextView lweatherDate;
        private TextView lweatherDescription;
        private TextView ltempHigh;
        private TextView ltempLow;

        public WeatherReportViewHolder(View itemView) {
            super(itemView);

            lweatherIcon = (ImageView)itemView.findViewById(R.id.list_weather_icon);
            lweatherDate = (TextView)itemView.findViewById(R.id.list_weather_day);
            lweatherDescription = (TextView)itemView.findViewById(R.id.list_weather_description);
            ltempHigh = (TextView)itemView.findViewById(R.id.list_weather_temp_high);
            ltempLow = (TextView)itemView.findViewById(R.id.list_weather_temp_low);
        }

        public void updateUI(DailyWeatherReport report) {

            //Write getFormattedDate
            lweatherDate.setText(report.getFormattedDate());
            lweatherDescription.setText(report.getWeather());
            ltempHigh.setText(Integer.toString(report.getMaxTemp()));
            ltempLow.setText(Integer.toString(report.getMinTemp()));

            switch (report.getWeather()) {
                case DailyWeatherReport.WEATHER_TYPE_CLOUDS:
                    lweatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.cloudy_mini));
                    break;
                case DailyWeatherReport.WEATHER_TYPE_RAIN:
                    lweatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.rainy_mini));
                    break;
                case DailyWeatherReport.WEATHER_TYPE_SNOW:
                    lweatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.snow_mini));
                    break;
                default:
                    lweatherIcon.setImageDrawable(getResources().getDrawable(R.drawable.sunny_mini));
            }
        }
    }
}
