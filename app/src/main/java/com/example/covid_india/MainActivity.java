package com.example.covid_india;
//@author by ahegde3
//        contact-ahegde3@gmail.com
//       anishhegde.tk
import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private Button st, dst;
    public TextView Cases, Active, rec, death,state,dstr;
    Switch swtch;
    Spinner states,district;
    String arr1[];
    String[][] arr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arr2=new String[][]{{"none"},
                {"none","South Andaman","North and Middle Andaman"},
                {"none","S.P.S. Nellore","Prakasam","Visakhapatnam","East Godavari","Krishna","Chittoor","Guntur","Kurnool","Anantapur","West Godavari","Y.S.R"},
                {"none"},
                {"none","Cachar"},
                {"none","Munger","Patna","Siwan","Nalanda","Unknown","Lakhisarai","Bhagalpur","Gopalganj","Gaya","Begusarai"},
                {"none","Chandigarh"},
                {"none","Raipur","Rajnandgaon","Bilaspur","Durg","Korba"},
                {"none"},
                {"none"},
                {"none","East Delhi","South West Delhi","West Delhi","North Delhi","New Delhi","South Delhi","North East Delhi","North West Delhi","Unknown"},
                {"none","North Goa","Unknown"},
                {"none","Rajkot","Surat","Ahmadabad","Vadodara","Gandhinagar","Kachchh","Unknown","Mahesana","Porbandar","Gir Somnath","Bhavnagar"},
                {"none","Gurugram","Faridabad","Panipat","Panchkula","Sonipat","Palwal","Ambala","Sirsa","Unknown"},
                {"none","Kangra"},
                {"none","Jammu","Srinagar","Bandipore","Unknown","Rajouri","Badgam","Baramula","Shopian"},
                {"none","Ranchi"},
                {"none","Bengaluru","Kalaburagi","Kodagu","Chikkaballapura","Mysuru","Dharwad","Uttara Kannada","Dakshina Kannada","Udupi","Chitradurga","Tumakuru","Davanagere","Ballari"},
                {"none","Thrissur","Alappuzha","Kasaragod","Pathanamthitta","Kannur","Ernakulam","Kottayam","Thiruvananthapuram","Idukki","Malappuram","Kozhikode","Palakkad","Wayanad","Kollam"},
                {"none","Leh","Kargil"},
                {"none"},
                {"none","Jabalpur","Bhopal","Indore","Ujjain","Gwalior","Shivpuri","Unknown"},
                {"none","Pune","Mumbai","Nagpur","Thane","Ahmadnagar","Yavatmal","Aurangabad","Ratnagiri","Mumbai Suburban","Unknown","Sangli","Satara","Kolhapur","Gondiya","Jalgaon","Nashik","Buldana"},
                {"none","Imphal West"},
                {"none"},
                {"none","Aizawl"},
                {"none"},
                {"none","Khordha","Bhadrak"},
                {"none","Mahe","Puducherry"},
                {"none","Amritsar","Shahid Bhagat Singh Nagar","S.A.S. Nagar","Hoshiarpur","Jalandhar","Unknown","Ludhiana","Mohali"},
                {"none","Jaipur","Jhunjhunu","Bhilwara","Jodhpur","Unknown","Pratapgarh","Ajmer","Alwar","Dungarpur"},
                {"none"},
                {"none","Kancheepuram","Chennai","Erode","Coimbatore","Tirunelveli","Tiruppur","Madurai","Salem","Vellore","Tiruchirappalli","Chengalpattu","Thanjavur","Virudhunagar","Karur","Tiruvannamalai","Viluppuram","Namakkal","Kanniyakumari","Thoothukkudi"},
                {"none","Hyderabad","Karimnagar","Bhadradri Kothagudem","Ranga Reddy","Medchal Malkajgiri","Unknown"},
                {"none"},
                {"none","Agra","Unknown","Ghaziabad","Lucknow","Gautam Buddha Nagar","Moradabad","Varanasi","Kanpur Nagar","Pilibhit","Jaunpur","Baghpat","Meerut","Bareilly"},
                {"none","Dehradun","Pauri Garhwal","Unknown"},
                {"none","Kolkata","North 24 Parganas","Nadia","Purba Medinipur","Kalimpong","Hooghly","Howrah","South 24 Parganas","Medinipur East"}
        };
        arr1=new String[]{"none","Andaman and Nicobar Islands","Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chandigarh","Chhattisgarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Ladakh","Lakshadweep","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Puducherry","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"};
        st=(Button)findViewById(R.id.button);
        state=(TextView)findViewById(R.id.textView10);
        state.setVisibility(View.INVISIBLE);
        states=(Spinner)findViewById(R.id.spinner2);
        states.setOnItemSelectedListener(this);
        district=(Spinner)findViewById(R.id.spinner3);
        district.setOnItemSelectedListener(this);
        states.setVisibility(View.INVISIBLE);
        district.setVisibility(View.INVISIBLE);
        dstr=(TextView)findViewById(R.id.textView11);
        dstr.setVisibility(View.INVISIBLE);
        Cases = (TextView) findViewById(R.id.textView5);
        Cases.setVisibility(View.INVISIBLE);
        Active = (TextView) findViewById(R.id.textView6);
        Active.setVisibility(View.INVISIBLE);
        death = (TextView) findViewById(R.id.textView7);
        death.setVisibility(View.INVISIBLE);
        rec = (TextView) findViewById(R.id.textView8);
        rec.setVisibility(View.INVISIBLE);
        swtch=(Switch)findViewById(R.id.switch1);
        swtch.setChecked(false);

        swtch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(swtch.isChecked()){
                    state.setVisibility(View.VISIBLE);
                    dstr.setVisibility(View.VISIBLE);
                    states.setVisibility(View.VISIBLE);
                    district.setVisibility(View.VISIBLE);
                }
                else {

                    state.setVisibility(View.INVISIBLE);
                    dstr.setVisibility(View.INVISIBLE);
                    states.setVisibility(View.INVISIBLE);
                    district.setVisibility(View.INVISIBLE);
                }
            }
        });

        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("testing","state:-"+states.getSelectedItem());
                Log.d("testing","dist:-"+district.getSelectedItem());
