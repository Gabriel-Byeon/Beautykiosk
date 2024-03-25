package com.beauty.Beautykiosk.Goods;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.beauty.Beautykiosk.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public List<Goods> getList() {
        return this.goodsRepository.findAll();
    }

    public Goods getGoods(Integer id) {
        Optional<Goods> goods = this.goodsRepository.findById(id);
        if (goods.isPresent()) {
            return goods.get();
        } else {
            throw new DataNotFoundException("goods not found");
        }
    }

    public void create(String name, String effect, String image, Integer number) {
        Goods g = new Goods();
        g.setName(name);
        g.setEffect(effect);
        g.setImage(image);
        g.setNumber(number);
        g.setCreateDate(LocalDateTime.now());
        this.goodsRepository.save(g);
    }

    public Page<Goods> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.goodsRepository.findAll(pageable);
    }

    public void modify(Goods goods, String name, String effect, String image, Integer number) {
        goods.setName(name);
        goods.setEffect(effect);
        goods.setImage(image);
        goods.setNumber(number);
        goods.setModifyDate(LocalDateTime.now());
        this.goodsRepository.save(goods);
    }

    public void delete(Goods goods) {
        this.goodsRepository.delete(goods);
    }
}
