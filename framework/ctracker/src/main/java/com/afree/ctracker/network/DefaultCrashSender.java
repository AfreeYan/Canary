package com.afree.ctracker.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afree.ctracker.config.IConfiguration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author afree on 8/15/16.
 */
public class DefaultCrashSender implements ICrashSender {
  private IConfiguration mConfig;

  public DefaultCrashSender(IConfiguration configuration) {
    mConfig = configuration;
  }

  @Override
  public void send(@NonNull Context context, @NonNull File data) {

    try {
      URL url = new URL(mConfig.getUri());

      HttpUploadRequest request = new HttpUploadRequest();
      request.setConnectionTimeOut(mConfig.getConnectionTimeout());
      request.setReadTimeOut(mConfig.getReadTimeout());
      request.setHeaders(mConfig.getHeader());

      request.upload(context, url, data);
    } catch (IOException e) {
      throw new RuntimeException("IOException occur on sending data to service");
    }
  }
}