//             AsynCheck t=new AsynCheck();
//              t.execute();
                AsyncTaskExample asyncTask = new AsyncTaskExample();
                asyncTask.execute(states.getSelectedItem().toString()+","+district.getSelectedItem().toString());
            }



        });

    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
        if(parent.getId()==R.id.spinner2){
        List<String> li=new ArrayList<>();
        int i=states.getSelectedItemPosition();
      // Log.d("SPINNER","iNSIDE");
        li=Arrays.asList(arr2[i]);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, li);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        district.setAdapter(ad);}
        else {}
        //district.setOnItemSelectedListener(this);
        //Toast.makeText(getApplicationContext(),pos,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
    class AsyncTaskExample extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Response response=null;
            try {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://corona-virus-world-and-india-data.p.rapidapi.com/api_india")
                        .get()
                        .addHeader("x-rapidapi-host", "corona-virus-world-and-india-data.p.rapidapi.com")
                        .addHeader("x-rapidapi-key", "50ra0N9ALymshbWWjmEpvtCjKTNQp1OUomqjsn2P6Lu9IWBvXs")
                        .build();

                response = client.newCall(request).execute();
                String val[]=strings[0].split(",");
                String state=val[0],dst=val[1];
               Log.d("testing","para "+state+" , "+dst);
                String result = response.body().string();
                JSONObject ja =new JSONObject(result);
                //State_wise->state->district
                if(state.equals("none"))  return ja.getJSONObject("total_values").toString();
                else if(dst.equals("none")) return ja.getJSONObject("state_wise").getJSONObject(state).toString();
                else return ja.getJSONObject("state_wise").getJSONObject(state).getJSONObject("district").get(dst).toString();

              //  Log.d("testing","city"+ja.getJSONObject("state_wise").getJSONObject("Maharashtra").get("active"));


             //   return ja.getJSONObject("total_values").toString();

            }  catch (IOException e) {
                //TODO Handle problems..
                Log.d("testing","IOExcep");
            }
            catch(Exception e) {Log.d("testing","Ex:"+e.toString());}
            return null;
        }
        @Override
        protected void onPostExecute(String result) {

            try {
                 Log.d("testing res",result);
                 JSONObject ja = new JSONObject(result);
                if(ja.has("confirmed") && ja.has("active") && ja.has("deaths")) {
                    int tot, act, rc, dth;
                    tot = Integer.parseInt(ja.get("confirmed").toString());
                    act = Integer.parseInt(ja.get("active").toString());
                    dth = Integer.parseInt(ja.get("deaths").toString());
                    rc = tot - act - dth;
                    Log.d("testing", "active:" + act + " tot:" + tot + " death:" + death);
                    Cases.setText(String.valueOf(tot));
                    Active.setText(String.valueOf(act));
                    rec.setText(String.valueOf(rc));
                    death.setText(String.valueOf(dth));
                    Cases.setVisibility(View.VISIBLE);
                    Active.setVisibility(View.VISIBLE);
                    rec.setVisibility(View.VISIBLE);
                    death.setVisibility(View.VISIBLE);
                }
                else { Cases.setText(ja.get("confirmed").toString());
                        Cases.setVisibility(View.VISIBLE);
                    Active.setText("NA");
                    rec.setText("NA");
                    death.setText("NA");
                    Cases.setVisibility(View.VISIBLE);
                    Active.setVisibility(View.VISIBLE);
                    rec.setVisibility(View.VISIBLE);
                    death.setVisibility(View.VISIBLE);
                  }
            } catch(Exception e) {Log.d("testing ex",e.toString());}
            super.onPostExecute(result);
        }
    }
    private class AsynCheck extends AsyncTask<String,String, String> {
        protected String doInBackground(String... args) {
            try {
                InetAddress ipAddr = InetAddress.getByName("https://www.google.com" );
//                HttpURLConnection conn=(HttpURLConnection)new URL("https://www.google.com").openConnection();
//                conn.connect();

                //You can replace it with your name
                Log.d("testing","Int"+ipAddr.toString());
                return String.valueOf(!ipAddr.equals(""));

            } catch (UnknownHostException e) { Log.d("testing","int ex"+e.toString());
            Log.d("testing","after excep");
                return "false";
            }

        }
        @Override
        protected void onPostExecute(String result)
        {
             super.onPostExecute(result);
             Log.d("Testing","Inside onPostexecute");
        if(result.equals("true")){
            Log.d("testing","Internet Connected");
            AsyncTaskExample asyncTask = new AsyncTaskExample();
            asyncTask.execute(states.getSelectedItem().toString()+","+district.getSelectedItem().toString());}
                else { Log.d("testing","internet unavialable");
                try {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

                    alertDialog.setTitle("Info");
                    alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });

                    alertDialog.show();
                } catch (Exception e) {
                    Log.d("testing", "Show Dialog: " + e.getMessage());
                }
            }
        }
    }
}
