package io.github.jast90.swt.component.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class MyTable extends Composite {
    private Table table;
    private TableData tableData;

    public MyTable(Composite parent, int style, TableData tableData) {
        super(parent, style);
        this.tableData = tableData;
        setLayout(new FillLayout(SWT.HORIZONTAL));
        init();
    }

    private void init(){

        table = new Table (this, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible (true);
        table.setHeaderVisible (true);
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.heightHint = 200;
        table.setLayoutData(data);
        for (String title : tableData.getTitles()) {
            TableColumn column = new TableColumn (table, SWT.NONE);
            column.setText (title);
        }
//        int count = 128;
//        for (int i=0; i<count; i++) {
//            TableItem item = new TableItem (table, SWT.NONE);
//            item.setText (0, "x");
//            item.setText (1, "y");
//            item.setText (2, "!");
//            item.setText (3, "this stuff behaves the way I expect");
//            item.setText (4, "almost everywhere");
//            item.setText (5, "some.folder");
//            item.setText (6, "line " + i + " in nowhere");
//        }
        for (int i=0; i < tableData.getTitles().size(); i++) {
            table.getColumn (i).pack ();
        }
        table.pack();
    }
}
