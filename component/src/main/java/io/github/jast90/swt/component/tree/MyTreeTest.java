package io.github.jast90.swt.component.tree;

import io.github.jast90.swt.component.base.AbstractTest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public class MyTreeTest extends AbstractTest {

    public MyTreeTest(String title) {
        super(title);
    }

    @Override
    protected void addComponent(Shell shell) {
        MyTree myTree = new MyTree(shell, SWT.BORDER);
        myTree.pack();
    }

    public static void main(String[] args) {
        new MyTreeTest("自定义tree");
    }

}
