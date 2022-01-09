package io.github.jast90.zk.client.ui;

import io.github.jast90.zk.client.SessionManager;
import io.github.jast90.zk.client.ZkManager;
import io.github.jast90.zk.client.ZookeeperConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.ZooKeeper;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class ConnectDialog extends Dialog {

    protected Object result;
    protected Shell shell;
    private Text hostText;
    private Text portText;

    private Main main;

    /**
     * Create the dialog.
     *
     * @param parent
     * @param style
     */
    public ConnectDialog(Shell parent, int style) {
        super(parent, style);
        setText("创建连接");
    }

    /**
     * Open the dialog.
     *
     * @return the result
     */
    public Object open() {
        createContents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return result;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setSize(450, 300);
        shell.setText(getText());
        shell.setLayout(new GridLayout(2, false));

        Label lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblNewLabel.setText("Host");

        hostText = new Text(shell, SWT.BORDER);
        hostText.setText("localhost");
        hostText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Label lblPort = new Label(shell, SWT.NONE);
        lblPort.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblPort.setText("Port");

        portText = new Text(shell, SWT.BORDER);
        portText.setText("2181");
        portText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        new Label(shell, SWT.NONE);

        Button btnConnect = new Button(shell, SWT.NONE);
        btnConnect.setText("connect");

        btnConnect.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String host = hostText.getText();
                String port = portText.getText();
                ZookeeperConfig zookeeperConfig = new ZookeeperConfig();
                zookeeperConfig.setHost(host);
                zookeeperConfig.setPort(port);
                CuratorFramework connection = null;
                try {
                    connection = ZkManager.getConnection(zookeeperConfig);
                    byte[] bytes = connection.getData().forPath("/");
                    if(bytes != null){
                        SessionManager.setCurrentConfig(zookeeperConfig);
                        main.updateTree();
                        shell.close();
                    }

                } catch (Exception exception) {
                    exception.printStackTrace();
                }finally {
                    if(connection != null){
                        connection.close();
                    }
                }
            }
        });
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
