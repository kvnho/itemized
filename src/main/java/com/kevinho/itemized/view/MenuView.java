package com.kevinho.itemized.view;

import com.vaadin.ui.*;

@org.springframework.stereotype.Component
public class MenuView implements UIComponentBuilder {

    private class Menu extends VerticalLayout {
        /*
        private TabSheet tabSheet;
        private VerticalLayout tab1;
        private VerticalLayout tab2;
        */
        private Button button;

        public Menu init(){
            /*
            tabSheet = new TabSheet();
            tab1 = new VerticalLayout();
            tab2 = new VerticalLayout();
            tabSheet.addTab(tab1, "Inventory");
            tabSheet.addTab(tab2, "Summary");
            return this;
            */
            button = new Button();
            return this;
        }

        public Menu layout(){
            /*
            addComponent(tabSheet);
            setComponentAlignment(tabSheet, Alignment.MIDDLE_CENTER);
            return this;
            */
            button = new Button("Delete");
            setWidth("100%");
            setHeightUndefined();
            addComponent(button);
            return this;
        }

    }


    @Override
    public Component createComponent() {
        return new Menu().init().layout();
    }
}
