package com.beauty.Beautykiosk.Goods;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/goods")
@RequiredArgsConstructor
@Controller
public class GoodsController {

    private final GoodsService goodsService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Goods> paging = this.goodsService.getList(page);
        model.addAttribute("paging", paging);
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
        this.goodsService.create(goodsForm.getName(), goodsForm.getEffect(), goodsForm.getImage(), goodsForm.getNumber());
        return "redirect:/goods/list"; // 질문 저장후 질문목록으로 이동
    }
}