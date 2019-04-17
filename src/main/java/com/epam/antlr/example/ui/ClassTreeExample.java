package com.epam.antlr.example.ui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ClassTreeExample extends JPanel {
    private JTree tree;

    public ClassTreeExample(DefaultMutableTreeNode classTree) {
        super(new GridLayout(1, 0));

        tree = new JTree(classTree);
        tree.setCellRenderer(noIconsRenderer());

        JScrollPane pane = new JScrollPane(tree);
        Dimension size = new Dimension(300, 300);
        pane.setPreferredSize(size);
        add(pane);
    }

    private DefaultTreeCellRenderer noIconsRenderer() {
        DefaultTreeCellRenderer cr = new DefaultTreeCellRenderer();
        cr.setOpenIcon(null);
        cr.setClosedIcon(null);
        cr.setLeafIcon(null);
        return cr;
    }

    public static void display(DefaultMutableTreeNode classTree) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Class Tree Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ClassTreeExample(classTree));
            frame.pack();
            frame.setVisible(true);
        });
    }
}
