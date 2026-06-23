package com.kurt.brs.view;

import java.awt.BorderLayout;

import com.kurt.brs.utility.ViewComponentFactory;
import com.kurt.brs.utility.constants.ResourcePaths;


public class BannerViewPanel extends BaseView implements View{
	public BannerViewPanel(){
		this.setLayout(new BorderLayout());
		this.add(ViewComponentFactory.createJLabelImage(ResourcePaths.BANNER));
	}
}