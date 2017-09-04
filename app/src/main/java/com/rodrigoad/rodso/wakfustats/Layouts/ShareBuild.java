package com.rodrigoad.rodso.wakfustats.Layouts;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rodrigoad.rodso.wakfustats.R;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.rodrigoad.rodso.wakfustats.AnalyticsApplication;
import com.rodrigoad.rodso.wakfustats.ShareBuildAcitivy;
import com.rodrigoad.rodso.wakfustats.Utils.Build;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShareBuild.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShareBuild#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShareBuild extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=2;
    private String mParam1;
    private String mParam2;
    private Build build;
    private ProgressDialog load;
    public void setBuild(Build build){ this.build=build; }
    private View view;
    private ImageView QRCode;
    private TextView name;
    private TextView level;
    private TextView classe;
    private ImageView ele1;
    private ImageView ele2;
    private ImageView ele3;
    private ImageView ele4;
    private ImageView res1;
    private ImageView res2;
    private ImageView res3;
    private ImageView res4;
    private FloatingActionButton shareIntent;
    private FloatingActionButton save;
    private TextView share_date;
    private Tracker mTracker;

    private OnFragmentInteractionListener mListener;

    public ShareBuild() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShareBuild.
     */
    // TODO: Rename and change types and number of parameters
    public static ShareBuild newInstance(String param1, String param2) {
        ShareBuild fragment = new ShareBuild();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //Get a Tracker (should auto-report)
        ((AnalyticsApplication) this.getActivity().getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        mTracker = ((AnalyticsApplication) this.getActivity().getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
    }

    private class gerateQR extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            load = ProgressDialog.show(view.getContext(), "Por favor Aguarde ...",
                    "Gerando QRCode ...");
        }

        protected Bitmap doInBackground(String... strings){
            Bitmap bmpaux=null;
            Log.e("Classe:",strings[0]);
            try {
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(strings[0], BarcodeFormat.QR_CODE, 500,500);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                bmpaux = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmpaux.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }
            } catch (WriterException e) {
                e.printStackTrace();
            }
            return bmpaux;
        }

        @Override
        protected void onPostExecute(Bitmap imageView) {
            if(imageView!=null) {
                QRCode.setImageBitmap(imageView);
            }else{
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_share_build, container, false);

        QRCode = (ImageView) view.findViewById(R.id.share_qrcode);
        new gerateQR().execute(build.generateQRString());
        name = (TextView) view.findViewById(R.id.share_name);
        name.setText(build.getNome());
        level = (TextView) view.findViewById(R.id.share_level);
        level.setText(""+build.getNivel());
        classe = (TextView) view.findViewById(R.id.share_classe);
        switch (build.getClasse()){
            case 0:
                classe.setText(getResources().getString(R.string.cra));
                break;
            case 1:
                classe.setText(getResources().getString(R.string.eca));
                break;
            case 2:
                classe.setText(getResources().getString(R.string.elio));
                break;
            case 3:
                classe.setText(getResources().getString(R.string.eni));
                break;
            case 4:
                classe.setText(getResources().getString(R.string.enu));
                break;
            case 5:
                classe.setText(getResources().getString(R.string.feca));
                break;
            case 6:
                classe.setText(getResources().getString(R.string.hupp));
                break;
            case 7:
                classe.setText(getResources().getString(R.string.iop));
                break;
            case 8:
                classe.setText(getResources().getString(R.string.osa));
                break;
            case 9:
                classe.setText(getResources().getString(R.string.panda));
                break;
            case 10:
                classe.setText(getResources().getString(R.string.lad));
                break;
            case 11:
                classe.setText(getResources().getString(R.string.sac));
                break;
            case 12:
                classe.setText(getResources().getString(R.string.sad));
                break;
            case 13:
                classe.setText(getResources().getString(R.string.sram));
                break;
            case 14:
                classe.setText(getResources().getString(R.string.steamer));
                break;
            case 15:
                classe.setText(getResources().getString(R.string.xelor));
                break;
            case 16:
                classe.setText(getResources().getString(R.string.zob));
                break;
        }
        ele1 = (ImageView) view.findViewById(R.id.share_ele1);
        ele2 = (ImageView) view.findViewById(R.id.share_ele2);
        ele3 = (ImageView) view.findViewById(R.id.share_ele3);
        ele4 = (ImageView) view.findViewById(R.id.share_ele4);
        setImages(build.getElementp(),ele1,ele2,ele3,ele4);
        res1 = (ImageView) view.findViewById(R.id.share_res1);
        res2 = (ImageView) view.findViewById(R.id.share_res2);
        res3 = (ImageView) view.findViewById(R.id.share_res3);
        res4 = (ImageView) view.findViewById(R.id.share_res4);
        setImages(build.getResistp(),res1,res2,res3,res4);

        share_date = (TextView) view.findViewById(R.id.share_date);
        Calendar c = Calendar.getInstance();
        share_date.setText(c.get(Calendar.DAY_OF_MONTH)+"/"+c.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault())+"/"+c.get(Calendar.YEAR));

        shareIntent = (FloatingActionButton) view.findViewById(R.id.share_shareIntent);//fazer o share no facebook
        shareIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });
        save = (FloatingActionButton) view.findViewById(R.id.share_float_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPhoneStatePermission();
            }
        });

        return view;
    }

    private void shareImage(){
        Log.i("Vai fazer share","meu cu viu");
        View v = view.findViewById(R.id.share_image);
        Uri uri = getImageUri(view.getContext(), getBitmapFromView(v));
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));
        // Build and Send the Analytics Event.
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Action")
                .setAction("Share")
                .build());
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public  Bitmap getBitmapFromView(View v) {
        //Pre-measure the view so that height and width don't remain null.
        v.measure(View.MeasureSpec.makeMeasureSpec(v.getMeasuredWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(v.getMeasuredHeight(), View.MeasureSpec.EXACTLY));
        //Assign a size and position to the view and all of its descendants
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        // Create bitmap
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.RGB_565);

        //Create a canvas with the specified bitmap to draw into
        Canvas canvas = new Canvas(bitmap);

        // if it's scrollView we get gull size
        canvas.translate(-v.getScrollX(), -v.getScrollY());
        //Render this view (and all of its children) to the given Canvas
        v.draw(canvas);
        return bitmap;
    }

    private void showPhoneStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            saveImage();
        }
    }

    public void saveImage(){
        Toast.makeText(view.getContext(),getResources().getString(R.string.imagesaved), Toast.LENGTH_LONG).show();
        View v = view.findViewById(R.id.share_image);
        MediaStore.Images.Media.insertImage(v.getContext().getContentResolver(), getBitmapFromView(v), build.getNome(), "jpg");
    }

    public void setImages(int elementop, ImageView i1, ImageView i2, ImageView i3, ImageView i4){
        //cuida do primeiro elemento
        if(elementop>=0 && elementop <=5){
            i1.setImageDrawable(getResources().getDrawable(R.drawable.fire));
        }else if(elementop>=6 && elementop<=11){
            i1.setImageDrawable(getResources().getDrawable(R.drawable.water));
        }else if(elementop>=12 && elementop<=17){
            i1.setImageDrawable(getResources().getDrawable(R.drawable.air));
        }else if(elementop>=18 && elementop<=23){
            i1.setImageDrawable(getResources().getDrawable(R.drawable.earth));
        }

        //cuida do elemento 2
        if((elementop>=6 && elementop <=7) || (elementop>=12 && elementop <=13) || (elementop>=18 && elementop <=19)){
            i2.setImageDrawable(getResources().getDrawable(R.drawable.fire));
        }else if((elementop>=0 && elementop <=1) || (elementop>=14 && elementop <=15) || (elementop>=20 && elementop <=21)){
            i2.setImageDrawable(getResources().getDrawable(R.drawable.water));
        }else if((elementop>=2 && elementop <=3) || (elementop>=8 && elementop <=9) || (elementop>=22 && elementop <=23)){
            i2.setImageDrawable(getResources().getDrawable(R.drawable.air));
        }else if((elementop>=4 && elementop <=5) || (elementop>=10 && elementop <=11) || (elementop>=16 && elementop <=17)){
            i2.setImageDrawable(getResources().getDrawable(R.drawable.earth));
        }

        //cuida do 3° elemento
        if(elementop==8 || elementop==11 || elementop==14 || elementop==16 || elementop==21 || elementop==23){
            i3.setImageDrawable(getResources().getDrawable(R.drawable.fire));
        }else if(elementop==2 || elementop==5 || elementop==12 || elementop==17 || elementop==19 || elementop==22){
            i3.setImageDrawable(getResources().getDrawable(R.drawable.water));
        }else if(elementop==0 || elementop==4 || elementop==6 || elementop==10 || elementop==18 || elementop==20){
            i3.setImageDrawable(getResources().getDrawable(R.drawable.air));
        }else if(elementop==1 || elementop==3 || elementop==7 || elementop==9 || elementop==13 || elementop==15){
            i3.setImageDrawable(getResources().getDrawable(R.drawable.earth));
        }

        //cuida do 4° elemento
        if(elementop==9 || elementop==10 || elementop==15 || elementop==17 || elementop==20 || elementop==22){
            i4.setImageDrawable(getResources().getDrawable(R.drawable.fire));
        }else if(elementop==3 || elementop==4 || elementop==13 || elementop==16 || elementop==18 || elementop==23){
            i4.setImageDrawable(getResources().getDrawable(R.drawable.water));
        }else if(elementop==1 || elementop==5 || elementop==7 || elementop==11 || elementop==19 || elementop==21){
            i4.setImageDrawable(getResources().getDrawable(R.drawable.air));
        }else if(elementop==0 || elementop==2 || elementop==6 || elementop==8 || elementop==12 || elementop==14){
            i4.setImageDrawable(getResources().getDrawable(R.drawable.earth));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
