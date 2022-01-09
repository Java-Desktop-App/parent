package io.github.jast90.swt.component.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyTree extends Composite {
    protected Tree tree;

    public MyTree(Composite parent, int style) {
        super(parent, style);
        setLayout(new FillLayout(SWT.HORIZONTAL));
        init();
    }

    private void init(){
        tree = new Tree(this, SWT.NONE);
        tree.addListener(SWT.Expand, event -> {
            final TreeItem root = (TreeItem) event.item;
            TreeItem [] items = root.getItems ();
            for (TreeItem item : items) {
                if (item.getData () != null) return;
                item.dispose ();
            }
            updateItem(root);
        });

        tree.addListener(SWT.Selection,event -> {
            getData4Item((TreeItem) event.item);
        });

        Menu treeMenu = new Menu(tree);
        tree.setMenu(treeMenu);
        MenuItem addNode = new MenuItem(treeMenu, SWT.NONE);
        addNode.setText("添加");
        addNode.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TreeItem treeItem = tree.getSelection()[0];
                addNode(treeItem);
                tree.redraw();
            }
        });
        MenuItem deleteNode = new MenuItem(treeMenu, SWT.NONE);
        deleteNode.setText("删除");
        deleteNode.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TreeItem treeItem = tree.getSelection()[0];
                deleteNode(treeItem);
                treeItem.dispose();
            }
        });

        MenuItem refreshNode = new MenuItem(treeMenu, SWT.NONE);
        refreshNode.setText("刷新");
        refreshNode.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            TreeItem treeItem = tree.getSelection()[0];
            updateItem(treeItem);
            }
        });
    }

    public void updateItem(TreeItem treeItem){
        TreeItem[] items = treeItem.getItems();
        if(items!=null){
            for (TreeItem item : items) {
                item.dispose();
            }
        }
        List<TreeNode<String>> datas = getTreeNodes(treeItem);
        for (TreeNode<String> data : datas) {
            TreeItem item = new TreeItem (treeItem, 0);
            item.setText (data.getLabel());
            item.setData (data.getData());
            new TreeItem (item, 0);
        }
    }
    public void updateTree(){
        for (TreeNode each : getRootNodes()) {
            TreeItem treeItem = new TreeItem (tree, 0);
            treeItem.setText (each.getLabel());
            treeItem.setData (each.getLabel());
            new TreeItem (treeItem, 0);
        }
    }

    /**
     * 获取父节点
     *
     * @return
     */
    public List<TreeNode> getRootNodes(){
        List<TreeNode> roots = new ArrayList<>();
        TreeNode treeNode = new TreeNode();
        treeNode.setLabel("/");
        roots.add(treeNode);
        return roots;
    }

    /**
     * 根据父节点获取数据
     * @param root
     * @return
     */
    public List<TreeNode<String>> getTreeNodes(TreeItem root){
        String parent = root.getText();
        System.out.println(parent);
        ArrayList<TreeNode<String>> treeNodes = new ArrayList<>();
        TreeNode<String> treeNode = new TreeNode();
        treeNode.setLabel("1");
        treeNode.setData((Objects.equals("/",parent)?"":parent)+"/1");
        treeNodes.add(treeNode);
        return treeNodes;
    }

    public String getData4Item(TreeItem root){
        throw new UnsupportedOperationException();
    }

    public void deleteNode(TreeItem treeItem){

    }

    public void addNode(TreeItem treeItem){

    }
}
