package com.kevinho.itemized.view;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class LogoPanelView implements UIComponentBuilder {

    private class LogoPanel extends VerticalLayout{
        private Label logo;

        public LogoPanel init(){
            logo = new Label("ITEMIZED");
            logo.addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_BOLD, ValoTheme.LABEL_COLORED);
            return this;
        }

        public LogoPanel layout(){
            addComponent(logo);
            setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
            return this;
        }
    }

    @Override
    public Component createComponent() {
        return new LogoPanel().init().layout();
    }
}
