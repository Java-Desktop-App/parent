package io.github.jast90.swt.component.menu;

import io.github.jast90.swt.component.base.AbstractTest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;
import java.util.List;

public class MyMenuTest extends AbstractTest {

    public MyMenuTest(String title) {
        super(title);
    }

    @Override
    protected void addComponent(Shell shell) {
        new MyMenu(getData(),shell);
    }

    public static List<MenuNode> getData() {
        List<MenuNode> list = new ArrayList<>();
        MenuNode menuNode;
        MenuNode child;
        List<MenuNode> children;
        for (int i = 0; i < 10; i++) {
            menuNode = new MenuNode();
            menuNode.setTitle("菜单"+i);
            children = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                child = new MenuNode();
                String title = "子菜单"+i+"-"+j;
                child.setTitle(title);
                child.setSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent e) {
                        System.out.println(title);
                    }
                });
                children.add(child);
            }
            menuNode.setChildren(children);
            list.add(menuNode);
        }
        return list;
    }

    public static void main(String[] args) {
        new MyMenuTest("自定义菜单");
    }
}
