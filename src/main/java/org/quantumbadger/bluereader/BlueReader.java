/*******************************************************************************
 * This file is part of BlueReader.
 *
 * BlueReader is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BlueReader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BlueReader.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package org.quantumbadger.bluereader;

import android.app.Application;
import android.os.Environment;
import android.util.Log;
import org.quantumbadger.bluereader.cache.CacheManager;
import org.quantumbadger.bluereader.common.Alarms;
import org.quantumbadger.bluereader.io.RedditChangeDataIO;
import org.quantumbadger.bluereader.receivers.NewMessageChecker;
import org.quantumbadger.bluereader.reddit.prepared.RedditChangeDataManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.UUID;

public class BlueReader extends Application {

	@Override
	public void onCreate() {

		super.onCreate();

		Log.i("BlueReader", "Application created.");

		final Thread.UncaughtExceptionHandler androidHandler = Thread.getDefaultUncaughtExceptionHandler();

		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread thread, Throwable t) {

				try {
					t.printStackTrace();

					File dir = Environment.getExternalStorageDirectory();

					if(dir == null) {
						dir = Environment.getDataDirectory();
					}

					final FileOutputStream fos = new FileOutputStream(new File(dir, "BlueReader_crash_log_" + UUID.randomUUID().toString() + ".txt"));
					final PrintWriter pw = new PrintWriter(fos);
					t.printStackTrace(pw);
					pw.flush();
					pw.close();

				} catch(Throwable t1) {}

				androidHandler.uncaughtException(thread, t);
			}
		});

		final CacheManager cm = CacheManager.getInstance(this);

		new Thread() {
			@Override
			public void run() {

				android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

				cm.pruneTemp();
				cm.pruneCache(); // Hope for the best :)
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				RedditChangeDataIO.getInstance(BlueReader.this).runInitialReadInThisThread();
				RedditChangeDataManager.pruneAllUsers(BlueReader.this);
			}
		}.start();

		Alarms.onBoot(this);

		NewMessageChecker.checkForNewMessages(this);
	}
}
