package com.tmall_backend.bysj.service.behavior.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetActFromOrderCommodityBaseDTO {
    private Integer commodityId;
    private Integer number;
    private Double oldPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetActFromOrderCommodityBaseDTO that = (GetActFromOrderCommodityBaseDTO) o;
        return Objects.equals(commodityId, that.commodityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodityId);
    }
}
