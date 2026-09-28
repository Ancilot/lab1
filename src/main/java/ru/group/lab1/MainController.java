package ru.group.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.group.lab1.repository.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller ("main")
public class MainController {

    private final CarRepository carRepository;

    public MainController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Главная страница со списком владельцев
    @GetMapping("/")
    public String mainPage(Model model) {

        List<CarOwner> carOwners = new ArrayList<>();
        carRepository.findAll().forEach(carOwners::add);

        model.addAttribute("carOwners", carOwners);

        return "main";
    }

    // Страница добавления нового владельца
    @GetMapping("/add")
    public String addPage(Model model) {

        model.addAttribute("carOwner", new CarOwner());

        return "edit";
    }

    // Добавление нового владельца
    @PostMapping("/add")
    public String addCarOwner(@ModelAttribute CarOwner carOwner) {

        carRepository.save(carOwner);

        return "redirect:/";
    }

    // Получение владельца по id
    @GetMapping("/details/{id}")
    public String detailsPage(
            Model model,
            @PathVariable("id") Long id) {

        Optional<CarOwner> optionalCarOwner =
                carRepository.findById(id);

        if (optionalCarOwner.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute(
                "selectedCarOwner",
                optionalCarOwner.get()
        );

        return "details";
    }

    // Страница редактирования
    @GetMapping("/update/{id}")
    public String editPage(
            Model model,
            @PathVariable("id") Long id) {

        Optional<CarOwner> optionalCarOwner =
                carRepository.findById(id);

        if (optionalCarOwner.isEmpty()) {
            return "redirect:/";
        }

        model.addAttribute(
                "carOwner",
                optionalCarOwner.get()
        );

        return "edit";
    }

    // Сохранение изменений
    @PostMapping("/update")
    public String editCarOwner(
            @ModelAttribute CarOwner carOwner) {

        if (!carRepository.existsById(carOwner.getId())) {
            return "redirect:/";
        }

        carRepository.save(carOwner);

        return "redirect:/";
    }

    // Удаление
    @GetMapping("/delete/{id}")
    public String deleteCarOwner(
            @PathVariable("id") Long id) {

        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        }

        return "redirect:/";
    }
}

