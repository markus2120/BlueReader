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

package org.quantumbadger.bluereader.reddit.api;

import android.annotation.SuppressLint;
import android.content.Context;

import org.quantumbadger.bluereader.cache.CacheRequest;
import org.quantumbadger.bluereader.common.General;
import org.quantumbadger.bluereader.common.RRError;

import java.net.URI;

public class SubredditRequestFailure {
	public final @CacheRequest.RequestFailureType int requestFailureType;
	public final Throwable t;
	public final Integer statusLine;
	public final String readableMessage;
	public final String url;

	public SubredditRequestFailure(@CacheRequest.RequestFailureType int requestFailureType, Throwable t,
								   Integer statusLine, String readableMessage, String url) {
		this.requestFailureType = requestFailureType;
		this.t = t;
		this.statusLine = statusLine;
		this.readableMessage = readableMessage;
		this.url = url;
	}

	public SubredditRequestFailure(@CacheRequest.RequestFailureType int requestFailureType, Throwable t,
								   Integer statusLine, String readableMessage, URI url) {
		this(requestFailureType, t, statusLine, readableMessage, url != null ? url.toString() : null);
	}

	@SuppressLint("WrongConstant")
	public RRError asError(Context context) {
		return General.getGeneralErrorForFailure(context, requestFailureType, t, statusLine, url);
	}
}
