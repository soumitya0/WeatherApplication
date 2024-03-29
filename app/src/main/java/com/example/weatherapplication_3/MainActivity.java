package com.example.weatherapplication_3;


import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Long.getLong;

public class MainActivity extends AppCompatActivity {

    TextView tv_cityName,tv_temp,tv_description,tv_CurrentDate,tv_weak_day1,tv_smalldec,tv_presser,tv_airHumidity,tv_wind;
    TextView tv_weak_day2,tv_temp_day2;
    TextView tv_weak_day3,tv_temp_day3;
    TextView tv_weak_day4,tv_temp_day4;
    TextView tv_weak_day5,tv_temp_day5;
    TextView tv_weak_day6,tv_temp_day6;
    TextView tv_weak_day7,tv_temp_day7;


    EditText et_search_city;

    ImageView img_dec1,img_day2,img_day3,img_day4,img_day5,img_day6,img_day7;
    Button btn_search;
    RelativeLayout relativeLayout;
    // http://api.openweathermap.org/data/2.5/forecast/daily?q="+addres+"&units=metric&cnt=7&lang=en&APPID=0e948b3ecd877b785634d8ebdd15621e

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_temp_day7=(TextView)findViewById(R.id.tv_temp_day7);
        tv_weak_day7=(TextView)findViewById(R.id.tv_weak_day7);

        tv_temp_day6=(TextView)findViewById(R.id.tv_temp_day6);
        tv_weak_day6=(TextView)findViewById(R.id.tv_weak_day6);

        tv_temp_day5=(TextView)findViewById(R.id.tv_temp_day5);
        tv_weak_day5=(TextView)findViewById(R.id.tv_weak_day5);

        tv_temp_day4=(TextView)findViewById(R.id.tv_temp_day4);
        tv_weak_day4=(TextView)findViewById(R.id.tv_weak_day4);

        tv_temp_day3=(TextView)findViewById(R.id.tv_temp_day3);
        tv_weak_day3=(TextView)findViewById(R.id.tv_weak_day3);

        tv_temp_day2=(TextView)findViewById(R.id.tv_temp_day2);
        tv_weak_day2=(TextView)findViewById(R.id.tv_weak_day2);

        tv_wind=(TextView)findViewById(R.id.tv_wind);
        tv_airHumidity=(TextView)findViewById(R.id.tv_airHumidity);
        tv_presser=(TextView)findViewById(R.id.tv_presser);
        tv_cityName=(TextView)findViewById(R.id.tv_cityName);
        tv_CurrentDate=(TextView)findViewById(R.id.tv_CurrentDate);
        tv_description=(TextView)findViewById(R.id.tv_description);
        tv_temp=(TextView)findViewById(R.id.tv_temp);
        tv_weak_day1=(TextView)findViewById(R.id.tv_weak_day1);
        tv_smalldec=(TextView)findViewById(R.id.tv_smalldec);
        et_search_city=(EditText)findViewById(R.id.et_search_city);

