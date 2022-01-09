package io.github.jast90.swt.component.tab;

import io.github.jast90.swt.component.base.AbstractTest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class TabTableTest extends AbstractTest {
    public TabTableTest(String title) {
        super(title);
    }

    @Override
    protected void addComponent(Shell shell) {
        TabTable tabTable = new TabTable(shell, SWT.BORDER, shell);
        tabTable.pack();
    }

    public static void main(String[] args) {
        new TabTableTest("自定义tabTable");
    }
}
