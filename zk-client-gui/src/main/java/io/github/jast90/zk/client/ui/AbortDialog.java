package io.github.jast90.zk.client.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;

import java.io.InputStream;

public class AbortDialog extends Dialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public AbortDialog(Shell parent, int style) {
		super(parent, style);
		setText("赞赏");
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		Display display = getParent().getDisplay();
		createContents(display);
		shell.open();
		shell.layout();
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
	private void createContents(Display display) {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(640, 340);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Canvas wechatPay = new Canvas(shell, SWT.NONE);

		wechatPay.addPaintListener(e -> {
			Image image = new Image(display, getClasspathImage("static/image/wechatPay.jpg"));
			e.gc.drawImage(image, 0, 0);
			image.dispose();
		});

		Canvas alipay = new Canvas(shell, SWT.NONE);
		
		alipay.addPaintListener(e -> {
			Image image = new Image(display, getClasspathImage("static/image/alipay.jpg"));
			e.gc.drawImage(image, 53, 0);
			image.dispose();
		});

	}

	public InputStream getClasspathImage(String path){
		return AbortDialog.class.getClassLoader().getResourceAsStream(path);
	}
}
