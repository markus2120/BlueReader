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

package org.quantumbadger.bluereader.views.liststatus;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.quantumbadger.bluereader.R;
import org.quantumbadger.bluereader.common.LinkHandler;
import org.quantumbadger.bluereader.reddit.url.PostCommentListingURL;

public final class CommentSubThreadView extends StatusListItemView {

	private final PostCommentListingURL mUrl;

	public CommentSubThreadView(
			final AppCompatActivity activity,
			final PostCommentListingURL url,
			int messageRes) {

		super(activity);

		mUrl = url;

		final TypedArray attr = activity.obtainStyledAttributes(new int[] {
				R.attr.rrCommentSpecificThreadHeaderBackCol,
				R.attr.rrCommentSpecificThreadHeaderTextCol
		});

		final int rrCommentSpecificThreadHeaderBackCol = attr.getColor(0, 0);
		final int rrCommentSpecificThreadHeaderTextCol = attr.getColor(1, 0);

		attr.recycle();

		final TextView textView = new TextView(activity);
		textView.setText(messageRes);
		textView.setTextColor(rrCommentSpecificThreadHeaderTextCol);
		textView.setTextSize(15.0f);
		textView.setPadding((int) (15 * dpScale), (int) (10 * dpScale), (int) (10 * dpScale), (int) (4 * dpScale));

		final TextView messageView = new TextView(activity);
		messageView.setText(R.string.comment_header_specific_thread_message);
		messageView.setTextColor(rrCommentSpecificThreadHeaderTextCol);
		messageView.setTextSize(12.0f);
		messageView.setPadding((int) (15 * dpScale), 0, (int) (10 * dpScale), (int) (10 * dpScale));

		final LinearLayout layout = new LinearLayout(activity);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(textView);
		layout.addView(messageView);

		setContents(layout);
		setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

		setBackgroundColor(rrCommentSpecificThreadHeaderBackCol);

		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				final PostCommentListingURL allComments = mUrl.commentId(null);
				LinkHandler.onLinkClicked(activity, allComments.toString());
			}
		});
	}

}
