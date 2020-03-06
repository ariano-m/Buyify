package com.buyify;

import com.buyify.order.Order;
import com.buyify.order.OrderRepository;
import com.buyify.product.Product;
import com.buyify.product.ProductRepository;
import com.buyify.promotion.Promotion;
import com.buyify.promotion.PromotionRepository;
import com.buyify.review.Review;
import com.buyify.review.ReviewRepository;
import com.buyify.user.User;
import com.buyify.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DatabaseUsage implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

//        User u1 = new User("Bustamante", "bustamante_", "liz@sen.ma.gov", "12345678", "USER");
//        User u2 = new User("Pepe", "pepe28", "pepe@gmail.com", "12345678", "USER");
//        User u3 = new User("Juan", "juan98", "juan98@gmail.com", "12345678", "USER");
//        User u4 = new User("Santiago", "santi_ago", "santi00@outlook.es", "12345678", "USER");
//        User u5 = new User("Fernando", "alonso12", "alonso12@gmail.es", "12345678", "USER");
//        User admin = new User("Admin", "admin", "admin1@gmail.com", "admin123", "USER", "ADMIN");
//
//        userRepository.save(u1);
//        userRepository.save(u2);
//        userRepository.save(u3);
//        userRepository.save(u4);
//        userRepository.save(u5);
//        userRepository.save(admin);
//
//
//        Product p1 = new Product("Solán de Cabras", "alimentación", 1.2F, 5,
//                "Agua mineral natural.",
//                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201901/21/00110237800353____1__640x640.jpg");
//
//        Product p2 = new Product("Chocolate Milka", "alimentación", 0.99F, 3,
//                "Puede contener Gluten y trazas de frutos secos.",
//                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201808/10/00120646700896____22__600x600.jpg");
//
//        Product p3 = new Product("BUSTAMANTE Muy Mío", "higiene", 6.95F, 111,
//                "Un seductor natural que despierta pasiones. Confianza en sí mismo, entrega, emperamento, carisma, naturalidad, espíritu de lucha? concentrados en una fragancia.",
//                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201909/23/00155760200737____1__600x600.jpg");
//
//        Product p4 = new Product("DODOT Activity toallitas infantiles", "higiene", 8.63F, 56,
//                "Máxima comodidad y protección en la piel, su capa absorvente cora-soft es suave como una pluma.",
//                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201905/10/00155763100421____15__600x600.jpg");
//
//        Product p5 = new Product("ASTURIANA leche semidesnatada", "alimentación", 0.89F, 23,
//                "Leche UHT semidesnatada sin lactosa",
//                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201902/07/00120912000260____23__600x600.jpg");
//
//        Product p6 = new Product("Vino blanco", "alimentación", 13.90F, 45,
//                "Color amarillo verdoso pálido con destellos alimonados, brillante, limpio y cristalino. Aromas de intensidad media con claras notas minerales.",
//                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201909/24/00118775700711____2__600x600.jpg");
//
//        productRepository.save(p1);
//        productRepository.save(p2);
//        productRepository.save(p3);
//        productRepository.save(p4);
//        productRepository.save(p5);
//        productRepository.save(p6);
//
//
//        LocalDate localDate = java.time.LocalDate.now();
//        Date date = java.sql.Date.valueOf(localDate);
//
//        Review r1 = new Review(p1, u1, date, "Cumple su funcion");
//        Review r2 = new Review(p2, u2, date, "Cumple su funcion");
//        Review r3 = new Review(p3, u3, date, "Cumple su funcion");
//        Review r4 = new Review(p4, u4, date, "Cumple su funcion");
//        Review r5 = new Review(p5, u5, date, "Cumple su funcion");
//
//        reviewRepository.save(r1);
//        reviewRepository.save(r2);
//        reviewRepository.save(r3);
//        reviewRepository.save(r4);
//        reviewRepository.save(r5);
//
//
//        List<Product> l1 = new ArrayList<>();
//        l1.add(p1);
//        l1.add(p2);
//
//        List<Product> l2 = new ArrayList<>();
//        l2.add(p3);
//        l2.add(p4);
//        l2.add(p5);
//
//        List<Product> l3 = new ArrayList<>();
//        l3.add(p3);
//
//        Order o1 = new Order(u1, date, l1);
//        Order o2 = new Order(u3, date, l2);
//        Order o3 = new Order(u4, date, l3);
//        Order o4 = new Order(u3, date, l3);
//
//        orderRepository.save(o1);
//        orderRepository.save(o2);
//        orderRepository.save(o3);
//        orderRepository.save(o4);
//
//
//        Promotion pr1 = new Promotion(20);
//        pr1.setProduct(p3);
//
//        promotionRepository.save(pr1);


        List<Product> query = productRepository.findAll();
        for (Product p : query) {
            System.out.println("PRODUCTO= " + p.getName());
        }

        List<Review> query2 = reviewRepository.findAll();
        for (Review r : query2) {
            System.out.println("REVIEW= " + "producto: " + r.getProduct().getName() + " | " + r.getText());
        }

        List<Promotion> query3 = promotionRepository.findAll();
        for (Promotion r : query3) {
            System.out.println("PROMOTION= " + "producto: " + r.getProduct().getName() + " | " + r.getPromotion());
        }

        List<User> query4 = userRepository.findAll();
        for (User u : query4) {
            System.out.println("Usuario= " + u.getUsername() + " ROLES:" + u.getRoles().toString());
        }
    }
}
