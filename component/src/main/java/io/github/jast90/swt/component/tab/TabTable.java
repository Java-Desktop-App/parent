package io.github.jast90.swt.component.tab;

import io.github.jast90.swt.component.table.MyTable;
import io.github.jast90.swt.component.table.MyTableTest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class TabTable extends Composite {
    private Shell shell;
    private TabFolder tabFolder;
    private String title="tab title";

    public TabTable(Composite parent, int style, Shell shell) {
        super(parent, style);
        setLayout(new FillLayout(SWT.HORIZONTAL));
        this.shell = shell;
        init();
    }

    private void init(){
        tabFolder = new TabFolder(this, SWT.NONE);
        Rectangle clientArea = shell.getClientArea ();
        tabFolder.setLocation (clientArea.x, clientArea.y);
        TabItem item = new TabItem (tabFolder, SWT.NONE);
        item.setText (title);
        MyTable myTable = new MyTable(tabFolder,SWT.BORDER, MyTableTest.getData());
        item.setControl (myTable);

        TabItem item1 = new TabItem (tabFolder, SWT.NONE);
        item1.setText (title);
        Button button = new Button (tabFolder, SWT.PUSH);
        button.setText ("button ");
        item1.setControl (button);

        tabFolder.pack();
    }

}
