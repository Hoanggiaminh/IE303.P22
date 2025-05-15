package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Product;

public class ProductCard extends RoundedPanel {
    public ProductCard(Product product, MouseAdapter listener) {
        super(25);
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        setForeground(getBackground());
        setBorder(BorderFactory.createEmptyBorder(14, 12, 14, 12));

        // Top - Tên và mô tả
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);

        JLabel name = new JLabel(truncateText(product.getName(), 22));
        name.setFont(new Font("Arial", Font.BOLD, 20));
        name.setForeground(new Color(30, 30, 30));

        JLabel desc = new JLabel(truncateText(product.getDescription(), 32));
        desc.setFont(new Font("Arial", Font.BOLD, 16));
        desc.setForeground(new Color(0xa6a6a6));

        topPanel.add(name);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(desc);
        add(topPanel, BorderLayout.NORTH);

        // Center - Hình ảnh
        JLabel imgLabel = new JLabel();
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        imgLabel.setIcon(new ImageIcon(
            new ImageIcon(product.getImagePath()).getImage().getScaledInstance(220, 220, Image.SCALE_SMOOTH)
        ));
        add(imgLabel, BorderLayout.CENTER);

        // Bottom - Brand và Price
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JLabel brand = new JLabel(product.getBrand());
        brand.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel price = new JLabel(product.getPrice());
        price.setFont(new Font("Arial", Font.BOLD, 24));

        bottomPanel.add(brand, BorderLayout.WEST);
        bottomPanel.add(price, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(listener);
    }

    private String truncateText(String text, int maxLength) {
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }
}
