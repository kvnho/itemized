package com.kevinho.itemized;

import com.kevinho.itemized.navigator.PageNavigator;
import com.kevinho.itemized.view.LogoPanelView;
import com.kevinho.itemized.view.MenuView;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

@SpringUI(path="/home")
public class MainView extends UI {

    private Panel changeTab = new Panel();

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SpringViewProvider springViewProvider;
    @Autowired
    private LogoPanelView logoPanelView;
    @Autowired
    private MenuView menuView;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        changeTab.setHeight("100%");
        VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);


        Panel logoPanel = new Panel();
        logoPanel.setWidth("90%");
        logoPanel.setHeightUndefined();

        Panel contentPanel = new Panel();
        contentPanel.setWidth("90%");
        contentPanel.setHeight("100%");

        HorizontalLayout contentLayout = new HorizontalLayout();
        contentLayout.setSizeFull();
        contentLayout.setMargin(true);

        Component logo = logoPanelView.createComponent();
        Component menu = menuView.createComponent();

        contentLayout.addComponent(menu);
        contentLayout.addComponent(changeTab);

        contentLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
        contentLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);

        contentLayout.setExpandRatio(menu, 1);
        contentLayout.setExpandRatio(changeTab, 3);

        logoPanel.setContent(logo);
        contentPanel.setContent(contentLayout);

        root.addComponents(logoPanel, contentPanel);
        root.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
        root.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
        root.setExpandRatio(contentPanel,2);

        initNavigator();
        setContent(root);

    }

    private void initNavigator(){
        PageNavigator pageNavigator = new PageNavigator(this, changeTab);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(pageNavigator);
        pageNavigator.addProvider(springViewProvider);
        pageNavigator.navigateTo("/additem");
    }
}
