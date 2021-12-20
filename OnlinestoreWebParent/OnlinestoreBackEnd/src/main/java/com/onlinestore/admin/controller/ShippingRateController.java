package com.onlinestore.admin.controller;


import com.onlinestore.admin.paging.PagingAndSortingHelper;
import com.onlinestore.admin.paging.PagingAndSortingParam;
import com.onlinestore.admin.shippingrate.ShippingRateAlreadyExistsException;
import com.onlinestore.admin.shippingrate.ShippingRateNotFoundException;
import com.onlinestore.admin.shippingrate.ShippingRateService;
import com.onlinestore.common.entity.Country;
import com.onlinestore.common.entity.ShippingRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ShippingRateController {
    private String defaultRedirectURL = "redirect:/shipping_rates/page/1?sortField=country&sortDir=asc";

    @Autowired
    private ShippingRateService shippingRateService;

    @GetMapping("/shipping_rates")
    public String listFirstPage(){
        return defaultRedirectURL;
    }

    @GetMapping("/shipping_rates/page/{pageNum}")
    public String listByPage(@PagingAndSortingParam(listName = "shippingRates",
                             moduleURL = "/shipping_rates") PagingAndSortingHelper helper,
                             @PathVariable(name = "pageNum") int pageNum){
        shippingRateService.listByPage(pageNum, helper);
        return "shipping_rates/shipping_rates";
    }

    @GetMapping("/shipping_rates/new")
    public String newRate(Model model) {
        List<Country> listCountries = shippingRateService.listAllCountries();

        model.addAttribute("rate", new ShippingRate());
        model.addAttribute("listCountries", listCountries);
        model.addAttribute("pageTitle", "New Rate");

        return "shipping_rates/shipping_rate_form";
    }

    @PostMapping("/shipping_rates/save")
    public String saveRate(ShippingRate rate, RedirectAttributes ra) {
        try {
            shippingRateService.save(rate);
            ra.addFlashAttribute("message", "The shipping rate has been saved successfully.");
        } catch (ShippingRateAlreadyExistsException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
        }
        return defaultRedirectURL;
    }

    @GetMapping("/shipping_rates/edit/{id}")
    public String editRate(@PathVariable(name = "id") Integer id,
                           Model model, RedirectAttributes ra) {
        try {
            ShippingRate rate = shippingRateService.get(id);
            List<Country> listCountries = shippingRateService.listAllCountries();

            model.addAttribute("listCountries", listCountries);
            model.addAttribute("rate", rate);
            model.addAttribute("pageTitle", "Edit Rate (ID: " + id + ")");

            return "shipping_rates/shipping_rate_form";
        } catch (ShippingRateNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
            return defaultRedirectURL;
        }
    }

    @GetMapping("/shipping_rates/cod/{id}/enabled/{supported}")
    public String updateCODSupport(@PathVariable(name = "id") Integer id,
                                   @PathVariable(name = "supported") Boolean supported,
                                   Model model, RedirectAttributes ra) {
        try {
            shippingRateService.updateCODSupport(id, supported);
            ra.addFlashAttribute("message", "COD support for shipping rate ID " + id + " has been updated.");
        } catch (ShippingRateNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
        }
        return defaultRedirectURL;
    }

    @GetMapping("/shipping_rates/delete/{id}")
    public String deleteRate(@PathVariable(name = "id") Integer id,
                             Model model, RedirectAttributes ra) {
        try {
            shippingRateService.delete(id);
            ra.addFlashAttribute("message", "The shipping rate ID " + id + " has been deleted.");
        } catch (ShippingRateNotFoundException ex) {
            ra.addFlashAttribute("message", ex.getMessage());
        }
        return defaultRedirectURL;
    }
}