        img_dec1=(ImageView)findViewById(R.id.img_dec1);
        img_day2=(ImageView)findViewById(R.id.img_day2);
        img_day3=(ImageView)findViewById(R.id.img_day3);
        img_day4=(ImageView)findViewById(R.id.img_day4);
        img_day5=(ImageView)findViewById(R.id.img_day5);
        img_day6=(ImageView)findViewById(R.id.img_day6);
        img_day7=(ImageView)findViewById(R.id.img_day7);
        relativeLayout = (RelativeLayout) findViewById(R.id.mainLayout);
    }

    //btn onclick method find weather
    public void findWeather(View view)
    {


        AsynTaskDownload asynTaskDownload= new AsynTaskDownload();
        asynTaskDownload.execute();

    }




    class AsynTaskDownload extends AsyncTask<String,Void,String>
    {


        public String urladdress="http://api.openweathermap.org/data/2.5/forecast/daily?q="+et_search_city.getText().toString()+"&units=metric&cnt=7&lang=en&APPID=0e948b3ecd877b785634d8ebdd15621e";


        public String cityName="";
        public Long date1;
        public String date1day;
        public String date1dec;
        public String date1DecText;
        public int mintemp;
        public int airpressure;
        public int humidity;
        public int wind;


        public String date2day;
        public String date2dec;
        public String date2DecText;
        public int mintemp2;

        public String date3day;
        public String date3dec;
        public String date3DecText;
        public int mintemp3;

        public String date4day;
        public String date4dec;
        public String date4DecText;
        public int mintemp4;

        public String date5day;
        public String date5dec;
        public String date5DecText;
        public int mintemp5;

        public String date6day;
        public String date6dec;
        public String date6DecText;
        public int mintemp6;


        public String date7day;
        public String date7dec;
        public String date7DecText;
        public int mintemp7;












        public String strJson;

        public String dateText;


        @Override
        protected String doInBackground(String... urls)
        {


            String data="";



            try {
                URL url= new URL(urladdress);

                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

                InputStream inputStream= httpURLConnection.getInputStream();

                BufferedReader bufferedReader= new BufferedReader( new InputStreamReader(inputStream));

                String line="";
                while (line!=null)
                {
                    line=bufferedReader.readLine();
                    data= data+line;
                }


                strJson= data;


                JSONObject  jsonRootObject = new JSONObject(strJson);


                JSONObject sys  = jsonRootObject.getJSONObject("city");


                cityName = sys.getString("name");

                JSONArray jsonarraylist = jsonRootObject.getJSONArray("list");

                for(int i=0;i<jsonarraylist.length();i++)
                {
                    JSONObject j1=(JSONObject)jsonarraylist.get(i);


                    if(jsonarraylist.get(i)==jsonarraylist.get(0))
                    {

                        date1=j1.getLong("dt");
                        Date date = new Date(j1.getLong("dt")*1000);
                        SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yy");
                        SimpleDateFormat date_day = new SimpleDateFormat("EEEE");

                        date1day=date_day.format(date);
                        dateText = date_format.format(date);


                        //	JSONArray weather = (JSONArray)j1.get("weather");
                        //JSONObject weather1 = (JSONObject)weather.get(0);
                        //daytwoweather.setText((String)weather1.get("description")+System.getProperty("line.separator"));
                        //
                        JSONArray weatherDec=(JSONArray)j1.get("weather");
                        JSONObject weatherJSon=(JSONObject)weatherDec.get(0);
                        date1dec= (String) weatherJSon.get("main");
                        date1DecText=(String) weatherJSon.get("description");

                        ChangeDay1weatherLogo(date1dec);
                        ChangeDay1weatherLogo(date1DecText);

                        changeBackgroundImg(date1dec);

                        //Temp
                        JSONObject tempJSON= (JSONObject) j1.get("temp");
                        Double min=tempJSON.getDouble("min");
                        mintemp= (int) Math.round(min);

                        //airpresser

                        Double pressure=j1.getDouble("pressure");
                        airpressure=(int)Math.round(pressure);

                        //humidity
                        Double humidityper=j1.getDouble("humidity");
                        humidity =(int)Math.round(humidityper);

                        //windSpeed

                        Double windSpeed=j1.getDouble("speed");
                        wind=(int)Math.round(windSpeed);





                    }
                    if(jsonarraylist.get(i)==jsonarraylist.get(1))
                    {


                        date1=j1.getLong("dt");
                        Date date = new Date(j1.getLong("dt")*1000);
                        SimpleDateFormat date_day = new SimpleDateFormat("EE");
                        date2day=date_day.format(date);

                        JSONArray jsonArrayWeather= (JSONArray) j1.get("weather");
                        JSONObject jsonObject= (JSONObject) jsonArrayWeather.get(0);

                        date2dec= (String) jsonObject.get("main");
                        date2DecText=(String) jsonObject.get("description");

                        ChangeDay2weatherLogo(date2dec);
                        ChangeDay2weatherLogo(date2DecText);

                        JSONObject tempJSONobj= (JSONObject) j1.get("temp");
                        Double min=tempJSONobj.getDouble("min");
                        mintemp2= (int) Math.round(min);


                    }
                    if(jsonarraylist.get(i)==jsonarraylist.get(2))
                    {
                        date1=j1.getLong("dt");
                        Date date = new Date(j1.getLong("dt")*1000);
                        SimpleDateFormat date_day = new SimpleDateFormat("EE");
                        date3day=date_day.format(date);

                        JSONArray WeathjsonArray= (JSONArray) j1.get("weather");
                        JSONObject jsonObject=(JSONObject) WeathjsonArray.get(0);
                        date3dec= (String) jsonObject.get("main");
                        date3DecText=(String) jsonObject.get("description");
                        ChangeDay3weatherLogo(date3dec);
                        ChangeDay3weatherLogo(date3DecText);

                        JSONObject jsonObject1= (JSONObject) j1.get("temp");
                        Double min=jsonObject1.getDouble("min");
                        mintemp3= (int) Math.round(min);

                    }
                    if(jsonarraylist.get(i)==jsonarraylist.get(3))
                    {
                        date1=j1.getLong("dt");
                        Date date= new Date(j1.getLong("dt")*1000);
                        SimpleDateFormat date_day=new SimpleDateFormat("EE");
                        date4day=date_day.format(date);


                        JSONArray jsonArray= (JSONArray) j1.get("weather");
                        JSONObject jsonObject2= (JSONObject) jsonArray.get(0);
                        date4dec=(String)jsonObject2.get("main");
                        date4DecText=(String)jsonObject2.get("description");
                        ChangeDay4weatherLogo(date4dec);
                        ChangeDay4weatherLogo(date4DecText);

                        JSONObject jsonObject3=(JSONObject) j1.get("temp");
                        Double min=jsonObject3.getDouble("min");
                        mintemp4=(int)Math.round(min);

                    }

                    if(jsonarraylist.get(i)==jsonarraylist.get(4))
                    {
                        date1= j1.getLong("dt");
                        Date date= new Date(j1.getLong("dt")*1000);
                        SimpleDateFormat date_day= new SimpleDateFormat("EE");
                        date5day=date_day.format(date);

                        JSONArray jsonArray= (JSONArray) j1.get("weather");
                        JSONObject jsonObject= (JSONObject) jsonArray.get(0);
                        date5dec=(String)jsonObject.get("main");
                        date5DecText=(String)jsonObject.get("description");
                        ChangeDay5weatherLogo(date5dec);
                        ChangeDay5weatherLogo(date5DecText);

                        JSONObject jsonObject1= (JSONObject)j1.get("temp");
                        Double min=jsonObject1.getDouble("min");
                        mintemp5=(int)Math.round(min);

                    }

                    if(jsonarraylist.get(i)==jsonarraylist.get(5))
                    {
                        date1=j1.getLong("dt");
                        Date date= new Date(j1.getLong("dt")*1000);
                        SimpleDateFormat date_day= new SimpleDateFormat("EE");
                        date6day=date_day.format(date);

                        JSONArray jsonArray=(JSONArray)j1.get("weather");
                        JSONObject jsonObject=(JSONObject)jsonArray.get(0);
                        date6dec=(String)jsonObject.get("main");
                        date6DecText=(String)jsonObject.get("description");
                        ChangeDay6weatherLogo(date6dec);
                        ChangeDay6weatherLogo(date6DecText);

                        JSONObject jsonObject1= (JSONObject)j1.get("temp");
                        Double min=jsonObject1.getDouble("min");
                        mintemp6=(int)Math.round(min);

                    }


                    if(jsonarraylist.get(i)==jsonarraylist.get(6))
                    {
                        date1=j1.getLong("dt");
                        Date date= new Date(j1.getLong("dt")*1000);
                        SimpleDateFormat date_day= new SimpleDateFormat("EE");
                        date7day=date_day.format(date);

                        JSONArray jsonArray=(JSONArray)j1.get("weather");
                        JSONObject object=(JSONObject)jsonArray.get(0);
                        date7dec=(String)object.get("main");
                        date7DecText=(String)object.get("description");
                        ChangeDay7weatherLogo(date7dec);
                        ChangeDay7weatherLogo(date7DecText);




                        JSONObject jsonObject1= (JSONObject)j1.get("temp");
                        Double min=jsonObject1.getDouble("min");
                        mintemp7=(int)Math.round(min);

                    }
                }

            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (JSONException e)
            {
                e.printStackTrace();
            }


            return null;
        }








        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            tv_cityName.setText(this.cityName);

            tv_CurrentDate.setText(this.dateText);

            tv_description.setText(this.date1dec);

            tv_smalldec.setText(this.date1DecText);
            tv_weak_day1.setText(this.date1day);
            tv_temp.setText(Integer.toString(this.mintemp)+"°");
            tv_presser.setText(Integer.toString(this.airpressure)+"hPa");
            tv_airHumidity.setText(Integer.toString(this.humidity)+"%");
            tv_wind.setText(Integer.toString(this.wind)+"m/s");

            tv_weak_day2.setText("   "+this.date2day);
            tv_temp_day2.setText(Integer.toString(this.mintemp2)+"°");

            tv_weak_day3.setText(this.date3day);
            tv_temp_day3.setText(Integer.toString(this.mintemp3)+"°");

            tv_weak_day4.setText(this.date4day);
            tv_temp_day4.setText(Integer.toString(this.mintemp4)+"°");

            tv_weak_day5.setText(this.date5day);
            tv_temp_day5.setText(Integer.toString(this.mintemp5)+"°");

            tv_weak_day6.setText(this.date6day);
            tv_temp_day6.setText(Integer.toString(this.mintemp6)+"°");

            tv_weak_day7.setText(this.date7day);
            tv_temp_day7.setText(Integer.toString(this.mintemp7)+"°");



        }



        public void changeBackgroundImg(final String textDec)
        {


            runOnUiThread(new Runnable() {
                @Override
                public void run()
                {
                    if(textDec .equals("Clouds")|| textDec.equals("clouds"))
                    {
                        relativeLayout.setBackgroundResource(R.drawable.bg1);
                    }

                    if(textDec .equals("Clear")|| textDec.equals("clear"))
                    {
                        relativeLayout.setBackgroundResource(R.drawable.bg1);
                    }

                    if(textDec .equals("Rain")|| textDec.equals("rain"))
                    {
                        relativeLayout.setBackgroundResource(R.drawable.bg1);
                    }



                }
            });
        }


        public void ChangeDay1weatherLogo(final String text)
        {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(text .equals("Thunderstorm"))
                    {
                        img_dec1.setImageResource(R.drawable.seven);
                    }
                    if(text .equals("Drizzle"))
                    {
                        img_dec1.setImageResource(R.drawable.five);
                    }

                    if(text .equals("Snow"))
                    {
                        img_dec1.setImageResource(R.drawable.eight);
                    }
                    if(text .equals("Mist")||text .equals("Smoke")||text .equals("Haze")||text .equals("Fog")||text .equals("Sand")||text .equals("Dust")||text .equals("Ash")||text .equals("Squall")||text .equals("Tornado"))
                    {
                        img_dec1.setImageResource(R.drawable.nine);
                    }
                    if(text .equals("Clear"))
                    {
                        img_dec1.setImageResource(R.drawable.one);
                    }

                    if(text .equals("light rain")||text .equals("moderate rain")||text .equals("heavy intensity rain")||text .equals("very heavy rain")||text .equals("extreme rain"))
                    {
                        img_dec1.setImageResource(R.drawable.six);
                    }

                    if(text .equals("freezing rain"))
                    {
                        img_dec1.setImageResource(R.drawable.eight);
                    }

                    if(text .equals("light intensity shower rain")||text .equals("shower rain")||text .equals("heavy intensity shower rain")||text .equals("ragged shower rain"))
                    {
                        img_dec1.setImageResource(R.drawable.five);
                    }


                    if(text .equals("few clouds"))
                    {
                        img_dec1.setImageResource(R.drawable.two);
                    }

                    if(text .equals("scattered clouds"))
                    {
                        img_dec1.setImageResource(R.drawable.two);
                    }

                    if(text .equals("broken clouds")||text .equals("overcast clouds"))
                    {
                        img_dec1.setImageResource(R.drawable.four);
                    }


                }
            });
        }



        public void ChangeDay2weatherLogo(final String text)
        {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(text .equals("Thunderstorm"))
                    {
                        img_day2.setImageResource(R.drawable.seven);
                    }
                    if(text .equals("Drizzle"))
                    {
                        img_day2.setImageResource(R.drawable.five);
                    }

                    if(text .equals("Snow"))
                    {
                        img_day2.setImageResource(R.drawable.eight);
                    }
                    if(text .equals("Mist")||text .equals("Smoke")||text .equals("Haze")||text .equals("Fog")||text .equals("Sand")||text .equals("Dust")||text .equals("Ash")||text .equals("Squall")||text .equals("Tornado"))
                    {
                        img_day2.setImageResource(R.drawable.nine);
                    }
                    if(text .equals("Clear"))
                    {
                        img_day2.setImageResource(R.drawable.one);
                    }

                    if(text .equals("light rain")||text .equals("moderate rain")||text .equals("heavy intensity rain")||text .equals("very heavy rain")||text .equals("extreme rain"))
                    {
                        img_day2.setImageResource(R.drawable.six);
                    }

                    if(text .equals("freezing rain"))
                    {
                        img_day2.setImageResource(R.drawable.eight);
                    }

                    if(text .equals("light intensity shower rain")||text .equals("shower rain")||text .equals("heavy intensity shower rain")||text .equals("ragged shower rain"))
                    {
                        img_day2.setImageResource(R.drawable.five);
                    }


                    if(text .equals("few clouds"))
                    {
                        img_day2.setImageResource(R.drawable.two);
                    }

                    if(text .equals("scattered clouds"))
                    {
                        img_day2.setImageResource(R.drawable.two);
                    }

                    if(text .equals("broken clouds")||text .equals("overcast clouds"))
                    {
                        img_day2.setImageResource(R.drawable.four);
                    }


                }
            });
        }


        public void ChangeDay3weatherLogo(final String text)
        {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(text .equals("Thunderstorm"))
                    {
                        img_day3.setImageResource(R.drawable.seven);
                    }
                    if(text .equals("Drizzle"))
                    {
                        img_day3.setImageResource(R.drawable.five);
                    }

                    if(text .equals("Snow"))
                    {
                        img_day3.setImageResource(R.drawable.eight);
                    }
                    if(text .equals("Mist")||text .equals("Smoke")||text .equals("Haze")||text .equals("Fog")||text .equals("Sand")||text .equals("Dust")||text .equals("Ash")||text .equals("Squall")||text .equals("Tornado"))
                    {
                        img_day3.setImageResource(R.drawable.nine);
                    }
                    if(text .equals("Clear"))
                    {
                        img_day3.setImageResource(R.drawable.one);
                    }

                    if(text .equals("light rain")||text .equals("moderate rain")||text .equals("heavy intensity rain")||text .equals("very heavy rain")||text .equals("extreme rain"))
                    {
                        img_day3.setImageResource(R.drawable.six);
                    }

                    if(text .equals("freezing rain"))
                    {
                        img_day3.setImageResource(R.drawable.eight);
                    }

                    if(text .equals("light intensity shower rain")||text .equals("shower rain")||text .equals("heavy intensity shower rain")||text .equals("ragged shower rain"))
                    {
                        img_day3.setImageResource(R.drawable.five);
                    }


                    if(text .equals("few clouds"))
                    {
                        img_day3.setImageResource(R.drawable.two);
                    }

                    if(text .equals("scattered clouds"))
                    {
                        img_day3.setImageResource(R.drawable.two);
                    }

                    if(text .equals("broken clouds")||text .equals("overcast clouds"))
                    {
                        img_day3.setImageResource(R.drawable.four);
                    }


                }
            });
        }


        public void ChangeDay4weatherLogo(final String text)
        {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(text .equals("Thunderstorm"))
                    {
                        img_day4.setImageResource(R.drawable.seven);
                    }
                    if(text .equals("Drizzle"))
                    {
                        img_day4.setImageResource(R.drawable.five);
                    }

                    if(text .equals("Snow"))
                    {
                        img_day4.setImageResource(R.drawable.eight);
                    }
                    if(text .equals("Mist")||text .equals("Smoke")||text .equals("Haze")||text .equals("Fog")||text .equals("Sand")||text .equals("Dust")||text .equals("Ash")||text .equals("Squall")||text .equals("Tornado"))
                    {
                        img_day4.setImageResource(R.drawable.nine);
                    }
                    if(text .equals("Clear"))
                    {
                        img_day4.setImageResource(R.drawable.one);
                    }

                    if(text .equals("light rain")||text .equals("moderate rain")||text .equals("heavy intensity rain")||text .equals("very heavy rain")||text .equals("extreme rain"))
                    {
                        img_day4.setImageResource(R.drawable.six);
                    }

                    if(text .equals("freezing rain"))
                    {
                        img_day4.setImageResource(R.drawable.eight);
                    }

                    if(text .equals("light intensity shower rain")||text .equals("shower rain")||text .equals("heavy intensity shower rain")||text .equals("ragged shower rain"))
                    {
                        img_day4.setImageResource(R.drawable.five);
                    }


                    if(text .equals("few clouds"))
                    {
                        img_day4.setImageResource(R.drawable.two);
                    }

                    if(text .equals("scattered clouds"))
                    {
                        img_day4.setImageResource(R.drawable.two);
                    }

                    if(text .equals("broken clouds")||text .equals("overcast clouds"))
                    {
                        img_day4.setImageResource(R.drawable.four);
                    }


                }
            });
        }

        public void ChangeDay5weatherLogo(final String text)
        {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(text .equals("Thunderstorm"))
                    {
                        img_day5.setImageResource(R.drawable.seven);
                    }
                    if(text .equals("Drizzle"))
                    {
                        img_day5.setImageResource(R.drawable.five);
                    }

                    if(text .equals("Snow"))
                    {
                        img_day5.setImageResource(R.drawable.eight);
                    }
                    if(text .equals("Mist")||text .equals("Smoke")||text .equals("Haze")||text .equals("Fog")||text .equals("Sand")||text .equals("Dust")||text .equals("Ash")||text .equals("Squall")||text .equals("Tornado"))
                    {
                        img_day5.setImageResource(R.drawable.nine);
                    }
                    if(text .equals("Clear"))
                    {
                        img_day5.setImageResource(R.drawable.one);
                    }

                    if(text .equals("light rain")||text .equals("moderate rain")||text .equals("heavy intensity rain")||text .equals("very heavy rain")||text .equals("extreme rain"))
                    {
                        img_day5.setImageResource(R.drawable.six);
                    }

                    if(text .equals("freezing rain"))
                    {
                        img_day5.setImageResource(R.drawable.eight);
                    }

                    if(text .equals("light intensity shower rain")||text .equals("shower rain")||text .equals("heavy intensity shower rain")||text .equals("ragged shower rain"))
                    {
                        img_day5.setImageResource(R.drawable.five);
                    }


                    if(text .equals("few clouds"))
                    {
                        img_day5.setImageResource(R.drawable.two);
                    }

                    if(text .equals("scattered clouds"))
                    {
                        img_day5.setImageResource(R.drawable.two);
                    }

                    if(text .equals("broken clouds")||text .equals("overcast clouds"))
                    {
                        img_day5.setImageResource(R.drawable.four);
                    }


                }
            });
        }

        public void ChangeDay6weatherLogo(final String text)
        {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(text .equals("Thunderstorm"))
                    {
                        img_day6.setImageResource(R.drawable.seven);
                    }
                    if(text .equals("Drizzle"))
                    {
                        img_day6.setImageResource(R.drawable.five);
                    }

                    if(text .equals("Snow"))
                    {
                        img_day6.setImageResource(R.drawable.eight);
                    }
                    if(text .equals("Mist")||text .equals("Smoke")||text .equals("Haze")||text .equals("Fog")||text .equals("Sand")||text .equals("Dust")||text .equals("Ash")||text .equals("Squall")||text .equals("Tornado"))
                    {
                        img_day6.setImageResource(R.drawable.nine);
                    }
                    if(text .equals("Clear"))
                    {
                        img_day6.setImageResource(R.drawable.one);
                    }

                    if(text .equals("light rain")||text .equals("moderate rain")||text .equals("heavy intensity rain")||text .equals("very heavy rain")||text .equals("extreme rain"))
                    {
                        img_day6.setImageResource(R.drawable.six);
                    }

                    if(text .equals("freezing rain"))
                    {
                        img_day6.setImageResource(R.drawable.eight);
                    }

                    if(text .equals("light intensity shower rain")||text .equals("shower rain")||text .equals("heavy intensity shower rain")||text .equals("ragged shower rain"))
                    {
                        img_day6.setImageResource(R.drawable.five);
                    }


                    if(text .equals("few clouds"))
                    {
                        img_day6.setImageResource(R.drawable.two);
                    }

                    if(text .equals("scattered clouds"))
                    {
                        img_day6.setImageResource(R.drawable.two);
                    }

                    if(text .equals("broken clouds")||text .equals("overcast clouds"))
                    {
                        img_day6.setImageResource(R.drawable.four);
                    }


                }
            });
        }

        public void ChangeDay7weatherLogo(final String text)
        {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(text .equals("Thunderstorm"))
                    {
                        img_day7.setImageResource(R.drawable.seven);
                    }
                    if(text .equals("Drizzle"))
                    {
                        img_day7.setImageResource(R.drawable.five);
                    }

                    if(text .equals("Snow"))
                    {
                        img_day7.setImageResource(R.drawable.eight);
                    }
                    if(text .equals("Mist")||text .equals("Smoke")||text .equals("Haze")||text .equals("Fog")||text .equals("Sand")||text .equals("Dust")||text .equals("Ash")||text .equals("Squall")||text .equals("Tornado"))
                    {
                        img_day7.setImageResource(R.drawable.nine);
                    }
                    if(text .equals("Clear"))
                    {
                        img_day7.setImageResource(R.drawable.one);
                    }

                    if(text .equals("light rain")||text .equals("moderate rain")||text .equals("heavy intensity rain")||text .equals("very heavy rain")||text .equals("extreme rain"))
                    {
                        img_day7.setImageResource(R.drawable.six);
                    }

                    if(text .equals("freezing rain"))
                    {
                        img_day7.setImageResource(R.drawable.eight);
                    }

                    if(text .equals("light intensity shower rain")||text .equals("shower rain")||text .equals("heavy intensity shower rain")||text .equals("ragged shower rain"))
                    {
                        img_day7.setImageResource(R.drawable.five);
                    }


                    if(text .equals("few clouds"))
                    {
                        img_day7.setImageResource(R.drawable.two);
                    }

                    if(text .equals("scattered clouds"))
                    {
                        img_day7.setImageResource(R.drawable.two);
                    }

                    if(text .equals("broken clouds")||text .equals("overcast clouds"))
                    {
                        img_day7.setImageResource(R.drawable.four);
                    }


                }
            });
        }




    }

}
