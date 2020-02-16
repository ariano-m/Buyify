package com.buyify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Controller
public class DatabaseUsage implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User("Bustamante", "bustamante_", "liz@sen.ma.gov", "12345678");

        Product p1 = new Product("Solán de Cabras", "alimentación", 1.2F, 5,
                "Agua mineral natural.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201901/21/00110237800353____1__640x640.jpg");

        Product p2 = new Product("Chocolate Milka", "alimentación", 0.99F, 3,
                "Puede contener Gluten y trazas de frutos secos.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201808/10/00120646700896____22__600x600.jpg");

        Product p3 = new Product("BUSTAMANTE Muy Mío", "higiene", 6.95F, 111,
                "Un seductor natural que despierta pasiones. Confianza en sí mismo, entrega, emperamento, carisma, naturalidad, espíritu de lucha? concentrados en una fragancia.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201909/23/00155760200737____1__600x600.jpg");

        Product p4 = new Product("DODOT Activity toallitas infantiles", "higiene", 8.63F, 56,
                "Máxima comodidad y protección en la piel, su capa absorvente cora-soft es suave como una pluma.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201905/10/00155763100421____15__600x600.jpg");

        Product p5 = new Product("ASTURIANA leche semidesnatada", "alimentación", 0.89F, 23,
                "Leche UHT semidesnatada sin lactosa",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201902/07/00120912000260____23__600x600.jpg");

        Product p6 = new Product("Vino blanco", "alimentación", 13.90F, 45,
                "Color amarillo verdoso pálido con destellos alimonados, brillante, limpio y cristalino. Aromas de intensidad media con claras notas minerales.",
                "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201909/24/00118775700711____2__600x600.jpg");


        userRepository.save(u1);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);
        productRepository.save(p6);

        Review r1 = new Review(p3, u1, Date.from(Instant.now()), "Increíble perfume con notas dulces.");

        reviewRepository.save(r1);

        List<Product> query = productRepository.findAll();
        for (Product p : query) {
            System.out.println("PRODUCTO= " + p.getName());
        }

        List<Review> query2 = reviewRepository.findAll();
        for (Review r : query2) {
            System.out.println("REVIEW= " + "producto: " + r.getProduct().getName() + " | " + r.getText());
        }
    }
}
