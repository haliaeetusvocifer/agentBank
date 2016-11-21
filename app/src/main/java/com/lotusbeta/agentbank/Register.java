


        package com.lotusbeta.agentbank;

        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.drawable.BitmapDrawable;
        import android.os.AsyncTask;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Base64;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.apache.http.HttpResponse;
        import org.apache.http.NameValuePair;
        import org.apache.http.client.ClientProtocolException;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.entity.UrlEncodedFormEntity;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.impl.client.DefaultHttpClient;
        import org.apache.http.message.BasicNameValuePair;
        import org.apache.http.protocol.HTTP;

        import java.io.BufferedReader;
        import java.io.ByteArrayOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.UnsupportedEncodingException;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Calendar calendar = Calendar.getInstance();
    TextView etNewDate;
    private EditText etInstitutionID;
    EditText etTitle;
    EditText etLga;
    private EditText etTerminalID;
    private EditText etFirstName;
    private EditText etLastname;
    private EditText etOtherName;
    private EditText etAddress;
    private Spinner etAddressTown;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etDob;
    private Spinner etSex;
    private Spinner etAccountType;
    private EditText etBranchCode;
    private EditText etProductCode;
    private Spinner etAccountServices;
    private EditText etOccupation;
    private EditText etAccountOfficer;
    private EditText etBankFiid;
    private EditText etPlaceOfBirth;
    private Button sendAccountButton;
    private Button sendClearButton;
    ImageView takenImage;
    private static final int CAM_REQUEST = 1313;
    ImageView signShow;
    Bitmap bm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if(getIntent().hasExtra("Bitmap")) {

            ImageView signedImage = (ImageView) findViewById(R.id.signature);
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("Bitmap"), 0, getIntent().getByteArrayExtra("Bitmap").length
            );
            signedImage.setImageBitmap(b);
        }


        /*Bitmap comingBitmap = (Bitmap)this.getIntent().getParcelableExtra("Bitmap");
        ImageView signedImage = (ImageView) findViewById(R.id.signature);
        signedImage.setImageBitmap(comingBitmap);*/

        takenImage = (ImageView) findViewById(R.id.pictureTaken);

        //takeButton.setOnClickListener(new takeButtonClicker());
        takenImage.setOnClickListener(new takeButtonClicker());

        //signature pad
        signShow = (ImageView) findViewById(R.id.signature);
        signShow.setOnClickListener(new signMe()); /*{
            @Override
            public void onClick(View v) {
                Intent signIntent = new Intent(Register.this, Biometric.class);
                startActivity(signIntent);
            }
        });*/

        /*etInstitutionID = (EditText) findViewById(R.id.InstitutionID);
        etTerminalID = (EditText) findViewById(R.id.TerminalID);*/
        etTitle=(EditText)findViewById(R.id.Title);
        etLga=(EditText)findViewById(R.id.Lga);
        etFirstName = (EditText) findViewById(R.id.firstname);
        etLastname = (EditText) findViewById(R.id.surname);
        etOtherName = (EditText) findViewById(R.id.othername);
        etAddress = (EditText) findViewById(R.id.address);
        etAddressTown = (Spinner) findViewById(R.id.state);
        etEmail = (EditText) findViewById(R.id.email);
        etPhone = (EditText) findViewById(R.id.telephone);
        /*etDob = (EditText) findViewById(R.id.dob);*/
        etNewDate=(TextView)findViewById(R.id.newDate);
        etSex = (Spinner) findViewById(R.id.userGender);
        etAccountType = (Spinner) findViewById(R.id.accountType);
       /* etBranchCode = (EditText) findViewById(R.id.branchCode);
        etProductCode = (EditText) findViewById(R.id.productCode);*/
        etAccountServices = (Spinner) findViewById(R.id.accountServices);
        etOccupation = (EditText) findViewById(R.id.occupation);
        /*etAccountOfficer = (EditText) findViewById(R.id.accountOfficer);*/
        etPlaceOfBirth = (EditText) findViewById(R.id.PlaceOfBirth);



        sendAccountButton = (Button) findViewById(R.id.sendButton);
        sendAccountButton.setOnClickListener(this);

        sendClearButton = (Button) findViewById(R.id.clearButton);
        sendClearButton.setOnClickListener(this);







        Button menuButton = (Button)findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String givenLga = etLga.getEditableText().toString();
                String givenTitle = etTitle.getEditableText().toString();
                String givenFirstName = etFirstName.getEditableText().toString();
                String givenLastName = etLastname.getEditableText().toString();
                String givenOtherName = etOtherName.getEditableText().toString();
                String givenAddress = etAddress.getEditableText().toString();
                String givenAddressTown = etAddressTown.getSelectedItem().toString() +","+givenLga;
                String givenEmail = etEmail.getEditableText().toString();
                String givenPhone = etPhone.getEditableText().toString();

                String givenDob = etNewDate.getText().toString();
                String givenSex = etSex.getSelectedItem().toString();

                String givenProductCode = etAccountServices.getSelectedItem().toString();
                String givenAccountType = etAccountType.getSelectedItem().toString();
                String givenOccupation = etOccupation.getEditableText().toString();

                String givenPlaceOfBirth = etPlaceOfBirth.getEditableText().toString();



                Intent reviewIntent = new Intent(Register.this, review.class);
                //Create the bundle
                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("Title",givenTitle );
                bundle.putString("Surname",givenLastName );
                bundle.putString("FirstName",givenFirstName );
                bundle.putString("OtherName",givenOtherName );
                bundle.putString("Gender",givenSex );
                bundle.putString("PlaceOfBirth",givenPlaceOfBirth );
                bundle.putString("Dob",givenDob );
                bundle.putString("HomeAddress",givenAddress );
                bundle.putString("StateOfOrigin",givenAddressTown+","+givenLga );
                bundle.putString("Lga",givenLga );
                bundle.putString("Telephone",givenPhone );
                bundle.putString("Email",givenEmail );
                bundle.putString("Occupation",givenOccupation );
                bundle.putString("AccountServices",givenProductCode );
                bundle.putString("AccountType",givenAccountType );


               reviewIntent.putExtras(bundle);
                startActivity(reviewIntent);

                //startActivity(reviewIntent);
            }
        });


        etNewDate = (TextView)findViewById(R.id.newDate);
        Button etDateButton = (Button)findViewById(R.id.dateButton);
        etDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new DatePickerDialog(Register.this,listener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
        /*etNewDate.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);*/
            etNewDate.setText((monthOfYear+1)+"/"+dayOfMonth+"/"+year);
        }
    };

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.clearButton){
            etInstitutionID.setText("");
            etTerminalID.setText("");
            etTerminalID.setCursorVisible(false);
            etTerminalID.setFocusable(false);
            etInstitutionID.setCursorVisible(true);
            etTerminalID.setFocusable(true);
        }else if(v.getId() == R.id.sendButton){
            String givenInstitutionID = "001";
            String givenTerminalID = "001";
            String givenLga = etLga.getEditableText().toString();
            String givenTitle = etTitle.getEditableText().toString();
            String givenFirstName = etFirstName.getEditableText().toString();
            String givenLastName = etLastname.getEditableText().toString();
            String givenOtherName = etOtherName.getEditableText().toString();
            String givenAddress = etAddress.getEditableText().toString();
            String givenAddressTown = etAddressTown.getSelectedItem().toString() +","+givenLga;
            String givenEmail = etEmail.getEditableText().toString();
            String givenPhone = etPhone.getEditableText().toString();
           /* String givenDob = etDob.getEditableText().toString();*/
           String givenDob = etNewDate.getText().toString();
            String givenSex = etSex.getSelectedItem().toString();
            String givenBranchCode = "001";
            String givenProductCode = etAccountServices.getSelectedItem().toString();
            String givenAccountType = etAccountType.getSelectedItem().toString();
            String givenOccupation = etOccupation.getEditableText().toString();
            String givenAccountOfficer = "001";

            String givenPlaceOfBirth = etPlaceOfBirth.getEditableText().toString();

            signShow = (ImageView)findViewById(R.id.signature);
            BitmapDrawable drawable = (BitmapDrawable) signShow.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
            byte[] bb = bos.toByteArray();
            String signImage = Base64.encodeToString(bb, 1);
            //Log.w("ImageString","Img:"+image);

            takenImage = (ImageView) findViewById(R.id.pictureTaken);
            BitmapDrawable pictureDrawable = (BitmapDrawable) takenImage.getDrawable();
            Bitmap pictureBitmap = pictureDrawable.getBitmap();
            ByteArrayOutputStream bosPicture = new ByteArrayOutputStream();
            pictureBitmap.compress(Bitmap.CompressFormat.PNG,100,bosPicture);
            byte[] pp = bosPicture.toByteArray();
            String pictureSnap = Base64.encodeToString(pp,1);






            //System.out.println("Givenname :" + givenUsername + " Given password :" + givenPassword);

            sendPostRequest(givenInstitutionID, givenTerminalID, givenFirstName, givenLastName, givenOtherName, givenAddress, givenAddressTown, givenEmail, givenPhone, givenDob, givenSex, givenBranchCode, givenProductCode, givenOccupation, givenAccountOfficer, givenPlaceOfBirth, signImage, pictureSnap, givenTitle, givenAccountType);



        }
    }

    private void sendPostRequest(String givenInstitutionID, String givenTerminalID, String givenFirstName,String givenLastName,String givenOtherName, String givenAddress,String givenAddressTown, String givenEmail, String givenPhone,String givenDob,String givenSex,String givenBranchCode, String givenProductCode,String givenOccupation,String givenAccountOfficer,String givenPlaceOfBirth, String signImage, String pictureSnap, String givenTitle, String givenAccountType)
    {



        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String paramInstitutionID = params[0];
                String paramTerminalID = params[1];
                String paramFirstName = params[2];
                String paramLastName = params[3];
                String paramOtherName = params[4];
                String paramAddress = params[5];
                String paramAddressTown = params[6];
                String paramEmail = params[7];
                String paramPhone = params[8];
                String paramDob = params[9];
                String paramSex = params[10];
                String paramBranchCode = params[11];
                String paramProductCode = params[12];
                String paramOccupation = params[13];
                String paramAccountOfficer = params[14];
                String paramPlaceOfBirth = params[15];
                String paramSignImage=params[16];
                String paramPictureSnap=params[17];
                String paramTitle=params[18];
                String paramAccountType=params[19];




                //System.out.println("*** doInBackground ** paramUsername " + paramUsername + " paramPassword :" + paramPassword);

                HttpClient httpClient = new DefaultHttpClient();

                // In a POST request, we don't pass the values in the URL.
                //Therefore we use only the web page URL as the parameter of the HttpPost argument
                HttpPost httpPost = new HttpPost("http://agentbankportal.azurewebsites.net/api/DailyWorksWeb");
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8");

                // Because we are not passing values over the URL, we should have a mechanism to pass the values that can be
                //uniquely separate by the other end.
                //To achieve that we use BasicNameValuePair
                //Things we need to pass with the POST request
                BasicNameValuePair InstitutionID = new BasicNameValuePair("InstitutionID",paramInstitutionID);
                BasicNameValuePair TerminalID = new BasicNameValuePair("TerminalID", paramTerminalID);
                BasicNameValuePair Title= new BasicNameValuePair("Title", paramTitle);
                BasicNameValuePair FirstName = new BasicNameValuePair("FirstName", paramFirstName);
                BasicNameValuePair LastName = new BasicNameValuePair("Surname", paramLastName);
                BasicNameValuePair OtherName = new BasicNameValuePair("OtherName", paramOtherName);
                BasicNameValuePair Address = new BasicNameValuePair("HomeAddress", paramAddress);
                BasicNameValuePair AddressTown = new BasicNameValuePair("Region", paramAddressTown);
                BasicNameValuePair Email = new BasicNameValuePair("EmailAddress", paramEmail);
                BasicNameValuePair Phone = new BasicNameValuePair("Telephone", paramPhone);
                BasicNameValuePair Dob = new BasicNameValuePair("DOB", paramDob);
                BasicNameValuePair Sex = new BasicNameValuePair("Gender", paramSex);
                BasicNameValuePair BranchCode = new BasicNameValuePair("BranchCode", paramBranchCode);
                BasicNameValuePair ProductCode = new BasicNameValuePair("ProductCode", paramProductCode);
                BasicNameValuePair Occupation = new BasicNameValuePair("Occupation", paramOccupation);
                BasicNameValuePair AccountOfficer = new BasicNameValuePair("AccountOfficer", paramAccountOfficer);
                BasicNameValuePair PlaceOfBirth = new BasicNameValuePair("BankFIID", paramPlaceOfBirth);
                BasicNameValuePair SignatureCaptured = new BasicNameValuePair("SignatureCaptured", paramSignImage);
                BasicNameValuePair CameraCaptured = new BasicNameValuePair("CameraCaptured", paramPictureSnap);
                BasicNameValuePair AccountType = new BasicNameValuePair("City", paramAccountType);







                // We add the content that we want to pass with the POST request to as name-value pairs
                //Now we put those sending details to an ArrayList with type safe of NameValuePair
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                nameValuePairList.add(InstitutionID);
                nameValuePairList.add(TerminalID);
                nameValuePairList.add(Title);
                nameValuePairList.add(FirstName);
                nameValuePairList.add(LastName);
                nameValuePairList.add(OtherName);
                nameValuePairList.add(Address);
                nameValuePairList.add(AddressTown);
                nameValuePairList.add(Email);
                nameValuePairList.add(Phone);
                nameValuePairList.add(Dob);
                nameValuePairList.add(Sex);
                nameValuePairList.add(BranchCode);
                nameValuePairList.add(ProductCode);
                nameValuePairList.add(Occupation);
                nameValuePairList.add(AccountOfficer);
                nameValuePairList.add(PlaceOfBirth);
                nameValuePairList.add(SignatureCaptured);
                nameValuePairList.add(CameraCaptured);
                nameValuePairList.add(AccountType);


                try {
                    // UrlEncodedFormEntity is an entity composed of a list of url-encoded pairs.
                    //This is typically useful while sending an HTTP POST request.
                    UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);

                    // setEntity() hands the entity (here it is urlEncodedFormEntity) to the request.
                    httpPost.setEntity(urlEncodedFormEntity);

                    try {
                        // HttpResponse is an interface just like HttpPost.
                        //Therefore we can't initialize them
                        HttpResponse httpResponse = httpClient.execute(httpPost);

                        // According to the JAVA API, InputStream constructor do nothing.
                        //So we can't initialize InputStream although it is not an interface
                        InputStream inputStream = httpResponse.getEntity().getContent();

                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                        StringBuilder stringBuilder = new StringBuilder();

                        String bufferedStrChunk = null;

                        while((bufferedStrChunk = bufferedReader.readLine()) != null){
                            stringBuilder.append(bufferedStrChunk);
                        }

                        return stringBuilder.toString();

                    } catch (ClientProtocolException cpe) {
                        System.out.println("Firstption caz of HttpResponese :" + cpe);
                        cpe.printStackTrace();
                    } catch (IOException ioe) {
                        System.out.println("Secondption caz of HttpResponse :" + ioe);
                        ioe.printStackTrace();
                    }

                } catch (UnsupportedEncodingException uee) {
                    System.out.println("Anption given because of UrlEncodedFormEntity argument :" + uee);
                    uee.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.w("cAccount", "result is: " + result);

                if(result.equals("-1")){
                    Toast.makeText(getApplicationContext(), "HTTP POST is working...", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid POST req...", Toast.LENGTH_LONG).show();
                    Log.w("dACcount", "result is: " + result);
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(givenInstitutionID, givenTerminalID, givenFirstName,givenLastName,givenOtherName, givenAddress,givenAddressTown, givenEmail,givenPhone,givenDob,givenSex,givenBranchCode,givenProductCode,givenOccupation,givenAccountOfficer,givenPlaceOfBirth, signImage,pictureSnap, givenTitle, givenAccountType);
    }

    //FirstName={FirstName}&LastName={LastName}&OtherName={OtherName}&Address={Address}
    // &AddressTown={AddressTown}&Email={Email}&Phone={Phone}&Dob={Dob}&Sex={Sex}&BranchCode={BranchCode}
    // &ProductCode={ProductCode}&Occupation={Occupation}&AccountOfficer={AccountOfficer}
    // &InstitutionID={InstitutionID}&TerminalID={TerminalID}&BankFiid={BankFiid}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class takeButtonClicker implements Button.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);
        }
    }


    class signMe implements Button.OnClickListener{


            @Override
            public void onClick(View v) {
                Intent signIntent = new Intent(Register.this, Biometric.class);
                startActivity(signIntent);
            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST)
        {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            takenImage.setImageBitmap(thumbnail);
        }

    }
}
