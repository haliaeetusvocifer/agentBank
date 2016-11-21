package com.lotusbeta.agentbank;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class review extends AppCompatActivity {
    TextView tvFullname;
    RelativeLayout rlDetailsReview;
    ImageView imSignature;
    RelativeLayout rlSignCanvas;
    ImageView takenImage;
    private static final int CAM_REQUEST = 1313;


    TextView Title;
    TextView Surname;
    TextView Firstname;
    TextView Othername;
    TextView Gender;
    TextView PlaceOfBirth;
    TextView dob;
    TextView HomeAddress;
    TextView StateOfOrigin;
    TextView Lga;
    TextView Telephone;
    TextView Email;
    TextView Occupation;
    TextView AccountServices;
    TextView AccountType;




    ImageView signShow;
    Bitmap bm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();

        //Extract the data…

        final String givenLga = "LGA: "+bundle.getString("Lga");
        final String givenTitle = "Title: "+bundle.getString("Title");
        final String givenFirstName = "FirstName: "+bundle.getString("FirstName");
        final String givenLastName = "Lastname: "+bundle.getString("Surname");
        final String givenOtherName = "Othername: "+bundle.getString("OtherName");
        final String givenAddress = "Home Address: "+bundle.getString("HomeAddress");
        final String givenAddressTown = "State of Origin: "+bundle.getString("StateOfOrigin");
        final String givenEmail = "Email: "+bundle.getString("Email");
        final String givenPhone = "Telephone: "+bundle.getString("Telephone");
        final String givenDob = "DOB: "+bundle.getString("Dob");
        final String givenSex = "Gender: "+bundle.getString("Gender");
        final String givenProductCode = "Account Services: "+bundle.getString("AccountServices");
        final String givenOccupation = "Occupation: "+bundle.getString("Occupation");
        final String givenPlaceOfBirth = "Place of Birth:"+bundle.getString("PlaceOfBirth");
        final String givenAccountType = "Type: "+bundle.getString("AccountType");
        final String givenInstitutionID = "001";
        final String givenTerminalID = "001";
        final String givenBranchCode = "001";
        final String givenAccountOfficer = "001";







        Title = (TextView) findViewById(R.id.Title);
        Title.setText(givenTitle);
        Surname = (TextView) findViewById(R.id.surname);
        Surname.setText(givenLastName);
        Firstname = (TextView) findViewById(R.id.firstname);
        Firstname.setText(givenFirstName);
        Othername = (TextView) findViewById(R.id.othername);
        Othername.setText(givenOtherName);
        Gender = (TextView) findViewById(R.id.genderReview);
        Gender.setText(givenSex);
        PlaceOfBirth = (TextView) findViewById(R.id.placeOfBirthReview);
        PlaceOfBirth.setText(givenPlaceOfBirth);
        dob = (TextView) findViewById(R.id.dobReview);
        dob.setText(givenDob);
        HomeAddress = (TextView) findViewById(R.id.addressReview);
        HomeAddress.setText(givenAddress);
        StateOfOrigin = (TextView) findViewById(R.id.stateOfOriginReview);
        StateOfOrigin.setText(givenAddressTown);
        Lga = (TextView) findViewById(R.id.lgaReview);
        Lga.setText(givenLga);
        Telephone = (TextView) findViewById(R.id.telephoneReview);
        Telephone.setText(givenPhone);
        Email = (TextView) findViewById(R.id.emailReview);
        Email.setText(givenEmail);
        Occupation = (TextView) findViewById(R.id.occupationReview);
        Occupation.setText(givenOccupation);
        AccountServices = (TextView) findViewById(R.id.accountServicesReview);
        AccountServices.setText(givenProductCode);
        AccountType = (TextView) findViewById(R.id.accountTypeReview);
        AccountType.setText(givenAccountType);





//        tvFullname = (TextView)findViewById(R.id.fullname);
        rlDetailsReview= (RelativeLayout)findViewById(R.id.detailsReview);
        rlSignCanvas=(RelativeLayout)findViewById(R.id.signCanvas);
        takenImage = (ImageView) findViewById(R.id.pictureTaken);


        //takeButton.setOnClickListener(new takeButtonClicker());
        takenImage.setOnClickListener(new takeButtonClicker());

        imSignature = (ImageView)findViewById(R.id.signature);
        imSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                rlSignCanvas.setVisibility(View.VISIBLE);

                /*Intent intent = new Intent(review.this, menu.class);
                startActivity(intent);*/


            }
        });

        Button sendDetails = (Button) findViewById(R.id.disappear);
        sendDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                String givenLga = bundle.getString("Lga");
                String givenTitle = bundle.getString("Title");
                String givenFirstName = bundle.getString("FirstName");
                String givenLastName = bundle.getString("Surname");
                String givenOtherName = bundle.getString("OtherName");
                String givenAddress = bundle.getString("HomeAddress");
                String givenAddressTown = bundle.getString("StateOfOrigin");
                String givenEmail = bundle.getString("Email");
                String givenPhone = bundle.getString("Telephone");
                String givenDob = bundle.getString("Dob");
                String givenSex = bundle.getString("Gender");
                String givenProductCode = bundle.getString("AccountServices");
                String givenOccupation = bundle.getString("Occupation");
                String givenPlaceOfBirth = bundle.getString("PlaceOfBirth");
                String givenAccountType = bundle.getString("AccountType");
                String givenInstitutionID = "001";
                String givenTerminalID = "001";
                String givenBranchCode = "001";
                String givenAccountOfficer = "001";

                imSignature = (ImageView)findViewById(R.id.signature);
                BitmapDrawable drawable = (BitmapDrawable) imSignature.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
                byte[] bb = bos.toByteArray();
                String signImage = Base64.encodeToString(bb, 1);

                takenImage = (ImageView) findViewById(R.id.pictureTaken);
                BitmapDrawable pictureDrawable = (BitmapDrawable) takenImage.getDrawable();
                Bitmap pictureBitmap = pictureDrawable.getBitmap();
                ByteArrayOutputStream bosPicture = new ByteArrayOutputStream();
                pictureBitmap.compress(Bitmap.CompressFormat.PNG, 100, bosPicture);
                byte[] pp = bosPicture.toByteArray();
                String pictureSnap = Base64.encodeToString(pp,1);






                //System.out.println("Givenname :" + givenUsername + " Given password :" + givenPassword);

                sendPostRequest(givenInstitutionID, givenTerminalID, givenFirstName, givenLastName, givenOtherName, givenAddress, givenAddressTown, givenEmail, givenPhone, givenDob, givenSex, givenBranchCode, givenProductCode, givenOccupation, givenAccountOfficer, givenPlaceOfBirth, signImage, pictureSnap, givenTitle, givenAccountType);


                Intent intent = new Intent(review.this, menu.class);
                startActivity(intent);

            }
        });





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
                BasicNameValuePair Title= new BasicNameValuePair("title", paramTitle);
                BasicNameValuePair FirstName = new BasicNameValuePair("firstname", paramFirstName);
                BasicNameValuePair LastName = new BasicNameValuePair("surname", paramLastName);
                BasicNameValuePair OtherName = new BasicNameValuePair("middlename", paramOtherName);
                BasicNameValuePair Address = new BasicNameValuePair("homeAddress", paramAddress);
                BasicNameValuePair AddressTown = new BasicNameValuePair("Region", paramAddressTown);
                BasicNameValuePair Email = new BasicNameValuePair("emailAddress", paramEmail);
                BasicNameValuePair Phone = new BasicNameValuePair("phoneNumber", paramPhone);
                BasicNameValuePair Dob = new BasicNameValuePair("dateOfBirth", paramDob);
                BasicNameValuePair Sex = new BasicNameValuePair("gender", paramSex);
                BasicNameValuePair BranchCode = new BasicNameValuePair("BranchCode", paramBranchCode);
                BasicNameValuePair ProductCode = new BasicNameValuePair("ProductCode", paramProductCode);
                BasicNameValuePair Occupation = new BasicNameValuePair("Occupation", paramOccupation);
                BasicNameValuePair AccountOfficer = new BasicNameValuePair("AccountOfficer", paramAccountOfficer);
                BasicNameValuePair PlaceOfBirth = new BasicNameValuePair("BankFIID", paramPlaceOfBirth);
                BasicNameValuePair SignatureCaptured = new BasicNameValuePair("SignatureCaptured", paramSignImage);
                BasicNameValuePair CameraCaptured = new BasicNameValuePair("passportPath", paramPictureSnap);
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
                    Toast.makeText(getApplicationContext(), "Congratulations, the account has been created.", Toast.LENGTH_LONG).show();
                    Log.w("dACcount", "result is: " + result);
                }
            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(givenInstitutionID, givenTerminalID, givenFirstName,givenLastName,givenOtherName, givenAddress,givenAddressTown, givenEmail,givenPhone,givenDob,givenSex,givenBranchCode,givenProductCode,givenOccupation,givenAccountOfficer,givenPlaceOfBirth, signImage,pictureSnap, givenTitle, givenAccountType);
    }


    public void saveSig(View view) {

        try {

            GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.signaturePad);

            gestureView.setDrawingCacheEnabled(true);

            Bitmap bm = Bitmap.createBitmap(gestureView.getDrawingCache());

            //code to pass image to intent
            /*ByteArrayOutputStream bstream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, bstream);
            byte[] byteArray = bstream.toByteArray();
            Intent signIntent = new Intent();
            signIntent.setClass(Biometric.this, Register.class);
            signIntent.putExtra("Bitmap", byteArray);
            startActivity(signIntent);
            finish();*/
            //end of code


            File f = new File(Environment.getExternalStorageDirectory()

                    + File.separator + "signature.png");

            f.createNewFile();

            FileOutputStream os;

            os = new FileOutputStream(f);

            //compress to specified format (PNG), quality - which is ignored for PNG, and out stream

            bm.compress(Bitmap.CompressFormat.PNG, 100, os);


            Intent signIntent = new Intent();
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 50, bs);
            /*signIntent.setClass(review.this, Register.class);
            signIntent.putExtra("Bitmap", bs.toByteArray());
            startActivity(signIntent);*/

            os.close();
            ImageView signing = (ImageView)findViewById(R.id.signature);
            signing.setImageBitmap(bm);








        } catch (Exception e) {

            Log.w("Gestures", e.getMessage());

            e.printStackTrace();

        }

        rlSignCanvas.setVisibility(View.INVISIBLE);

    }

    class takeButtonClicker implements Button.OnClickListener
    {

        @Override
        public void onClick(View v) {
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraintent, CAM_REQUEST);
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
