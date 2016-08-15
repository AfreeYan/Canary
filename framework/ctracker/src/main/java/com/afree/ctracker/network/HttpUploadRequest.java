package com.afree.ctracker.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afree.ctracker.utils.IOUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author afree on 8/15/16.
 */
public class HttpUploadRequest {
  private static final String CONTENT_TYPE = "application/json";
  private static final String METHOD_POST = "POST";
  private static final String UTF8 = "UTF-8";

  private static final int HTTP_SUCCESS = 200;
  private static final int HTTP_REDIRECT = 300;
  private int mConnectionTimeOut = 6000;
  private int mReadTimeOut = 30000;
  private Map<String, String> mHeaders;


  public void upload(@NonNull Context context, @NonNull URL url, @NonNull File file) throws IOException {

    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

    if (urlConnection instanceof HttpsURLConnection) {
      configHttpsUrlConnection(urlConnection);
    }

    urlConnection.setConnectTimeout(mConnectionTimeOut);
    urlConnection.setReadTimeout(mReadTimeOut);

    // set headers
    urlConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
    if (mHeaders != null) {
      for (final Map.Entry<String, String> header : mHeaders.entrySet()) {
        urlConnection.setRequestProperty(header.getKey(), header.getValue());
      }
    }
    urlConnection.setRequestMethod(METHOD_POST);
    urlConnection.setDoOutput(true);

    urlConnection.connect();
    OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
    writeToSteam(file, outputStream);

    int responseCode = urlConnection.getResponseCode();
    if ((responseCode >= HTTP_SUCCESS) && (responseCode < HTTP_REDIRECT)) {
      // TODO: 8/15/16
    } else {
      throw new RuntimeException("http request failed when upload to service");
    }


    urlConnection.disconnect();

  }

  private void writeToSteam(@NonNull File file, OutputStream outputStream) throws IOException {
    int bufLen = 1024;
    byte[] buf = new byte[bufLen];
    FileInputStream fi = new FileInputStream(file);
    try {
      while (true) {
        int count = fi.read(buf, 0, bufLen);
        if (count <= 0) {
          break;
        }
        outputStream.write(buf, 0, count);
      }
      outputStream.flush();
    } finally {
      IOUtils.safeClose(fi);
    }
  }


  // TODO: 8/15/16
  private void configHttpsUrlConnection(HttpURLConnection urlConnection) {

  }

  public void setConnectionTimeOut(int connectionTimeOut) {
    this.mConnectionTimeOut = connectionTimeOut;
  }

  public void setHeaders(Map<String, String> headers) {
    this.mHeaders = headers;
  }

  public void setReadTimeOut(int readTimeOut) {
    this.mReadTimeOut = readTimeOut;
  }


}
