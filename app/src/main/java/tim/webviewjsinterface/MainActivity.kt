package tim.webviewjsinterface

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WebView.setWebContentsDebuggingEnabled(true)

        val viewGroup = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL

            val webView = WebView(this@MainActivity).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)

                    }
                }
                addJavascriptInterface(JavaScriptInterface(), "Android")
                loadUrl("file:///android_asset/index.html")
            }


            val button = Button(this@MainActivity).apply {
                text = "Android call JS"
                setOnClickListener {
                    webView.evaluateJavascript("hello('PaymentToken', 'CosmileToken', 'Prime')", null)
                }
            }

            addView(button)

            addView(webView, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            ))

        }
        setContentView(viewGroup)
    }
}

class JavaScriptInterface {
    @JavascriptInterface
    fun jsCallAndroid1(s: String) {
        Log.d("TAG", "jsCallAndroid1$s")
    }

    @JavascriptInterface
    fun jsCallAndroid2(paymentToken: String, cosmileToken: String, prime: String) {
        Log.d("TAG", "jsCallAndroid2 paymentToken$paymentToken")
        Log.d("TAG", "jsCallAndroid2 cosmileToken$cosmileToken")
        Log.d("TAG", "jsCallAndroid2 prime$prime")
    }
}