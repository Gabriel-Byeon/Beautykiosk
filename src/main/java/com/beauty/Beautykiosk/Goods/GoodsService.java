package com.beauty.Beautykiosk.Goods;

import com.beauty.Beautykiosk.User.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    private Specification<Goods> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Goods> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                return cb.or(cb.like(q.get("name"), "%" + kw + "%"), // 제목
                        cb.like(q.get("effect"), "%" + kw + "%"));   // 내용
            }
        };
    }
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

    public void create(Integer id, String name, String effect, String image, Integer number, String age) {
        Goods g = new Goods();
        g.setId(id);
        g.setName(name);
        g.setEffect(effect);
        g.setImage(image);
        g.setNumber(number);
        g.setAge(age);
        this.goodsRepository.save(g);
    }

    public Page<Goods> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        //sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Goods> spec = search(kw);
        return this.goodsRepository.findAll(spec, pageable);
    }

    public void modify(Goods goods, Integer id, String name, String effect, String image, Integer number, String age) {
        goods.setId(id);
        goods.setName(name);
        goods.setEffect(effect);
        goods.setImage(image);
        goods.setNumber(number);
        goods.setAge(age);
        this.goodsRepository.save(goods);
    }

    public void delete(Goods goods) {
        this.goodsRepository.delete(goods);
    }
}
