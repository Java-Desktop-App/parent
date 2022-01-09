package io.github.jast90.zk.client.ui;

import org.apache.zookeeper.CreateMode;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

public class NodeAddDialog extends Dialog {

    protected Object result;
    protected Shell shell;
    private Text name;
    private Text value;
    private Label label_2;
    private Button btnPersistent;
    private Button btnEphemeral;
    private Button btnCancel;
    private Button btnOk;
    private Label lblNewLabel;
    private String prefix;
    private OkListener okListener;

    /**
     * Create the dialog.
     *
     * @param parent
     * @param style
     */
    public NodeAddDialog(Shell parent, int style, String prefix, String title, OkListener okListener) {
        super(parent, style);
        this.prefix = prefix;
        this.okListener = okListener;
        setText(title);
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
        shell.setLayout(new GridLayout(3, false));

        Label label = new Label(shell, SWT.NONE);
        label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label.setText("名称");

        lblNewLabel = new Label(shell, SWT.NONE);
        lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblNewLabel.setText(prefix);

        name = new Text(shell, SWT.BORDER);
        name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Label label_1 = new Label(shell, SWT.NONE);
        label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        label_1.setText("值");

        value = new Text(shell, SWT.BORDER);
        value.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

        label_2 = new Label(shell, SWT.NONE);
        label_2.setText("类型");

        btnPersistent = new Button(shell, SWT.RADIO);
        btnPersistent.setSelection(true);
        btnPersistent.setText("永久节点");

        btnEphemeral = new Button(shell, SWT.RADIO);
        btnEphemeral.setText("零时节点");
        new Label(shell, SWT.NONE);

        btnOk = new Button(shell, SWT.NONE);
        btnOk.setText("确定");
        btnOk.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                CreateMode createMode = CreateMode.PERSISTENT;
                if (btnEphemeral.getSelection()) {
                    createMode = CreateMode.EPHEMERAL;
                }
                NameValue nameValue = new NameValue();
                nameValue.setName(name.getText());
                nameValue.setValue(value.getText());
                nameValue.setCreateMode(createMode);
                okListener.onOk(nameValue);
                shell.close();
            }
        });
        btnCancel = new Button(shell, SWT.NONE);
        btnCancel.setText("取消");
        btnCancel.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });

    }

    public interface OkListener {
        void onOk(NameValue nameValue);
    }

    public class NameValue {
        private String name;
        private String value;
        private CreateMode createMode;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public CreateMode getCreateMode() {
            return createMode;
        }

        public void setCreateMode(CreateMode createMode) {
            this.createMode = createMode;
        }
    }

}
