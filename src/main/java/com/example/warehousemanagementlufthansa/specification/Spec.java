package com.example.warehousemanagementlufthansa.specification;

import com.example.warehousemanagementlufthansa.model.InventoryHeader;
import com.example.warehousemanagementlufthansa.model.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class Spec {
    public static Specification<InventoryHeader> searchByStatus(OrderStatus status) {
        return (Specification<InventoryHeader>) (root, query, criteriaBuilder) -> {
            if(status == null){
                return null;
            }
            return criteriaBuilder.in(root.get("status")).value(status);
        };
    }
}
