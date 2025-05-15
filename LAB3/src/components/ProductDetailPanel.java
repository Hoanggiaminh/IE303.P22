package components;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.Timer;

import models.Product;

public class ProductDetailPanel extends JPanel {
    private float alpha = 0f;

    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel brandLabel;
    private JTextArea descriptionArea;

    public ProductDetailPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(80, 20, 20, 20));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(360, 200));
        contentPanel.add(imageLabel, BorderLayout.NORTH);
        
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        separator.setBackground(Color.LIGHT_GRAY);
        separator.setForeground(Color.LIGHT_GRAY);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(separator);
        infoPanel.add(Box.createVerticalStrut(20));

        nameLabel = new JLabel("Product Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(10));

        priceLabel = new JLabel("$0.00");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(10));

        brandLabel = new JLabel("Brand");
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(brandLabel);
        infoPanel.add(Box.createVerticalStrut(10));

        descriptionArea = new JTextArea("Product description...");
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setFont(new Font("Arial", Font.BOLD, 18));
        descriptionArea.setForeground(new Color(0xa6a6a6));
        descriptionArea.setBorder(null);
        descriptionArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(descriptionArea);

        contentPanel.add(infoPanel, BorderLayout.CENTER);
        add(contentPanel);
    }

    public void showProduct(Product product) {
        imageLabel.setIcon(new ImageIcon(
            new ImageIcon(product.getImagePath()).getImage().getScaledInstance(320, 310, java.awt.Image.SCALE_SMOOTH)
        ));
        nameLabel.setText(product.getName());
        priceLabel.setText(product.getPrice());
        brandLabel.setText(product.getBrand());
        descriptionArea.setText(product.getDescription());
    }

    public void animateShowProduct(Product product) {
        // Cập nhật nội dung trước
        imageLabel.setIcon(new ImageIcon(
            new ImageIcon(product.getImagePath()).getImage().getScaledInstance(320, 310, java.awt.Image.SCALE_SMOOTH)
        ));
        nameLabel.setText(product.getName());
        priceLabel.setText(product.getPrice());
        brandLabel.setText(product.getBrand());
        descriptionArea.setText(product.getDescription());

        // Bắt đầu hiệu ứng mờ dần
        alpha = 0f;
        Timer timer = new Timer(30, e -> {
            alpha += 0.05f;
            if (alpha >= 1f) {
                alpha = 1f;
                ((Timer) e.getSource()).stop();
            }
            repaint();
        });
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Áp dụng hiệu ứng mờ
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        super.paintComponent(g2d);
        g2d.dispose();
    }
}
