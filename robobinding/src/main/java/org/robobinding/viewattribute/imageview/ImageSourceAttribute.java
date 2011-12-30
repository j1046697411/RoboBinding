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
package org.robobinding.viewattribute.imageview;

import org.robobinding.viewattribute.AbstractDelegatePropertyViewAttribute;
import org.robobinding.viewattribute.AbstractReadOnlyPropertyViewAttribute;
import org.robobinding.viewattribute.PrimitiveTypeUtils;
import org.robobinding.viewattribute.PropertyViewAttribute;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 *
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 */
public class ImageSourceAttribute extends AbstractDelegatePropertyViewAttribute<ImageView>
{
	@Override
	protected PropertyViewAttribute<ImageView> lookupPropertyViewAttribute(Class<?> propertyType)
	{
		if (PrimitiveTypeUtils.integerIsAssignableFrom(propertyType))
		{
			return new IntegerImageSourceAttribute();
		}
		else if (Drawable.class.isAssignableFrom(propertyType))
		{
			return new DrawableImageSourceAttribute();
		}
		else if (Bitmap.class.isAssignableFrom(propertyType))
		{
			return new BitmapImageSourceAttribute();
		}
		
		throw new RuntimeException("Could not find a suitable image attribute class for property type: " + propertyType);
	}

	
	class IntegerImageSourceAttribute extends AbstractReadOnlyPropertyViewAttribute<Integer, ImageView>
	{
		@Override
		protected void valueModelUpdated(Integer resourceId)
		{
			view.setImageResource(resourceId);
		}
	}
	
	class DrawableImageSourceAttribute extends AbstractReadOnlyPropertyViewAttribute<Drawable, ImageView>
	{
		@Override
		protected void valueModelUpdated(Drawable drawable)
		{
			view.setImageDrawable(drawable);
		}
	}
	
	class BitmapImageSourceAttribute extends AbstractReadOnlyPropertyViewAttribute<Bitmap, ImageView>
	{
		@Override
		protected void valueModelUpdated(Bitmap bitmap)
		{
			view.setImageBitmap(bitmap);
		}
	}

}
