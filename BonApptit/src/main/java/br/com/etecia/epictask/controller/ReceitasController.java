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
            "https://images.unsplash.com/photo-1641640267000-320d420711c9?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1025"
        ),
        new Receita("Salada Mediterrânea", "15 minutos", "Almoço",
            List.of("Tomate-cereja", "Pepino", "Azeitonas pretas", "Queijo feta", "Azeite de oliva", "Orégano"),
            "Corte os vegetais, misture com azeite e orégano, finalize com queijo feta esfarelado.",
            "https://plus.unsplash.com/premium_photo-1690561082420-fad21ede2431?auto=format&fit=crop&w=687&q=80"
        ),
        new Receita("Frango ao Curry", "35 minutos", "Jantar",
            List.of("500g de peito de frango", "1 cebola", "2 dentes de alho", "1 colher de sopa de curry em pó", "Leite de coco", "Azeite", "Sal e pimenta"),
            "Refogue cebola e alho, acrescente o frango e o curry. Cozinhe até dourar, adicione leite de coco e deixe apurar.",
            "https://plus.unsplash.com/premium_photo-1661419883163-bb4df1c10109?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=687"
        ),
        new Receita("Tapioca com Queijo e Tomate", "10 minutos", "Café da manhã",
            List.of("3 colheres de goma de tapioca", "2 fatias de queijo", "3 rodelas de tomate", "Orégano a gosto"),
            "Aqueça a frigideira, espalhe a goma, recheie com queijo e tomate, dobre e sirva.",
            "https://images.unsplash.com/photo-1623062411350-8bbf6f4b0f65?auto=format&fit=crop&w=687&q=80"
        ),
        new Receita("Lasanha de Berinjela", "45 minutos", "Almoço",
            List.of("2 berinjelas fatiadas", "Molho de tomate", "Queijo mussarela", "Presunto", "Orégano"),
            "Monte camadas alternadas de berinjela, molho, queijo e presunto. Leve ao forno até gratinar.",
            "https://plus.unsplash.com/premium_photo-1723770033472-0b0452d98225?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1097"
        ),
        new Receita("Torta de Limão", "60 minutos", "Sobremesa",
            List.of("1 pacote de bolacha maisena", "100g de manteiga", "1 lata de leite condensado", "1/2 xícara de suco de limão", "Raspas de limão"),
            "Faça a base com bolacha e manteiga. Misture leite condensado com suco de limão e coloque por cima. Leve à geladeira.",
            "https://images.unsplash.com/photo-1673883705924-8d0e7ae9bfb6?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=687"
        ),
        new Receita("Risoto de Cogumelos", "40 minutos", "Jantar",
            List.of("1 xícara de arroz arbório", "200g de cogumelos", "1/2 cebola", "1 taça de vinho branco", "Caldo de legumes", "Queijo parmesão"),
            "Refogue a cebola e os cogumelos, adicione o arroz e o vinho. Cozinhe com caldo até o ponto e finalize com parmesão.",
            "https://plus.unsplash.com/premium_photo-1694850980288-b14bd7f9c458?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=681"
        ),
        new Receita("Suco Verde Detox", "5 minutos", "Bebidas",
            List.of("1 folha de couve", "1 maçã", "1 limão espremido", "200ml de água gelada", "Gelo a gosto"),
            "Bata todos os ingredientes no liquidificador e sirva gelado.",
            "https://plus.unsplash.com/premium_photo-1675011400509-5e39c4c17309?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=706"
        ),
        new Receita("Wrap de Frango com Creme de Ricota", "20 minutos", "Lanche",
            List.of("1 pão folha", "100g de frango desfiado", "2 colheres de creme de ricota", "Alface", "Tomate"),
            "Espalhe o creme no pão, adicione o frango e os vegetais, enrole e sirva.",
            "https://images.unsplash.com/photo-1625945275277-9b8b5b70e2f3?auto=format&fit=crop&w=687&q=80"
        ),
        new Receita("Brownie de Chocolate", "40 minutos", "Sobremesa",
            List.of("200g de chocolate meio amargo", "2 ovos", "1 xícara de açúcar", "1/2 xícara de farinha", "100g de manteiga"),
            "Derreta o chocolate e a manteiga, misture os demais ingredientes, asse por 30 minutos.",
            "https://images.unsplash.com/photo-1594223801958-db8561d7009a?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=687"
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
