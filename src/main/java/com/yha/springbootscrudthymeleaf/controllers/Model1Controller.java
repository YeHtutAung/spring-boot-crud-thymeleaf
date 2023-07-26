package com.yha.springbootscrudthymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.yha.springbootscrudthymeleaf.models.Model1;
import com.yha.springbootscrudthymeleaf.services.Model1Service;
import jakarta.validation.Valid;

@Controller
public class Model1Controller {

	@Autowired
	Model1Service model1Service;

	
	@GetMapping("/signup")
    public String showAddModel1Form(Model1 model1) {
        return "add-model1";
    }
	
	@PostMapping("/addmodel1")
	public String addModel1(@Valid Model1 model1, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-model1";
		}

		model1Service.saveModel1(model1);
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String showModel1List(Model model) {
		model.addAttribute("model1s", model1Service.getAll());
		return "index";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Model1 model1 = model1Service.getModel1ById(id);

		model.addAttribute("model1", model1);
		return "update-model1";
	}

	@PostMapping("/update/{id}")
	public String updateModel1(@PathVariable("id") long id, @Valid Model1 model1, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model1.setId(id);
			return "update-model1";
		}

		model1Service.saveModel1(model1);
		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Model1 model1 = model1Service.getModel1ById(id);
		model1Service.deleteModel1(model1);
		return "redirect:/index";
	}
}
