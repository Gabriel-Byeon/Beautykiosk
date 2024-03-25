package com.beauty.Beautykiosk.Goods;

import com.beauty.Beautykiosk.Goods.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
    Goods findByName(String name);
    Goods findByNameAndEffect(String name, String effect);
    List<Goods> findByNameLike(String Name);
    Page<Goods> findAll(Pageable pageable);
}
