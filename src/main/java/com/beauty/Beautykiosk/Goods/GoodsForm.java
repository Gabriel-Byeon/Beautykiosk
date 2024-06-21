package com.beauty.Beautykiosk.Goods;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsForm {
    @NotEmpty(message = "품목명은 필수항목입니다")
    private String name;

    @NotEmpty(message = "효과는 필수항목입니다")
    private String effect;

    private String image;

    @NotNull(message = "가격 필수입니다")
    private Integer number;

    private String age;
}
