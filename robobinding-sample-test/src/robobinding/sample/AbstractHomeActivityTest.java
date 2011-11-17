/**
 * Copyright 2011 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package robobinding.sample;

import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 */
public abstract class AbstractHomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity>
{
	protected Solo solo;

	public AbstractHomeActivityTest()
	{
		super("robobinding.sample", HomeActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testCreatingAndEditingAnAlbum()
	{
		clickOnButtonWithLabel(homeButtonStringResId());
		
		clickOnButtonWithLabel(R.string.create);
		
		solo.enterText(0, "Album name");
		solo.enterText(1, "Artist name");
		solo.clickOnCheckBox(0);
		solo.enterText(2, "Composer name");
		
		clickOnButtonWithLabel(R.string.save);
		
		assertTrue(solo.searchText("Album name"));
		assertTrue(solo.searchText("Artist name"));
		
		solo.clickOnText("Album name");
		
		assertTrue(solo.searchText("Classical"));
		assertTrue(solo.searchText("Composer name"));
		
		clickOnButtonWithLabel(R.string.edit);
		
		assertTrue(solo.isCheckBoxChecked(0));
		assertTrue(solo.searchEditText("Composer name"));
		
		solo.clearEditText(0);
		solo.enterText(0, "New album name");
		
		assertTrue(solo.searchText("Edit Classical Album"));
		solo.clickOnCheckBox(0);
		assertTrue(solo.searchText("Edit Album"));
		
		clickOnButtonWithLabel(R.string.save);
		
		assertTrue(solo.searchText("New album name"));
		assertTrue(solo.searchText("Not classical"));
		
		solo.goBack();
		
		assertTrue(solo.searchText("New album name"));
	}

	void clickOnButtonWithLabel(String label)
	{
		solo.clickOnButton(label);
	}

	void clickOnButtonWithLabel(int resId)
	{
		clickOnButtonWithLabel(getString(resId));
	}
	
	private String getString(int resId)
	{
		return getActivity().getString(resId);
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
		solo.finishOpenedActivities();
	}

	protected abstract int homeButtonStringResId();

}
