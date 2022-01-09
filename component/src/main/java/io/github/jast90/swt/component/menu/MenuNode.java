package io.github.jast90.swt.component.menu;

import org.eclipse.swt.events.SelectionListener;

import java.util.List;

public class MenuNode {
    private String title;
    private List<MenuNode> children;

    private SelectionListener selectionListener;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<MenuNode> children) {
        this.children = children;
    }

    public SelectionListener getSelectionListener() {
        return selectionListener;
    }

    public void setSelectionListener(SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }
}
