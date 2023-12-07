package com.example.uzum_market.utils;

import com.example.uzum_market.dto.MainCriteriaDTO;

public class MakeQuery2 {
    public static String makeQueryForProduct(MainCriteriaDTO mainCriteriaDTO, Integer id, String user) {
        StringBuilder sb = new StringBuilder(250);
        sb.append("SELECT DISTINCT p.id FROM product p ");
        sb.append("INNER JOIN price pr ON p.id = pr.product_id ");
        boolean hasColor = mainCriteriaDTO.getColor() != null;
        if (hasColor) {
            sb.append("INNER JOIN color c ON pr.color_id = c.id ");
        }
        boolean hasSpecification = mainCriteriaDTO.getSpecification() != null;
        if (hasSpecification) {
            sb.append("INNER JOIN specification s ON pr.specification_id = s.id ");
        }
        sb.append("WHERE ");
        sb.append("p.is_active");
        if (mainCriteriaDTO.getBrand() != null)
            sb.append(" AND p.brand = '").append(mainCriteriaDTO.getBrand()).append("'");

        if (mainCriteriaDTO.getSearch() != null)
            sb.append(" AND p.name_uz ~* '").append(mainCriteriaDTO.getSearch()).append("'");
        sb.append(" AND (pr.price > ").append(mainCriteriaDTO.getMinPrice())
                .append(" AND pr.price < ").append(mainCriteriaDTO.getMaxPrice()).append(" )");

        if (hasColor) {
            sb.append(" AND c.name = '").append(mainCriteriaDTO.getColor()).append("'");
        }
        if (hasSpecification) {
            sb.append(" AND s.title = '").append(mainCriteriaDTO.getSpecification()).append("'");
        }
        if (user.equals("user")) {
            sb.append(" AND p.category_id = ").append(id);
        } else if (user.equals("admin")) {
            sb.append(" AND p.seller_id = ").append(id);
        }
        sb.append(" offset ").append(mainCriteriaDTO.getPage() * mainCriteriaDTO.getSize());
        sb.append(" limit ").append(mainCriteriaDTO.getSize());
        return sb.toString();
    }

}
