package com.kevinho.itemized.view;

import com.kevinho.itemized.entity.Item;
import com.kevinho.itemized.service.ShowItemsService;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Component
public class ShowAllItemView implements UIComponentBuilder{

    private List<Item> items;
    private Grid<Item> itemGrid;
    private ListDataProvider<Item> listDataProvider;
    private Button deleteButton;
    private Button editButton;
    private HorizontalLayout buttonsLayout;

    private class ShowAllItemViewLayout extends VerticalLayout{

        public ShowAllItemViewLayout init(){
            setMargin(true);
            itemGrid = new Grid<>(Item.class);
            itemGrid.setSizeFull();

            listDataProvider = DataProvider.ofCollection(items);
            itemGrid.setDataProvider(listDataProvider);
            itemGrid.setColumnOrder("id", "name", "color", "size", "price");

            deleteButton = new Button("Delete");
            deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);

            editButton = new Button("Edit");
            editButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);

            editButton.setWidth("100%");
            deleteButton.setWidth("100%");

            buttonsLayout = new HorizontalLayout();
            buttonsLayout.setWidth("100%");
            buttonsLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
            buttonsLayout.addComponents(editButton, deleteButton);

            return this;
        }

        public ShowAllItemViewLayout load(){
            items = showItemsService.getAllItems();
            return this;
        }

        public ShowAllItemViewLayout layout(){
            addComponents(itemGrid, buttonsLayout);
            return this;
        }
    }

    public void refreshTable(){
        items = showItemsService.getAllItems();
        listDataProvider = DataProvider.ofCollection(items);
        itemGrid.setDataProvider(listDataProvider);
    }

    @Autowired
    private ShowItemsService showItemsService;

    @Override
    public Component createComponent() {
        return new ShowAllItemViewLayout().load().init().layout();
    }
}
