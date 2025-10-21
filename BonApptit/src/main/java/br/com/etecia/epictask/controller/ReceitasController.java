package br.com.etecia.epictask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ReceitasController {

    private final List<Receita> receitas = List.of(
        new Receita("Panqueca de Banana", "20 minutos", "Café da manhã",
            List.of("2 bananas maduras", "2 ovos", "1/2 xícara de farinha de aveia", "1 colher de chá de canela"),
            "Amasse as bananas, misture com os ovos, farinha e canela. Cozinhe em frigideira até dourar dos dois lados.",
            "https://images.unsplash.com/photo-1581428283071-62f7f4902d87?auto=format&fit=crop&w=687&q=80"
        ),
        new Receita("Bolo de Cenoura", "50 minutos", "Sobremesa",
            List.of("3 cenouras médias", "4 ovos", "1 xícara de óleo", "2 xícaras de açúcar", "2 e 1/2 xícaras de farinha de trigo", "1 colher de sopa de fermento"),
            "Bata cenoura, ovos e óleo. Misture açúcar, farinha e fermento. Asse em forno médio por 40 minutos.",
            "https://images.unsplash.com/photo-1601050691034-c2d94f4b8c06?auto=format&fit=crop&w=800&q=80"
        ),
        new Receita("Salada Mediterrânea", "15 minutos", "Almoço",
            List.of("Tomate-cereja", "Pepino", "Azeitonas pretas", "Queijo feta", "Azeite de oliva", "Orégano"),
            "Corte os vegetais, misture com azeite e orégano, finalize com queijo feta esfarelado.",
            "https://plus.unsplash.com/premium_photo-1690561082420-fad21ede2431?auto=format&fit=crop&w=687&q=80"
        ),
        new Receita("Frango ao Curry", "35 minutos", "Jantar",
            List.of("500g de peito de frango", "1 cebola", "2 dentes de alho", "1 colher de sopa de curry em pó", "Leite de coco", "Azeite", "Sal e pimenta"),
            "Refogue cebola e alho, acrescente o frango e o curry. Cozinhe até dourar, adicione leite de coco e deixe apurar.",
            "https://images.unsplash.com/photo-1623845359245-f8c3b13b2911?auto=format&fit=crop&w=800&q=80"
        )
    );

    @GetMapping("/receitas")
    public String receitas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String categoria,
            Model model
    ) {
        List<Receita> filtradas = receitas;

        if (nome != null && !nome.isEmpty()) {
            filtradas = filtradas.stream()
                    .filter(r -> r.getNome().toLowerCase().contains(nome.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (categoria != null && !categoria.isEmpty()) {
            filtradas = filtradas.stream()
                    .filter(r -> r.getCategoria().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        }

        model.addAttribute("titulo", "Receitas - Bon'Apptit");
        model.addAttribute("receitas", filtradas);
        model.addAttribute("categorias", List.of("Café da manhã", "Almoço", "Jantar", "Sobremesa"));

        return "receitas";
    }

    public static class Receita {
        private String nome;
        private String tempoPreparo;
        private String categoria;
        private List<String> ingredientes;
        private String modoPreparo;
        private String imagemUrl;

        public Receita(String nome, String tempoPreparo, String categoria, List<String> ingredientes, String modoPreparo, String imagemUrl) {
            this.nome = nome;
            this.tempoPreparo = tempoPreparo;
            this.categoria = categoria;
            this.ingredientes = ingredientes;
            this.modoPreparo = modoPreparo;
            this.imagemUrl = imagemUrl;
        }

        public String getNome() { return nome; }
        public String getTempoPreparo() { return tempoPreparo; }
        public String getCategoria() { return categoria; }
        public List<String> getIngredientes() { return ingredientes; }
        public String getModoPreparo() { return modoPreparo; }
        public String getImagemUrl() { return imagemUrl; }
    }
}
