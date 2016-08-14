package com.afree.ctracker.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.android.internal.util.Predicate;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author afree on 8/15/16.
 */
public class IOUtils {
  public static final int DEFAULT_BUFFER_SIZE_IN_BYTES = 8192;

  private static final Predicate<String> DEFAULT_FILTER = new Predicate<String>() {
    @Override
    public boolean apply(String s) {
      return true;
    }
  };
  private static final int NO_LIMIT = -1;

  private IOUtils() {
  }


  /**
   * Closes a Closeable.
   *
   * @param closeable Closeable to close. If closeable is null then method just returns.
   */
  public static void safeClose(@Nullable Closeable closeable) {
    if (closeable == null) return;

    try {
      closeable.close();
    } catch (IOException ignored) {
      // We made out best effort to release this resource. Nothing more we can do.
    }
  }

  /**
   * Reads an InputStream into a string
   *
   * @param input the stream
   * @return the read string
   * @throws IOException
   */
  @NonNull
  public static String streamToString(@NonNull InputStream input) throws IOException {
    return streamToString(input, DEFAULT_FILTER);
  }


  /**
   * Reads an InputStream into a string
   *
   * @param input  the stream
   * @param filter should return false for lines which should be excluded
   * @return the read string
   * @throws IOException
   */
  @NonNull
  public static String streamToString(@NonNull InputStream input, Predicate<String> filter) throws IOException {
    final BufferedReader reader = new BufferedReader(new InputStreamReader(input), DEFAULT_BUFFER_SIZE_IN_BYTES);
    try {
      String line;
      final List<String> buffer = new LinkedList<>();
      while ((line = reader.readLine()) != null) {
        if (filter.apply(line)) {
          buffer.add(line);
        }
      }
      return TextUtils.join("\n", buffer);
    } finally {
      safeClose(reader);
    }
  }

}
