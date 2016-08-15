package com.afree.ctracker.config;

import com.afree.ctracker.network.ICrashSender;

import java.util.Map;

/**
 * @author afree on 8/7/16.
 */
public interface IConfiguration {


  String getUri();

  int getConnectionTimeout();

  int getReadTimeout();

  Map<String, String> getHeader();

  ICrashSender getCrashSender();
}
