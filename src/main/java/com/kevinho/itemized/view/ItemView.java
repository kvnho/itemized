package com.kevinho.itemized.view;

import com.kevinho.itemized.MainView;
import com.kevinho.itemized.listener.ItemSavedListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name="/inventory", ui= MainView.class)
public class ItemView extends VerticalLayout implements View, ItemSavedListener {

    private TabSheet tabSheet;

    @Autowired
    private AddItemView addItemView;
    @Autowired
    private ShowAllItemView showAllItemView;

    public void addLayout() {
        setMargin(true);

        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");

        Component addItemsTab = addItemView.createComponent(this);
        Component viewItemsTab = showAllItemView.createComponent();

        VerticalLayout addItemsTabLayout = new VerticalLayout(addItemsTab);
        addItemsTabLayout.setComponentAlignment(addItemsTab, Alignment.MIDDLE_CENTER);



        tabSheet.addTab(viewItemsTab, "Inventory");
        tabSheet.addTab(addItemsTabLayout, "Add Item");


        addComponent(tabSheet);

    }

    public void enter(ViewChangeListener.ViewChangeEvent event){
        removeAllComponents();
        addLayout();
    }


    @Override
    public void itemSaved() {
        showAllItemView.refreshTable();
    }
}
