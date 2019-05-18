package org.quantumbadger.bluereader.common;

import android.os.Handler;
import android.os.Looper;

public class AndroidCommon {
	public static final Handler UI_THREAD_HANDLER = new Handler(Looper.getMainLooper());
}
