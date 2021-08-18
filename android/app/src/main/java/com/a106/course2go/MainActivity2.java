//package com.a106.course2go;
//
//import android.content.DialogInterface;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.webkit.JsResult;
//import android.webkit.ValueCallback;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class MainActivity2 extends AppCompatActivity {
//    private WebView webView;
//
//    private WebSettings mWebSettings;
//
//    public ValueCallback<Uri> filePathCallbackNormal;
//    public ValueCallback<Uri[]> filePathCallbackLollipop;
//    public final static int FILECHOOSER_NORMAL_REQ_CODE = 2001;
//    public final static int FILECHOOSER_LOLLIPOP_REQ_CODE = 2002;
//    private Uri cameraImageUri = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        webView = findViewById(R.id.webView);
//        webView.setWebViewClient(new WebViewClient());
//
//        webView.setWebChromeClient(new WebChromeClient(){
//            //웹뷰에 alert창에 url을 제거한다.
//            public boolean onJsAlert(WebView view, String url, String message, final JsResult result)
//            {
//                new AlertDialog.Builder(MainActivity2.this)
//                        .setTitle("")
//                        .setMessage(message)
//                        .setPositiveButton(android.R.string.ok,
//                                new AlertDialog.OnClickListener()
//                                {
//                                    public void onClick(DialogInterface dialog, int which)
//                                    {
//                                        result.confirm();
//                                    }
//                                })
//                        .setCancelable(false)
//                        .create()
//                        .show();
//
//                return true;
//            }
//
//            //웹뷰에 Confirm창에 url을 제거한다.
//            @Override
//            public boolean onJsConfirm(WebView view, String url,
//                                       String message, final JsResult result) {
//
//                new AlertDialog.Builder(MainActivity2.this)
//                        .setTitle("")
//                        .setMessage(message)
//                        .setPositiveButton(android.R.string.ok,
//                                new DialogInterface.OnClickListener() {
//
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        result.confirm();
//                                    }
//                                })
//                        .setNegativeButton(android.R.string.cancel,
//                                new DialogInterface.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        result.cancel();
//                                    }
//                                })
//                        .create()
//                        .show();
//                return true;
//            }
//
//            // For Android 5.0+ 카메라 - input type="file" 태그를 선택했을 때 반응
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            public boolean onShowFileChooser(
//                    WebView webView, ValueCallback<Uri[]> filePathCallback,
//                    FileChooserParams fileChooserParams) {
//                Log.d("MainActivity", "5.0+");
//
//                // Callback 초기화 (중요!)
//                if (filePathCallbackLollipop != null) {
//                    filePathCallbackLollipop.onReceiveValue(null);
//                    filePathCallbackLollipop = null;
//                }
//                filePathCallbackLollipop = filePathCallback;
//
//                boolean isCapture = fileChooserParams.isCaptureEnabled();
//                runCamera(isCapture);
//                return true;
//            }
//
//        });
//
//
//
//
//
//
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//
//        webView.loadUrl("http://www.course2go.site/");
//    }
//
//
//}