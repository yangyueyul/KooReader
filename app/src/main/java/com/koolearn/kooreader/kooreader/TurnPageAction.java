/*
 * Copyright (C) 2007-2015 FBReader.ORG Limited <contact@fbreader.org>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package com.koolearn.kooreader.kooreader;

import com.koolearn.kooreader.kooreader.options.PageTurningOptions;

class TurnPageAction extends KooAction {
	private final boolean myForward;

	TurnPageAction(KooReaderApp kooreader, boolean forward) {
		super(kooreader);
		myForward = forward;
	}

	@Override
	public boolean isEnabled() {
		final PageTurningOptions.FingerScrollingType fingerScrolling =
			Reader.PageTurningOptions.FingerScrolling.getValue();
		return
			fingerScrolling == PageTurningOptions.FingerScrollingType.byTap ||
			fingerScrolling == PageTurningOptions.FingerScrollingType.byTapAndFlick;
	}

	@Override
	protected void run(Object ... params) {
		final PageTurningOptions preferences = Reader.PageTurningOptions;
		if (params.length == 2 && params[0] instanceof Integer && params[1] instanceof Integer) {
			final int x = (Integer)params[0];
			final int y = (Integer)params[1];
			Reader.getViewWidget().startAnimatedScrolling(
				myForward ? KooView.PageIndex.next : KooView.PageIndex.previous,
				x, y,
				preferences.Horizontal.getValue()
					? KooView.Direction.rightToLeft : KooView.Direction.up,
				preferences.AnimationSpeed.getValue()
			);
		} else {
			Reader.getViewWidget().startAnimatedScrolling(
				myForward ? KooView.PageIndex.next : KooView.PageIndex.previous,
				preferences.Horizontal.getValue()
					? KooView.Direction.rightToLeft : KooView.Direction.up,
				preferences.AnimationSpeed.getValue()
			);
		}
	}
}
