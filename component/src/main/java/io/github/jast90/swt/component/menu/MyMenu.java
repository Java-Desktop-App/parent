package io.github.jast90.swt.component.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import java.util.List;

public class MyMenu {

    private List<MenuNode> data;

    private Shell shell;

    public MyMenu(List<MenuNode> data,Shell shell) {
        this.data = data;
        this.shell = shell;
        init();
    }

    private void init() {
        Menu bar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(bar);
        for (MenuNode each : data) {
            MenuItem menuItem = new MenuItem(bar, SWT.CASCADE);
            menuItem.setText(each.getTitle());
            Menu submenu = new Menu(menuItem);
            menuItem.setMenu(submenu);
            if (each.getChildren() != null && each.getChildren().size() > 0) {
                for (MenuNode child : each.getChildren()) {
                    MenuItem childMenuItem = new MenuItem(submenu, SWT.NONE);
                    childMenuItem.setText(child.getTitle());
                    if(child.getSelectionListener()!=null){
                        childMenuItem.addSelectionListener(child.getSelectionListener());
                    }
                }
            }
        }
    }
}
