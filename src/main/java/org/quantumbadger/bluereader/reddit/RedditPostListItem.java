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

package org.quantumbadger.bluereader.reddit;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import org.quantumbadger.bluereader.adapters.GroupedRecyclerViewAdapter;
import org.quantumbadger.bluereader.fragments.PostListingFragment;
import org.quantumbadger.bluereader.reddit.prepared.RedditPreparedPost;
import org.quantumbadger.bluereader.views.RedditPostView;

public class RedditPostListItem extends GroupedRecyclerViewAdapter.Item {

	private final PostListingFragment mFragment;
	private final AppCompatActivity mActivity;

	private final RedditPreparedPost mPost;
	private final boolean mLeftHandedMode;

	public RedditPostListItem(
			final RedditPreparedPost post,
			final PostListingFragment fragment,
			final AppCompatActivity activity,
			final boolean leftHandedMode) {

		mFragment = fragment;
		mActivity = activity;
		mPost = post;
		mLeftHandedMode = leftHandedMode;
	}

	@Override
	public Class getViewType() {
		return RedditPostView.class;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup) {

		final RedditPostView view = new RedditPostView(
				mActivity,
				mFragment,
				mActivity,
				mLeftHandedMode);

		return new RecyclerView.ViewHolder(view) {};
	}

	@Override
	public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder) {
		((RedditPostView)viewHolder.itemView).reset(mPost);
	}

	@Override
	public boolean isHidden() {
		return false;
	}

}
