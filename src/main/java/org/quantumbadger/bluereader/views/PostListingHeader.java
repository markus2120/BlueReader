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

package org.quantumbadger.bluereader.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.quantumbadger.bluereader.R;
import org.quantumbadger.bluereader.common.General;


public final class PostListingHeader extends LinearLayout {

	public PostListingHeader(final Context context, final String titleText, final String subtitleText) {

		super(context);

		{
			final TypedArray appearance = context.obtainStyledAttributes(new int[]{
					R.attr.rrPostListHeaderBackgroundCol});

			setBackgroundColor(appearance.getColor(0, General.COLOR_INVALID));

			appearance.recycle();
		}

		final float dpScale = context.getResources().getDisplayMetrics().density;

		setOrientation(LinearLayout.VERTICAL);

		final int sidesPadding = (int)(15.0f * dpScale);
		final int topPadding = (int)(10.0f * dpScale);

		setPadding(sidesPadding, topPadding, sidesPadding, topPadding);

		final Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");

		final TextView title = new TextView(context);
		title.setText(titleText);
		title.setTextSize(22.0f);
		title.setTypeface(tf);
		title.setTextColor(Color.WHITE);
		addView(title);

		final TextView subtitle = new TextView(context);
		subtitle.setTextSize(14.0f);
		subtitle.setText(subtitleText);
		subtitle.setTextColor(Color.rgb(200, 200, 200));
		addView(subtitle);
	}
}
