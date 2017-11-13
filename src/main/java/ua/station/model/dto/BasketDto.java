package ua.station.model.dto;

import ua.station.model.entity.Basket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sa on 13.11.17.
 */
public class BasketDto {

    private List<BasketItemDto> basketItemDtoList=new ArrayList<>();
    private BigDecimal summ = new BigDecimal(0);

    public BasketDto(Basket basket) {
        basket.getBasketItems().forEach(item -> {
            basketItemDtoList.add(new BasketItemDto(item));
            summ=summ.add(item.getPrice().getPrice().multiply(new BigDecimal(item.getCount())));
            }
        );
    }

    public List<BasketItemDto> getBasketItemDtoList() {
        return basketItemDtoList;
    }

    public void setBasketItemDtoList(List<BasketItemDto> basketItemDtoList) {
        this.basketItemDtoList = basketItemDtoList;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }
}
