package com.kevinho.itemized.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

public class PageNavigator extends Navigator {
    public PageNavigator(UI ui, SingleComponentContainer container){
        super(ui, container);
    }

    public static PageNavigator getNavigator(){
        UI ui = UI.getCurrent();
        Navigator navigator = ui.getNavigator();
        return (PageNavigator) navigator;
    }
}
