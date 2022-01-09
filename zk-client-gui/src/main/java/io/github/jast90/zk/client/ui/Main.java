package io.github.jast90.zk.client.ui;

import io.github.jast90.zk.client.SessionManager;
import io.github.jast90.zk.client.ZkManager;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import java.nio.charset.StandardCharsets;

public class Main extends Composite {
    private StyledText text;
    private ZkTree zkTree;

    public Main(Composite parent, int style) {
        super(parent, style);
        setLayout(new GridLayout(2, false));
        zkTree = new ZkTree(this, SWT.NONE);
        GridData gd_tree = new GridData(SWT.LEFT, SWT.FILL, false, false,
                1, 1);
        gd_tree.minimumWidth = 100;
        gd_tree.widthHint = 300;
        zkTree.setLayoutData(gd_tree);

        text = new StyledText(this, SWT.NONE);
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
                1, 1));
        zkTree.setText(text);

        Menu menu = new Menu(text);
        text.setMenu(menu);
        MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
        mntmNewItem.setText("保存");
        mntmNewItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                CuratorFramework connection = ZkManager.getConnection();
                if(connection!=null){
                    try {

                        Stat stat = connection.setData().forPath(SessionManager.getCurrentPath(),
                                text.getText().getBytes(StandardCharsets.UTF_8));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

            }
        });



    }

    public void updateTree(){
        zkTree.updateTree();
    }
}
