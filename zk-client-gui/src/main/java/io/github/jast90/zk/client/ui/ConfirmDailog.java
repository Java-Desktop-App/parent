package io.github.jast90.zk.client.ui;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FillLayout;

public class ConfirmDailog extends Dialog {

	protected Object result;
	protected Shell shell;
	private SelectionListener selectionListener;
	private String title;
	private String msg;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ConfirmDailog(Shell parent, int style,String title,String msg,SelectionListener selectionListener) {
		super(parent, style);
		setText("SWT Dialog");
		this.title = title;
		this.msg = msg;
		this.selectionListener = selectionListener;
	}

	/**
	 * Open the dialog.
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
		shell.setSize(200, 150);
		shell.setText(title);
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));

		Label msgLabel = new Label(shell, SWT.NONE);
		msgLabel.setText(msg);

		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectionListener.widgetSelected(e);
				shell.close();
			}
		});
		btnOk.setText("确定");
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setText("取消");

		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});

	}

	public void close(){
		shell.close();
	}

}
