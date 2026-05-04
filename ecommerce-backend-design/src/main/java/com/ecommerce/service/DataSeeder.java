package com.ecommerce.service;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        seedUsers();
        seedProducts();
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            // Admin user
            userRepository.save(User.builder()
                    .fullName("Admin User")
                    .email("admin@shop.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(User.Role.ADMIN)
                    .build());

            // Regular user
            userRepository.save(User.builder()
                    .fullName("Rafay Test")
                    .email("user@shop.com")
                    .password(passwordEncoder.encode("user123"))
                    .role(User.Role.USER)
                    .build());

            System.out.println("✅ Seeded default users.");
        }
    }

    private void seedProducts() {
        if (productRepository.count() == 0) {
            List<Product> products = List.of(
                Product.builder().name("Wireless Noise-Cancelling Headphones").price(new BigDecimal("129.99"))
                    .category("Electronics").stock(50)
                    .description("Premium over-ear headphones with active noise cancellation, 30hr battery life, and foldable design.")
                    .imageUrl("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400").build(),

                Product.builder().name("Running Sneakers Pro").price(new BigDecimal("89.99"))
                    .category("Footwear").stock(30)
                    .description("Lightweight breathable running shoes with cushioned sole, perfect for daily runs or gym sessions.")
                    .imageUrl("https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=400").build(),

                Product.builder().name("Smart Watch Series X").price(new BigDecimal("199.99"))
                    .category("Electronics").stock(20)
                    .description("Feature-packed smartwatch with health tracking, GPS, notifications, and 5-day battery.")
                    .imageUrl("https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400").build(),

                Product.builder().name("Leather Crossbody Bag").price(new BigDecimal("59.99"))
                    .category("Accessories").stock(40)
                    .description("Genuine leather crossbody bag with adjustable strap, multiple compartments, and elegant finish.")
                    .imageUrl("https://images.unsplash.com/photo-1548036328-c9fa89d128fa?w=400").build(),

                Product.builder().name("Casual Linen T-Shirt").price(new BigDecimal("24.99"))
                    .category("Clothing").stock(100)
                    .description("Soft, breathable linen t-shirt available in multiple colors. Perfect for everyday wear.")
                    .imageUrl("https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=400").build(),

                Product.builder().name("Portable Bluetooth Speaker").price(new BigDecimal("49.99"))
                    .category("Electronics").stock(60)
                    .description("360-degree sound, waterproof design, 12hr playtime. Great for outdoor adventures.")
                    .imageUrl("https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=400").build(),

                Product.builder().name("Stainless Steel Water Bottle").price(new BigDecimal("19.99"))
                    .category("Kitchen").stock(80)
                    .description("Keeps drinks cold for 24 hours or hot for 12 hours. BPA-free and eco-friendly.")
                    .imageUrl("https://images.unsplash.com/photo-1602143407151-7111542de6e8?w=400").build(),

                Product.builder().name("Minimalist Backpack").price(new BigDecimal("69.99"))
                    .category("Accessories").stock(35)
                    .description("Slim profile laptop backpack with USB charging port, water-resistant fabric, and ergonomic straps.")
                    .imageUrl("https://images.unsplash.com/photo-1553062407-98eeb64c6a62?w=400").build(),

                Product.builder().name("Yoga Mat Premium").price(new BigDecimal("34.99"))
                    .category("Sports").stock(45)
                    .description("6mm thick non-slip yoga mat with alignment lines, carrying strap included.")
                    .imageUrl("https://images.unsplash.com/photo-1601925228740-02a6f54de3b5?w=400").build(),

                Product.builder().name("Sunglasses Aviator Style").price(new BigDecimal("44.99"))
                    .category("Accessories").stock(55)
                    .description("UV400 protection polarized lenses with lightweight metal frame. Classic aviator look.")
                    .imageUrl("https://images.unsplash.com/photo-1572635196237-14b3f281503f?w=400").build()
            );
            productRepository.saveAll(products);
            System.out.println("✅ Seeded " + products.size() + " sample products.");
        }
    }
}
