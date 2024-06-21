package com.beauty.Beautykiosk.Goods;

import java.security.Principal;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RequestMapping("/goods")
@RequiredArgsConstructor
@Controller
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page",
            defaultValue = "0") int page, @RequestParam(value = "kw",
            defaultValue = "") String kw) {
        log.info("page:{}, kw:{}", page, kw);
        Page<Goods> paging = this.goodsService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "goods_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Goods goods = this.goodsService.getGoods(id);
        model.addAttribute("goods", goods);
        return "goods_detail";
    }

    @GetMapping("/create")
    public String goodsCreate(GoodsForm goodsForm) {
        return "goods_form";
    }

    @PostMapping("/create")
    public String goodsCreate(@Valid GoodsForm goodsForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "goods_form";
        }
        this.goodsService.create(goodsForm.getId(), goodsForm.getName(), goodsForm.getEffect(), goodsForm.getImage(), goodsForm.getNumber(), goodsForm.getAge());
        return "redirect:/goods/list"; // 질문 저장후 질문목록으로 이동
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String goodsModify(GoodsForm goodsForm, @PathVariable("id") Integer id, Principal principal) {
        Goods goods = this.goodsService.getGoods(id);
        goodsForm.setName(goods.getName());
        goodsForm.setEffect(goods.getEffect());
        goodsForm.setImage(goods.getImage());
        goodsForm.setNumber(goods.getNumber());
        return "goods_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String goodsModify(@Valid GoodsForm goodsForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "goods_form";
        }
        Goods goods = this.goodsService.getGoods(id);
        this.goodsService.modify(goods, goodsForm.getId(), goodsForm.getName(), goodsForm.getEffect(), goodsForm.getImage(), goodsForm.getNumber(), goodsForm.getAge());
        return String.format("redirect:/goods/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Goods goods = this.goodsService.getGoods(id);
        this.goodsService.delete(goods);
        return "redirect:/goods/list";
    }

}
