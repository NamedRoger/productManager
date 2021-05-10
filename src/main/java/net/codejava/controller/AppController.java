package net.codejava.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.codejava.services.ProductService;
import net.codejava.Usuario;
import net.codejava.entity.Calculo;
import net.codejava.entity.Product;
import net.codejava.entity.Triangulo;
import net.codejava.services.TrianguloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    private ProductService service;
    
    @Autowired
    private TrianguloService trianguloService;

    @RequestMapping("/")
    public String viewHomePage(HttpSession session, Model model) {

        if (session.getAttribute("mySessionAttribute") != null) {
            List<Product> listProducts = service.listAll();
            model.addAttribute("listProducts", listProducts);
            return "index";
        } else {
            model.addAttribute("usuario", new Usuario());
            return "login";
        }
        //model.addAttribute("listProducts", listProducts);
    }
    
    @RequestMapping("/suma")
    public String viewCalculoPage( Model model) {
        Calculo calculo = new Calculo();
        model.addAttribute("calculo",calculo);
        return "numeros";
        //model.addAttribute("listProducts", listProducts);
    }
    
    @RequestMapping("/resultado")
    public String viewResultadoPage(Model model,@RequestParam double number1,@RequestParam double number2) {
        Calculo calculo = new Calculo();
        calculo.setNumber1(number1);
        calculo.setNumber2(number2);
        
        model.addAttribute("resultado",calculo.getSuma());
        return "resultado";
        //model.addAttribute("listProducts", listProducts);
    }

    @RequestMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("mySessionAttribute", "sasas");

        // model.addAttribute("listProducts", listProducts);
        return "redirect:/";
    }
    
    @RequestMapping("/triangulos")
    public String viewTriangulos(Model model){
        List<Triangulo> triangulos = trianguloService.listAll();
        model.addAttribute("triangulos",triangulos);
        return "/triangulos";
    }
    
    @RequestMapping("/triangulos/add")
    public String viewTriangulosAdd(Model model){
        Triangulo triangulo = new Triangulo();
        model.addAttribute("triangulo",triangulo);
        return "/add_triangulo";
    }

    
    @RequestMapping(value = "/triangulos/add", method = RequestMethod.POST)
    public String newTriangulo(@ModelAttribute("triangulo") Triangulo triangulo) {
        double area = (triangulo.getAltura()*triangulo.getBase()) / 2;
        triangulo.setArea(area);
        trianguloService.save(triangulo);
        return "redirect:/triangulos";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product", product);

        return mav;
    }
    

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}
