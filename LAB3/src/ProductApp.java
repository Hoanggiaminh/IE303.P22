import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import components.ProductCard;
import components.ProductDetailPanel;
import components.RoundedPanel;
import models.Product;

public class ProductApp extends JFrame {

    private ProductDetailPanel detailPanel;
    private JPanel productPanel;
    private JPanel selectedCard;

    public ProductApp() {
        setTitle("Product App");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        setLayout(new BorderLayout());

        // Panel trái
        detailPanel = new ProductDetailPanel();
        add(detailPanel, BorderLayout.WEST);

        // Panel phải
        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 4, 12, 12));
        productPanel.setBorder(BorderFactory.createEmptyBorder(80, 10, 10, 20));
        productPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        loadProducts();
    }

    private void loadProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("4DFWD PULSE SHOES", "$160.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img1.png"));
        products.add(new Product("FORUM MID SHOES", "$100.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img2.png"));
        products.add(new Product("SUPERNOVA SHOES", "$150.00", "Adidas", "NMD City Stock 2", "images/img3.png"));
        products.add(new Product("Adidas", "$160.00", "Adidas", "NMD City Stock 2", "images/img4.png"));
        products.add(new Product("Adidas", "$120.00", "Adidas", "NMD City Stock 2", "images/img5.png"));
        products.add(new Product("4DFWD PULSE SHOES", "$160.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img6.png"));
        products.add(new Product("4DFWD PULSE SHOES", "$160.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img1.png"));
        products.add(new Product("FORUM MID SHOES", "$100.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img2.png"));
        products.add(new Product("SUPERNOVA SHOES", "$150.00", "Adidas", "NMD City Stock 2", "images/img3.png"));
        products.add(new Product("Adidas", "$160.00", "Adidas", "NMD City Stock 2", "images/img4.png"));
        products.add(new Product("Adidas", "$120.00", "Adidas", "NMD City Stock 2", "images/img5.png"));
        products.add(new Product("4DFWD PULSE SHOES", "$160.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img6.png"));
        products.add(new Product("4DFWD PULSE SHOES", "$160.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img1.png"));
        products.add(new Product("FORUM MID SHOES", "$100.00", "Adidas", "This product is excluded from all promotional discounts and offers.", "images/img2.png"));
        products.add(new Product("SUPERNOVA SHOES", "$150.00", "Adidas", "NMD City Stock 2", "images/img3.png"));
        products.add(new Product("Adidas", "$160.00", "Adidas", "NMD City Stock 2", "images/img4.png"));

        for (Product product : products) {
            MouseAdapter listener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    detailPanel.animateShowProduct(product);
                    highlightSelectedCard((RoundedPanel) e.getSource());
                }
            };

            JPanel panel = new ProductCard(product, listener);
            productPanel.add(panel);
        }
    }

    private void highlightSelectedCard(RoundedPanel card) {
        if (selectedCard != null) {
            selectedCard.setForeground(selectedCard.getBackground());
            selectedCard.repaint();
        }

        card.setForeground(new Color(0x1E90FF));
        card.repaint();

        selectedCard = card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
