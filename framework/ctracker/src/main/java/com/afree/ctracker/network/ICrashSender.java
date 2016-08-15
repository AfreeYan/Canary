package com.afree.ctracker.network;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * interface for sending crash data to service
 *
 * @author afree on 8/15/16.
 */
public interface ICrashSender {

  void send(@NonNull Context context, @NonNull File data);
}
