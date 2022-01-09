package io.github.jast90.swt.component.table;

import io.github.jast90.swt.component.base.AbstractTest;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;
import java.util.List;

public class MyTableTest extends AbstractTest {
    public MyTableTest(String title) {
        super(title);
    }

    @Override
    protected void addComponent(Shell shell) {
        shell.setLayout(new FillLayout());
        new MyTable(shell, SWT.FILL,getData());
    }

    public static void main(String[] args) {
        new MyTableTest("自定义Table");
    }

    public static TableData getData(){
        TableData tableData = new TableData();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            titles.add("title"+i);
        }
        tableData.setTitles(titles);
        return  tableData;

    }
}
