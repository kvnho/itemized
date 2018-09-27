package com.kevinho.itemized.view;

import com.kevinho.itemized.entity.Item;
import com.kevinho.itemized.listener.ItemSavedListener;
import com.kevinho.itemized.service.AddItemService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Component
public class AddItemView {

    private class AddItemViewLayout extends VerticalLayout implements Button.ClickListener{

        private TextField name;
        private TextField color;
        private TextField size;
        private TextField price;
        private Button saveButton;
        private Button resetButton;

        private Binder<Item> binder;
        private Item item;

        private ItemSavedListener itemSavedListener;
        public AddItemViewLayout(ItemSavedListener itemSavedListener){
            this.itemSavedListener = itemSavedListener;
        }

        public AddItemViewLayout init(){
            binder = new Binder<>(Item.class);
            item = new Item();

            name = new TextField("Item Name");
            color = new TextField("Color");
            size = new TextField("Size");
            price = new TextField("Price");

            saveButton = new Button("Save");
            resetButton = new Button("Reset");

            saveButton.addClickListener(this);
            resetButton.addClickListener(this);

            saveButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

            name.setWidth("100%");
            color.setWidth("100%");
            size.setWidth("100%");
            price.setWidth("100%");


            saveButton.setWidth("50%");
            resetButton.setWidth("50%");

            return this;
        }

        public AddItemViewLayout bind(){
            binder.forField(name).bind(Item::getName, Item::setName);
            binder.forField(color).bind(Item::getColor, Item::setColor);
            binder.forField(size).bind(Item::getSize, Item::setSize);
            binder.forField(price)
                    .withConverter(value -> value.isEmpty() ? new Double("0") : new Double(value),
                            value -> value == null ? "" : value.toString())
                    .bind(Item::getPrice, Item::setPrice);
            binder.setBean(item);
            return this;
        }

        public Component layout(){
            setMargin(true);
            setSpacing(true);

            GridLayout gridLayout = new GridLayout(2, 10);
            gridLayout.setSizeFull();
            gridLayout.setSpacing(true);

            gridLayout.addComponent(name, 0,0);
            gridLayout.addComponent(color, 1,0);

            gridLayout.addComponent(size, 0,1);
            gridLayout.addComponent(price, 1,1);

            gridLayout.addComponent(saveButton, 0,9);
            gridLayout.addComponent(resetButton, 1,9);


            gridLayout.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);
            gridLayout.setComponentAlignment(resetButton, Alignment.MIDDLE_LEFT);

            return gridLayout;
        }

        @Override
        public void buttonClick(Button.ClickEvent clickEvent) {
            if(clickEvent.getSource() == this.saveButton){
                try {
                    save();
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
                addItemService.saveItem(item);
                itemSavedListener.itemSaved();
                clear();
                Notification.show("Added Successfully!", Notification.Type.HUMANIZED_MESSAGE);


            }
            else{
                clear();
            }
        }

        private void save() throws ValidationException {
            binder.writeBean(item);
        }

        private void clear(){
            name.setValue("");
            color.setValue("");
            size.setValue("");
            price.setValue("");
        }
    }

    @Autowired
    private AddItemService addItemService;

    public Component createComponent(ItemSavedListener itemSavedListener){
        return new AddItemViewLayout(itemSavedListener).init().bind().layout();
    }

}
