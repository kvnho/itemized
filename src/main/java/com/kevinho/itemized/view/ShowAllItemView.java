package com.kevinho.itemized.view;

import com.kevinho.itemized.entity.Item;
import com.kevinho.itemized.service.ShowItemsService;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Component
public class ShowAllItemView implements UIComponentBuilder{

    private List<Item> items;
    private Grid<Item> itemGrid;
    private ListDataProvider<Item> listDataProvider;

    private class ShowAllItemViewLayout extends VerticalLayout{

        public ShowAllItemViewLayout init(){
            setMargin(true);
            itemGrid = new Grid<>(Item.class);

            listDataProvider = DataProvider.ofCollection(items);
            itemGrid.setDataProvider(listDataProvider);
            itemGrid.setColumnOrder("id", "name", "color", "size", "price");

            return this;
        }

        public ShowAllItemViewLayout load(){
            items = showItemsService.getAllItems();
            return this;
        }

        public ShowAllItemViewLayout layout(){
            addComponent(itemGrid);
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
