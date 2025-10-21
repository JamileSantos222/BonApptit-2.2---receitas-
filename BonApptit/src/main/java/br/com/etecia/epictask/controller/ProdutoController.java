package br.com.etecia.epictask.controller;

import br.com.etecia.epictask.model.Produto;
import br.com.etecia.epictask.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProdutoController {

    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        List<Produto> todos = repository.findAll();
        model.addAttribute("produtos", todos);
        model.addAttribute("titulo", "Todos os Alimentos");
        return "produtos";
    }

    @GetMapping("/produtos/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastro";
    }

    @PostMapping("/produtos")
    public String salvarProduto(Produto produto) {
        repository.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/produtos/disponiveis")
    public String produtosDisponiveis(Model model) {
        List<Produto> disponiveis = repository.findByQuantidadeGreaterThan(0);
        model.addAttribute("produtos", disponiveis);
        model.addAttribute("titulo", "Alimentos Disponíveis");
        return "produtos";
    }

    @GetMapping("/produtos/falta")
    public String produtosEmFalta(Model model) {
        List<Produto> falta = repository.findByQuantidadeEquals(0);
        model.addAttribute("produtos", falta);
        model.addAttribute("titulo", "Alimentos em Falta");
        return "produtos";
    }

    @GetMapping("/produtos/vencendo")
    public String produtosVencendo(Model model) {
        LocalDate hojeMais3Dias = LocalDate.now().plusDays(3);
        List<Produto> vencendo = repository.findByValidadeBefore(hojeMais3Dias);
        model.addAttribute("produtos", vencendo);
        model.addAttribute("titulo", "Produtos Próximos do Vencimento");
        return "produtos";
    }
}
